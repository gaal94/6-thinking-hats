<template>
  <ul v-if="!token" class="navbarul">
    <li><router-link to="/"><img src = "@/assets/logoAlphabet.png" id="logo" href="/" class="navbarleft"></router-link></li>
    <li><router-link to="/noticepage" class="navbarleft link1">공지사항</router-link></li>
    <li><router-link to="/explainpage" class="navbarleft link1">설명</router-link></li>
    <li><router-link to="/teampage" class="navbarleft link1">팀소개</router-link></li>
    <li><router-link to="/qnapage" class="navbarleft link1">Q&A</router-link></li>
    <li><router-link to="/signuppage" class="navbarright link1">회원가입</router-link></li>
    <li><router-link to="/loginpage" class="navbarright link1">로그인</router-link></li>
  </ul>
  <ul v-else class="navbarul">
    <li><router-link to="/"><img src = "@/assets/logoAlphabet.png" id="logo" href="/"></router-link></li>
    <li><router-link to="/noticepage" class="navbarleft link1">공지사항</router-link></li>
    <li><router-link to="/explainpage" class="navbarleft link1">설명</router-link></li>
    <li><router-link to="/teampage" class="navbarleft link1">팀소개</router-link></li>
    <li><router-link to="/qnapage" class="navbarleft link1">Q&A</router-link></li>
    <li>
      <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
        {{name}}님
        </button>
        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
          <li><div id="navbarprofilebox">
            <img :src="image" id="navbarprofileimg">
          </div></li>
          <li><router-link to="/profilepage" class = "dropdown-item link1">내정보</router-link></li>
          <li><router-link to="/historypage" class = "dropdown-item link1">회의이력</router-link></li>
          <li><button class="dropdown-item link1" id= "logoutbtn" @click = "logout">로그아웃</button></li>
        </ul>
      </div>
    </li>
    <li id = "createroom" @click="createRoom" class = "navbarright link1">방 생성</li>
  </ul>
</template>

<script>
import interceptor from "@/api/interceptors";
import { mapGetters } from "vuex"

export default {
  name : 'NavBar',
  data() {
    return {
      image: 'https://i7a709.p.ssafy.io:8081/file/image?profileImageUrl=basic',
    }
  },
  methods: {
    logout() {
      localStorage.clear();
      this.$store.commit('ChangeToken', '');
      this.$router.push('/')
    },
    createRoom() {
      const sessionId = Math.random().toString(22).substring(2,22);
      interceptor({
        url: '/room',
        method: 'post',
        data: {
          sessionId: sessionId
        }
      })
      .then((res) => {
          console.log(res);
          
          this.$router.push({
          name: "ConferencePage",
          params: {
            sessionCode: sessionId
          }
          
        })
      })
      .catch((err) => {
        alert(err);
      })
    }
  },
  computed: {
    ...mapGetters(['loginstatus','name','token'])
  },
  created() {
    this.image = 'https://i7a709.p.ssafy.io:8081/file/image?profileImageUrl=' + (localStorage.getItem("profileImageUrl")? localStorage.getItem("profileImageUrl"): "basic");
    this.$store.commit("ChangeName", localStorage.getItem('username'));
  }


}
</script>

<style lang="css" scoped>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap');
/* #logo:hover{
  background-color: white;
} */
#logo{
  width : 60px;
  height: 60px;
  float : left;
  margin-top : 5px;
  margin-left: 10px;
  margin-right: 10px;
  padding:0px;
}
#dropdown-menu{
  float: right;
}
.navbarul {
  font-family: 'Noto Sans KR', sans-serif;
  border-bottom: 1px solid #939393;
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: visible;
  background-color: white;
  position: sticky;
  top: 0;
  box-sizing: border-box;
  height: 72px;
}
.navbarleft {
    color: black;
    text-decoration: none;
  padding: 14px 16px;
  margin : 8px;
  float : left;
}
.navbarright{
    color: black;
    text-decoration: none;
  padding: 14px 16px;
    margin : 8px;
  float : right;
}
#dropdownMenuButton1{
  float: right;
  margin: 16px;
  color:black;
  background-color: white;
  border: 0px;
}
#navbarprofilebox{
    margin:auto;
    width: 60px;
    height: 60px; 
    border-radius: 70%;
    overflow: hidden;
    margin-bottom: 5px;
}
#navbarprofileimg{
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.link1::before {
  content: '[';
  opacity: 0;
  margin-left: 10px;
  transition: all 0.5s;
}

.link1::after {
  content: ']';
  opacity: 0;
  margin-right: 10px;
  transition: all 0.5s;
}

.link1:hover::before {
  opacity: 1;
  margin-left: 0;
  margin-right: 10px;
}

.link1:hover::after {
  opacity: 1;
  margin-left: 0;
  margin-left: 10px;
}
</style>