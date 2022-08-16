<template>
    <div class="main-notice-contents">
        <h2 style="padding:15px">공지사항</h2>
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
                <div>
                    댓글
                    <hr style="width:100%; height:2px;border-width:0;color:black; background-color:black;">
                </div>
                <div style="display:flex; justify-content: flex-end;">
                    <router-link :to ="{
                        path: '/noticemodifypage/' + this.boardId
                        }">
                    <button>수정</button></router-link>
                    <button @click="boarddelete">삭제</button>
                </div>
        </div>
    </div>
</template>

<script>
import interceptor from "@/api/interceptors";
import router from "@/router";
export default {
    name: 'NoticecontentsPage',
    data() {
        return {
            boardId: '',
            title: '',
            boardContents: '',
            name: '',
            views: '',
            boardCreatedAt: '',

        }
    },
    created() {
        this.boardId = this.$route.params.boardId;
        interceptor({
            url: '/board/notice/' + this.boardId,
            method: 'get'
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
    methods: {
        boarddelete() {
            if (confirm("정말로 삭제하시겠습니까?") == true) {
                interceptor({
                    url: '/board/' + this.boardId,
                    method: 'delete'
                }).then((res) => {
                    console.log(res);
                    console.log("완료되었습니다.");
                    router.push({ name: "NoticePage" });

                }).catch((err) => {
                    alert(err);
                });
            }
            else {
                console.log("취소되었습니다");
            }
        }
    }
}


</script>

<style scoped lang="css" src="@/assets/css/views/main/notice/NoticecontentsPage.css">

</style>