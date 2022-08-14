<template>
<div>

</div>
</template>
<script>
import interceptor from "@/api/interceptors";
export default {
    name: 'NoticecontentsPage',
    data() {
        return {
            boardId:'',
        }
    },
    created() {
        this.boardId = this.$route.params.boardId;
    interceptor({
            url: '/board/'+this.boardId,
            method: 'get'
          }).then((res) => {
              console.log(res);
          }).catch((err) => {
            alert(err);
          });

    }
}
</script>

<style>

</style>