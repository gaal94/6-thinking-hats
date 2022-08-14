<template>
  <div id="recgo">
    <header class="pagename">
    <h1>녹화 파일</h1>
    </header>
  <table class="table">
  <thead class="Rechead">
    <tr class="headrow">
      <th scope="col">#</th>
      <th colspan="2">비디오 파일 다운로드</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for ="(no,idx) in roomVideos" :key="idx">
      <td scope="row">{{idx+1}}</td>
      <td colspan="2" v-if="no.videoValid">
        <router-link to="/recpage">{{no.videoFileUrl}}</router-link>
      </td>
      <td v-else>{{no.videoFileUrl}}</td>
    </tr>
  </tbody>
</table>
</div>
</template>

<script>
import interceptor from "@/api/interceptors";
import jwt_decode from "jwt-decode";

export default {
  name: 'RecPage',
  data() {
    return {
      roomVideos:{
        videoFileUrl: '',
        videoValid:''
      }
    }
  }
  ,
     created() {
          var token=localStorage.getItem('access-token');
          var decoded = jwt_decode(token);//token 디코드
          this.$store.commit('ChangeId',decoded.userId);

          // Intercepotor 시작
          interceptor({
            url: '/room/' + 1 +'/videos',
            method: 'get'
          }).then((res) => {
            this.roomVideos = res.data.roomVideos;
            console.log(this.roomVideos);
          }).catch((err) => {
            alert(err);
          });
  }

}
</script>

<style>
#recgo{
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
</style>