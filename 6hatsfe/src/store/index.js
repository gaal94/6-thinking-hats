import { createStore } from 'vuex'
import usersa from '@/store/modules/usersa'
import conference from '@/store/modules/conference'
import boards from "@/store/modules/boards"

export default createStore({
  // strict: true,
  modules: {
    usersa, conference, boards,
  }
})
