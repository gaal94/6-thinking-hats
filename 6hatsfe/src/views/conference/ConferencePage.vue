<template>
  <div class="conference-page">
    <!-- 화면공유 -->
    <button @click="seeScreenShare" class="screen-share-btn"
    v-if="!seeScreen && !screenPublisher">
      <i class="screen-share-btn-icon bx bxs-caret-down-circle"></i>
    </button>
    <screen-share class="screen-share" v-if="seeScreen"
    @closeScreenShareModal="closeScreenShare"
    :screen-sub="screenSub"></screen-share>

    <div class="conference-body">
      <!-- 왼쪽 캠화면 + 모자 키워드 -->
      <div class="left-side">
        <role-keyword :hat-color="myHat" class="role-keyword"
        v-if="isConferencing"></role-keyword>
        <i class='bx bx-chevron-up cam-arrow-icon' ></i>
        <cam-screen :stream-manager="publisher"></cam-screen>
        <cam-screen v-for="sub in subscribers.slice(0, 2)" :key="sub.stream.connection.connectionId" :stream-manager="sub"></cam-screen>
        <i class='bx bx-chevron-down cam-arrow-icon' ></i>
      </div>

      <!-- 셋팅할 때 -->
      <div class="join-screen" v-if="!isConferencing">
        <info-box></info-box>
      </div>

      <!-- 회의 시작 후 -->
      <div class="in-conference-screen" v-else-if="isConferencing">
        <role-explain :hat-color="myHat"></role-explain>
        <opinion-box :hat-color="myHat"
        @updateSubject="updateSubject"
        :session="session"></opinion-box>
      </div>

      <!-- 오른쪽 캠화면 + 발언 순서 -->
      <div class="right-side">
        <speech-order class="speech-order" v-if="isConferencing"></speech-order>
        <i class='bx bx-chevron-up cam-arrow-icon' ></i>
        <cam-screen v-for="sub in subscribers.slice(2, 5)" :key="sub.stream.connection.connectionId" :stream-manager="sub"></cam-screen>
        <i class='bx bx-chevron-down cam-arrow-icon' ></i>
      </div>  
    </div>

    <!-- 아이콘바 -->
    <icon-bar 
    :hat-color="myHat"
    :role="host"
    :session="session"
    @changeConferenceStatus="changeConf"
    @leaveRoom="leaveSession"
    @changeMic="changeMicrophone"
    @changeVideo="changeVideo"
    @shareScreen="shareScreen"
    @menuModal="menuModal"
    class="icon-bar"></icon-bar>
    
    <menu-modal
    class="menu-modal"
    v-if="seeMenu"
    @chatModal="chatModal"
    @userListModal="userListModal"></menu-modal>
    <chat-modal
    class="chat-modal"
    v-show="seeChat"
    ref="chatRef"
    @sendChat="sendChat"
    @clostChatModal="clostChatModal"></chat-modal>
  </div>
</template>

<script>
import IconBar from '@/views/conference/footer/IconBar.vue'
import InfoBox from '@/views/conference/contents/info/InfoBox.vue'
import OpinionBox from '@/views/conference/contents/opinions/OpinionBox.vue'
import RoleExplain from '@/views/conference/header/RoleExplain.vue'
import RoleKeyword from '@/views/conference/header/RoleKeyword.vue'
import SpeechOrder from '@/views/conference/header/SpeechOrder.vue'
import CamScreen from '@/views/conference/user/CamScreen.vue'
import ScreenShare from '@/views/conference/ScreenShare.vue'
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import { mapActions, mapGetters } from 'vuex'
// import UserVideo from './components/UserVideo';
import MenuModal from '@/views/conference/modal/MenuModal.vue'
import ChatModal from '@/views/conference/modal/ChatModal.vue'

axios.defaults.headers.post['Content-Type'] = 'application/json';

// const OPENVIDU_SERVER_URL = "https://" + 'i7a709.p.ssafy.io' + ":4443";
const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";


