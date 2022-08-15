<template>
  <div class="user-modal-box" @mousedown="highlightUserListModal">
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
    highlightUserListModal () {
      let chatModal = document.querySelector('div.chat-modal')
      let userListModal = document.querySelector('div.user-list-modal')
      chatModal.style.zIndex = '2'
      userListModal.style.zIndex = '3'
    }
  },
  mounted() {
      dragElement(document.querySelector('div.user-list-modal'));

      function dragElement(elmnt) {
        var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
        if (document.querySelector('div.user-modal-header')) {
          // if present, the header is where you move the DIV from:
          document.querySelector('div.user-modal-header').onmousedown = dragMouseDown;
        } else {
          // otherwise, move the DIV from anywhere inside the DIV:
          elmnt.onmousedown = dragMouseDown;
        }

        function dragMouseDown(e) {
          e = e || window.event;
          e.preventDefault();
          // get the mouse cursor position at startup:
          pos3 = e.clientX;
          pos4 = e.clientY;
          document.onmouseup = closeDragElement;
          // call a function whenever the cursor moves:
          document.onmousemove = elementDrag;
        }

        function elementDrag(e) {
          e = e || window.event;
          e.preventDefault();
          // calculate the new cursor position:
          pos1 = pos3 - e.clientX;
          pos2 = pos4 - e.clientY;
          pos3 = e.clientX;
          pos4 = e.clientY;
          // set the element's new position:
          elmnt.style.top = (elmnt.offsetTop - pos2) + 'px';
          if (elmnt.style.top.replace('px', '') < 0) {
            elmnt.style.top = '0px'
          } else if (elmnt.style.top.replace('px', '') > window.innerHeight - elmnt.clientHeight - 2){
            elmnt.style.top = String(window.innerHeight - elmnt.clientHeight - 2) + 'px'
          }
          elmnt.style.left = (elmnt.offsetLeft - pos1) + 'px';
          if (elmnt.style.left.replace('px', '') < 0) {
            elmnt.style.left = '0px'
          } else if (elmnt.style.left.replace('px', '') > window.innerWidth - elmnt.clientWidth - 2){
            elmnt.style.left = String(window.innerWidth - elmnt.clientWidth - 2) + 'px'
          }
        }

        function closeDragElement() {
          // stop moving when mouse button is released:
          document.onmouseup = null;
          document.onmousemove = null;
        }
      }
    }
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
    cursor: move;
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