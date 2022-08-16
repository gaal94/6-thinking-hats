<template>
  <div class="mode-setting-box">
    <p class="mode-setting-word">회의 모드 설정</p>

    <p class="hat-type-setting-word">모자 종류</p>
    <div class="hat-type-setting">
      <div v-if="isHost">
        <input type="radio" name="hat-type" id="sixhats" :checked="hatMode === 'sixhats'"
        @click="clickChangeHatMode('sixhats')" class="sixhats-mode-btn">
        <label for="sixhats" class="sixhats-label">
          <img src="@/assets/sixhats_mode.png" alt="" class="sixhats-img host-img" 
          :class="{'hat-mode-checked': hatMode === 'sixhats'}">
        </label>
        <input type="radio" name="hat-type" id="onehat" :checked="hatMode === 'onehat'"
        @click="clickChangeHatMode('onehat')" class="onehat-mode-btn">
        <label for="onehat" class="onehat-label">
          <img src="@/assets/onehat_mode.png" alt="" class="onehat-img host-img" 
          :class="{'hat-mode-checked': hatMode === 'onehat'}">
        </label>
      </div>

      <div v-else>
        <img src="@/assets/sixhats_mode.png" alt="" class="sixhats-img" :class="{'hat-mode-checked': hatMode === 'sixhats'}">
        <img src="@/assets/onehat_mode.png" alt="" class="onehat-img" :class="{'hat-mode-checked': hatMode === 'onehat'}">
      </div>
    </div>

    <div class="idea-type-setting">
      <p class="idea-type-setting-word">회의 모드</p>
      <div v-if="isHost">
        <input type="radio" name="idea-type" id="suggest"
        @click="clickChangeIdeaMode('ideaSuggest')" :checked="ideaMode === 'ideaSuggest'">
        <label for="suggest">아이디어 제안</label>
        <input type="radio" name="idea-type" id="judge"
        @click="clickChangeIdeaMode('ideaJudge')" :checked="ideaMode === 'ideaJudge'">
        <label for="judge">아이디어 검증</label>
      </div>

      <div v-else>
        <span :class="{'selected-option': ideaMode === 'ideaSuggest'}">아이디어 제안</span>
        <span :class="{'selected-option': ideaMode === 'ideaJudge'}">아이디어 검증</span>
      </div>
    </div>
    
    <div class="timer-setting">
      <p class="timer-word">타이머</p>
      <div v-if="isHost">
        <select name="timer" id="time-select" @change="timeSettingChange($event)">
          <option value="1" :selected="timeSetting === 1">1분</option>
          <option value="3" :selected="timeSetting === 3">3분</option>
          <option value="5" :selected="timeSetting === 5">5분</option>
          <option value="7" :selected="timeSetting === 7">7분</option>
          <option value="10" :selected="timeSetting === 10">10분</option>
        </select>
      </div>

      <div v-else>
        <span>{{ timeSetting }}분</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'ModeSettingView',
  components: {
	},
	data: () => {
		return {
		}
	},
	computed: {
    ...mapGetters(['session', 'ideaMode', 'hatMode', 'baseTime', 'isHost',]),
    timeSetting() {
      return parseInt(this.baseTime / 60)
    }
	},
	methods: {
    ...mapActions(['changeIdeaMode', 'setTime', 'changeHatMode', 'changeSpeechOrder']),
    clickChangeIdeaMode(selected) {
      this.session.signal({
        data: selected,
        type: 'change-idea-mode'
      })
    },
    clickChangeHatMode(selected) {
      this.session.signal({
        data: selected,
        type: 'change-hat-mode'
      })
    },
    timeSettingChange(event) {
      this.session.signal({
        data: event.target.value,
        type: 'time-setting'
      })
    },
	},
  created() {
    this.session.on('signal:change-idea-mode', (event) => {
      this.changeIdeaMode(event.data)
      this.changeSpeechOrder(event.data)
    })

    this.session.on('signal:time-setting', (event) => {
      this.setTime(Number(event.data))
    })

    this.session.on('signal:change-hat-mode', (event) => {
      this.changeHatMode(event.data)
    })
  }
}
</script>

<style scoped>
.selected-option {
  color: brown;
}

.sixhats-mode-btn, .onehat-mode-btn {
  display: none;
}

.hat-type-setting {
  margin-bottom: 12px;
}

.host-img:hover {
  cursor: pointer;
}

.sixhats-img, .onehat-img {
  width: 8.5938vw;
  height: 10.4167vw;
  border-radius: 0.6510vw;
  margin-right: 8px;
}

.hat-mode-checked {
  box-shadow: 2px 2px 4px rgb(155, 155, 155);
}

</style>