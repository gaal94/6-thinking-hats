<template>
  <div class="mode-setting-box">
    <p class="mode-setting-word">회의 모드 설정</p>
    <div class="hat-type-setting">
      <p class="hat-type-setting-word">모자 종류</p>
      <input type="radio" name="hat-type" id="sixhat"><label for="sixhat"></label>
      <input type="radio" name="hat-type" id="onehat"><label for="onehat"></label>
    </div>
    <div class="idea-type-setting">
      <p class="idea-type-setting-word">회의 모드</p>
      <input type="radio" name="idea-type" id="proposal"
      value="ideaSuggest"
      @click="changeIdeaMode('ideaSuggest')" checked>
      <label for="proposal">아이디어 제안</label>
      <input type="radio" name="idea-type" id="verification"
      value="ideaJudge"
      @click="changeIdeaMode('ideaJudge')">
      <label for="verification">아이디어 검증</label>
    </div>
    <div class="timer-setting">
      <p class="timer-word">타이머</p>
      <select name="timer" id="time-select" v-model="timeSetting" @change="setTime(timeSetting)">
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
import { mapActions } from 'vuex'

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
	},
	methods: {
    ...mapActions(['changeIdeaMode', 'setTime'])
	},
}
</script>

<style>

</style>