<template>
  <div class="menu-modal-box">
    <div class="chat-button" @click="chatModal">
      <i class="bx bxs-message-rounded-dots chat-icon"></i>
      <p class="chat-text">채팅</p>
    </div>
    <div class="user-list-button" @click="userListModal">
      <i class="bx bxs-user user-list-icon"></i>
      <p class="user-list-text">참가자</p>
    </div>
  </div>
</template>

<script>
export default {
	name: 'MenuModal',
	components: {
	},
	data: () => {
		return {
		}
	},
	computed: {
	},
	methods: {
    chatModal () {
      this.$emit('chatModal')
    },
    userListModal () {
      this.$emit('userListModal')
    }
	},
}
</script>

<style>
  .menu-modal-box {
    background: #F6F6F6;
    width: 136px;
    height: 112px;
    border-radius: 14px;
  }

  .chat-button, .user-list-button {
    position: relative;
    display: flex;
    left: 17px;
    width: 102px;
    align-items: center;
    cursor: pointer;
  }

  .chat-button {
    top: 12px;
  }

  .user-list-button {
    top: 28px;
  }

  .chat-icon, .user-list-icon {
    font-size: 36px;
    color: black;
  }

  .chat-text, .user-list-text {
    font-size: 14px;
    margin-left: 12px;
    margin-bottom: 0;
    -webkit-user-select:none;
    -moz-user-select:none;
    -ms-user-select:none;
    user-select:none;
  }
</style>