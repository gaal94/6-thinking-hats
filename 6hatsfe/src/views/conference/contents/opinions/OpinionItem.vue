<template>
  <div class="opinion-item-box" :class="opinion.hatColor">
    <img v-if="opinion.hatColor === 'red-hat'" src="@/assets/redhat_circle.png" alt="" class="opinion-item-hatface">
    <img v-else-if="opinion.hatColor === 'yellow-hat'" src="@/assets/yellowhat_circle.png" alt="" class="opinion-item-hatface">
    <img v-else-if="opinion.hatColor === 'green-hat'" src="@/assets/greenhat_circle.png" alt="" class="opinion-item-hatface">
    <img v-else-if="opinion.hatColor === 'blue-hat'" src="@/assets/bluehat_circle.png" alt="" class="opinion-item-hatface">
    <img v-else-if="opinion.hatColor === 'black-hat'" src="@/assets/blackhat_circle.png" alt="" class="opinion-item-hatface">
    <img v-else-if="opinion.hatColor === 'white-hat'" src="@/assets/whitehat_border_circle.png" alt="" class="opinion-item-hatface">
    <div class="user-info">
      <p class="username">{{ opinion.userName }}</p>
      <p class="hatname">
        <span v-if="opinion.hatColor === 'red-hat'">빨간모자</span>
        <span v-else-if="opinion.hatColor === 'yellow-hat'">노란모자</span>
        <span v-else-if="opinion.hatColor === 'green-hat'">초록모자</span>
        <span v-else-if="opinion.hatColor === 'blue-hat'">파란모자</span>
        <span v-else-if="opinion.hatColor === 'white-hat'">하얀모자</span>
        <span v-else-if="opinion.hatColor === 'black-hat'">검은모자</span>
      </p>
    </div>
    <span class="opinion-word">{{ opinion.content }}</span>
    <i class='bx bx-x delete-icon' v-if="myHat === 'blue-hat'"
    @click="clickDeleteOpinion(opinionIndex)"></i>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'OpinionItem',
  components: {
	},
  props: {
    opinion: Object,
    opinionIndex: Number,
  },
	data: () => {
		return {
		}
	},
	computed: {
    ...mapGetters(['myHat', 'session',]),
	},
	methods: {
    clickDeleteOpinion(opIdx) {
      if (opIdx > -1) {
        this.session.signal({
          data: String(opIdx),
          type: 'delete-opinion',
        })
      }
    },
  }
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

p {
  margin: 0;
}

.opinion-item-box {
  display: flex;
  align-items: center;
  width: 65.1042vw;
  gap: 8px;
  border-radius: 3.2552vw;
  padding: 2px;
}

.opinion-item-hatface {
  width: 2.6042vw;
  height: 2.6042vw;
  margin-left: 4px;
}

.username {
  font-size: 0.9115vw;
}

.hatname {
  margin-right: 14px;
  font-size: 0.9115vw;
}

.delete-icon {
  font-size: 24px;
  color: rgb(240, 63, 63);
  margin-left: auto;
  margin-right: 12px;
}

.delete-icon:hover {
  cursor: pointer;
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

</style>