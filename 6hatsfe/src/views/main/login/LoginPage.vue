<template>
  <div>
      <h2>Log In</h2>
          <input placeholder="ID" v-model="user.email">
          <br>
          <input placeholder="password" v-model="user.password">
          <button type="submit" v-on:click="getUserInfo">Login</button>
  </div>
</template>
<script>
import http from "@/api/http";

export default {
  data() {
    return {
      user: {
        email: '',
        password: '',
      }
    };
  },
  methods: {
    getUserInfo() {
      console.log(this.user);
      alert("");
      http
        .post("/user/login", null, { params: this.user })
        .then((data) => {
          alert();
          localStorage.setItem("access-token", data.data["access-token"]);//access-token 로컬 스토리지에 저장
        })
        .catch((err) => {
          alert(err);
        });
    }
  }
}
</script>

<style>

</style>