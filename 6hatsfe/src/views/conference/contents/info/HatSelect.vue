<template>
  <div class="hat-select-box">
    <img v-if="hat === 'red-hat'" src="@/assets/redhat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'yellow-hat'" src="@/assets/yellowhat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'green-hat'" src="@/assets/greenhat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'blue-hat'" src="@/assets/bluehat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'black-hat'" src="@/assets/blackhat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'white-hat'" src="@/assets/whitehat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'random-hat'" src="@/assets/randomhat.png" alt="" class="hat-img">
    <img v-else-if="hat === 'spectator'" src="@/assets/spectator.png" alt="" class="hat-img">
    <div class="user-list"
    :class="hat">
      <user-list-item></user-list-item>
    </div>
  </div>
</template>

<script>
import UserListItem from '@/views/conference/contents/info/UserListItem.vue'

export default {
  name: 'HatSelect',
  components: {
    UserListItem,
  },
  props: {
    hat: String,
  },
  data: () => {
		return {
		}
	},
	computed: {
	},
	methods: {
	},
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.hat-select-box {
  display: flex;
  align-items: center;
  gap: 28px;
}

.hat-img {
  width: 52px;
  height: 52px;
}

.user-list {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 200px;
  height: 40px;
  border-radius: 14px;
}

.red-hat {
  background-color: #FFDCD9;
}

.yellow-hat {
  background-color: #FFF1CA;
}

.green-hat {
  background-color: #CFF3D9;
}

.blue-hat {
  background-color: #CEE0FF;
}

.white-hat {
  background-color: #FFFFFF;
}

.black-hat {
  background-color: #8E8E8E;
}

.random-hat {
  background-color: #D2D2D2;
}

.spectator {
  background-color: #E9E9E9;
}

</style>