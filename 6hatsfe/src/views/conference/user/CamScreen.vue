<template>
  <!-- 6hats 모드 -->
  <div v-if="hatMode === 'sixhats'" class="cam-box">
    <img v-if="hat === 'red-hat'" src="@/assets/redhat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'yellow-hat'" src="@/assets/yellowhat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'green-hat'" src="@/assets/greenhat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'blue-hat'" src="@/assets/bluehat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'black-hat'" src="@/assets/blackhat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'white-hat'" src="@/assets/whitehat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'random-hat'" src="@/assets/randomhat.png" alt="" class="hat-img">
    <div class="cam-screen-box">
			<div v-if="streamManager">
      <ov-video class="cam-screen" :stream-manager="streamManager"/>
      <div><p>{{ clientData }}</p></div>
    </div>
    </div>
  </div>

  <!-- 1hat 모드 -->
  <div v-else-if="hatMode === 'onehat'" class="cam-box">
    <img v-if="hat === 'blue-hat'" src="@/assets/bluehat.png" alt="" class="hat-img">
    <img v-else-if="hat !== 'blue-hat' && hat !== 'spectator'" :src="require(`@/assets/${speechOrder[currentTurn].replace('-', '')}.png`)" alt="" class="hat-img">
    <div class="cam-screen-box">
			<div v-if="streamManager">
      <ov-video class="cam-screen" :stream-manager="streamManager"/>
      <div><p>{{ clientData }}</p></div>
    </div>
    </div>
  </div>
</template>

<script>
import OvVideo from '@/views/conference/user/OvVideo.vue';
import { mapGetters } from 'vuex';

export default {
  name: 'CamScreen',
  components: {
    OvVideo,
  },
  props: {
    streamManager: Object,
  },
  data () {
    return {
      hat: false,
    }
  },
  computed: {
    ...mapGetters(['users', 'publisher', 'myHat', 'session', 'hatMode', 'speechOrder', 'currentTurn',]),
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},
  methods: {
    getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
  },
  created() {
    this.session.on('signal:change-hat-color', () => {
      if (this.streamManager === this.publisher) {
        this.hat = this.myHat
      } else {
        const idx = this.users.findIndex(userInfo => userInfo.connectionId === this.streamManager.stream.connection.connectionId)
        this.hat = this.users[idx].hatColor
      }
    })
  }
}
</script>

<style scoped>
  .cam-box {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .cam-screen {
    width: 200px;
    height: 120px;
    border-radius: 14px;
  }

  .hat-img {
    width: 40px;
    height: 40px;
  }
</style>