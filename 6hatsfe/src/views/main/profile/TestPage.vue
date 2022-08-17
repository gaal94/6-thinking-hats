<template>
    <div id="myprofile">
        <div id="myprofilebox">
            <img :src="image" id="myprofileimg" />
        </div>
        <a>
            <i class='bx bxs-camera-plus' @click="Profileupbtn"></i>
        </a>

        <input id="customFile" type="file" @change="handleFileChange">
    </div>
    <div id="profilebtn">
        <button @click="postImage">정보수정</button>
    </div>
</template>

<script>
//import interceptor from "@/api/interceptors";
import axios from 'axios';
export default {
    name: 'ProfilePage',
    data() {
        return {
            image: "파일",

        }
    },
    created() {//프로필 출력시 개인정보 띄워줌
    },
    methods: {
        postImage() {
            const frm = new FormData();
            frm.append('name', '이름');
            frm.append("file", this.image);

            axios.post('http://localhost:8081/image', frm, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
                .then((res) => {
                    // 응답 처리
                    console.log(res);
                })
                .catch((err) => {
                    // 예외 처리
                    alert(err);
                })
        },
        handleFileChange(e) {
            const image = e.target.files[0];
            const url = URL.createObjectURL(image);
            this.image = url;
            const frm = new FormData();
            frm.append('name', '이름');
            frm.append("file", image);

            axios.post('https://i7a709.p.ssafy.io:8082/image', frm, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
                .then((res) => {
                    // 응답 처리
                    console.log(res);
                })
                .catch((err) => {
                    // 예외 처리
                    alert(err);
                })
        },
    }
}

</script>

<style>
</style>