<template>
  <div class="opinion-box">
    <div class="subject-box">
      <div class="timer-box">
        <i class="timer-icon"></i>
        <div class="time">
  
        </div>
      </div>
      <div class="subject-content">
  
      </div>
      <button class="subject-btn"></button>
    </div>
    <div class="opinion-content-box">
      <div class="opinion-contents">
        <opinion-item></opinion-item>
      </div>
      <div class="input-box">
        <input type="text" class="input-box-content">
        <i class="send-icon"></i>
      </div>
    </div>
  </div>
</template>

<script>
import OpinionItem from '@/views/conference/contents/opinions/OpinionItem.vue'

export default {
  name: 'OpinionBox',
  components: {
    OpinionItem,
  }
}
</script>

<style>

</style>