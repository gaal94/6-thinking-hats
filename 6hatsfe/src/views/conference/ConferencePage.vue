<template>
  <button class="screen-share-btn">
    <i class="screen-share-btn-icon"></i>
  </button>
  <div class="conference-body">
    <div class="left-cam-screens">
      <cam-screen-box></cam-screen-box>
    </div>
    <div class="join-screen" v-if="!isConferencing">
      <info-box></info-box>
    </div>
    <div class="in-conference-screen" v-else-if="isConferencing">
      <div class="in-conference-box">
        <role-explain></role-explain>
        <role-keyword></role-keyword>
        <speech-order></speech-order>
      </div>
      <opinion-box></opinion-box>
    </div>
    <div class="right-cam-screens">
      <cam-screen-box></cam-screen-box>
    </div>  
  </div>
  <icon-bar :isConferencing="isConferencing" @changeConferenceStatus="changeConf()"></icon-bar>
</template>

<script>
import IconBar from '@/views/conference/footer/IconBar.vue'
import CamScreenBox from '@/views/conference/user/CamScreenBox.vue'
import InfoBox from '@/views/conference/contents/info/InfoBox.vue'
import OpinionBox from '@/views/conference/contents/opinions/OpinionBox.vue'
import RoleExplain from '@/views/conference/header/RoleExplain.vue'
import RoleKeyword from '@/views/conference/header/RoleKeyword.vue'
import SpeechOrder from '@/views/conference/header/SpeechOrder.vue'

export default {
  name: 'ConferencePage',
  components: {
    IconBar,
    CamScreenBox,
    InfoBox,
    OpinionBox,
    RoleExplain,
    RoleKeyword,
    SpeechOrder,
  },
  data: () => {
		return {
      isConferencing: false,
		}
	},
	computed: {
	},
	methods: {
    changeConf() {
      this.isConferencing = !this.isConferencing
    }
	},
}
</script>

<style>

</style>