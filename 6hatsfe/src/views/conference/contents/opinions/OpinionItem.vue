<template>
  <div class="opinion-item-box" :class="hatColor">
    <img v-if="hatColor === 'red-hat'" src="@/assets/redhat_circle.png" alt="" class="opinion-item-hatface">
    <img v-else-if="hatColor === 'yellow-hat'" src="@/assets/yellowhat_circle.png" alt="" class="opinion-item-hatface">
    <img v-else-if="hatColor === 'green-hat'" src="@/assets/greenhat_circle.png" alt="" class="opinion-item-hatface">
    <img v-else-if="hatColor === 'blue-hat'" src="@/assets/bluehat_circle.png" alt="" class="opinion-item-hatface">
    <img v-else-if="hatColor === 'black-hat'" src="@/assets/blackhat_circle.png" alt="" class="opinion-item-hatface">
    <img v-else-if="hatColor === 'white-hat'" src="@/assets/whitehat_border_circle.png" alt="" class="opinion-item-hatface">
    <div class="user-info">
      <p class="username">권수린</p>
      <p class="hatname">
        <span v-if="hatColor === 'red-hat'">빨간모자</span>
        <span v-else-if="hatColor === 'yellow-hat'">노란모자</span>
        <span v-else-if="hatColor === 'green-hat'">초록모자</span>
        <span v-else-if="hatColor === 'blue-hat'">파란모자</span>
        <span v-else-if="hatColor === 'white-hat'">하얀모자</span>
        <span v-else-if="hatColor === 'black-hat'">검은모자</span>
      </p>
    </div>
    <span class="opinion-word">{{ message }}</span>
    <i class='bx bx-x delete-icon' v-if="hatColor === 'blue-hat'"
    @click="deleteMessage"></i>
  </div>
</template>

<script>
export default {
  name: 'OpinionItem',
  components: {
	},
  props: {
    message: String,
    hatColor: String,
  },
	data: () => {
		return {
		}
	},
	computed: {
	},
	methods: {
    deleteMessage() {
      this.$emit('deleteMessage')
    }
	},
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
  gap: 8px;
  border-radius: 50px;
  height: 52px;
}

.opinion-item-hatface {
  width: 44px;
  height: 44px;
  margin-left: 4px;
}

.username {
  font-size: 14px;
}

.hatname {
  margin-right: 14px;
  font-size: 16px;
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