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
    <tr>
      <td scope="row">1</td>
      <td colspan="2">YY : MM : DD : HH : MM</td>
      <td>1h 30m</td>
      <td>다운로드</td>
      <td>녹화파일보기</td>
    </tr>
    <tr>
      <td scope="row">2</td>
      <td colspan="2">YY : MM : DD : HH : MM</td>
      <td>1h 30m</td>
      <td>다운로드</td>
      <td>녹화파일보기</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td colspan="2">YY : MM : DD : HH : MM</td>
      <td>1h 30m</td>
      <td>다운로드</td>
      <td>녹화파일보기</td>
    </tr>
  </tbody>
</table>
</div>
</template>

<script>
export default {
    name : 'HistoryPage'

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
  font-size: 20px;
}

</style>