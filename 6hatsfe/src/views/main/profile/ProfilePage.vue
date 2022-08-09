<template>
<img src=""/>
<br>이메일 : {{email}}
<br>이름 : {{name}}
<br>직업 : {{job}}
<br>성별 : {{gender}}
<br>생일 : {{birth}}
</template>

<script>
import http from "@/api/http";
import { mapGetters } from "vuex";
export default {
    name: 'ProfilePage',

    mounted() {

        http
            .get("/user/" + this.$store.state.users.id, null)
            .then((res) => {

                const info = res.data.user;
                this.$store.commit('ChangeName', info.name);
                this.$store.commit('ChangeJob', info.job);
                this.$store.commit('ChangeBirth', info.birth);
                this.$store.commit('ChangeEmail', info.email);
                this.$store.commit('ChangeGender', info.gender);
                console.log(this.$store.state.users);
            }).catch((err) => {
                alert(err);
            })

            .catch((err) => {
                alert(err);
            });

    },
    computed: {
        ...mapGetters(['name','job','birth','email','gender'])
    }
}

</script>

<style>

</style>