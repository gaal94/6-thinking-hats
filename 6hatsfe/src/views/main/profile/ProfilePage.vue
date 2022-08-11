<template>
    <div id="myprofile">
        <div id="myprofilebox">
            <img src="@/assets/melong.jpg" id="myprofileimg"/>
        </div><i class='bx bxs-camera-plus' ></i>
        <h1><br>닉네임 : {{name}}<i class='bx bxs-pen' style="font-size:16px"></i></h1>
        <div id="profilebody">
        <div class="profilecontent" id="profilecontentleft">
            <ul class="leftul">
                <li class="leftli">이메일 : </li>
                <li class="leftli">직업 : </li>
                <li class="leftli">성별 : </li>
                <li class="leftli">생일 : </li>
            </ul>
        </div>
        <div class="profilecontent">
            <ul>
                <li class="rightli" v-if="emailupdatebtnon">{{email}}  <a><i class='bx bxs-pen' @click="Emailupbtn" ></i></a></li>
                <li class="rightli" v-else><input v-model="temail"/><a><i class='bx bxs-pen' @click="Emailupbtn" ></i></a></li>
                <li class="rightli">{{job}}  <i class='bx bxs-pen' ></i></li>
                <li class="rightli">{{gender}}  <i class='bx bxs-pen' ></i></li>
                <li class="rightli">{{birth}}  <i class='bx bxs-pen' ></i></li>
            </ul>
        </div>
        </div>
    </div>
    <div id ="profilebtn"><button type="button" class="btn btn-primary" @click="UserUpdate">정보수정</button>  <button type="button" class="btn btn-danger">취소</button></div>
</template>

<script>
import http from "@/api/http";
import { mapGetters } from "vuex";
export default {
    name: 'ProfilePage',
    data() {
        return {
            emailupdatebtnon: true,temail:this.$store.email
        }
    },

    mounted() {//프로필 출력시 개인정보 띄워줌

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
    },
    methods: {
        UserUpdate() {
            http
                .put("/user/" + this.$store.state.users.id, null, { //수정할 데이터를 json형태로 전달
        email:this.temail,
        name:"",
        birth:"",
        gender:"MAN",
        job:"STUDENT"
        })
        .then(() => {
          alert('회원정보 수정 완료');
        });
        
        },
        Emailupbtn() {
            if (this.emailupdatebtnon) {
                this.emailupdatebtnon = !this.emailupdatebtnon;
            }
            else {
                this.$store.commit('ChangeEmail', this.temail);
                this.emailupdatebtnon = !this.emailupdatebtnon;
            }
        },

    }
}

</script>

<style>
#myprofile{
    margin-top:5%;
}
#myprofilebox {
    margin:auto;
    width: 200px;
    height: 200px; 
    border-radius: 70%;
    overflow: hidden;
    
}
#myprofileimg {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.profilecontent{
    padding: 20px;
    float:left;
    margin:auto;
    width:50%;
}
.profilecontent #profilecontentleft{
    padding-left:10%;
}
.profilecontent ul{
    list-style:none;
    padding-left:0px;
}
.profilecontent #leftul{
    float: right;
}
.profilecontent ul .rightli{
    text-align:left;
}
.profilecontent ul .leftli{
    text-align:right;
}
#profilebody{
    width: 100%;
}
#profilebtn{
    width: 100%;
}
</style>