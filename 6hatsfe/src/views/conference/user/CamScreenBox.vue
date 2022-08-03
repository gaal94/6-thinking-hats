<template>
  <div class="cam-screen-box">
    <i class="up-arrow"></i>
    <cam-screen></cam-screen>
    <i class="down-arrow"></i>
  </div>
</template>

<script>
import CamScreen from '@/views/conference/user/CamScreen.vue'

export default {
  name: 'CamScreenBox',
  components: {
    CamScreen,
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

<style>

</style>