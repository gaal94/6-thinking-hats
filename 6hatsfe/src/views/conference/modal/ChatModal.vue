<template>
  <div class="chat-modal-box">
    <div class="chat-modal-header">
      <div class="header-content">
        <i class="header-content-icon"></i>
        <span class="header-content-word">채팅</span>
      </div>
      <i class="close-icon"></i>
    </div>
    <div class="chat-modal-body">
      <div class="body-content">
        <!-- <div class="body-content-header">
          <span class="user-name"></span>
          <span class="time"></span>
        </div>
        <p class="chat-word"></p> -->
      </div>
    </div>
    <div class="chat-modal-input-box">
      <input type="text" v-model="chatInput">
      <button class="send-btn" @click="sendChat"><i></i></button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ChatModal',
  components: {
	},
  props: {
    chat: String,
  },
	data: () => {
		return {
      chatInput: undefined,
		}
	},
	computed: {
	},
	methods: {
    sendChat () {
      this.$emit('sendChat', this.chatInput)
    },
    clearChat () {
      this.chatInput = undefined
    },
    receiveChat (event) {
      let chatScreen = document.querySelector('div.body-content')
      let chatComponent = document.createElement('p')
      let chatFrom = event.from.data.split(':')[1].replace(/"/g, '').replace('}', '')
      let currentTime = new Date()
      let currentHour = currentTime.getHours()
      currentHour = currentHour >= 10 ? currentHour : '0' + currentHour
      let currentMinute = currentTime.getMinutes()
      currentMinute = currentMinute >= 10 ? currentMinute : '0' + currentMinute
      let currentSecond = currentTime.getSeconds()
      currentSecond = currentSecond >= 10 ? currentSecond : '0' + currentSecond
      let chatTime = currentHour + ':' + currentMinute + ':' + currentSecond
      let chatMessage = document.createTextNode(chatFrom + '[' + chatTime + '] : ' + event.data)
      chatComponent.appendChild(chatMessage)
      chatScreen.appendChild(chatComponent)
    },
  }
}
</script>

<style>

</style>