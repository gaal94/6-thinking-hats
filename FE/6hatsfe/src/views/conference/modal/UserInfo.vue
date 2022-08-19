<template>
  <div class="user-info">
    <div class="user-name-authority">
      <span class="user-name">
        {{ user['userName'] }}
      </span>
      <i class="user-authority bx bxs-crown" v-if="user['isHost']"></i>
    </div>
    <div class="user-info-icon">
      <i class="user-cam bx bxs-video" v-if="user['camOn']"></i>
      <i class="user-cam bx bxs-video-off" v-else></i>

      <i class="user-mic bx bxs-microphone" v-if="user['micOn']"></i>
      <i class="user-mic bx bxs-microphone-off" v-else></i>

      <i class="user-hat random-hat bx bx-question-mark" v-if="user['hatColor'] == 'random-hat'"></i>
      <div class="user-hat red-hat" v-if="user['hatColor'] == 'red-hat'"></div>
      <div class="user-hat yellow-hat" v-if="user['hatColor'] == 'yellow-hat'"></div>
      <div class="user-hat green-hat" v-if="user['hatColor'] == 'green-hat'"></div>
      <div class="user-hat blue-hat" v-if="user['hatColor'] == 'blue-hat'"></div>
      <div class="user-hat white-hat" v-if="user['hatColor'] == 'white-hat'"></div>
      <div class="user-hat black-hat" v-if="user['hatColor'] == 'black-hat'"></div>
      <i class="user-hat spectator bx bx-show" v-if="user['hatColor'] == 'spectator'"></i>
      
      <i class="user-expulsion bx bxs-user-x" v-if="!user['isHost'] && this.publisher.stream.session.connection.connectionId == this.hostConnectionId" @click="kickUser(user['connectionId'])"></i>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { mapActions } from 'vuex'

export default {
	name: 'UserInfo',
	components: {
	},
  props: {
    user: Object,
  },
	data: () => {
		return {
		}
	},
	computed: {
    ...mapGetters(['users', 'publisher', 'hostConnectionId', 'session', 'isConferencing',])
	},
	methods: {
    ...mapActions(['removeUser',]),
    kickUser (conId) {
      if (this.isConferencing) {
        alert('회의 중엔 강퇴할 수 없습니다.')
      } else {
        for (let idx in this.users) {
          if (this.users[idx]['connectionId'] == conId) {
            if(window.confirm(this.users[idx]['userName'] + '님을 정말로 강퇴하시겠습니까?')) {
                this.session.signal({
                data: idx,
                type: 'kick-user'
              })
            }
            break
          }
        }
      }
      
    }
	},
}
</script>

<style scoped>
  .user-info {
    display: flex;
    width: 280px;
    height: 24px;
    margin-bottom: 1px;
  }

  .user-name-authority {
    display: flex;
    width: 152px;
  }

  .user-name {
    display: flex;
    font-size: 18px;
    max-width: 120px;
    text-align: left;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .user-info-icon {
    display: flex;
    width: 120px;
    height: 24px;
    margin-left: 8px;
  }

  .user-authority, .user-cam, .user-mic, .user-hat, .user-expulsion {
    width: 24px;
    height: 24px;
    font-size: 24px;
    color: black;
  }

  .user-authority {
    margin-left: 8px;
  }

  .user-cam, .user-mic, .user-hat {
    margin-right: 8px;
  }

  .user-hat {
    border-radius: 50%;
  }

  .random-hat, .spectator {
    background-color: #585858;
    color: white;
    font-size: 14.4px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .red-hat {
    background-color: #EA4335;
  }

  .yellow-hat {
    background-color: #FBBC05;
  }

  .green-hat {
    background-color: #34A853;
  }

  .blue-hat {
    background-color: #4285F4;
  }

  .white-hat {
    background-color: #C7C6C6;
  }

  .black-hat {
    background-color: #000000;
  }

  .user-expulsion {
    cursor: pointer;
  }
</style>