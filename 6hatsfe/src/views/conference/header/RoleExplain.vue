<template>
  <!-- sixhats 모드, 파란모자 -->
  <div v-if="(hatMode === 'sixhats' && hatColor !== 'spectator') || hatColor === 'blue-hat'" class="explain-box">
    <div class="explain-line">
      <div class="explain-word explain-1"
      :class="hatColor">
        <span>제가 생각한 아이디어는 ~ 입니다</span>
      </div>
      <div class="explain-word explain-2"
      :class="hatColor">
        <span>이런 걸 해보면 어떨까요?</span>
      </div>
      <div class="explain-word explain-3"
      :class="hatColor">
        <span>이 아이디어를 개선할 방안은 ~ 입니다</span>
      </div>
    </div>
    <div class="explain-line">
      <div class="explain-word explain-5"
      :class="hatColor">
        <span>이 아이디어를 개선할 방안은 ~ 입니다</span>
      </div>
      <div class="explain-word explain-4"
      :class="hatColor">
        <span>기존의 사고에서 벗어나 새로운 시각과 창의적인 아이디어 제시</span>
      </div>
    </div>
  </div>

  <!-- onehat 모드, 관전자 -->
  <div v-else-if="(hatMode === 'onehat' && hatColor !== 'blue-hat') || hatColor === 'spectator'" class="explain-box">
    <div class="explain-line">
      <div class="explain-word explain-1"
      :class="speechOrder[currentTurn]">
        <span>제가 생각한 아이디어는 ~ 입니다</span>
      </div>
      <div class="explain-word explain-2"
      :class="speechOrder[currentTurn]">
        <span>이런 걸 해보면 어떨까요?</span>
      </div>
      <div class="explain-word explain-3"
      :class="speechOrder[currentTurn]">
        <span>이 아이디어를 개선할 방안은 ~ 입니다</span>
      </div>
    </div>
    <div class="explain-line">
      <div class="explain-word explain-5"
      :class="speechOrder[currentTurn]">
        <span>이 아이디어를 개선할 방안은 ~ 입니다</span>
      </div>
      <div class="explain-word explain-4"
      :class="speechOrder[currentTurn]">
        <span>기존의 사고에서 벗어나 새로운 시각과 창의적인 아이디어 제시</span>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'RoleExplain',
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

.explain-box {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.explain-line {
  display: flex;
  gap: 4px;
}

.explain-line .explain-word {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 36px;
  padding: 2px 20px;
  margin: 0;
  border-radius: 8px;
}

.explain-1 {
  width: 362px;
}

.explain-2 {
  width: 248px;
}

.explain-3 {
  width: 424px;
}

.explain-4 {
  width: 612px;
}

.explain-5 {
  width: 426px;
}

.white-hat {
  background-color: white;
  color: black;
}

.red-hat {
  background-color: #EA4335;
  color: white;
}

.yellow-hat {
  background-color: #FBBC05;
  color: white;
}

.black-hat {
  background-color: black;
  color: white;
}

.green-hat {
  background-color: #34A853;
  color: white;
}

.blue-hat {
  background-color: #4285F4;
  color: white;
}
</style>