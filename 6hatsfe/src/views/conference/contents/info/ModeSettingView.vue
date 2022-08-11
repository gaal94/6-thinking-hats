<template>
  <div class="mode-setting-box">
    <p class="mode-setting-word">회의 모드 설정</p>
    <div class="hat-type-setting">
      <p class="hat-type-setting-word">모자 종류</p>
      <input type="radio" name="hat-type" id="sixhats" :checked="hatMode === 'sixhats'"
      @click="clickChangeHatMode('sixhats')">
      <label for="sixhats">6hats</label>
      <input type="radio" name="hat-type" id="onehat" :checked="hatMode === 'onehat'"
      @click="clickChangeHatMode('onehat')">
      <label for="onehat">1hat</label>
    </div>
    <div class="idea-type-setting">
      <p class="idea-type-setting-word">회의 모드</p>
      <input type="radio" name="idea-type" id="proposal"
      @click="clickChangeIdeaMode('ideaSuggest')" :checked="ideaMode === 'ideaSuggest'">
      <label for="proposal">아이디어 제안</label>
      <input type="radio" name="idea-type" id="verification"
      @click="clickChangeIdeaMode('ideaJudge')" :checked="ideaMode === 'ideaJudge'">
      <label for="verification">아이디어 검증</label>
    </div>
    <div class="timer-setting">
      <p class="timer-word">타이머</p>
      <select name="timer" id="time-select" v-model="timeSetting" @change="timeSettingChange(timeSetting)">
        <option value="1">1분</option>
        <option value="3">3분</option>
        <option value="5">5분</option>
        <option value="7">7분</option>
        <option value="10">10분</option>
      </select>
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
      timeSetting: 1,
		}
	},
	computed: {
    ...mapGetters(['session', 'ideaMode', 'hatMode',]),
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
    timeSettingChange(timeSet) {
      this.session.signal({
        data: timeSet,
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

<style>

</style>