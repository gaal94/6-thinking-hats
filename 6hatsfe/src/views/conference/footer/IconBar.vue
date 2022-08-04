<template>
  <div class="icon-box">
    <button><i class='bx bxs-microphone-off'></i></button>
    <button><i class='bx bxs-video-off' ></i></button>
    <button><i class='bx bx-window-open'></i></button>
    <button><i class='bx bx-radio-circle-marked'></i></button>
    <button><i class='bx bxs-smile'></i></button>
    <button><i class='bx bx-dots-vertical-rounded'></i></button>
    <button><i class='bx bx-link-alt' ></i></button>
    <button class="pre-btn"><i class='bx bx-chevron-left'></i></button>
    <button class="next-btn"><i class='bx bx-chevron-right' ></i></button>
    <button class="pass-btn">차례 넘기기</button>
    <button class="end-btn" @click="changeConference()">
      <span v-if="!isConferencing">회의 시작</span>
      <span v-else-if="isConferencing">회의 종료</span>
      </button>
    <button @click="outToMain()"><i class='bx bxs-x-circle'></i></button>
  </div>
</template>

<script>
export default {
  name: 'IconBar',
  components: {
	},
  props: {
    isConferencing: Boolean,
  },
	data: () => {
		return {
      
		}
	},
	computed: {
	},
	methods: {
    changeConference() {
      this.$emit('changeConferenceStatus')
    },
    outToMain() {
      alert('회의에서 나가시겠습니까?')
      this.$router.push({name: 'home'})
    }
	},
}
</script>

<style>

</style>