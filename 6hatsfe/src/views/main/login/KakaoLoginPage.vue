<template>
    <div></div>
</template>

<script>
import http from "@/api/http";
import router from "../../../router";
//import router from "@/router";

export default {
    created() {
        http.post("/user/login/kakao", this.$route.query.code)
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                alert(err);
            })

        router.push({ name: "LandingPage" });
    }
}
</script>

<style>
</style>