<template>
  <div class="opinion-box">
    <div class="subject-box">
      <div class="timer-box">
        <i class="timer-icon bx bx-stopwatch"></i>
        <div class="time">
  
        </div>
      </div>
      <div class="subject-content">
        <p v-if="!subUpdating">{{ subject }}</p>
        <input class="sub-input" 
        v-else-if="subUpdating" type="text" :value="subject">
      </div>
      <div class="subject-btn" v-if="hatColor === 'blue-hat'">
        <button class="sub-update-btn" v-if="!subUpdating"
        @click="updateToggle">
        <span v-if="!subject">입력</span>
        <span v-else-if="subject">수정</span></button>
        <button class="sub-update-confirm-btn"
        v-if="subUpdating"
        @click="updateSubject">확인</button>
        <button class="sub-update-cancel-btn"
        v-if="subUpdating"
        @click="updateToggle">취소</button>
      </div>
    </div>
    <div class="opinion-content-box">
      <div class="opinion-contents">
        <opinion-item v-for="(op, idx) in opinions" :key="`message-${idx}`"
        :opinion="op"
        :hat-color="hatColor"
        @deleteMessage="deleteMessage(idx)"></opinion-item>
      </div>
      <div class="input-box">
        <input type="text" class="input-box-content" v-model="opinion">
        <i class='bx bxs-send' @click="sendMessage(opinion)"></i>
      </div>
    </div>
  </div>
</template>

<script>
import OpinionItem from '@/views/conference/contents/opinions/OpinionItem.vue'
import { mapGetters } from 'vuex'

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
      subject: '',
      opinions: [],
      opinion: '',
      subUpdating: false,
		}
	},
	computed: {
    ...mapGetters(['baseTime']),
    minute() {
      const min = parseInt(this.time / 60)
      return min > 9 ? String(min) : '0' + String(min)
    },
    second() {
      const sec = String(this.time % 60)
      return sec.length > 1 ? sec : '0' + sec
    },
	},
	methods: {
    updateToggle() {
      this.subUpdating = !this.subUpdating
    },
    updateSubject() {
      const changedSub = document.querySelector('.sub-input').value
      this.subject = changedSub
      this.subUpdating = false
    },
    sendMessage(op) {
      if (op) {
        this.opinions.push(op)
        this.opinion = ''
      }
    },
    deleteMessage(index) {
      this.opinions.splice(index, 1)
    }
	},
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
}

.input-box-content:focus {
  outline: none;
}

.bxs-send:hover {
  cursor: pointer;
}
</style>