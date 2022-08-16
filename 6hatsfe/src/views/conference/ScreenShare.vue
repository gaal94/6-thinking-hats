<template>
  <div class="screen-share-modal-box">
    <div class="screen-share-window">
      <p 
      class="no-screen-share-word"
      v-if="!screenSub">
        화면공유 중이 아닙니다
      </p>
      <ov-video 
      class="shared-screen" :stream-manager="screenSub"
      v-if="screenSub"/>
    </div>
    <button class="screen-share-modal-close-btn"
    @click="closeScreenShareModal">
      <i class='bx bxs-caret-up-circle'></i>
    </button>
  </div>
</template>

<script>
import OvVideo from '@/views/conference/user/OvVideo.vue';

export default {
  name: 'ScreenShareModal',
  components: {
    OvVideo,
  },
  data: () => {
    return {
      
      }
  },
  props: {
    screenSub: Object
  },
  computed: {},
  methods: {
    closeScreenShareModal() {
      this.$emit('closeScreenShareModal')
    }
  }
}
</script>

<style scoped>
  .screen-share-modal-box {
    justify-content: space-around;
    background-color: #121212;
    height: 91.4634vh;
    width: 66.9271vw;;
    z-index: 999;
  }

  .screen-share-modal-close-btn {
    border: none;
    background-color: #121212;
    position: absolute;
    bottom: 12px;
  }

  .screen-share-modal-close-btn:hover {
    cursor: pointer;
  }

  .no-screen-share-word {
    margin-left: auto;
    margin-right: auto;
    color: white;
    margin-top: 330px;
    margin-bottom: 0;
  }

  .bxs-caret-up-circle {
    color: white;
    font-size: 24px;
  }

  .shared-screen {
    margin-top: 20px;
    width: 52.0833vw;
    height: 83.1486vh;
  }

</style>