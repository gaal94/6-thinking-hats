export default {
  state: {
    ideaMode: ['white-hat', 'green-hat', 'yellow-hat', 'black-hat', 'red-hat', 'blue-hat'],
    currentTurn: 0,
    baseTime: 60
  },
  getters: {
    ideaMode: state => state.ideaMode,
    currentTurn: state => state.currentTurn,
    baseTime: state => state.baseTime,
  },
  mutations: {
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
    endConf(state) {
      state.currentTurn = 0
    },
    setTime(state, minute) {
      state.baseTime = minute * 60
      console.log(state.baseTime);
    }
  },
  actions: {
    changeIdeaMode({commit}, whichMode) {
      commit('changeIdeaMode', whichMode)
    },
    passTurn({commit}) {
      commit('passTurn')
    },
    backToPreTurn({commit}) {
      commit('backToPreTurn')
    },
    endConf({commit}) {
      commit('endConf')
    },
    setTime({commit}, minute) {
      commit('setTime', minute)
    },
  },
  modules: {
  }
}
