export default {
  state: {
    ideaMode: ['white-hat', 'green-hat', 'yellow-hat', 'black-hat', 'red-hat', 'blue-hat'],
    currentTurn: 0,
    baseTime: 60,
    totalTime: 60,
    timer: null,
    confSubject: '',
    session: undefined,
    myHat: 'spectator',
    users: [],
    participants: [],

  },
  getters: {
    session: state => state.session,
    ideaMode: state => state.ideaMode,
    currentTurn: state => state.currentTurn,
    baseTime: state => state.baseTime,
    minutes(state) {
      const min = parseInt(state.totalTime / 60)
      return min > 9 ? String(min) : '0' + String(min)
    },
    seconds(state) {
      const sec = String(state.totalTime % 60)
      return sec.length > 1 ? sec : '0' + sec
    },
    confSubject(state) {
      return state.confSubject
    },
    users: state => state.users,
    participants: state => state.participants,
  },
  mutations: {
    setSession(state, session) {
      state.session = session
    },
    changeIdeaMode(state, whichMode) {
      if (whichMode === 'ideaSuggest') {
        state.ideaMode = ['white-hat', 'green-hat', 'yellow-hat', 'black-hat', 'red-hat', 'blue-hat']
      } else {
        state.ideaMode = ['white-hat', 'red-hat', 'yellow-hat', 'black-hat', 'green-hat', 'blue-hat']
      }
    },
    passTurn(state) {
      state.currentTurn = (state.currentTurn + 1) % 6
    },
    backToPreTurn(state) {
      state.currentTurn = (state.currentTurn + 5) % 6
    },
    resetTurn(state) {
      state.currentTurn = 0
    },
    setTime(state, minutes) {
      state.baseTime = minutes * 60
      state.totalTime = minutes * 60
    },
    startTimer(state) {
      const countdown = function() {
        if(state.totalTime >= 1) {
          state.totalTime -= 1
        } else {
          state.currentTurn = (state.currentTurn + 1) % 6

          state.totalTime = state.baseTime;
          clearInterval(state.timer);
          state.timer = null;
        
          state.timer = setInterval(() => countdown(), 1000);
        }
      }

      state.timer = setInterval(() => countdown(), 1000);
    },
    stopTimer(state) {
      clearInterval(state.timer)
      state.timer = null
    },
    resetTimer(state) {
      clearInterval(state.timer);
      state.totalTime = state.baseTime;
      state.timer = null;
    },
    setConfSubject(state, changedSubject) {
      state.confSubject = changedSubject
    },
    addUser(state, user) {
      state.users.push(user)
    },
    changeUserHatColor(state, { user, changedHat }) {
      console.log('뮤테이션');
      console.log(user);
      console.log(state.users);
      const idx = state.users.indexOf(user)
      state.user[idx].hatColor = changedHat
    }
  },
  actions: {
    setSession({commit}, session) {
      commit('setSession', session)
    },
    changeIdeaMode({commit}, whichMode) {
      commit('changeIdeaMode', whichMode)
    },
    passTurn({commit}) {
      commit('passTurn')
      commit('resetTimer')
      commit('startTimer')
    },
    backToPreTurn({commit}) {
      commit('backToPreTurn')
      commit('resetTimer')
      commit('startTimer')
    },
    resetTurn({commit}) {
      commit('resetTurn')
    },
    setTime({commit}, minutes) {
      commit('setTime', minutes)
    },
    startTimer({commit}) {
      commit('startTimer')
    },
    stopTimer({commit}) {
      commit('stopTimer')
    },
    resetTimer({commit}) {
      commit('resetTimer')
    },
    setConfSubject({commit}, changedSubject) {
      commit('setConfSubject', changedSubject)
    },
    addUser({commit}, user) {
      commit('addUser', user)
    },
    changeUserHatColor({commit}, data) {
      commit('changeUserHatColor', data)
    }
  },
  modules: {
  }
}
