<template>
  <div>
      <h2>로그인</h2>
          <input placeholder="ID" v-model="user.email" >
          <br>
          <input placeholder="password" v-model="user.password" type = "password">
          <button type="submit" v-on:click="getUserToken">Login</button>
          <br>
          <a href="https://kauth.kakao.com/oauth/authorize?client_id=519439ce954029ab868883d1f092d2dc&redirect_uri=http://localhost:8080/kakaologinpage&response_type=code">
            KAKAO LOGIN
          </a>
          <br>
          <a href="">
            GOOGLE LOGIN
          </a>
  </div>
</template>
<script>
import http from "@/api/http";
import interceptor from "@/api/interceptors";
import jwt_decode from "jwt-decode";

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
    getUserToken() {
      http
        .post("/user/login", this.user )
        .then((data) => {
          localStorage.setItem("access-token", data.data["access-token"]);//access-token 로컬 스토리지에 저장
          this.$store.commit('ChangeToken')
          this.$store.commit('ChangeLoginstatus', true);
          var token=localStorage.getItem('access-token');
          var decoded = jwt_decode(token);//token 디코드
          
          // Intercepotor 시작
          interceptor({
            url: '/user/' + decoded.userId,
            method: 'get'
          }).then((res) => {
            console.log(res.data.user.name);
            alert(res.data.user.name);
          }).catch((err) => {
            alert(err);
          });
          // Intereceptor 끝
          this.$router.push('/') // 홈 화면 이동
        }).catch((err) => {
          alert(err);
        })

    }


},
  computed:{
  }}

</script>

<style>

</style>