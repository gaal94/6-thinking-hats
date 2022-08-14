<template>
  <div class="screen-share-modal-box">
    <div class="screen-share-window">
      <p 
      class="no-screen-share-word"
      v-if="!screenSub">
        화면 공유 안 하고 있음
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
  .screen-share-modal-close-btn {
    border: none;
    background-color: #121212;
  }

  .screen-share-modal-close-btn:hover {
    cursor: pointer;
  }

  .bxs-caret-up-circle {
    color: white;
    font-size: 24px;
  }

  .shared-screen {
    width: 600px;
    height: 400px;
  }

</style>