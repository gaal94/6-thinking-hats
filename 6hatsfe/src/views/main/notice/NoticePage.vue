<template>
  <div class="recgo">
    <header class="pagename">
    <h1>공지사항</h1>
    </header>
    <table class="table">
      <thead class="Rechead">
        <tr class="headrow">
          <th scope="col" style="width : 8%">글번호</th>
          <th scope ="col" style="width : 60%">제목</th>
          <th scope="col" >작성자</th>
          <th scope="col">작성일</th>
          <th scope="col">조회수</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(no,idx) in boards" :key ="idx" class="boardbody">
          <td scope="row">{{idx+1}}</td>
          <td><router-link :to ="{
              path: '/noticecontentspage/' + no.boardId
              }">
            {{no.title}}</router-link></td>
          <td>{{no.name}}</td>
          <td>{{no.boardCreatedAt}}</td>
          <td>{{no.views}}</td>
        </tr>
      </tbody>
    </table>
    <div class ="boardbtn">
      <button v-if ="userType==='ADMIN'" v-on:click="routeToWritePage" type="button" class="btn btn-primary">글쓰기</button>
    </div>
  </div>
</template>

<script>
import router from "@/router";
import interceptor from "@/api/interceptors";
import jwt_decode from "jwt-decode";

export default {
  name: 'NoticePage'
  ,
  data() {
    return {
      boards: { name: '', boardId: '', title: '', boardType: '', views: '',boardCreatedAt:'' },
      length: '', 
      userType:'',
    };
  }, 
  methods: {
    routeToWritePage(){
      router.push({ name: "NoticeWritePage" });
    }
  },
  mounted() {
    // Intercepotor 시작
    interceptor({
      url: '/board/notice',
      method: 'get'
    }).then((res) => {
      this.boards = res.data;
      this.length = res.data.length;
      console.log(res.data);
    }).catch((err) => {
      alert(err);
    });

    if (localStorage.getItem('access-token')){

      var decoded = jwt_decode(localStorage.getItem('access-token'));//token 디코드
      interceptor({
        url: '/user/' + decoded.userId,
        method: 'get'
      }).then((res) => {
        this.userType = res.data.user.userType;
      }).catch((err) => {
        alert(err);
      });
    
    }
  }
}
</script>

<style scoped lang="css" src="@/assets/css/views/main/notice/NoticePage.css">
</style>