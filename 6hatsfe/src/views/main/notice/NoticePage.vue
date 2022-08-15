<template>
  <div id="recgo">
    <header class="pagename">
    <h1>공지사항</h1>
    </header>
  <table class="table">
  <thead class="Rechead">
    <tr class="headrow">
      <th scope="col">글번호</th>
      <th scope ="col" colspan="4">제목</th>
      <th scope="col">작성자</th>
      <th scope="col">작성일</th>
      <th scope="col">조회수</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(no,idx) in boards" :key ="idx" class="boardbody">
      <td scope="row">{{idx+1}}</td>
      <td colspan="4"><router-link :to ="{
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
   <button v-if ="userType==='ADMIN'" v-on:click="routeToWritePage" type="button" class="btn btn-primary" id="boardwritingbtn">글쓰기</button>
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
        }).catch((err) => {
          alert(err);
        });
          var decoded = jwt_decode(localStorage.getItem('access-token'));//token 디코드
    interceptor({
          url: '/user/' + decoded.userId,
          method: 'get'
    }).then((res) => {
        this.userType = res.data.user.userType;
        }).catch((err) => {
          alert(err);
        });
    interceptor({
          url: '/board/notice',
          method: 'get'
        }).then((res) => {
          this.boards = res.data;
          this.length = res.data.length;
        }).catch((err) => {
          alert(err);
        });
                
  }
}
</script>

<style>
#recgo{
  position :relative;
  width:60%;
  margin:auto;
  background-color: white;
}
.pagename {
  position: relative;
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
h1, p {
  margin: 0;
}
.Rechead{
  background: #4285F4;
  color:#FFFFFF;
}
.table{
  font-size: 14px;
}
.boardbody:hover{
  background-color: #C7C6C6;
}
.boardbtn{
  position: absolute;
  width : 100%;
}
#boardwritingbtn{
  right :0;
  position: absolute;
}
</style>