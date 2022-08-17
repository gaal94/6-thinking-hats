<template>
    <div class="main-qna-contents">
        <h2 style="padding:15px">QnA</h2>
        <div id="bar"></div>

        <div style="margin-left:20px">
            <table class="main-contents" style="width:100%; ">
                <tr>
                    <td style="width:60%;">제목 : {{ title }}</td>
                    <td>작성일시 : {{ boardCreatedAt }}</td>
                </tr>
                <tr>
                    <td style="width:60%">작성자 : {{ name }}</td>
                    <td>조회수 : {{ views }}</td>
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
                {{ boardContents }}
            </div>


            <div style="margin-top:50px">
                <table style="width:100%">

                    <tr>
                        댓글 ({{ length }})
                    </tr>
                    <tr>
                        <hr style="width:100%; height:2px;border-width:0;color:black; background-color:black;">
                    </tr>
                    <tr v-for="(no, idx) in comments" :key="idx">
                        <div v-if="!no.commentUpdate">
                            {{ pageNum * pageSize + idx + 1 }}. {{ no.userName }} : {{ no.comment_contents }} <i
                                class='bx bxs-trash' @click="deleteComment(no.commentId)" style="float:right"></i> <i
                                class='bx bxs-pen' @click="UpdateComment(idx)"
                                style="float:right; margin-right:5px;"></i>
                        </div>
                        <div v-else>
                            {{ idx + 1 }}. {{ no.userName }} : <input type="text" v-model="no.comment_contents"> <i
                                class='bx bxs-pen' @click="UpdateComment(idx)" style="float:right;"></i>
                        </div>
                    </tr>
                </table>
                <nav aria-label="Page navigation example" style="display:flex; justify-content: center; padding:5px;">
                    <ul class="pagination">
                        <li class="page-item">
                            <a v-on:click="substractPageNum" class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        <li class="page-item"><a class="page-link" href="#">{{ pageNum + 1 }}</a></li>
                        <li class="page-item">
                            <a v-on:click="addPageNum" class="page-link" href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div>
                <input v-model="postComment.commentContents" type="text" style="width:80%"> <button
                    v-on:click="PostComment" style="float:right" type="button" class="btn btn-primary">댓글 쓰기</button>
            </div>

        </div>
        <div style="float:right; margin-top:10px">
            <router-link :to="{
                path: '/qnamodifypage/' + this.boardId
            }">
                <button style="margin-right:5px" type="button" class="btn btn-primary">게시글 수정</button>
            </router-link>
            <button @click="boarddelete" type="button" class="btn btn-danger">게시글 삭제</button>
        </div>
    </div>
</template>


<script>
import interceptor from "@/api/interceptors";
import router from "@/router";
import jwt_decode from "jwt-decode";
export default {
    name: 'QnacontentsPage',
    data() {
        return {
            comments: {
                userId: '',
                boardId: '',
                commentId: '',
                comment_contents: '',
                commnetCreatedAt: '',
                userName: '',
                commentUpdate: true,
            },
            postComment: {
                boardId: '',
                commentContents: '',
            },
            boardId: '',
            title: '',
            boardContents: '',
            name: '',
            views: '',
            boardCreatedAt: '',
            length: '',
            pageNum: 0,
            pageSize: 5,

        }
    },
    created() {
        this.boardId = this.$route.params.boardId;
        this.getQna();
        this.getCommentList();

    },

    mounted() {



    },

    methods: {
        getQna() {
            interceptor({
                url: '/board/qna/' + this.boardId,
                method: 'get',
            }).then((res) => {
                console.log(res);
                this.title = res.data.title;
                this.boardContents = res.data.boardContents;
                this.name = res.data.name;
                this.views = res.data.views;
                this.boardCreatedAt = res.data.boardCreatedAt;
            }).catch((err) => {
                console.log(err);
                router.push({ path: "/qnapage" });
            });
        },
        getCommentList() {
            interceptor({
                url: '/comment?boardId=' + this.boardId + '&page=' + this.pageNum + '&size=' + this.pageSize + '&sort=commentCreatedAt,commentId,desc',
                method: 'get',
            }).then((res) => {
                console.log("commnet data");
                console.log(res.data);
                this.length = res.data.length;
                this.comments = res.data;

                console.log(this.comments);
            }).catch((err) => {
                console.log(err);
                router.push({ path: "/qnapage" });
            });
        },
        addPageNum() {
            if (this.length == this.pageSize) { // 꼼수 같은 코드
                this.pageNum++;
                this.getCommentList();
            }
        },
        substractPageNum() {
            if (this.pageNum - 1 >= 0) {
                this.pageNum--;
                this.getCommentList();
            }
        },

        deleteComment(commentId) {
            if (confirm("댓글을 삭제하시겠습니까?")) {

                interceptor({
                    url: '/comment/' + commentId,
                    method: 'delete',
                }).then((res) => {
                    console.log(res);
                    location.reload();
                }).catch((err) => {
                    console.log(err);
                    alert("본인이 작성한 댓글만 삭제할 수 있습니다.")
                });
            }
        },
        boarddelete() {
            if (confirm("정말로 삭제하시겠습니까?") == true) {
                interceptor({
                    url: '/board/' + this.boardId,
                    method: 'delete'
                }).then((res) => {
                    console.log(res);
                    console.log("완료되었습니다.");
                    router.push({ name: "QnaPage" });

                }).catch((err) => {
                    alert(err);
                });
            }
            else {
                console.log("취소되었습니다");
            }
        },
        PostComment() {
            this.postComment.boardId = this.boardId;
            interceptor({
                url: '/comment',
                method: 'post',
                data: this.postComment
            }).then((res) => {
                console.log(res);
                console.log("완료되었습니다.");
                location.reload();
            }).catch((err) => {
                alert(err);
            });
        },
        UpdateComment(idx) {
            var decoded = jwt_decode(localStorage.getItem('access-token'));
            if (this.comments[idx].userId == decoded.userId) {
                if (this.comments[idx].commentUpdate) {
                    interceptor({
                        url: '/comment/' + this.comments[idx].commentId,
                        method: 'patch',
                        data: this.comments[idx].comment_contents
                    }).then((res) => {
                        console.log(res);
                        console.log("완료되었습니다.");
                        location.reload();
                    }).catch((err) => {
                        alert(err);
                    });
                }
                this.comments[idx].commentUpdate = !this.comments[idx].commentUpdate;
            } else {
                alert("본인이 작성한 댓글만 수정할 수 있습니다.")
            }
        }




    }
}
</script>


<style scoped lang="css" src="@/assets/css/views/main/qna/QnacontentsPage.css">
.pagination {}

.page-item {}

.page-link {}

.sr-only {}
</style>