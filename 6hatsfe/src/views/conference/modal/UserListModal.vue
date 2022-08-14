<template>
  <div class="user-modal-box" @mousedown="mouseDown">
    <div class="user-modal-header">
      <div class="header-content">
        <i class="user-icon bx bxs-user"></i>
        <span class="header-word">방 참가자 목록</span>
      </div>
      <i class="close-icon bx bx-x" @click="closeUserListModal"></i>
    </div>
    <div class="user-modal-body">
      <user-info v-for="(user, idx) in users" :key="idx" :user="user"></user-info>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import UserInfo from '@/views/conference/modal/UserInfo.vue'

export default {
  name: 'UserListModal',
  components: {
    UserInfo
	},
	data: () => {
		return {
      oldX: undefined,
      oldY: undefined
		}
	},
	computed: {
    ...mapGetters(['users'])
	},
	methods: {
    closeUserListModal () {
      this.$emit('closeUserListModal')
    },
    mouseDown (event) {
      this.oldX = event.clientX
      this.oldY = event.clientY
      let userModalBox = document.querySelector('div.user-modal-box')
      userModalBox.addEventListener('mousemove', this.mouseMove)
      userModalBox.addEventListener('mouseup', () => {
        userModalBox.removeEventListener('mousemove', this.mouseMove)
      })
    },
    mouseMove (event) {
      let newX = event.clientX
      let newY = event.clientY
      let difX = newX - this.oldX
      let difY = newY - this.oldY
      let userModalBox = document.querySelector('div.user-modal-box')
      let modalX = userModalBox.getBoundingClientRect().left
      let modalY = userModalBox.getBoundingClientRect().top
      userModalBox.style.left = `${modalX + difX}px`
      userModalBox.style.top = `${modalY + difY}px`
      this.oldX = newX
      this.oldY = newY
    }
  },
}
</script>

<style scoped>
  .user-modal-box {
    background: #F6F6F6;
    width: 344px;
    height: 372px;
    border: solid black 1px;
    border-radius: 10px;
  }

  .user-modal-header {
    position: relative;
    display: flex;
    top: 12px;
    left: 32px;
    width: 280px;
    height: 36px;
    justify-content: space-between;
  }

  .header-content {
    display: flex;
    align-items: center;
  }

  .user-icon, .close-icon {
    font-size: 36px;
    color: black;
  }

  .close-icon {
    cursor: pointer;
  }

  .header-word {
    font-size: 14px;
    margin-left: 12px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
  }

  .user-modal-body {
    position: relative;
    top: 24px;
    left: 32px;
    width: 280px;
    height: 300px;
  }
</style>