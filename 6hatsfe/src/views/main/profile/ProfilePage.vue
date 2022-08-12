<template>
    <div id="myprofile">
        <div id="myprofilebox">
            <img src="@/assets/melong.jpg" id="myprofileimg"/>
        </div><i class='bx bxs-camera-plus' ></i>
        <h1 v-if="nameUpdatebtnon"><br>닉네임 : {{name}}<a><i class='bx bxs-pen' @click="Nameupbtn" ></i></a></h1>
        <h1 v-else><input v-model="tname"/><a><i class='bx bxs-pen' @click="Nameupbtn" ></i></a></h1>
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
                <li class="rightli">{{email}}</li>
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
import interceptor from "@/api/interceptors";
import jwt_decode from "jwt-decode";
export default {
    name: 'ProfilePage',
    data() {
        return {
            nameUpdatebtnon: true,tname:this.$store.name
        }
    },
    mounted() {//프로필 출력시 개인정보 띄워줌
          var decoded = jwt_decode(localStorage.getItem('access-token'));//token 디코드
          interceptor({
            url: '/user/' + decoded.userId,
            method: 'get'
          }).then((res) => {
                const info = res.data.user;
                this.$store.commit('ChangeName', info.name);
                this.$store.commit('ChangeJob', info.job);
                this.$store.commit('ChangeBirth', info.birth);
                this.$store.commit('ChangeEmail', info.email);
                this.$store.commit('ChangeGender', info.gender);
          }).catch((err) => {
            alert(err);
          });
    },
    computed: {
        ...mapGetters(['name','job','birth','email','gender'])
    },
    methods: {
        UserUpdate() {
            var decoded = jwt_decode(localStorage.getItem('access-token'));//token 디코드
            http
                .put("/user/" + decoded.userId ,{ //수정할 데이터를 json형태로 전달
            name:this.name,
            birth:"2022-08-02",
            gender:"MAN",
            job: "STUDENT",
            email:this.email
            
        })
        .then(() => {
          alert('회원정보 수정 완료');
        });
        
        },
        Nameupbtn() {
            if (this.nameUpdatebtnon) {
                this.nameUpdatebtnon = !this.nameUpdatebtnon;
            }
            else {
                this.$store.commit('ChangeName', this.tname);
                this.nameUpdatebtnon = !this.nameUpdatebtnon;
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