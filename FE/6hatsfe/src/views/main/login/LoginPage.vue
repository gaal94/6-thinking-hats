<template>
  <div id="logindiv">
  <header class="pagename">
    <h1>로그인</h1>
  </header>
    <div id="bar"></div>
    <div class="logincontent">
    <table>
      <tr>
        <td>Email : </td>
        <td><input placeholder="입력해주세요" v-model="user.email"></td>
      </tr>
      <tr>
        <td>비밀번호 : </td>
        <td><input placeholder="입력해주세요" v-model="user.password" type="password"></td>
      </tr>
    </table>
    <br>
    <!-- <a
      href="https://kauth.kakao.com/oauth/authorize?client_id=519439ce954029ab868883d1f092d2dc&redirect_uri=http://localhost:8080/kakaologinpage&response_type=code">
      KAKAO LOGIN
    </a>
    <a href="">
      GOOGLE LOGIN
    </a> -->
    <button type="button" v-on:click="getUserToken" class="btn btn-primary">Login</button>
  </div>

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
        .post("/user/login", this.user)
        .then((data) => {
          localStorage.setItem("access-token", data.data["access-token"]);//access-token 로컬 스토리지에 저장
          this.$store.commit('ChangeToken', data.data["access-token"]);
          this.$store.commit('ChangeLoginstatus', true);
          var token = localStorage.getItem('access-token');
          var decoded = jwt_decode(token);//token 디코드
          this.$store.commit('ChangeId', decoded.userId);

          // Intercepotor 시작
          interceptor({
            url: '/user/' + decoded.userId,
            method: 'get'
          }).then((res) => {
            localStorage.setItem("profileImageUrl", res.data.user.profileImageUrl);
            localStorage.setItem("username", res.data.user.name);
            //this.$store.commit('ChangeName', res.data.user.name);
            location.reload();
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
  computed: {
  }
}

</script>

<style>
#logindiv {
  width: 40%;
  margin: auto;
  background: rgba(255, 255, 255, 0.7);
  height: calc(100% - 72px);
}
h1, p {
    margin: 0;
}
.pagename {
    top: 0;
    left: 0;
    right: 0;
    width: 100%;
    height: 75px;
    padding: 1rem;
    color:black;
    /* background: #C1EFFF; */
    font-weight: bold;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
#bar{
  width: 100%;
  height: 37.5px;
  background-color: #4285F4;
  margin-bottom: 2%;
}
.logincontent table{
  margin :auto;
  position:relative;
}
</style>