export default {
  name: 'ConferencePage',
  components: {
    IconBar,
    InfoBox,
    OpinionBox,
    RoleExplain,
    RoleKeyword,
    SpeechOrder,
    CamScreen,
    ScreenShare,
    MenuModal,
    ChatModal,
  },
  data: () => {
		return {
      OV: undefined,
      screenOV: undefined,
			session: undefined,
      screenSession: undefined,
			mainStreamManager: undefined,
			subscribers: [],
      screenPublisher: undefined,
      screenSub: undefined,

			mySessionId: 'SessionAAAAAA',
			myUserName: 'Participant' + Math.floor(Math.random() * 100),
      audio: false,
      video: false,
      isScreenShared: false,
      seeScreen: false,
      seeMenu: false,
      seeChat: false,
      seeUserList: false,
		}
	},
	computed: {
    ...mapGetters(['publisher', 'users', 'myHat', 'isHost', 'ideaMode', 'hatMode',
                    'speechOrder', 'currentTurn', 'baseTime', 'totalTime',
                    'confSubject', 'opinions', 'hostConnectionId', 'isConferencing',
                    'conferenceStatus',]),
	},
	methods: {
    ...mapActions(['startTimer', 'resetTimer', 'resetTurn', 'setSession', 'addUser',
                    'changeUserHatColor', 'setMyHat', 'setPublisher', 'clearUsers',
                    'setMyName', 'removeUser', 'addOpinion', 'removeOpinion', 'setRole',
                    'initialSetting', 'setHostConnectionId', 'turnOffAudio', 'turnOnAudio',
                    'turnOffVideo', 'turnOnVideo', 'endConference', 'startConference',
                    ]),
    sendChat (chat) {
      this.session.signal({
        data: chat,
        type: 'chat'
      })
      .then(() => {
        console.log('Message successfully sent')
        this.$refs.chatRef.clearChat()
      })
      .catch(error => {
        console.error(error)
      })
    },
    menuModal () {
      this.seeMenu = !this.seeMenu
    },
    chatModal () {
      this.seeMenu = false
      this.seeChat = true
    },
    userListModal () {
      this.seeMenu = false
      this.seeUserList = true
    },
    clostChatModal () {
      this.seeChat = !this.seeChat
    },
    changeConf() {
      this.session.signal({
        to: [],
        type: 'changeConf'
      })
      .then(() => {
        console.log('conference just started!');
      })
    },
    joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();

			// --- Init a session ---
			this.session = this.OV.initSession();
      
      this.setSession(this.session)

			// --- Specify the actions when events take place in the session ---

      this.session.on('signal:chat', (event) => {
        this.$refs.chatRef.receiveChat(event)
      })

			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
        if (stream.typeOfVideo === 'SCREEN') {
          const screen = this.session.subscribe(stream)
          this.screenSub = screen
        }
        if (stream.typeOfVideo === 'CAMERA') {
          const subscriber = this.session.subscribe(stream);
          subscriber.hatColor = 'spectator'
          this.subscribers.push(subscriber);
        }
			});

			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
        if (stream.typeOfVideo === 'SCREEN') {
          this.screenSub = undefined
        }
				if (stream.typeOfVideo === 'CAMERA') {
          const index = this.subscribers.indexOf(stream.streamManager, 0);
          if (index >= 0) {
            this.subscribers.splice(index, 1);
          }
        }
			});

			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});
      
      this.session.on('connectionCreated', ({connection}) => {
        if (this.isHost) {
          const name = JSON.parse(connection.data).clientData
          const userInfo = { hatColor: 'spectator', 
                            connectionId: connection.connectionId,
                            userName: name}
          this.addUser(userInfo)

          const settingData = { users: this.users,
                                ideaMode: this.ideaMode,
                                hatMode: this.hatMode,
                                speechOrder: this.speechOrder,
                                currentTurn: this.currentTurn,
                                baseTime: this.baseTime,
                                totalTime: this.totalTime,
                                confSubject: this.confSubject,
                                opinions: this.opinions,
                                hostConnectionId: this.hostConnectionId,
                                conferenceStatus: this.conferenceStatus}
          const jsonSettingData = JSON.stringify(settingData)
          this.session.signal({
            data: jsonSettingData,
            type: 'initial-setting'
          })
        }
      })

      this.session.on('connectionDestroyed', ({connection}) => {
        if (this.hostConnectionId !== connection.connectionId) {
          let idx = this.users.findIndex(userInfo => {
          if (userInfo.connectionId === connection.connectionId) {
            return true
          }
        })

        this.removeUser(idx)
        } else {
          this.leaveSession()
          this.$router.push({name: 'LandingPage'})
        }
      })

			// --- Connect to the session with a valid user token ---

			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {
            
            this.setMyName(this.myUserName)
						// --- Get your own camera stream with the desired properties ---

						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: false,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: false,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
						});

						this.mainStreamManager = publisher;
						this.setPublisher(publisher)
            const userInfo = { hatColor: 'spectator', connectionId: publisher.stream.session.connection.connectionId,
                              userName: this.myUserName }
            this.addUser(userInfo)
						// --- Publish your stream ---

            // 호스트 판별
            if (this.subscribers.length === 0) {
              this.setRole('host')
              this.setHostConnectionId(publisher.stream.session.connection.connectionId)
            }

						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});
			window.addEventListener('beforeunload', this.leaveSession)
		},

		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect();
      if (this.screenSession) this.screenSession.disconnect()

			this.session = undefined;
      this.setSession(undefined)
			this.mainStreamManager = undefined;
			this.setPublisher(undefined);
			this.subscribers = [];
			this.OV = undefined;
      this.screenSession = undefined
      this.clearUsers()
      this.setRole('particitant')
      this.setHostConnectionId(undefined)
      this.resetTimer()

			window.removeEventListener('beforeunload', this.leaveSession);
		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */

		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},

    changeMicrophone() {
      this.audio = !this.audio
      if (this.audio) {
        this.turnOnAudio()
      } else {
        this.turnOffAudio()
      }
    },

    changeVideo(){
      this.video = !this.video
      if (this.video) {
        this.turnOnVideo()
      } else {
        this.turnOffVideo()
      }
    },
    
    shareScreen() {
      this.screenOV = new OpenVidu()
      this.screenSession = this.screenOV.initSession()

      this.getToken(this.mySessionId).then(token => {
				this.screenSession.connect(token)
					.then(() => {

						// --- Get your own camera stream with the desired properties ---

            this.screenPublisher = this.screenOV.initPublisher(undefined, { videoSource: 'screen', publishAudio: false})

            this.screenPublisher.once('accessAllowed', () => {
              this.screenPublisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
                this.screenSession.unpublish(this.screenPublisher)
                this.screenSession.disconnect()
                this.screenPublisher = undefined
                this.screenSub = undefined
                this.screenOV = undefined
              })
            })
            this.screenSession.publish(this.screenPublisher)

					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});    
    },

    seeScreenShare() {
      this.seeScreen = true
    },

    closeScreenShare() {
      this.seeScreen = false
    },
	},
  created() {
    this.joinSession()

    // 회의를 시작하거나 종료할 때 신호를 받고 실행됨
    this.session.on('signal:changeConf', () => {
      this.resetTurn()
      if (this.isConferencing) {
        this.resetTimer()
        this.endConference()
      } else {
        this.startTimer()
        this.startConference()
      }
    })

    // 유저들의 모자 색을 바꿀 때 실행됨
    this.session.on('signal:change-hat-color', event => {
      const data = JSON.parse(event.data)
      this.changeUserHatColor(data)
      if (this.publisher.stream.session.connection.connectionId === data.user.connectionId) {
        this.setMyHat(data.changedHat)
      }
    })

    // 의견창구에 의견을 보낼 때 실행됨
    this.session.on('signal:send-opinion', ({data}) => {
      const opinionData = JSON.parse(data)
      this.addOpinion(opinionData)
    })

    // 의견창구에서 의견을 지울 때 실행됨
    this.session.on('signal:delete-opinion', ({data}) => {
      this.removeOpinion(Number(data))
    })

    this.session.on('signal:initial-setting', ({data}) => {
      // users, ideaMode, hatMode, speechOrder, currentTurn, baseTime, 
      // totalTime, timer, confSubject, opinions
      
      if (!this.isHost) {
        const settingData = JSON.parse(data)
        this.initialSetting(settingData)
      }
    })
  }
}
</script>

<style scoped>
  .conference-page {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: #121212;
  }

  .screen-share-btn-icon {
    color: white;
    font-size: 24px;
  }

  .screen-share-btn {
    border: none;
    background-color: #121212;
  }

  .screen-share-btn:hover {
    cursor: pointer;
  }

  .in-conference-screen {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 12px;
  }

  .conference-body {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .left-side {
    display: flex;
    flex-direction: column;
  }

  .right-side {
    display: flex;
    flex-direction: column;
  }

  .left-side, .right-side {
    align-self: flex-start;
  }

  .cam-arrow-icon {
    font-size: 40px;
    color: white;
  }

  .cam-arrow-icon:hover {
    cursor: pointer;
  }

  .icon-bar {
    align-self: bottom;
  }

  .menu-modal {
    position: absolute;
    bottom: 60px;
    z-index: 1;
  }

  .chat-modal {
    position: absolute;
    bottom: 60px;
    z-index: 2;
  }
</style>