<template>
  <!-- 6hats 모드 -->
  <div v-if="hatMode === 'sixhats'" class="name-box"
  :class="{ 'red-hat-back' : userInfo.hatColor === 'red-hat',
            'yellow-hat-back' : userInfo.hatColor === 'yellow-hat',
            'green-hat-back' : userInfo.hatColor === 'green-hat',
            'blue-hat-back' : userInfo.hatColor === 'blue-hat',
            'black-hat-back' : userInfo.hatColor === 'black-hat',
            'white-hat-back' : userInfo.hatColor === 'white-hat',
            'random-hat-back' : userInfo.hatColor === 'random-hat',
            'spectator-back' : userInfo.hatColor === 'spectator', }">
    <span class="user-name">{{ userInfo.userName }}</span>
    <div class="cnt-status dropdown">
      <div class="cnt-hat"
            :class="userInfo.hatColor"
            data-bs-toggle="dropdown" aria-expanded="false">
        <i v-if="userInfo.hatColor === 'random-hat'" class='bx bx-question-mark'></i>
        <i v-else-if="userInfo.hatColor === 'spectator'" class='bx bx-show'></i>
      </div>
      <ul class="select dropdown-menu" role="menu">
        <li @click="changeHatColor('red-hat')" class="status red-hat"></li>
        <li @click="changeHatColor('yellow-hat')" class="status yellow-hat"></li>
        <li @click="changeHatColor('green-hat')" class="status green-hat"></li>
        <li @click="changeHatColor('blue-hat')" class="status blue-hat"></li>
        <li @click="changeHatColor('white-hat')" class="status white-hat"></li>
        <li @click="changeHatColor('black-hat')" class="status black-hat"></li>
        <li @click="changeHatColor('random-hat')" class="status random-hat">
          <i class='bx bx-question-mark'></i>
        </li>
        <li @click="changeHatColor('spectator')" class="status spectator">
          <i class='bx bx-show'></i>
        </li>
      </ul>
    </div>
  </div>

  <!-- 1hat 모드 -->
  <div v-else-if="hatMode === 'onehat'" class="name-box"
  :class="{ 'blue-hat-back' : userInfo.hatColor === 'blue-hat',
            'random-hat-back' : userInfo.hatColor !== 'spectator' && userInfo.hatColor !== 'blue-hat',
            'spectator-back' : userInfo.hatColor === 'spectator', }">
    <span class="user-name">이름</span>
    <div class="cnt-status dropdown">
      <div class="cnt-hat"
            :class="{'random-hat': userInfo.hatColor !== 'blue-hat', 'blue-hat': userInfo.hatColor === 'blue-hat'}"
            data-bs-toggle="dropdown" aria-expanded="false">
        <i v-if="userInfo.hatColor === 'spectator'" class='bx bx-show'></i>
      </div>
      <ul class="select dropdown-menu" role="menu">
        <li @click="changeHatColor('blue-hat')" class="status blue-hat"></li>
        <li @click="changeHatColor('random-hat')" class="status random-hat">
        </li>
        <li @click="changeHatColor('spectator')" class="status spectator">
          <i class='bx bx-show'></i>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'UserListItme',
  components: {
	},
  props: {
    userInfo: Object,
  },
	data: () => {
		return {
		}
	},
	computed: {
    ...mapGetters(['session', 'users', 'hatMode', 'myName',]),
	},
	methods: {
    changeHatColor(targetHat) {
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
        const sending = {user: this.userInfo, changedHat: targetHat}
        const jsonData = JSON.stringify(sending)
        this.session.signal({
          data: jsonData,
          type: 'change-hat-color'
        })
      }
    }
	},
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.name-box {
  width: 184px;
  height: 24px;
  border-radius: 8px;
}

.select {
  --bs-dropdown-min-width: 30px !important;
  padding: 4px;
}

ul li {
  display: block;
  list-style: none;
  margin: 4px 0;
  margin-right: 4px;
}

ul li:hover {
  cursor: pointer;
}

.cnt-hat {
  border-radius: 50%;
  margin: 0, 4px;
  width: 20px;
  height: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.cnt-hat .bx {
  color: white;
  font-size: 12px;
}

.cnt-hat:hover {
  cursor: pointer;
}

.cnt-status {
  position: relative;
  float: right;
  padding-left: 4px;
}

.status {
  width: 20px;
  height: 20px;
  border-radius: 50%;
}

.name-box {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 12px 0;
}

.user-name {
  color: black;
  margin: 0;
}

.red-hat {
  background-color: #EA4335;
}

.yellow-hat {
  background-color: #FBBC05;
}

.black-hat {
  background-color: black;
}

.black-hat .user-name {
  color: rgb(228, 228, 228);
}

.white-hat {
  background-color: #C7C6C6;
}

.green-hat {
  background-color: #34A853;
}

.blue-hat {
  background-color: #4285F4;
}

.random-hat {
 background-color: #585858;
 display: flex;
 justify-content: center;
 align-items: center;
}

.spectator {
  background-color: #585858;
  display: flex;
  justify-content: center;
  align-items: center;
}

.random-hat .bx, .spectator .bx {
  font-size: 12px;
  color: white;
}

.red-hat-back {
  background-color: #FFC0BA;
}

.yellow-hat-back {
  background-color: #FFE394;
}

.black-hat-back {
  background-color: #515151;
}

.white-hat-back {
  background-color: #ECECEC;
}

.green-hat-back {
  background-color: #79ED9A;
}

.blue-hat-back {
  background-color: #A4C5FF;
}

.random-hat-back {
 background-color: #9C9C9C;
}

.spectator-back {
  background-color: #BFBFBF;
}
</style>