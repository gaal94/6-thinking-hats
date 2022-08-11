<template>
  <div class="opinion-box">
    <div class="subject-box">

      <!-- 타이머 -->
      <div class="timer-box">
        <i class="timer-icon bx bx-stopwatch"></i>
        <div class="time">
          {{ minutes }} : {{ seconds }}
        </div>
        <i class='bx bx-play' @click="clickStartTimer"
        v-if="hatColor === 'blue-hat'"></i>
        <i class='bx bx-pause' @click="clickStopTimer"
        v-if="hatColor === 'blue-hat'"></i>
        <i class='bx bx-revision' @click="clickResetTimer"
        v-if="hatColor === 'blue-hat'"></i>
      </div>

      <!-- 회의 주제 변경 인풋, 데이터 창 -->
      <div class="subject-content">
        <p v-if="!subUpdating">{{ confSubject }}</p>
        <input class="sub-input" 
        v-else-if="subUpdating" type="text">
      </div>

      <!-- 회의 주제 변경 버튼 -->
      <div class="subject-btn" v-if="hatColor === 'blue-hat'">
        <button class="sub-update-btn" v-if="!subUpdating"
        @click="updateToggle">
        <span v-if="!confSubject">입력</span>
        <span v-else-if="confSubject">수정</span></button>
        <button class="sub-update-confirm-btn"
        v-if="subUpdating"
        @click="updateSubject">확인</button>
        <button class="sub-update-cancel-btn"
        v-if="subUpdating"
        @click="updateToggle">취소</button>
      </div>

    </div>

    <!-- 의견 창구 -->
    <div class="opinion-content-box">
      <div class="opinion-contents">
        <opinion-item v-for="(op, idx) in opinions" :key="`message-${idx}`"
        :opinion="op" :opinion-index="idx"></opinion-item>
      </div>

      <!-- 의견 창구 입력 창 sixhats 모드 & onehat 모드 -->
      <div class="input-box" 
      v-if="(hatMode === 'sixhats' && (hatColor === speechOrder[currentTurn] || hatColor === 'blue-hat'))
            || (hatMode === 'onehat' && hatColor !== 'spectator')">
        <textarea class="input-box-content" v-model="opinion"></textarea>
        <i class='bx bxs-send' @click="sendOpinion(opinion)"></i>
      </div>
    </div>
  </div>
</template>

<script>
import OpinionItem from '@/views/conference/contents/opinions/OpinionItem.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'OpinionBox',
  components: {
    OpinionItem,
  },
  props: {
    hatColor: String,
  },
  data: () => {
		return {
      opinion: '',
      subUpdating: false,
		}
	},
	computed: {
    ...mapGetters(['minutes', 'seconds', 'confSubject', 'session', 'opinions',
                    'publisher', 'myName', 'speechOrder', 'currentTurn',
                    'hatMode',]),
	},
	methods: {
    ...mapActions(['startTimer', 'stopTimer', 'resetTimer', 'setConfSubject']),
    updateToggle() {
      this.subUpdating = !this.subUpdating
    },
    updateSubject() {
      const changedSub = document.querySelector('.sub-input').value
      this.setConfSubject(changedSub)
      this.subUpdating = false
      this.session.signal({
        data: changedSub,  // Any string (optional)
        to: [],                     // Array of Connection objects (optional. Broadcast to everyone if empty)
        type: 'update-subject'             // The type of message (optional)
      })
      .then(() => {
          console.log('Message successfully sent');
      })
      .catch(error => {
          console.error(error);
      });
    },
    clickStartTimer() {
      this.session.signal({
        type: 'start-timer'
      })
    },
    clickStopTimer() {
      this.session.signal({
        type: 'stop-timer'
      })
    },
    clickResetTimer() {
      this.session.signal({
        type: 'reset-timer'
      })
    },
    sendOpinion(op) {
      if (op) {
        let opinionData = {}
        if (this.hatMode === 'sixhats' || this.hatColor === 'blue-hat' ) {
          opinionData = { userName: this.myName, content: this.opinion, 
                          connectionId: this.publisher.stream.session.connection.connectionId,
                          hatColor: this.hatColor}
        } else {
          opinionData = { userName: this.myName, content: this.opinion, 
                          connectionId: this.publisher.stream.session.connection.connectionId,
                          hatColor: this.speechOrder[this.currentTurn]}
        }
        const jsonOpinionData = JSON.stringify(opinionData)
        this.session.signal({
          data: jsonOpinionData,
          type: 'send-opinion'
        })

        this.opinion = ''
      }
    },
	},
  created() {
  // 주제가 변화될 때
  this.session.on('signal:update-subject', event => {
    this.setConfSubject(event.data)
  })
  
  // 타이머를 실행할 때
  this.session.on('signal:start-timer', () => {
    this.startTimer()
  })
  
  // 타이머를 멈출 때
  this.session.on('signal:stop-timer', () => {
    this.stopTimer()
  })

  // 타이머를 재설정할 때
  this.session.on('signal:reset-timer', () => {
    this.resetTimer()
  })

  }
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.subject-box {
  display: flex;
  justify-content: space-between;
  background-color: #F6F6F6;
  align-items: center;
  border-radius: 14px;
  height: 52px;
  width: 1080px;
  margin-bottom: 12px;
  margin-left: auto;
  margin-right: auto;
}

.timer-box {
  display: flex;
  gap: 4px;
  margin-left: 8px;
}

.timer-icon {
  font-size: 36px;
}

.subject-btn {
  display: flex;
  justify-content: space-between;
}

.sub-update-btn, .sub-update-confirm-btn, .sub-update-cancel-btn {
  border: none;
  color: white;
  border-radius: 36px;
  width: 64px;
  height: 36px;
}

.sub-update-btn:hover, .sub-update-confirm-btn:hover, .sub-update-cancel-btn:hover {
  cursor: pointer;
}

.sub-update-btn {
  background-color: #4285F4;
}

.sub-update-confirm-btn {
  background-color: #34A853;
}

.sub-update-cancel-btn {
  background-color: #EA4335;
}

.opinion-content-box {
  background-color: #F6F6F6;
  width: 1080px;
  height: 565px;
  border-radius: 14px;
  margin-left: auto;
  margin-right: auto;
  padding: 12px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}

.opinion-contents {
  width: 1020px;
  display: flex;
  flex-direction: column;
  gap: 8px;
  overflow: auto;
}

.input-box {
  display: flex;
  width: 1020px;
  height: 48px;
  border: black solid 1px;
  border-radius: 14px;
  align-items: center;
  background-color: white;
}

.input-box-content {
  border: none;
  width: 980px;
  height: 36px;
  margin-left: 10px;
  position: relative;
  bottom: 4px;
  overflow: auto;
}

.input-box-content:focus {
  outline: none;
}

.bxs-send:hover {
  cursor: pointer;
}

.bx-play, .bx-pause {
  font-size: 24px;
}

.bx-revision {
  font-size: 20px;
}

.bx-play:hover, .bx-pause:hover, .bx-revision:hover {
  cursor: pointer;
}

</style>