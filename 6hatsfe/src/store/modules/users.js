
export default {
  state: {
    token: localStorage.getItem('access-token') ? localStorage.getItem('access-token') : ""
    ,
    id:null,
    user:{
      name:null,
      email:null,
      gender:null,
      job:null,
      birth:null
    }
  },
  getters: {
    token(state) {
      return state.token
    },
    user(state){
      return state.user
    },
    id(state){
      return state.id
    }
  },
  mutations: {
    ChangeToken(state){
      state.token=localStorage.getItem('access-token')
    },
    ChangeId(state,value){
      state.id=value
    }
  },
  actions: {
  },
  modules: {
  }
}
