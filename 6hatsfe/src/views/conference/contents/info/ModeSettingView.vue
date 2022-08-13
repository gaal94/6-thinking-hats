<template>
  <div class="mode-setting-box">
    <p class="mode-setting-word">회의 모드 설정</p>

    <p class="hat-type-setting-word">모자 종류</p>
    <div class="hat-type-setting">
      <div v-if="isHost">
        <input type="radio" name="hat-type" id="sixhats" :checked="hatMode === 'sixhats'"
        @click="clickChangeHatMode('sixhats')">
        <label for="sixhats">6hats</label>
        <input type="radio" name="hat-type" id="onehat" :checked="hatMode === 'onehat'"
        @click="clickChangeHatMode('onehat')">
        <label for="onehat">1hat</label>
      </div>

      <div v-else>
        <span :class="{'selected-option': hatMode === 'sixhats'}"> 6hats </span>
        <span :class="{'selected-option': hatMode === 'onehat'}"> 1hat</span>
      </div>
    </div>

    <div class="idea-type-setting">
      <p class="idea-type-setting-word">회의 모드</p>
      <div v-if="isHost">
        <input type="radio" name="idea-type" id="proposal"
        @click="clickChangeIdeaMode('ideaSuggest')" :checked="ideaMode === 'ideaSuggest'">
        <label for="proposal">아이디어 제안</label>
        <input type="radio" name="idea-type" id="verification"
        @click="clickChangeIdeaMode('ideaJudge')" :checked="ideaMode === 'ideaJudge'">
        <label for="verification">아이디어 검증</label>
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
</style>