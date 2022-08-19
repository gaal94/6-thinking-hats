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
    <div id ="profilebtn">
        <button @click="downLoadFile">파일 다운로드</button>
    </div>
    <br>
    <button @click="postTxt">postContents</button>
    <br>
    <button @click="getTxt">getTxt</button>
</template>

<script>
//import interceptor from "@/api/interceptors";
import axios from 'axios';
export default {
    name: 'ProfilePage',
    data() {
        return {
            image: "파일",
            sessionId: "file",
            contents: "dafdsfsadfsdfsdafasfe\nfsvdsfbvdfbfdbogvienv\nvgbndfpndfqfdsfvmvmkfpw",

        }
    },
    created() {//프로필 출력시 개인정보 띄워줌
    },
    methods: {
        postTxt(){
            axios.post('https://i7a709.p.ssafy.io:8081/file/txt',{
                sessionId: this.sessionId,
                contents: this.contents,
            }, null)
            .then((res) => {
                console.log(res);
            })
            .catch((err) => {
                console.log(err);
            })
        },
        downLoadFile(){
            axios.get('https://i7a709.p.ssafy.io:8081/file/video?fileName=' + "k", {
                responseType: "blob"
            }).then(response => {
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', "test.mp4"); //or any other extension
                document.body.appendChild(link);
                link.click();
            }).catch(exception => {
                alert(exception);
            })
        },
        getTxt(){
            axios.get('https://i7a709.p.ssafy.io:8081/file/txt?opinionFileUrl=' + "file", {
                responseType: "blob"
            }).then(response => {
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', "file.txt"); //or any other extension
                document.body.appendChild(link);
                link.click();
            }).catch(exception => {
                alert(exception);
            })
        },
        getImage(name){
            this.image = 'https://i7a709.p.ssafy.io:8081/file/image?profileImageUrl=' + name;
        },
        postImage(){
            const frm = new FormData();
            frm.append("image", this.image);

            axios.post('https://i7a709.p.ssafy.io:8081/file/image', frm, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then((res) => {
                // 응답 처리
                this.getImage(res.data.fileName);
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
            frm.append("image", image);

            axios.post('https://i7a709.p.ssafy.io:8081/file/image', frm, {
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