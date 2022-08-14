<template>
  <nav-bar v-if="!publisher"/>
  <router-view/>
  <background-wave v-if="!publisher"></background-wave>
</template>

<script>
import NavBar from "@/views/main/header/NavBar.vue"
import BackgroundWave from "@/views/main/background/BackgroundWave.vue"
import { mapGetters } from 'vuex'

export default{
  name: 'App',
  components : {
    NavBar,
    BackgroundWave
  },
  data() {
    return {

    }
  },
  computed: {
    ...mapGetters(['publisher', ])
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
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
