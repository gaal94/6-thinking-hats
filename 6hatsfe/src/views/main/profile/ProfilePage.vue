<template>
    <div id="myprofile">
        <div id="myprofilebox">
            <img :src="image" id="myprofileimg" />
        </div>
        <input id="customFile" type="file" @change="handleFileChange">
        <h1 v-if="nameUpdatebtnon"><br>{{ name }}<a><i class='bx bxs-pen' @click="Nameupbtn"
                    style="font-size:20px;"></i></a></h1>
        <h1 v-else><input @keyup.enter="Nameupbtn" v-model="tname" /><a><i class='bx bxs-pen' @click="Nameupbtn"
                    style="font-size:20px;"></i></a></h1>
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
                    <li class="rightli">{{ email }}</li>
                    <li v-if="jobUpdatebtnon" class="rightli">{{ job }}<a><i class='bx bxs-pen' @click="Jobupbtn"></i></a>
                    </li>
                    <li v-else class="rightli">
                        <select @change="Jobupbtn($event)">
                            <option>선택</option>
                            <option value="STUDENT">학생</option>
                            <option value="OFFICE">직장인</option>
                            <option value="JOBLESS">무직</option>
                            <option value="OTHER">기타</option>
                        </select>
                        <a><i class='bx bxs-pen' @click="Jobupbtn"></i></a>
                    </li>
                    <li v-if="genderUpdatebtnon" class="rightli">{{ gender }}<a><i class='bx bxs-pen'
                                @click="Genderupbtn"></i></a></li>
                    <li v-else class="rightli">
                        <input type="radio" v-model="tgender" @change="Genderupbtn($event)" value="MAN">남
                        <input type="radio" v-model="tgender" @change="Genderupbtn($event)" value="WOMAN">여
                    </li>
                    <li class="rightli" v-if="birthUpdatebtnon">{{ birth }}<a><i class='bx bxs-pen'
                                @click="Birthupbtn"></i></a></li>
                    <li class="rightli" v-else>
                        <input type="date" @change="Birthupbtn($event)" /><i class='bx bxs-pen'></i>
                    </li>
                </ul>
            </div>
        </div>
        <div id="profilebtn"><button type="button" class="btn btn-primary" @click="UserUpdate">정보수정</button> <button
                type="button" class="btn btn-danger" @click="ProfileupCanclebtn">취소</button></div>
    </div>
</template>

<script>
import http from "@/api/http";
import axios from "axios";
import { mapGetters } from "vuex";
import interceptor from "@/api/interceptors";
import jwt_decode from "jwt-decode";
export default {
    name: 'ProfilePage',
    data() {
        return {
            nameUpdatebtnon: true, genderUpdatebtnon: true, jobUpdatebtnon: true, birthUpdatebtnon: true,
            tname: this.$store.name,
            tgender: this.$store.gender,
            tjob: this.$store.job,
            tbirth: this.$store.birth,
            image:'https://i7a709.p.ssafy.io:8081/file/image?profileImageUrl=basic',
            img: '',
            profileImageUrl: '',
        }
    },
    created() {//프로필 출력시 개인정보 띄워줌
        var decoded = jwt_decode(localStorage.getItem('access-token'));//token 디코드
        this.image = 'https://i7a709.p.ssafy.io:8081/file/image?profileImageUrl=' + (localStorage.getItem("profileImageUrl")? localStorage.getItem("profileImageUrl"): "basic");
        this.profileImageUrl = (localStorage.getItem("profileImageUrl")? localStorage.getItem("profileImageUrl"): "basic");
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
        ...mapGetters(['name', 'job', 'birth', 'email', 'gender'])
    },
    methods: {
        UserUpdate() {
            const frm = new FormData();
            if(this.image != 'https://i7a709.p.ssafy.io:8081/file/image?profileImageUrl=basic'){
                frm.append('image', this.img);

                axios.post('https://i7a709.p.ssafy.io:8081/file/image', frm, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
                })
                .then((res) => {
                    // 응답 처리
                    this.profileImageUrl = res.data.fileName;
                    this.putUser();
                })
                .catch((err) => {
                    // 예외 처리
                    alert(err);
                })
            } else {
                this.putUser();
            }
        },
        putUser() {
            var decoded = jwt_decode(localStorage.getItem('access-token'));//token 디코드
            http
                .put("/user/" + decoded.userId, { //수정할 데이터를 json형태로 전달
                    name: this.name,
                    birth: this.birth,
                    gender: this.gender,
                    job: this.job,
                    email: this.email,
                    profileImageUrl: this.profileImageUrl
                })
                .then(() => {
                    localStorage.setItem('username', this.name);
                    localStorage.setItem('profileImageUrl', this.profileImageUrl);
                    alert('회원정보 수정 완료');
                });
        },  
        Nameupbtn() {
            if (!this.nameUpdatebtnon) {
                this.$store.commit('ChangeName', this.tname);
            }
            this.nameUpdatebtnon = !this.nameUpdatebtnon;
        },
        Jobupbtn(event) {
            if (!this.jobUpdatebtnon) {
                this.$store.commit('ChangeJob', event.target.value);
            }
            this.jobUpdatebtnon = !this.jobUpdatebtnon;
        },
        Genderupbtn(event) {
            var selected = event.target.value;
            if (!this.genderUpdatebtnon) {
                this.$store.commit('ChangeGender', selected);
            }
            this.genderUpdatebtnon = !this.genderUpdatebtnon;
        },
        Birthupbtn(event) {
            var selected = event.target.value;
            if (this.birthUpdatebtnon) {
                this.birthUpdatebtnon = !this.birthUpdatebtnon;
            }
            else {
                this.birthUpdatebtnon = !this.birthUpdatebtnon;
                this.$store.commit('ChangeBirth', selected);
            }
        },
        ProfileupCanclebtn() {
            alert('회원정보 수정을 취소하였습니다.');
            window.location.reload();
        },
        handleFileChange(e) {
            const image = e.target.files[0];
            this.img = image;
            const url = URL.createObjectURL(image);
            this.image = url;
        },

    }
}

</script>

<style>
#myprofile {
    margin: auto;
    height: calc(100% - 72px);
    padding: 5%;
}

#myprofilebox {
    margin: auto;
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

.profilecontent {
    padding: 20px;
    float: left;
    margin: auto;
    width: 50%;
}

.profilecontent #profilecontentleft {
    padding-left: 10%;
}

.profilecontent ul {
    list-style: none;
    padding-left: 0px;
}

.profilecontent #leftul {
    float: right;
}

.profilecontent ul .rightli {
    text-align: left;
}

.profilecontent ul .leftli {
    text-align: right;
}

#profilebody {
    width: 100%;
}

#profilebtn {
    width: 100%;
}
</style>