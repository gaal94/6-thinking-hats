<template>
  <div class="info-box">
    <mode-setting-view></mode-setting-view>
    <user-setting-view></user-setting-view>
  </div>
</template>

<script>
import UserSettingView from '@/views/conference/contents/info/UserSettingView.vue'
import ModeSettingView from '@/views/conference/contents/info/ModeSettingView.vue'
import { mapActions, mapGetters } from 'vuex'

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
    ...mapGetters(['session', 'users',]),
	},
	methods: {
    ...mapActions(['changeUserHatColor',]),
	},
  created() {
    this.session.on('signal:change-hat-color', event => {
      const data = JSON.parse(event.data)
      this.changeUserHatColor(data)
    })
  }
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