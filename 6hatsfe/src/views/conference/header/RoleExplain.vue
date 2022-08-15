<template>
  <!-- sixhats 모드, 파란모자 -->
  <div v-if="(hatMode === 'sixhats' && hatColor !== 'spectator') || hatColor === 'blue-hat'" class="explain-box">
    <div class="explain-line">
      <div class="explain-word explain-1"
      :class="hatColor">
        <span v-if="hatColor === 'blue-hat'">의견 요약, 의견 정리, 회의 진행</span>
        <span v-else-if="hatColor === 'red-hat'">~한 아이디어는 좋은 것 같습니다</span>
        <span v-else-if="hatColor === 'yellow-hat'">어떻게 구체화 될 수 있을까요?</span>
        <span v-else-if="hatColor === 'green-hat'">제가 생각한 아이디어는 ~ 입니다</span>
        <span v-else-if="hatColor === 'black-hat'">그 의견은 ~문제점을 지니고 있습니다</span>
        <span v-else-if="hatColor === 'white-hat'">현재 ~한 정보가 있습니다</span>
      </div>
      <div class="explain-word explain-2"
      :class="hatColor">
        <span v-if="hatColor === 'blue-hat'">냉정, 종합적인 사고</span>
        <span v-else-if="hatColor === 'red-hat'" style="font-size: 0.9766vw">~한 점은 별로인 것 같습니다</span>
        <span v-else-if="hatColor === 'yellow-hat'">어떠한 가치가 있나요?</span>
        <span v-else-if="hatColor === 'green-hat'">~에는 ~한 대안이 있습니다</span>
        <span v-else-if="hatColor === 'black-hat'" style="font-size: 0.9115vw">부정적인 사고로 위험요소 제시</span>
        <span v-else-if="hatColor === 'white-hat'">~에 대한 정보가 필요합니다</span>
      </div>
      <div class="explain-word explain-3"
      :class="hatColor">
        <span v-if="hatColor === 'blue-hat'">~주제에 ~의견이 나왔습니다</span>
        <span v-else-if="hatColor === 'red-hat'" style="font-size: 0.9115vw">깊이 생각하지 말고 생각나는 걸 바로바로 말해주세요</span>
        <span v-else-if="hatColor === 'yellow-hat'">그 계획은 ~한 이유로 실현 가능성이 충분합니다</span>
        <span v-else-if="hatColor === 'green-hat'">틀에서 벗어나 새로운 관점에서 바라봐주세요</span>
        <span v-else-if="hatColor === 'black-hat'" style="font-size: 0.9115vw">말씀하신 정보는 ~점을 봤을 때 잘못된 정보입니다</span>
        <span v-else-if="hatColor === 'white-hat'">객관적인 지식 만을 제시해야 합니다 </span>
      </div>
    </div>
    <div class="explain-line">
      <div class="explain-word explain-5"
      :class="hatColor">
        <span v-if="hatColor === 'blue-hat'">~모자는 ~의견을 내셨습니다 맞습니까?</span>
        <span v-else-if="hatColor === 'red-hat'">아이디어에 대한 첫 느낌은 어떤가요?</span>
        <span v-else-if="hatColor === 'yellow-hat'">그 의견은 ~한 면에서 좋을것 같습니다</span>
        <span v-else-if="hatColor === 'green-hat'">창의적인 아이디어와 대안을 생각해주세요</span>
        <span v-else-if="hatColor === 'black-hat'">제시된 아이디어의 실패 요인은 무엇일까요?</span>
        <span v-else-if="hatColor === 'white-hat'">논쟁을 하거나 의견을 제시하면 안됩니다</span>
      </div>
      <div class="explain-word explain-4"
      :class="hatColor">
        <span v-if="hatColor === 'blue-hat'">종합적으로 ~한 의견이 나왔습니다 모두 동의하십니까?</span>
        <span v-else-if="hatColor === 'red-hat'">정당한 이유나 근거 필요 없이 감정에 기반한 의견 제시</span>
        <span v-else-if="hatColor === 'yellow-hat'">본능보단 이성적으로 생각해서 말해주셔야 합니다</span>
        <span v-else-if="hatColor === 'green-hat'">기존의 사고에서 벗어나 새로운 시각과 창의적인 아이디어 제시</span>
        <span v-else-if="hatColor === 'black-hat'">~부분들은 단점이 될 수 있으므로 개선되어야 합니다</span>
        <span v-else-if="hatColor === 'white-hat'">정확한 정보 및 중립적이고 객관적인 의견 제시</span>
      </div>
    </div>
  </div>

  <!-- onehat 모드, 관전자 -->
  <div v-else-if="(hatMode === 'onehat' && hatColor !== 'blue-hat') || hatColor === 'spectator'" class="explain-box">
    <div class="explain-line">
      <div class="explain-word explain-1"
      :class="speechOrder[currentTurn]">
        <span v-if="speechOrder[currentTurn] === 'blue-hat'">의견 요약, 의견 정리, 회의 진행</span>
        <span v-else-if="speechOrder[currentTurn] === 'red-hat'">~한 아이디어는 좋은 것 같습니다</span>
        <span v-else-if="speechOrder[currentTurn] === 'yellow-hat'">어떻게 구체화 될 수 있을까요?</span>
        <span v-else-if="speechOrder[currentTurn] === 'green-hat'">제가 생각한 아이디어는 ~ 입니다</span>
        <span v-else-if="speechOrder[currentTurn] === 'black-hat'">그 의견은 ~문제점을 지니고 있습니다</span>
        <span v-else-if="speechOrder[currentTurn] === 'white-hat'">현재 ~한 정보가 있습니다</span>
      </div>
      <div class="explain-word explain-2"
      :class="speechOrder[currentTurn]">
        <span v-if="speechOrder[currentTurn] === 'blue-hat'">냉정, 종합적인 사고</span>
        <span v-else-if="speechOrder[currentTurn] === 'red-hat'" style="font-size: 0.9766vw">~한 점은 별로인 것 같습니다</span>
        <span v-else-if="speechOrder[currentTurn] === 'yellow-hat'">어떠한 가치가 있나요?</span>
        <span v-else-if="speechOrder[currentTurn] === 'green-hat'">~에는 ~한 대안이 있습니다</span>
        <span v-else-if="speechOrder[currentTurn] === 'black-hat'" style="font-size: 0.9115vw">부정적인 사고로 위험요소 제시</span>
        <span v-else-if="speechOrder[currentTurn] === 'white-hat'">~에 대한 정보가 필요합니다</span>
      </div>
      <div class="explain-word explain-3"
      :class="speechOrder[currentTurn]">
        <span v-if="speechOrder[currentTurn] === 'blue-hat'">~주제에 ~의견이 나왔습니다</span>
        <span v-else-if="speechOrder[currentTurn] === 'red-hat'" style="font-size: 0.9115vw">깊이 생각하지 말고 생각나는 걸 바로바로 말해주세요</span>
        <span v-else-if="speechOrder[currentTurn] === 'yellow-hat'">그 계획은 ~한 이유로 실현 가능성이 충분합니다</span>
        <span v-else-if="speechOrder[currentTurn] === 'green-hat'">틀에서 벗어나 새로운 관점에서 바라봐주세요</span>
        <span v-else-if="speechOrder[currentTurn] === 'black-hat'" style="font-size: 0.9115vw">말씀하신 정보는 ~점을 봤을 때 잘못된 정보입니다</span>
        <span v-else-if="speechOrder[currentTurn] === 'white-hat'">객관적인 지식 만을 제시해야 합니다 </span>
      </div>
    </div>
    <div class="explain-line">
      <div class="explain-word explain-5"
      :class="speechOrder[currentTurn]">
        <span v-if="speechOrder[currentTurn] === 'blue-hat'">~모자는 ~의견을 내셨습니다 맞습니까?</span>
        <span v-else-if="speechOrder[currentTurn] === 'red-hat'">아이디어에 대한 첫 느낌은 어떤가요?</span>
        <span v-else-if="speechOrder[currentTurn] === 'yellow-hat'">그 의견은 ~한 면에서 좋을것 같습니다</span>
        <span v-else-if="speechOrder[currentTurn] === 'green-hat'">창의적인 아이디어와 대안을 생각해주세요</span>
        <span v-else-if="speechOrder[currentTurn] === 'black-hat'">제시된 아이디어의 실패 요인은 무엇일까요?</span>
        <span v-else-if="speechOrder[currentTurn] === 'white-hat'">논쟁을 하거나 의견을 제시하면 안됩니다</span>
      </div>
      <div class="explain-word explain-4"
      :class="speechOrder[currentTurn]">
        <span v-if="speechOrder[currentTurn] === 'blue-hat'">종합적으로 ~한 의견이 나왔습니다 모두 동의하십니까?</span>
        <span v-else-if="speechOrder[currentTurn] === 'red-hat'">정당한 이유나 근거 필요 없이 감정에 기반한 의견 제시</span>
        <span v-else-if="speechOrder[currentTurn] === 'yellow-hat'">본능보단 이성적으로 생각해서 말해주셔야 합니다</span>
        <span v-else-if="speechOrder[currentTurn] === 'green-hat'">기존의 사고에서 벗어나 새로운 시각과 창의적인 아이디어 제시</span>
        <span v-else-if="speechOrder[currentTurn] === 'black-hat'">~부분들은 단점이 될 수 있으므로 개선되어야 합니다</span>
        <span v-else-if="speechOrder[currentTurn] === 'white-hat'">정확한 정보 및 중립적이고 객관적인 의견 제시</span>
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
  gap: 6px;
  margin: 0 auto;
}

.explain-line .explain-word {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 2.0833vw;
  padding: 2px 20px;
  margin: 0;
  border-radius: 0.5208vw;
  font-size: 1.0417vw;
}

.explain-1 {
  width: 23.3073vw;
}

.explain-2 {
  width: 16.1458vw;
}

.explain-3 {
  width: 26.3021vw;
}

.explain-4 {
  width: 40.1042vw;
}

.explain-5 {
  width: 26.0417vw;
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