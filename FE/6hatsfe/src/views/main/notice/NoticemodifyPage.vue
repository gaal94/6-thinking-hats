<template>
  <div class="write-body">
    <header class="pagename">
      <h1>공지사항 수정</h1>
    </header>
    <div id="bar"></div>
    <div class="input-group">
      <div class="input-group-prepend">
        <span class="input-group-text" id="basic-addon1">제목</span>
      </div>
      <input v-model="board.title" type="text" class="form-control" aria-label="Username"
        aria-describedby="basic-addon1">
    </div>
    <div class="input-group">
      <div class="input-group-prepend">
        <span class="input-group-text">내용</span>
      </div>
      <textarea v-model="board.boardContents" class="form-control" aria-label="With textarea"
        style="height:200px"></textarea>
    </div>
    <button v-on:click="modifyBoard" type="button" class="btn btn-primary" style="float: right; margin:4px">저장</button>
  </div>
</template>

<script>
import router from "@/router";
import interceptor from "@/api/interceptors";
export default {
  name: 'NoticemodifyPage',
  data() {
    return {
      boardId: '',
      board: {
        title: '',
        boardContents: '',
        boardType: 'ntc'
      }
    };
  },
  methods: {
    modifyBoard() {
      interceptor({
        url: '/board/' + this.boardId,
        method: 'patch',
        data: {
          title: this.board.title,
          boardContents: this.board.boardContents,
        },
      }).then((res) => {
        if (res.status == 201) {
          router.push({ path: '/noticecontentspage/' + this.boardId });
        }
      }).catch((err) => {
        alert(err);
        router.push({ name: "NoticePage" });
      });
    }
  },
  created() {
    this.boardId = this.$route.params.boardId;
    interceptor({
      url: '/board/notice/' + this.boardId,
      method: 'get',
    }).then((res) => {
      this.board = res.data;
    }).catch((err) => {
      alert(err);
      router.push({ path: "/" });
    });
  }
}

</script>

<style scoped lang="css" src="@/assets/css/views/main/notice/NoticemodifyPage.css">
</style>