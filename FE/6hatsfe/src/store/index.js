import { createStore } from 'vuex'
import users from '@/store/modules/users'
import conference from '@/store/modules/conference'
import boards from "@/store/modules/boards"

export default createStore({
  // strict: true,
  modules: {
    users, conference, boards,
  }
})
