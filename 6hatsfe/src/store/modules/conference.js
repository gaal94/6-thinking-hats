export default {
  state: {
    ideaMode: 'ideaSuggest',
    hatMode: 'sixhats',
    speechOrder: ['white-hat', 'green-hat', 'yellow-hat', 'black-hat', 'red-hat', 'blue-hat'],
    currentTurn: 0,
    baseTime: 60,
    totalTime: 60,
    timer: null,
    confSubject: '',
    session: undefined,
    myHat: 'spectator',
    users: [],
    publisher: undefined,
    myName: '',
    opinions: [],
    role: 'participant',
    hostConnectionId: undefined,
    conferenceStatus: false,
  },
  getters: {
    session: state => state.session,
    ideaMode: state => state.ideaMode,
    speechOrder: state => state.speechOrder,
    hatMode: state => state.hatMode,
    currentTurn: state => state.currentTurn,
    baseTime: state => state.baseTime,
    totalTime: state => state.totalTime,
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
    myName: state => state.myName,
    opinions: state => state.opinions,
    isHost: state => state.role === 'host',
    hostConnectionId: state => state.hostConnectionId,
    isConferencing: state => state.conferenceStatus,
    conferenceStatus: state => state.conferenceStatus,
  },
  mutations: {
    setSession(state, session) {
      state.session = session
    },
    setIdeaMode(state, whichMode) {
      state.ideaMode = whichMode
      if (whichMode === 'ideaSuggest') {
        state.speechOrder = ['white-hat', 'green-hat', 'yellow-hat', 'black-hat', 'red-hat', 'blue-hat']
      } else {
        state.speechOrder = ['white-hat', 'red-hat', 'yellow-hat', 'black-hat', 'green-hat', 'blue-hat']
      }
    },
    setHatMode(state, whichMode) {
      state.hatMode = whichMode
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
    removeUser(state, userIdx) {
      state.users.splice(userIdx, 1)
    },
    changeUserHatColor(state, { user, changedHat }) {
      const idx = state.users.findIndex(userInfo => userInfo.connectionId === user.connectionId)
      state.users[idx].hatColor = changedHat
    },
    setMyHat(state, hatColor) {
      state.myHat = hatColor
    },
    clearUsers(state) {
      state.users = []
    },
    setPublisher(state, value) {
      state.publisher = value
    },
    setMyName(state, name) {
      state.myName = name
    },
    addOpinion(state, opInfo) {
      state.opinions.push(opInfo)
    },
    removeOpinion(state, opIdx) {
      state.opinions.splice(opIdx, 1)
    },
    setRole(state, role) {
      state.role = role
    },
    initialSetting(state, {users, ideaMode, hatMode, speechOrder, currentTurn, baseTime, 
                  totalTime, confSubject, opinions, hostConnectionId, conferenceStatus}) {
      state.users = users
      state.ideaMode = ideaMode
      state.hatMode = hatMode
      state.speechOrder = speechOrder
      state.currentTurn = currentTurn
      state.baseTime = baseTime
      state.totalTime = totalTime
      state.confSubject = confSubject
      state.opinions = opinions
      state.hostConnectionId = hostConnectionId
      state.conferenceStatus = conferenceStatus
    },
    setHostConnectionId(state, conId) {
      state.hostConnectionId = conId
    },
    turnOffAudio(state) {
      state.publisher.publishAudio(false)
    },
    turnOnAudio(state) {
      state.publisher.publishAudio(true)
    },
    turnOffVideo(state) {
      state.publisher.publishVideo(false)
    },
    turnOnVideo(state) {
      state.publisher.publishVideo(true)
    },
    startConference(state) {
      state.conferenceStatus = true
    },
    endConference(state) {
      state.conferenceStatus = false
    },
  },
  actions: {
    setSession({commit}, session) {
      commit('setSession', session)
    },
    changeIdeaMode({commit}, whichMode) {
      commit('setIdeaMode', whichMode)
    },
    changeHatMode({commit}, whichMode) {
      commit('setHatMode', whichMode)
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
    removeUser({commit}, userIdx) {
      commit('removeUser', userIdx)
    },
    changeUserHatColor({commit}, data) {
      commit('changeUserHatColor', data)
    },
    setMyHat({commit}, hatColor) {
      commit('setMyHat', hatColor)
    },
    clearUsers({commit}) {
      commit('clearUsers')
    },
    setPublisher({commit}, value) {
      commit('setPublisher', value)
    },
    setMyName({commit}, name) {
      commit('setMyName', name)
    },
    addOpinion({commit}, opInfo) {
      commit('addOpinion', opInfo)
    },
    removeOpinion({commit}, opIdx) {
      commit('removeOpinion', opIdx)
    },
    setRole({commit}, role) {
      commit('setRole', role)
    },
    initialSetting({commit}, payload) {
      commit('initialSetting', payload)
      if (payload.conferenceStatus === true) {
        commit('startTimer')
      }
    },
    setHostConnectionId({commit}, conId) {
      commit('setHostConnectionId', conId)
    },
    turnOffAudio({commit}) {
      commit('turnOffAudio')
    },
    turnOnAudio({commit}) {
      commit('turnOnAudio')
    },
    turnOffVideo({commit}) {
      commit('turnOffVideo')
    },
    turnOnVideo({commit}) {
      commit('turnOnVideo')
    },
    startConference({commit}) {
      commit('startConference')
    },
    endConference({commit}) {
      commit('endConference')
    },
  },
}
