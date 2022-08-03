<template>
  <div>
      <h2>Log In</h2>
      <form @submit="onSubmit">
          <input placeholder="ID" v-model="uid">
          <input placeholder="password" v-model="password">
          <button type="submit">Login</button>
      </form>
  </div>
</template>
<script>
export default {
    name : 'LoginPage',
    data: () => ({
    uid: '',
    password: ''
  }),
  methods: {
    onSubmit () {
      console.log(this.uid)
      console.log(this.password)
    }
  }
}
</script>

<style>

</style>