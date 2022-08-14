export default {
    state: {
        roomId:'',
    },
    getters: {
        roomId(state) {
            return state.roomId;
        }
    },
    mutations: {
        ChangeRoomid(state,value) {
            state.roomId = value;
        }
    },
    actions: {
    },
    modules: {
    }
  }
  