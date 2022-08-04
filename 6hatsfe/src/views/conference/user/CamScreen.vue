<template>
  <div class="cam-box">
    <img src="" alt="" class="hat-img">
    <div class="cam-screen">
			<div id="session" v-if="session">
				<div id="video-container" class="col-md-6">
					<user-video :stream-manager="publisher"/>
					<user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub"/>
				</div>
			</div>
    </div>
  </div>
</template>

<script>
	import UserVideo from '@/views/conference/user/UserVideo.vue';

	export default {
		name: 'CamScreen',

		components: {
			UserVideo,
		},

		props: {
			OV: Object,
			session: Object,
			mainStreamManager: Object,
			publisher: Object,
			subscribers: Object,

			mySessionId: Object,
			myUserName: Object,
		},

		data () {

		},

		methods: {
			
		}
	}
</script>

<style>

</style>