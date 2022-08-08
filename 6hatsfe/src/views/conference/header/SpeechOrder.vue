<template>
  <div class="speech-order-box">
    <div v-for="(hat, idx) in ideaMode" :key="`hat-order-${idx}`"
    class="order" 
    :class="{ 'cnt-order' : idx === currentTurn,
    'red-hat' : hat === 'red-hat',
    'yellow-hat' : hat === 'yellow-hat',
    'green-hat' : hat === 'green-hat',
    'blue-hat' : hat === 'blue-hat',
    'white-hat' : hat === 'white-hat',
    'black-hat' : hat === 'black-hat'}"></div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'SpeechOrder',
  components: {
	},
	data: () => {
		return {
		}
	},
	computed: {
    ...mapGetters(['ideaMode', 'currentTurn',]),
	},
	methods: {
	},
}
</script>

<style scoped>
* {
  box-sizing: border-box;
}

.speech-order-box {
  display: flex;
  align-items: center;
  gap: 12px;
}

.order {
  width: 24px;
  height: 24px;
  border-radius: 50%;
}

.cnt-order {
  width: 60px;
  height: 60px;
  border-radius: 50%;
}

.white-hat {
  background-color: white;
}

.red-hat {
  background-color: #EA4335;
}

.yellow-hat {
  background-color: #FBBC05;
}

.black-hat {
  background-color: black;
}

.green-hat {
  background-color: #34A853;
}

.blue-hat {
  background-color: #4285F4;
}
</style>