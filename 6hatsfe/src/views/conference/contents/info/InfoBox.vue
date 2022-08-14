<template>
  <div class="info-box">
    <mode-setting-view></mode-setting-view>
    <user-setting-view></user-setting-view>
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
    align-items: flex-start;
    justify-content: space-evenly;
    background-color: #F6F6F6;
    padding: 12px 0 4px 0;
    border-radius: 10px;
    width: 1020px;
    height: 560px;
  }
</style>