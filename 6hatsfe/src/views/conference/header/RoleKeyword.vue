<template>
  <!-- sixhats 모드 -->
  <div v-if="(hatMode === 'sixhats' && hatColor !== 'spectator') || hatColor === 'blue-hat'" class="role-keyword-box">
    <img v-if="hatColor === 'red-hat'" src="@/assets/redhat_circle.png" alt="" class="hat-img">
    <img v-else-if="hatColor === 'yellow-hat'" src="@/assets/yellowhat_circle.png" alt="" class="hat-img">
    <img v-else-if="hatColor === 'green-hat'" src="@/assets/greenhat_circle.png" alt="" class="hat-img">
    <img v-else-if="hatColor === 'blue-hat'" src="@/assets/bluehat_circle.png" alt="" class="hat-img">
    <img v-else-if="hatColor === 'black-hat'" src="@/assets/blackhat_circle.png" alt="" class="hat-img">
    <img v-else-if="hatColor === 'white-hat'" src="@/assets/whitehat_circle.png" alt="" class="hat-img">
    <div class="role-keyword-list">
      <p class="role-keyword">창의적</p>
      <p class="role-keyword">혁신적</p>
      <p class="role-keyword">확산적</p>
    </div>
  </div>

  <!-- onehat 모드 -->
  <div v-else-if="(hatMode === 'onehat' && hatColor !== 'blue-hat') || hatColor === 'spectator'" class="role-keyword-box">
    <img v-if="speechOrder[currentTurn] === 'red-hat'" src="@/assets/redhat_circle.png" alt="" class="hat-img">
    <img v-else-if="speechOrder[currentTurn] === 'yellow-hat'" src="@/assets/yellowhat_circle.png" alt="" class="hat-img">
    <img v-else-if="speechOrder[currentTurn] === 'green-hat'" src="@/assets/greenhat_circle.png" alt="" class="hat-img">
    <img v-else-if="speechOrder[currentTurn] === 'blue-hat'" src="@/assets/bluehat_circle.png" alt="" class="hat-img">
    <img v-else-if="speechOrder[currentTurn] === 'black-hat'" src="@/assets/blackhat_circle.png" alt="" class="hat-img">
    <img v-else-if="speechOrder[currentTurn] === 'white-hat'" src="@/assets/whitehat_circle.png" alt="" class="hat-img">
    <div class="role-keyword-list">
      <p class="role-keyword">창의적</p>
      <p class="role-keyword">혁신적</p>
      <p class="role-keyword">확산적</p>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'RoleKeyword',
  components: {
	},
    props: {
    hatColor: String,
  },
	data: () => {
		return {
		}
	},
	computed: {
    ...mapGetters(['hatMode', 'speechOrder', 'currentTurn',]),
	},
	methods: {
	},
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.role-keyword-box {
  width: 132px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.role-keyword-face {
  width: 72px;
  height: 72px;
}

.role-keyword {
  font-size: 14px;
  margin: 0;
  padding: 2px;
  color: white;
}
</style>