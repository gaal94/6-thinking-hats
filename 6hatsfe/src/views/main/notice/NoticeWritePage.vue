<template>
  <div class="write-body">
		<header class="pagename">
    <h1>공지사항 작성</h1>
    </header>
    <div id="bar"></div>
		<div class="input-group">
  		<div class="input-group-prepend">
    		<span class="input-group-text" id="basic-addon1">제목</span>
  		</div>
  		<input v-model="board.title" type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
		</div>
		<div class="input-group h-75" >
  		<div class="input-group-prepend">
    		<span class="input-group-text">내용</span>
  		</div>
  		<textarea v-model="board.boardContents" class="form-control" aria-label="With textarea"></textarea>
		</div>
		<button v-on:click="PostBoard" type="button" class="btn btn-primary" style="float: right; margin:4px">글쓰기</button>
		<div>

		</div>
	</div>
</template>

<script>
import router from "@/router";
import interceptor from "@/api/interceptors";
export default {
  name: 'NoticeWritePage',
  data() {
    return {
      board: {
				title: '',
				boardContents: '',
				boardType: 'ntc'
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
							router.push({ name: "NoticePage" });
						}
          }).catch((err) => {
            alert(err);
						router.push({ name: "NoticePage" });
          });
			}
  },
}
</script>

<style scoped lang="css" src="@/assets/css/views/main/notice/NoticeWritePage.css">

</style>
