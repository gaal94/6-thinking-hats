<template>
  	<div class="write-body">
		<header class="pagename">
    	<h1>QnA 작성</h1>
    	</header>
		<div id="bar"></div>
			<div class="input-group">
  			<div class="input-group-prepend">
    			<span class="input-group-text" id="basic-addon1">제목</span>
  			</div>
  			<input v-model="board.title" type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
			</div>
			<div class="input-group" >
  			<div class="input-group-prepend">
    			<span class="input-group-text">내용</span>
  			</div>
  			<textarea v-model="board.boardContents" class="form-control" aria-label="With textarea" style="height:200px"></textarea>
		</div>
		<button v-on:click="PostBoard" type="button" class="btn btn-primary" style="float: right;">글쓰기</button>
	</div>
</template>

<script>
import router from "@/router";
import interceptor from "@/api/interceptors";
export default {
  name: 'QnaWritePage',
  data() {
    return {
      board: {
				title: '',
				boardContents: '',
				boardType: 'qna'
			}
    };
  }, 
  methods: {
      PostBoard() {
				interceptor({
            url: '/board',
            method: 'post',
						data: this.board,
          }).then((res) => {
            if(res.status == 201) {
							router.push({ name: "QnaPage" });
						}
          }).catch((err) => {
            alert(err);
						router.push({ name: "QnaPage" });
          });
			}
  },
}
</script>

<style scoped lang="css" src="@/assets/css/views/main/qna/QnaWritePage.css">

</style>
