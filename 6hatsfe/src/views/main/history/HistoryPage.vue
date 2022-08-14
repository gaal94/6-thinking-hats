<template>
  <div id="recgo">
    <header class="pagename">
    <h1>회의이력</h1>
    </header>
  <table class="table">
  <thead class="Rechead">
    <tr class="headrow">
      <th scope="col">#</th>
      <th colspan="2">회의생성일자</th>
      <th scope="col">진행시간</th>
      <th scope="col">회의록</th>
      <th scope="col">녹화파일</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for ="(no,idx) in rooms" :key="idx">
      <td scope="row">{{idx+1}}</td>
      <td colspan="2">{{conversionTime(no.roomStartTime)}}</td>
      <td>{{timeGapcal(no.roomStartTime,no.roomEndTime)}}</td>
      <td>다운로드</td>
      <td><router-link to="/recpage">녹화파일</router-link></td>
    </tr>
  </tbody>
</table>
<a @click="conversionTime">함수테스트</a>
</div>
</template>

<script>
import interceptor from "@/api/interceptors";
import jwt_decode from "jwt-decode";
export default {
  name: 'HistoryPage'
  ,
  data() {
    return {
      rooms: {
        creator: true,
        opinionFileUrl: "",
        opinionFileValid: '',
        roomEndTime: '',
        roomId: '',
        roomStartTime: '',
        userId: ''
      }
    }
  }
  , 
    methods: {
      timeRange() {
        this.rooms.roomEndTime
      },
      conversionTime(value) {
        if (typeof value === "string") { 
        let constartTime = value.slice(0, 16);
        constartTime = constartTime.replace(/\D/g, ':');
        return constartTime;
        }
      },
      timeGapcal(start,end) {
        var fromTime = new Date(start);
        var toTime = new Date(end);

        var differenceTravel = toTime.getTime() - fromTime.getTime();
        var hours = Math.floor((differenceTravel) / (1000 * 60 * 60));
        var minute = Math.floor((differenceTravel) / (1000 * 60));

        let value= hours%60+"시"+minute+"분"
        return value;
      }
      
  },
  created() {
          var token=localStorage.getItem('access-token');
          var decoded = jwt_decode(token);//token 디코드
          this.$store.commit('ChangeId',decoded.userId);

          // Intercepotor 시작
          interceptor({
            url: '/user/' + decoded.userId +'/rooms',
            method: 'get'
          }).then((res) => {
            this.rooms = res.data.rooms;
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