<template>
  <div class="info-box">
    <mode-setting-view></mode-setting-view>
    <user-setting-view
    :all-participants="allParticipants"></user-setting-view>
  </div>
</template>

<script>
import UserSettingView from '@/views/conference/contents/info/UserSettingView.vue'
import ModeSettingView from '@/views/conference/contents/info/ModeSettingView.vue'

export default {
  name: 'InfoBox',
  components: {
    UserSettingView,
    ModeSettingView
  },
  props: {
    allParticipants: Array,
  },
  data: () => {
		return {
		}
	},
	computed: {
	},
	methods: {
	},
}
</script>

<style scoped>
  .info-box {
    display: flex;
    align-items: center;
    background-color: #F6F6F6;
    padding: 12px;
    border-radius: 10px;
  }
</style>