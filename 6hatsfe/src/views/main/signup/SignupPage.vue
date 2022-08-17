<template>
  <div>
      <h2>회원가입</h2>
        <div id="myprofilebox">
            <img :src="image" id="myprofileimg"/>
        </div>
        <a>
            <i class='bx bxs-camera-plus' @click="Profileupbtn"></i>
        </a>
        
        <input id="customFile" type="file" @change="handleFileChange">
        <br>
        Email
        <input placeholder="입력해주세요" v-model="user.email">
        <br>
        비밀번호
        <input placeholder="입력해주세요" v-model="user.password" type="password">
        <br>
        비밀번호 확인
        <input placeholder="입력해주세요" type="password">
        <br>
        이름
        <input placeholder="입력해주세요" v-model="user.name">
        <br>
        성별
        <input type="radio" v-model="user.gender" value="MAN">남
        <input type="radio" v-model="user.gender" value="WOMAN">여
        <br>
        생년월일
        <input type="date" v-model="user.birth"/>
        <br>
        직업
        <select v-model="user.job">
  <option value="OFFICE">직장인</option>
  <option value="STUDENT" selected>학생</option>
  <option value="JOBLESS">무직</option>
  <option value="OTHER">기타</option>
</select>
    <br>
        <button type="submit" v-on:click="SignupUser">회원가입</button>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import axios from "axios";

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
        name:'',
        image: '',
      },
      image: require('@/assets/melong.jpg'),
    };
  },
  methods: {
    SignupUser() { // user 회원가입 함수
      const frm = new FormData();
		  frm.append('name', this.user.name);
      frm.append('profileImage', this.user.image);
      frm.append('email', this.user.email);
      frm.append('password', this.user.password);
      frm.append('gender', this.user.gender);
      frm.append('birth', this.user.birth);
      frm.append('job', this.user.job);

      console.log(frm.get('name'));

      axios.post('http://localhost:8081/user', frm, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then((res) => {
                // 응답 처리
                console.log(res);
            })
            .catch((err) => {
                // 예외 처리
                alert(err);
            })
    },
    handleFileChange(e) {
			const image = e.target.files[0];
      this.user.image = image;
			const url = URL.createObjectURL(image);
			this.image = url;
    },
  },
  computed:{
    ...mapGetters(['token'])
  }
}
</script>
<style>

#myprofilebox {
    margin:auto;
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
</style>