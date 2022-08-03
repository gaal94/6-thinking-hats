<template>
  <div>
      <h2>회원가입</h2>
      <form @submit="onSubmit">
        Email
        <input placeholder="입력해주세요" v-model="email">
        <br>
        비밀번호
        <input placeholder="입력해주세요" v-model="password">
        <br>
        비밀번호 확인
        <input placeholder="입력해주세요">
        <br>
        성별
        <input type="radio" v-model="gender" value="남">남
        <input type="radio" v-model="gender" value="여">여
        <br>
        생년월일
        <input type="date" v-model="birth"/>
        <br>
        직업
        <select v-model="job">
  <option value="직장인">직장인</option>
  <option value="학생" selected>학생</option>
  <option value="무직">무직</option>
  <option value="direct">기타</option>
</select>
<input type="text" id="selboxDirect" name="selboxDirect"/>
        <button type="submit">회원가입</button>
      </form>
  </div>
</template>

<script>
export default {
    name: 'SignupPage',
    data: () => ({
        email: '',
        password: '',
        gender: '',
    
  }),
  methods: {
    onSubmit () {
      console.log(this.email)
      console.log(this.password)
          console.log(this.gender)
          console.log(this.birth)
          console.log(this.job)
      alert("")
    }
  }

}
</script>

<style>
div{
    border : 1px solid black;
}
</style>