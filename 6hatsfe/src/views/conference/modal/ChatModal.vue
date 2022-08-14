<template>
  <div class="chat-modal-box">
    <div class="chat-modal-header">
      <div class="header-content">
        <i class="header-content-icon bx bxs-message-rounded-dots" ></i>
        <p class="header-content-text">채팅</p>
      </div>
      <i class="close-icon bx bx-x" @click="closeChatModal"></i>
    </div>
    <div class="chat-modal-body">
      <div class="body-content">
      </div>
    </div>
    <div class="chat-modal-input-box">
      <input class="chat-input" type="text" v-model="chatInput" @keyup.enter="sendChat">
      <i class="send-btn bx bxs-send" @click="sendChat"></i>
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
      oldX: undefined,
      oldY: undefined
		}
	},
	computed: {
	},
	methods: {
    sendChat () {
      if (this.chatInput) {
        this.$emit('sendChat', this.chatInput)
      }
    },
    clearChat () {
      this.chatInput = undefined
    },
    receiveChat (event) {
      let chatScreen = document.querySelector('div.body-content')
      let chatDiv = document.createElement('div')
      let chatHeaderDiv = document.createElement('div')
      let chatContentDiv = document.createElement('div')
      let chatFromP = document.createElement('p')
      let chatTimeP = document.createElement('p')
      let chatMessageP = document.createElement('p')
      let chatFrom = event.from.data.split(':')[1].replace(/"/g, '').replace('}', '')
      let currentTime = new Date()
      let currentHour = currentTime.getHours()
      let currentMeridium = currentHour >= 12 ? '오후' : '오전'
      currentHour = currentHour >= 13 ? currentHour - 12 : currentHour
      currentHour = currentHour == 0 ? 12 : currentHour
      let currentMinute = currentTime.getMinutes()
      currentMinute = currentMinute >= 10 ? currentMinute : '0' + currentMinute
      let chatTime = currentMeridium + ' ' + currentHour + ':' + currentMinute
      chatFromP.innerText = chatFrom
      chatTimeP.innerText = chatTime
      chatMessageP.innerText = event.data
      chatHeaderDiv.appendChild(chatFromP)
      chatHeaderDiv.appendChild(chatTimeP)
      chatContentDiv.appendChild(chatMessageP)
      chatDiv.appendChild(chatHeaderDiv)
      chatDiv.appendChild(chatContentDiv)
      chatDiv.className = 'chat-div'
      chatHeaderDiv.className = 'chat-header-div'
      chatContentDiv.className = 'chat-content-div'
      chatFromP.className = 'chat-from-p'
      chatTimeP.className = 'chat-time-p'
      chatMessageP.className = 'chat-message-p'
      chatScreen.appendChild(chatDiv)
      chatScreen.scrollTop = chatScreen.scrollHeight
      // 스크롤 여부에 따라 body-content 너비 조작
      if (chatScreen.scrollWidth == chatScreen.offsetWidth) {
        if (JSON.stringify(chatScreen.classList).includes('with-scroll')) {
          chatScreen.classList.remove('with-scroll')
        }
      } else {
        if (!JSON.stringify(chatScreen.classList).includes('with-scroll')) {
          chatScreen.classList.add('with-scroll')
        }
      }
    },
    closeChatModal () {
      this.$emit('closeChatModal')
    }
  },
  mounted() {
      dragElement(document.querySelector('div.chat-modal'));

      function dragElement(elmnt) {
        var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
        if (document.querySelector('div.chat-modal-header')) {
          // if present, the header is where you move the DIV from:
          document.querySelector('div.chat-modal-header').onmousedown = dragMouseDown;
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

<style>
  .chat-modal-box {
    background: #F6F6F6;
    width: 604px;
    height: 328px;
    border: solid black 1px;
    border-radius: 10px;
  }

  .chat-modal-header {
    position: relative;
    display: flex;
    top: 12px;
    left: 32px;
    width: 532px;
    height: 36px;
    justify-content: space-between;
    cursor: move;
  }

  .header-content {
    display: flex;
    height: 36px;
    align-items: center;
  }

  .header-content-icon, .close-icon {
    font-size: 36px;
    color: black;
  }

  .close-icon {
    cursor: pointer;
  }

  .header-content-text {
    font-size: 14px;
    margin-left: 12px;
    margin-bottom: 0;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
  }

  .chat-modal-body {
    position: relative;
    top: 24px;
    left: 31px;
    width: 558px;
    height: 204px;
  }

  .body-content {
    width: 542px;
    height: 100%;
    overflow: auto;
  }

  .with-scroll {
    width: 558px;
  }

  .body-content::-webkit-scrollbar {
    background: grey;
    width: 16px;
    border-radius: 14px;
  }

  .body-content::-webkit-scrollbar-thumb {
    background: black;
    border-radius: 14px;
  }

  .chat-div {
    display: flex;
    flex-direction: column;
    margin-right: 10px;
  }

  .chat-header-div {
    display: flex;
    justify-content: space-between;
  }

  .chat-from-p, .chat-time-p, .chat-message-p {
    margin-bottom: 0;
  }

  .chat-from-p, .chat-time-p {
    font-size: 14px;
  }

  .chat-message-p {
    font-size: 18px;
    margin-right: auto;
    text-align: left;
  }

  .chat-modal-input-box {
    position: relative;
    display: flex;
    top: 36px;
    left: 33px;
    width: 538px;
    height: 32px;
    border: solid black 1px;
    border-radius: 8px;
    align-items: center;
  }

  .chat-input {
    border: none;
    width: 514px;
    height: 100%;
    border-radius: 8px;
  }

  .chat-input:focus {
    outline: none;
  }

  .send-btn {
    font-size: 24px;
    color: black;
    cursor: pointer;
  }
</style>