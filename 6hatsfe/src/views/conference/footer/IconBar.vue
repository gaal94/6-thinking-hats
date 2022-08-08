<template>
  <div class="icon-box">
    <button @click="changeMic"><i class='bx bxs-microphone-off mic'></i></button>
    <button @click="changeVideo"><i class='bx bxs-video-off video'></i></button>
    <button @click="shareScreen"><i class='bx bx-window-open'></i></button>
    <button><i class='bx bx-radio-circle-marked'></i></button>
    <button><i class='bx bxs-smile'></i></button>
    <button v-if="role"><i class='bx bx-dots-vertical-rounded'></i></button>
    <button><i class='bx bx-link-alt' ></i></button>
    <button class="pre-btn" v-if="isConferencing && hatColor == 'blue-hat'"
    @click="clickBackToPreTurn"
    :class="ideaMode[(currentTurn+5)%6]"><i class='bx bx-chevron-left'></i></button>
    <button class="next-btn" v-if="isConferencing && hatColor == 'blue-hat'"
    @click="clickPassTurn"
    :class="ideaMode[(currentTurn+1)%6]"><i class='bx bx-chevron-right'></i></button>
    <button class="pass-btn" v-if="isConferencing" :class="hatColor"
    @click="clickPassTurn">차례 넘기기</button>
    <button class="end-btn" @click="startConference()" 
    v-if="!isConferencing && role">
      <span>회의 시작</span>
    </button>
    <button class="end-btn" @click="endConference()" 
    v-if="hatColor == 'blue-hat' && isConferencing">
      <span>회의 종료</span>
    </button>
    <button @click="outToMain()"><i class='bx bxs-x-circle'></i></button>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'IconBar',
  components: {
	},
  props: {
    isConferencing: Boolean,
    hatColor: String,
    role: Boolean,
  },
	data: () => {
		return {
      
		}
	},
	computed: {
    ...mapGetters(['ideaMode', 'currentTurn', 'session']),
	},
	methods: {
    ...mapActions(['passTurn', 'backToPreTurn', 'resetTurn', 'startTimer', 'resetTimer',]),
    startConference() {
      this.$emit('changeConferenceStatus')
    },
    endConference() {
      this.$emit('changeConferenceStatus')
    },
    outToMain() {
      alert('회의에서 나가시겠습니까?')
      this.$emit('leaveRoom')
      this.$router.push({name: 'LandingPage'})
    },
    changeMic() {
      this.$emit('changeMic')
      const micIcon = document.querySelector('.mic')
      if (micIcon.classList.item(1) === 'bxs-microphone-off') {
        micIcon.classList.replace('bxs-microphone-off', 'bxs-microphone')
      } else if (micIcon.classList.item(1) === 'bxs-microphone') {
        micIcon.classList.replace('bxs-microphone', 'bxs-microphone-off')
      }
    },
    changeVideo() {
      this.$emit('changeVideo')
      const videoIcon = document.querySelector('.video')
      if (videoIcon.classList.item(1) === 'bxs-video-off') {
        videoIcon.classList.replace('bxs-video-off', 'bxs-video')
      } else if (videoIcon.classList.item(1) === 'bxs-video') {
        videoIcon.classList.replace('bxs-video', 'bxs-video-off')
      }
    },
    shareScreen() {
      this.$emit('shareScreen')
    },
    clickBackToPreTurn() {
      this.session.signal({
        type: 'back-to-pre-turn'
      })
    },
    clickPassTurn() {
      this.session.signal({
        type: 'pass-turn'
      })
    },
	},
  mounted() {
    this.session.on('signal:back-to-pre-turn', () => {
      this.backToPreTurn()
    })
    this.session.on('signal:pass-turn', () => {
      this.passTurn()
    })
  }
}
</script>

<style scoped>
  * {
  box-sizing: border-box;
}

.icon-box {
  display: flex;
  align-items: center;
  gap: 4px;
}

button {
  border: none;
  background-color: rgb(18, 18, 18);
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
}

button:hover {
  cursor: pointer;
}

i {
  color: white;
  font-size: 24px;
}

.bxs-x-circle {
  color: rgb(223, 58, 58);
}

.pre-btn {
  border-radius: 50%;
}

.next-btn {
  border-radius: 50%;
}

.pass-btn, .end-btn {
  width: 160px;
  height: 48px;
  border-radius: 36px;
  color: white;
}

.end-btn {
  background-color: #4285F4;
}

.red-hat {
  background-color: #EA4335;
}

.yellow-hat {
  background-color: #FBBC05;
}

.green-hat {
  background-color: #34A853;
}

.blue-hat {
  background-color: #4285F4;
}

.white-hat {
  background-color: #FFFFFF;
  color: black;
}

.white-hat i {
  color: black;
}

.black-hat {
  background-color: #000000;
}

</style>