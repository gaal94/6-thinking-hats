<template>
  <div class="speech-order-box">
    <div v-for="(hat, idx) in speechOrder" :key="`hat-order-${idx}`"
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
    ...mapGetters(['speechOrder', 'currentTurn',]),
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
  min-height: 80px;
}

.order {
  width: 20px;
  height: 20px;
  border-radius: 50%;
}

.cnt-order {
  width: 54px;
  height: 54px;
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