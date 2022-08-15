<template>
<div class="main-notice-contents">

    <h2 style="padding:15px">QnA</h2>

    <hr style="height:50px;border-width:0;color:black; background-color:black; z-index:5">
    <div style="margin-left:20px">
        <table class="main-contents" style="width:100%; ">
            <tr>
                <td style="width:60%;">제목 : {{title}}</td>
                <td>작성일시 : {{boardCreatedAt}}</td>
            </tr>
            <tr>
                <td style="width:60%">작성자 : {{name}}</td>
                <td>조회수 : {{views}}</td>
            </tr>
            <tr></tr>
            <tr></tr>
            <tr></tr>
            <tr>
                <td style="font-weight: bold;">내용</td>
            </tr>

        </table>
                <div>
                <hr style="width:100%; height:2px;border-width:0;color:black; background-color:black;">
                {{boardContents}}

                </div>
                <div style="margin-top:50px">
                    <table style="width:100%">
                        <tr>
                    댓글 ({{length}})
                    </tr>
                    <tr>
                    <hr style="width:100%; height:2px;border-width:0;color:black; background-color:black;">
                    </tr>
                    <tr v-for="(no,idx) in comments" :key="idx">
                        {{idx+1}}. {{no.userName}} : {{no.comment_contents}}
                    </tr>
                    </table>
                    
                    
                </div>
                <div>
                    <button>수정</button>
                    <button>삭제</button>
                </div>

    </div>

</div>
</template>
<script>
import interceptor from "@/api/interceptors";
export default {
    name: 'QnacontentsPage',
    data() {
        return {
            comments:{
                userId:'',
                boardId:'',
                commentId:'',
                comment_contents:'',
                commnetCreatedAt:'',
                userName:'',
            },
            boardId:'',
            title:'',
            boardContents:'',
            name:'',
            views:'',
            boardCreatedAt:'',
            length: '',


        }
    },
    created() {
        this.boardId = this.$route.params.boardId;
    interceptor({
            url: '/board/notice/'+this.boardId,
            method: 'get',
          }).then((res) => {
              console.log(res);
              this.title = res.data.title;
              this.boardContents = res.data.boardContents;
              this.name = res.data.name;
              this.views = res.data.views;
              this.boardCreatedAt = res.data.boardCreatedAt;
          }).catch((err) => {
            alert(err);
          });
                

    },

    mounted(){
        interceptor({
        url: '/comment?boardId='+this.boardId,
        method: 'get',
        }).then((res) => {
            console.log("commnet data");
            console.log(res.data);
            this.length = res.data.length;
            this.comments = res.data;
            console.log(this.comments);

        }).catch((err) => {
        alert(err);
        });
        

    }
}
</script>

<style>

.main-notice-contents{
    margin-top: 20px;
    margin-left: auto;
    margin-right: auto;
    width: 70%;
    text-align: left;
}

table {
  border-collapse: separate;
  border-spacing: 0 10px;
}


</style>