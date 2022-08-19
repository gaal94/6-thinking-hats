<template>
  <nav-bar v-if="!isInConferenceRoom"/>
  <router-view/>
  <main-footer v-if="!isInConferenceRoom"/>
  <background-wave v-if="!isInConferenceRoom"/>
</template>

<script>
import NavBar from "@/views/main/header/NavBar.vue"
import BackgroundWave from "@/views/main/background/BackgroundWave.vue"
import MainFooter from '@/views/main/footer/MainFooter.vue'
import { mapGetters } from 'vuex'

export default{
  name: 'App',
  components : {
    NavBar,
    BackgroundWave,
    MainFooter
  },
	computed: {
    ...mapGetters(['isInConferenceRoom',]),
	},
}
</script>

<style>
html, body, #app {
  width: 100%;
  height: 100%;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  position: absolute;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
