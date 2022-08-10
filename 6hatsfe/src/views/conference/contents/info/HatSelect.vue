<template>
  <div class="hat-select-box">
    <img 
    @click="chooseHat('red-hat')" 
    v-if="hat === 'red-hat'" src="@/assets/redhat.png" alt="" class="hat-img">
    <img 
    @click="chooseHat('yellow-hat')" 
    v-else-if="hat === 'yellow-hat'" src="@/assets/yellowhat.png" alt="" class="hat-img">
    <img 
    @click="chooseHat('green-hat')" 
    v-else-if="hat === 'green-hat'" src="@/assets/greenhat.png" alt="" class="hat-img">
    <img 
    @click="chooseHat('blue-hat')" 
    v-else-if="hat === 'blue-hat'" src="@/assets/bluehat.png" alt="" class="hat-img">
    <img 
    @click="chooseHat('black-hat')" 
    v-else-if="hat === 'black-hat'" src="@/assets/blackhat.png" alt="" class="hat-img">
    <img 
    @click="chooseHat('white-hat')" 
    v-else-if="hat === 'white-hat'" src="@/assets/whitehat.png" alt="" class="hat-img">
    <img 
    @click="chooseHat('random-hat')" 
    v-else-if="hat === 'random-hat'" src="@/assets/randomhat.png" alt="" class="hat-img">
    <img 
    @click="chooseHat('spectator')" 
    v-else-if="hat === 'spectator'" src="@/assets/spectator.png" alt="" class="hat-img">
    
    <!-- 6hats 모드 -->
    <div v-if="hatMode === 'sixhats'" class="user-list" :class="hat">
      <div v-for="(user, idx) in users" :key="`user-${idx}`">
        <user-list-item v-if="user.hatColor === hat" :userInfo=user></user-list-item>
      </div>
    </div>

    <!-- 1hat 모드 -->
    <div v-else-if="hatMode === 'onehat'" class="user-list" :class="hat">
      <div v-for="(user, idx) in users" :key="`user-${idx}`">
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
  width: 52px;
  height: 52px;
}

.hat-img:hover {
  cursor: pointer;
}

.user-list {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 200px;
  min-height: 40px;
  border-radius: 14px;
  padding: 12px;
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