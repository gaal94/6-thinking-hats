<template>
  <div class="conference-page">
    <button @click="seeScreenShare" class="screen-share-btn"
    v-if="!seeScreen && !screenPublisher">
      <i class="screen-share-btn-icon bx bxs-caret-down-circle"></i>
    </button>
    <screen-share class="screen-share" v-if="seeScreen"
    @closeScreenShareModal="closeScreenShare"
    :screen-sub="screenSub"></screen-share>

    <div class="conference-body">
      <div class="left-side">
        <role-keyword :hat-color="myHat" class="role-keyword"
        v-if="isConferencing"></role-keyword>
        <i class='bx bx-chevron-up cam-arrow-icon' ></i>
        <cam-screen :stream-manager="publisher"></cam-screen>
        <cam-screen v-for="sub in subscribers.slice(0, 2)" :key="sub.stream.connection.connectionId" :stream-manager="sub"></cam-screen>
        <i class='bx bx-chevron-down cam-arrow-icon' ></i>
      </div>

      <div class="join-screen" v-if="!isConferencing">
        <info-box></info-box>
      </div>

      <div class="in-conference-screen" v-else-if="isConferencing">
        <role-explain :hat-color="myHat"></role-explain>
        <opinion-box :hat-color="myHat"
        @updateSubject="updateSubject"
        :session="session"></opinion-box>
      </div>

      <div class="right-side">
        <speech-order class="speech-order" v-if="isConferencing"></speech-order>
        <i class='bx bx-chevron-up cam-arrow-icon' ></i>
        <cam-screen v-for="sub in subscribers.slice(2, 5)" :key="sub.stream.connection.connectionId" :stream-manager="sub"></cam-screen>
        <i class='bx bx-chevron-down cam-arrow-icon' ></i>
      </div>  
    </div>

    <icon-bar 
    :isConferencing="isConferencing"
    :hat-color="myHat"
    :role="host"
    :session="session"
    @changeConferenceStatus="changeConf"
    @leaveRoom="leaveSession"
    @changeMic="changeMicrophone"
    @changeVideo="changeVideo"
    @shareScreen="shareScreen"
    class="icon-bar"></icon-bar>
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

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://" + 'i7a709.p.ssafy.io' + ":4443";
// const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
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
  },
  data: () => {
		return {
      isConferencing: false,
      OV: undefined,
      screenOV: undefined,
			session: undefined,
      screenSession: undefined,
			mainStreamManager: undefined,
			subscribers: [],
      screenPublisher: undefined,
      screenSub: undefined,

			mySessionId: 'SessionA',
			myUserName: 'Participant' + Math.floor(Math.random() * 100),
      audio: false,
      video: false,
      isScreenShared: false,
      seeScreen: false,
      host: true,
		}
	},
	computed: {
    ...mapGetters(['publisher', 'users', 'myHat']),
	},
	methods: {
    ...mapActions(['startTimer', 'resetTimer', 'resetTurn', 'setSession', 'addUser',
                    'changeUserHatColor', 'setMyHat', 'setPublisher',]),
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

          const userInfo = { hatColor: 'spectator', 
                            connectionId: subscriber.stream.connection.connectionId }
          this.addUser(userInfo)
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

      // 회의를 시작하거나 종료할 때 신호를 받고 실행됨
      this.session.on('signal:changeConf', () => {
        this.resetTurn()
        if (this.isConferencing) {
          this.resetTimer()
        } else {
          this.startTimer()
        }
        this.isConferencing = !this.isConferencing
      })

			// --- Connect to the session with a valid user token ---

			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

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
            const userInfo = { hatColor: 'spectator', connectionId: publisher.stream.session.connection.connectionId }
            this.addUser(userInfo)
						// --- Publish your stream ---

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
      this.publisher.publishAudio(this.audio)
    },

    changeVideo(){
      this.video = !this.video
      this.publisher.publishVideo(this.video)
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

    this.session.on('signal:change-hat-color', event => {
      const data = JSON.parse(event.data)
      this.changeUserHatColor(data)
      if (this.publisher.stream.session.connection.connectionId === data.user.connectionId) {
        this.setMyHat(data.changedHat)
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
</style>