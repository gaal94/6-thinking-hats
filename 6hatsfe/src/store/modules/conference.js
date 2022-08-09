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
    publisher: undefined,
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
    myHat: state => state.myHat,
    publisher: state => state.publisher,
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
      const idx = state.users.findIndex(userInfo => userInfo.connectionId === user.connectionId)
      state.users[idx].hatColor = changedHat
    },
    setMyHat(state, hatColor) {
      state.myHat = hatColor
    },
    setUsers(state) {
      state.users = []
    },
    setPublisher(state, value) {
      state.publisher = value
    },
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
    },
    setMyHat({commit}, hatColor) {
      commit('setMyHat', hatColor)
    },
    setUsers({commit}) {
      commit('setUsers')
    },
    setPublisher({commit}, value) {
      commit('setPublisher', value)
    },
  },
  modules: {
  }
}
