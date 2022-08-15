<template>
  <!-- 6hats 모드 -->
  <div v-if="hatMode === 'sixhats'" class="cam-box">
    <div class="hat-img-box">
      <img v-if="hat === 'red-hat'" src="@/assets/redhat.png" alt="" class="hat-img">
      <img v-else-if="hat === 'yellow-hat'" src="@/assets/yellowhat.png" alt="" class="hat-img">
      <img v-else-if="hat === 'green-hat'" src="@/assets/greenhat.png" alt="" class="hat-img">
      <img v-else-if="hat === 'blue-hat'" src="@/assets/bluehat.png" alt="" class="hat-img">
      <img v-else-if="hat === 'black-hat'" src="@/assets/blackhat.png" alt="" class="hat-img">
      <img v-else-if="hat === 'white-hat'" src="@/assets/whitehat.png" alt="" class="hat-img">
      <img v-else-if="hat === 'random-hat'" src="@/assets/randomhat.png" alt="" class="hat-img">
    </div>
    <div class="cam-screen-box">
			<div v-if="streamManager">
        <ov-video class="cam-screen" :stream-manager="streamManager"/>
      </div>
    </div>
  </div>

  <!-- 1hat 모드 -->
  <div v-else-if="hatMode === 'onehat'" class="cam-box">
    <div class="hat-img-box">
      <img v-if="hat === 'blue-hat'" src="@/assets/bluehat.png" alt="" class="hat-img">
      <img v-else-if="hat !== 'blue-hat' && hat !== 'spectator' && speechOrder[currentTurn] !== 'blue-hat'" 
      :src="require(`@/assets/${speechOrder[currentTurn].replace('-', '')}.png`)" alt="" class="hat-img">
    </div>
    <div class="cam-screen-box">
			<div v-if="streamManager">
        <ov-video class="cam-screen" :stream-manager="streamManager"/>
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
    }
  },
  computed: {
    ...mapGetters(['users', 'publisher', 'myHat', 'session', 'hatMode', 'speechOrder', 'currentTurn',]),
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
    hat() {
      const idx = this.users.findIndex(userInfo => userInfo.connectionId === this.streamManager.stream.connection.connectionId)
      return idx > -1 ? this.users[idx].hatColor : 'spectator'
    },
	},
  methods: {
    getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
  },
}
</script>

<style scoped>
  .cam-box {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 12px;
  }

  .cam-screen {
    width: 13.0208vw;
    height: 14.9667vh;
    border-radius: 1.9531vw;
  }

  .hat-img-box {
    min-width: 2.0833vw;
    min-height: 2.0833vw;
  }

  .hat-img {
    width: 2.0833vw;
    height: 2.0833vw;
  }

  .highlight {
    border: 2px solid white;
  }
</style>