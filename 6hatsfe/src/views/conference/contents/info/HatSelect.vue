<template>
  <div class="hat-select-box">
    <img 
    @click="chooseHat(hat)" 
    :src="require(`@/assets/${hat.replace('-', '')}.png`)" alt="" class="hat-img">
    
    <!-- 6hats 모드 -->
    <div v-if="hatMode === 'sixhats'" class="user-list-box" :class="hat">
      <div v-for="(user, idx) in users" :key="`user-${idx}`" class="user-list">
        <user-list-item v-if="user.hatColor === hat" :userInfo=user></user-list-item>
      </div>
    </div>

    <!-- 1hat 모드 -->
    <div v-else-if="hatMode === 'onehat'" class="user-list-box" :class="hat">
      <div v-for="(user, idx) in users" :key="`user-${idx}`" class="user-list">
        <user-list-item v-if="user.hatColor === hat || (hat === 'random-hat' && user.hatColor !== 'spectator' && user.hatColor !== 'blue-hat')" :userInfo=user></user-list-item>
      </div>
    </div>
  </div>
</template>

<script>
import UserListItem from '@/views/conference/contents/info/UserListItem.vue'
import { mapGetters } from 'vuex'

export default {
  name: 'HatSelect',
  components: {
    UserListItem,
  },
  props: {
    hat: String,
  },
  data: () => {
		return {
		}
	},
	computed: {
    ...mapGetters(['users', 'publisher', 'myHat', 'session', 'hatMode',])
	},
	methods: {
    chooseHat(targetHat) {
      let countHat = 0
      this.users.forEach(el => {
        if (el.hatColor === targetHat) {
          countHat += 1
        }
      });

      // 바꾸려는 모자에 3명까지만 넣음
      if ((targetHat !== 'random-hat' && targetHat !== 'spectator' && targetHat !== 'blue-hat' && countHat < 3) || 
      (targetHat === 'random-hat' || targetHat === 'spectator') || 
      (targetHat === 'blue-hat' && countHat === 0)) {
        const userInfo = { hatColor: this.myHat, connectionId: this.publisher.stream.session.connection.connectionId}
        const sending = {user: userInfo, changedHat: targetHat}
        const jsonData = JSON.stringify(sending)
        this.session.signal({
          data: jsonData,
          type: 'change-hat-color'
        })
      }
    },
	},
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.hat-select-box {
  display: flex;
  align-items: center;
  gap: 28px;
}

.hat-img {
  width: 3.3854vw;
  height: 3.3854vw;
  align-self: flex-start;
}

.hat-img:hover {
  cursor: pointer;
}

.user-list-box {
  width: 13.0208vw;
  min-height: 2.6042vw;
  border-radius: 0.9115vw;
  padding: 8px;
}

.user-list {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.red-hat {
  background-color: #FFDCD9;
}

.yellow-hat {
  background-color: #FFF1CA;
}

.green-hat {
  background-color: #CFF3D9;
}

.blue-hat {
  background-color: #CEE0FF;
}

.white-hat {
  background-color: #FFFFFF;
}

.black-hat {
  background-color: #8E8E8E;
}

.random-hat {
  background-color: #D2D2D2;
}

.spectator {
  background-color: #E9E9E9;
}

</style>