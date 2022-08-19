<template>
  <div id="signupdiv">
    <header class="pagename">
    <h1>회원가입</h1>
    </header>
    <div id="bar"></div>
    <div id="myprofilebox">
      <img :src="image" id="myprofileimg" />
    </div>
    <a>
      <i class='bx bxs-camera-plus' @click="Profileupbtn"></i>
    </a>
    <input id="customFile" type="file" @change="handleFileChange">
    <br>
    <table>
      <tr>
        <td>Email</td>
        <td><input placeholder="입력해주세요" v-model="user.email"></td>
      </tr>
      <tr>
        <td>비밀번호</td>
        <td><input placeholder="입력해주세요" v-model="user.password" type="password"></td>
      </tr>
      <tr>
        <td>비밀번호 확인</td>
        <td><input placeholder="입력해주세요" type="password"></td>
      </tr>
      <tr>
        <td>이름</td>
        <td><input placeholder="입력해주세요" v-model="user.name"></td>
      </tr>
      <tr>
        <td>성별</td>
        <td> <input type="radio" v-model="user.gender" value="MAN">남
          <input type="radio" v-model="user.gender" value="WOMAN">여
        </td>
      </tr>
      <tr>
        <td>생년월일</td>
        <td><input type="date" v-model="user.birth" /></td>
      </tr>
      <tr>
        <td>직업</td>
        <td> <select v-model="user.job">
            <option value="OFFICE">직장인</option>
            <option value="STUDENT" selected>학생</option>
            <option value="JOBLESS">무직</option>
            <option value="OTHER">기타</option>
          </select></td>
      </tr>
    </table>
    <br>
    <button v-on:click="SignupUser" type="button" class="btn btn-primary">회원가입</button>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";
import http from "@/api/http";
import router from "@/router";
export default {
  name: 'SignupPage',
  data() {
    return {
      user: {
        email: '',
        password: '',
        gender: '',
        birth: '',
        job: '',
        name: '',
        image: '',
        profileImageUrl: 'basic',
      },
      image:'https://i7a709.p.ssafy.io:8081/file/image?profileImageUrl=basic',
    };
  },
  methods: {
    SignupUser() { // user 회원가입 함수
      const frm = new FormData();
      if(this.image != 'https://i7a709.p.ssafy.io:8081/file/image?profileImageUrl=basic'){
        frm.append('image', this.user.image);

        axios.post('https://i7a709.p.ssafy.io:8081/file/image', frm, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        })
        .then((res) => {
          // 응답 처리
          this.user.profileImageUrl = res.data.fileName;
          this.postUser();
        })
        .catch((err) => {
          // 예외 처리
          alert(err);
        })
      } else {
        this.postUser();
      }
    },
    handleFileChange(e) {
      const image = e.target.files[0];
      this.user.image = image;
      const url = URL.createObjectURL(image);
      this.image = url;
    },
    postUser() {
      http
        .post("/user", this.user)
        .then((data) => {
          console.log(data);
          alert("회원가입에 성공하셨습니다!")
          router.push({ name: "LandingPage" });
        })
        .catch((err) => {
          alert(err);
          router.push({ name: "LandingPage" });
        });

    }
  },
  computed: {
    ...mapGetters(['token'])
  }
}
</script>
<style>
#signupdiv {
  width: 60%;
  margin: auto;
  background: rgba(255, 255, 255, 0.7);
  height: calc(100% - 72px);
  font-size: small;
}

#myprofilebox {
  margin: auto;
  width: 200px;
  height: 200px;
  border-radius: 70%;
  overflow: hidden;

}

#myprofileimg {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
#signupdiv table{
  margin:auto;
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
</style>