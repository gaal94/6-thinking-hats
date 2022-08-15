<template>
  <!-- sixhats 모드 -->
  <div v-if="(hatMode === 'sixhats' && hatColor !== 'spectator') || hatColor === 'blue-hat'" class="role-keyword-box">
    <img v-if="hatColor === 'red-hat'" src="@/assets/redhat_circle.png" alt="" class="role-keyword-face">
    <img v-else-if="hatColor === 'yellow-hat'" src="@/assets/yellowhat_circle.png" alt="" class="role-keyword-face">
    <img v-else-if="hatColor === 'green-hat'" src="@/assets/greenhat_circle.png" alt="" class="role-keyword-face">
    <img v-else-if="hatColor === 'blue-hat'" src="@/assets/bluehat_circle.png" alt="" class="role-keyword-face">
    <img v-else-if="hatColor === 'black-hat'" src="@/assets/blackhat_circle.png" alt="" class="role-keyword-face">
    <img v-else-if="hatColor === 'white-hat'" src="@/assets/whitehat_circle.png" alt="" class="role-keyword-face">
    <div class="role-keyword-list">
      <div v-if="hatColor === 'blue-hat'">
        <p class="role-keyword">냉정함</p>
        <p class="role-keyword">통제, 정리</p>
        <p class="role-keyword">결론</p>
      </div>
      <div v-else-if="hatColor === 'red-hat'">
        <p class="role-keyword">직감</p>
        <p class="role-keyword">감정</p>
        <p class="role-keyword">정서</p>
      </div>
      <div v-else-if="hatColor === 'yellow-hat'">
        <p class="role-keyword">긍정적</p>
        <p class="role-keyword">강점, 이점</p>
        <p class="role-keyword">실현 가능</p>
      </div>
      <div v-else-if="hatColor === 'green-hat'">
        <p class="role-keyword">창의적</p>
        <p class="role-keyword">혁신적</p>
        <p class="role-keyword">확산적</p>
      </div>
      <div v-else-if="hatColor === 'black-hat'">
        <p class="role-keyword">논리, 부정</p>
        <p class="role-keyword">잠재위험</p>
        <p class="role-keyword">오류</p>
      </div>
      <div v-else-if="hatColor === 'white-hat'">
        <p class="role-keyword">객관적</p>
        <p class="role-keyword">중립</p>
        <p class="role-keyword">사실, 정보</p>
      </div>
    </div>
  </div>

  <!-- onehat 모드 -->
  <div v-else-if="(hatMode === 'onehat' && hatColor !== 'blue-hat') || hatColor === 'spectator'" class="role-keyword-box">
    <img v-if="speechOrder[currentTurn] === 'red-hat'" src="@/assets/redhat_circle.png" alt="" class="role-keyword-face">
    <img v-else-if="speechOrder[currentTurn] === 'yellow-hat'" src="@/assets/yellowhat_circle.png" alt="" class="role-keyword-face">
    <img v-else-if="speechOrder[currentTurn] === 'green-hat'" src="@/assets/greenhat_circle.png" alt="" class="role-keyword-face">
    <img v-else-if="speechOrder[currentTurn] === 'blue-hat'" src="@/assets/bluehat_circle.png" alt="" class="role-keyword-face">
    <img v-else-if="speechOrder[currentTurn] === 'black-hat'" src="@/assets/blackhat_circle.png" alt="" class="role-keyword-face">
    <img v-else-if="speechOrder[currentTurn] === 'white-hat'" src="@/assets/whitehat_circle.png" alt="" class="role-keyword-face">
    <div class="role-keyword-list">
      <div v-if="speechOrder[currentTurn] === 'blue-hat'">
        <p class="role-keyword">냉정함</p>
        <p class="role-keyword">통제, 정리</p>
        <p class="role-keyword">결론</p>
      </div>
      <div v-else-if="speechOrder[currentTurn] === 'red-hat'">
        <p class="role-keyword">직감</p>
        <p class="role-keyword">감정</p>
        <p class="role-keyword">정서</p>
      </div>
      <div v-else-if="speechOrder[currentTurn] === 'yellow-hat'">
        <p class="role-keyword">긍정적</p>
        <p class="role-keyword">강점, 이점</p>
        <p class="role-keyword">실현가능성</p>
      </div>
      <div v-else-if="speechOrder[currentTurn] === 'green-hat'">
        <p class="role-keyword">창의적</p>
        <p class="role-keyword">혁신적</p>
        <p class="role-keyword">확산적</p>
      </div>
      <div v-else-if="speechOrder[currentTurn] === 'black-hat'">
        <p class="role-keyword">논리, 부정</p>
        <p class="role-keyword">잠재위험</p>
        <p class="role-keyword">오류</p>
      </div>
      <div v-else-if="speechOrder[currentTurn] === 'white-hat'">
        <p class="role-keyword">객관적</p>
        <p class="role-keyword">중립</p>
        <p class="role-keyword">사실, 정보</p>
      </div>
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
  width: 9.1146vw;
  min-height: 5.2083vw;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.role-keyword-face {
  width: 4.1667vw;
  height: 4.1667vw;
}

.role-keyword {
  font-size: 0.9115vw;
  margin: 0;
  padding: 2px;
  color: white;
}
</style>