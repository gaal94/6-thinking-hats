<template>
  <div>
    <router-link to="/"><img src = "@/assets/logoAlphabet.png"></router-link>
    <router-link to="/noticepage">공지사항</router-link>
    <router-link to="/explainpage">설명</router-link>
    <router-link to="/teampage">팀소개</router-link>
    <router-link to="/qnapage">Q&A</router-link>
    <router-link to="/signuppage">회원가입</router-link>
    <router-link to="/loginpage">로그인</router-link>
  </div>
</template>

<script>
export default {
  name : "NavBar"
}
</script>

<style>
img{
  width : 60px;
  height: 60px;
  float: left;
  margin-left: 10px;
}
div{
  bolder : 1px solid black;
}
</style>