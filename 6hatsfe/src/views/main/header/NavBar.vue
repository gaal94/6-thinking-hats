<template>
  <ul v-if="!loginstatus">
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
    <li><button @click = "logout" class="navbtn">로그아웃</button></li>
    <li><button @click = "createRoom" class="navbtn">방 생성</button></li>
    <li><router-link to="/conferencepage" class = "right">방 생성</router-link></li>
    <li><router-link to="/conferencepage" class = "right">방 참가</router-link></li>
    <li>{{this.$store.state.name}}님 어서오세요</li>
  </ul>
</template>

<script>
import { mapGetters } from "vuex"
export default {
  name : 'NavBar',
  methods: {
    logincheck() {
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
      localStorage.clear('username');
    },
    createRoom() {
      const sessionCode = Math.random().toString(22).substring(2,22);
      alert(sessionCode);
      this.$router.push({
        name: "RoomPage",
        params: {
          sessionCode: sessionCode
        }
      })
    }
  },
  computed: {
    ...mapGetters(['loginstatus','name'])
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
.navbtn{
  float : right;
  background-color: white;
  color: black;
  border: 0px;
  text-align: center;
  margin : 13px;

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