import { createStore } from 'vuex'

export default createStore({
  state: {
    token: localStorage.getItem('access-token') ? localStorage.getItem('access-token') : ""
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
