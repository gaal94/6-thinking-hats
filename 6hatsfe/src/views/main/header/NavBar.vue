<template>
  <ul v-if="logincheck">
    <li><router-link to="/"><img src = "@/assets/logoAlphabet.png" id="logo" href="/"></router-link></li>
    <li><router-link to="/noticepage" class = "left">공지사항</router-link></li>
    <li><router-link to="/explainpage" class = "left">설명</router-link></li>
    <li><router-link to="/teampage" class = "left">팀소개</router-link></li>
    <li><router-link to="/qnapage" class = "left">Q&A</router-link></li>
    <li><router-link to="/signuppage" class = 'right'>회원가입</router-link></li>
    <li><router-link to="/loginpage" class = "right">로그인</router-link></li>
    <li><router-link to="/profilepage" class = "right">내정보</router-link></li>
  </ul>
  <ul v-else>
    <li><router-link to="/"><img src = "@/assets/logoAlphabet.png" id="logo" href="/"></router-link></li>
    <li><router-link to="/noticepage" class = "left">공지사항</router-link></li>
    <li><router-link to="/explainpage" class = "left">설명</router-link></li>
    <li><router-link to="/teampage" class = "left">팀소개</router-link></li>
    <li><router-link to="/qnapage" class = "left">Q&A</router-link></li>
    <li><router-link to="/loginpage" class = "right">방 생성</router-link></li>
    <li><router-link to="/conferencepage" class = "right">방 참가</router-link></li>
    <li><button @click = "logout">로그아웃</button></li>
  </ul>
</template>

<script>
import { mapGetters } from "vuex"
export default {
  name : 'NavBar',
  data(){
    return{
    }
  },
  state:{},
  
  methods: {
    logincheck() {
      return this.$store.loginstatus;
    },
    logout() {
      this.$store.commit('ChangeName',null);
      this.$store.commit('ChangeJob',null);
      this.$store.commit('ChangeBirth',null);
      this.$store.commit('ChangeEmail',null);
      this.$store.commit('ChangeGender', null);
      this.$store.commit('ChangeLoginstatus', false);
      this.$store.commit('ChangeToken', null);
      this.$store.commit('ChangeId', null);
      localStorage.clear('access-token');
    }
  },
  computed: {
    ...mapGetters['loginstatus','token']
  }
}
</script>

<style>
#logo:hover{
  background-color: white;
}
img{
  width : 60px;
  height: 60px;
  float : left;
  margin : 5px;
}
.left {
  padding: 14px 16px;
  padding : 10px;
  float : left;
}
.right{
  padding: 14px 16px;
  float : right;
}
ul {
  border-bottom: 1px solid black;
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: white;
  position: sticky;
  top: 0;
  width: 100%;

}

li a {
  display: block;
  color: black;
  text-align: center;
  text-decoration: none;
}
li {
  display: inline;
}
li a:hover {
  background-color: black;
  color: white;
}
</style>