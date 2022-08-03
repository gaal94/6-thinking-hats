<template>
  <div>
    <router-link to="/"><img src = "@/assets/logoAlphabet.png"></router-link>
    <router-link to="/noticepage" class = "left">공지사항</router-link>
    <router-link to="/explainpage" class = "left">설명</router-link>
    <router-link to="/teampage" class = "left">팀소개</router-link>
    <router-link to="/qnapage" class = "left">Q&A</router-link>
    <router-link to="/signuppage" class = 'right'>회원가입</router-link>
    <router-link to="/loginpage" class = "right">로그인</router-link>
  </div>
  <hr>
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
  float : left;
  margin-left: 10px;
}
div{
  height: 72px;
}
a {
  text-decoration: none;
  margin : 4px;

}
.left {
  padding : 10px;
  float : left;
}
.right{
  float : right;
}
</style>