<template>
  <div>
    <div class="mainpage-mainpage">
      <span class="mainpage-text">
        <span>
          는
          <span v-html="rawveis"></span>
        </span>
        <br />
        <span>브레인 스토밍을 돕는 회의 서비스입니다.</span>
      </span>
      <div class="mainpage-navbarlogout">
        <img
          src="/playground_assets/1e7a5fbc-59b5-40c1-9347-1e5b54b774d8-n2dg.svg"
          alt="navbarI36811067364"
          class="mainpage-navbar"
        />
        <img
          src="/playground_assets/9f81a150-d8b0-488f-84e6-371d9f7238b0-mfkj-200h.png"
          alt="logoI3681106268450"
          class="mainpage-logo"
        />
        <div class="mainpage-menuright">
          <span class="mainpage-text04">회원가입</span>
          <span class="mainpage-text05">로그인</span>
        </div>
        <div class="mainpage-frame99">
          <span class="mainpage-text06">공지사항</span>
          <span class="mainpage-text07">설명</span>
          <span class="mainpage-text08">팀 소개</span>
          <span class="mainpage-text09">Q&amp;A</span>
        </div>
      </div>
      <img
        src="/playground_assets/aff7b168-b72c-4e19-ac34-5c7769f36c5c-brlp-400h.png"
        alt="KakaoTalk20220722122841198012383347"
        class="mainpage-kakao-talk20220722122841198012"
      />
      <div class="mainpage-wavehaikei41">
        <img
          src="/playground_assets/00f1a190-613e-4ca0-9ab7-4b530be72f1f-l5o.svg"
          alt="Group390666"
          class="mainpage-group"
        />
        <img
          src="/playground_assets/bc2f32fa-3128-48c8-875a-5354e23ab679-h3f.svg"
          alt="Group390668"
          class="mainpage-group1"
        />
        <img
          src="/playground_assets/724bb889-02e0-4615-b8c4-82ace82ed0f8-ky.svg"
          alt="Group390670"
          class="mainpage-group2"
        />
        <img
          src="/playground_assets/686e9a7d-97b9-4692-9017-9e41214dfeff-6rzb.svg"
          alt="Group390672"
          class="mainpage-group3"
        />
        <img
          src="/playground_assets/fdff9727-eadf-4334-a4b1-a60ecfdfd155-bsk.svg"
          alt="Group390674"
          class="mainpage-group4"
        />
        <img
          src="/playground_assets/272e277d-b2c6-404e-a520-2533a1091b9a-1x5.svg"
          alt="Group390676"
          class="mainpage-group5"
        />
        <img
          src="/playground_assets/25f61c07-cfeb-4c67-be30-abfaf857fd44-vri7.svg"
          alt="Group390678"
          class="mainpage-group6"
        />
      </div>
      <img
        src="/playground_assets/91081bb5-73df-4f2c-a54c-d98a8c056fc7-h18s-200h.png"
        alt="hatconference390714"
        class="mainpage-hatconference"
      />
      <div class="mainpage-footer">
        <a
          href="https://lab.ssafy.com/s07-webmobile1-sub2/S07P12A709"
          target="_blank"
          rel="noreferrer noopener"
          class="mainpage-link"
        >
          <img
            src="/playground_assets/fc2aa43d-4d48-4452-ba28-d6666d344dbe-wea-200h.png"
            alt="giticon310265"
            class="mainpage-giticon"
          />
        </a>
        <a
          href="https://lab.ssafy.com/s07-webmobile1-sub2/S07P12A709"
          target="_blank"
          rel="noreferrer noopener"
          class="mainpage-text10"
        >
          <span></span>
          <br />
          <span>
            Team Github : https://lab.ssafy.com/s07-webmobile1-sub2/S07P12A709
          </span>
        </a>
        <span class="mainpage-text14">Team Contact : 010-8756-2387</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Mainpage',
  props: {},

  data() {
    return {
      rawveis: ' ',
    }
  },
}
</script>

<style scoped>
.mainpage-mainpage {
  width: 1920px;
  height: 900px;
  display: flex;
  overflow: hidden;
  position: relative;
  max-width: 1920px;
  box-sizing: border-box;
  align-items: flex-start;
  border-color: transparent;
  background-color: rgba(255, 255, 255, 1);
}
.mainpage-text {
  top: 634px;
  left: 791px;
  color: rgba(0, 0, 0, 1);
  height: auto;
  position: absolute;
  font-size: 20px;
  align-self: auto;
  font-style: normal;
  text-align: center;
  font-family: Inter;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.mainpage-navbarlogout {
  top: 0px;
  left: 0px;
  width: 1920px;
  height: 72px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  border-color: transparent;
}
.mainpage-navbar {
  top: 0px;
  left: 0px;
  width: 1920px;
  height: 72px;
  position: absolute;
  box-sizing: border-box;
  object-fit: cover;
  border-color: rgba(18, 18, 18, 1);
  border-style: solid;
  border-width: 1px;
  background-color: rgba(255, 255, 255, 1);
}
.mainpage-logo {
  top: 6px;
  left: 40px;
  width: 60px;
  height: 60px;
  position: absolute;
  box-sizing: border-box;
  object-fit: cover;
  border-color: transparent;
}
.mainpage-menuright {
  top: 24px;
  left: 1687px;
  width: 198px;
  height: 24px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.mainpage-text04 {
  top: 0px;
  left: 0px;
  color: rgba(0, 0, 0, 1);
  width: 74px;
  height: auto;
  position: absolute;
  font-size: 20px;
  align-self: auto;
  font-style: normal;
  text-align: center;
  font-family: Inter;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.mainpage-text05 {
  top: 0px;
  left: 142px;
  color: rgba(0, 0, 0, 1);
  height: auto;
  position: absolute;
  font-size: 20px;
  align-self: auto;
  font-style: normal;
  text-align: center;
  font-family: Inter;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.mainpage-frame99 {
  top: 24px;
  left: 168px;
  width: 453px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
}
.mainpage-text06 {
  color: rgba(0, 0, 0, 1);
  width: 100px;
  height: auto;
  margin: 0 66px 0 0;
  font-size: 20px;
  align-self: auto;
  font-style: normal;
  text-align: center;
  font-family: Inter;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.mainpage-text07 {
  color: rgba(0, 0, 0, 1);
  width: 52px;
  height: auto;
  margin: 0 66px 0 0;
  font-size: 20px;
  align-self: auto;
  font-style: normal;
  text-align: center;
  font-family: Inter;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.mainpage-text08 {
  color: rgba(0, 0, 0, 1);
  height: auto;
  margin: 0 66px 0 0;
  font-size: 20px;
  align-self: auto;
  font-style: normal;
  text-align: center;
  font-family: Inter;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.mainpage-text09 {
  color: rgba(0, 0, 0, 1);
  height: auto;
  font-size: 20px;
  align-self: auto;
  font-style: normal;
  text-align: center;
  font-family: Inter;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.mainpage-kakao-talk20220722122841198012 {
  top: 283px;
  left: 803px;
  width: 317px;
  height: 312px;
  position: absolute;
  box-sizing: border-box;
  object-fit: cover;
  border-color: transparent;
}
.mainpage-wavehaikei41 {
  top: 341px;
  left: -1px;
  width: 1921px;
  height: 487px;
  display: flex;
  overflow: hidden;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  border-color: transparent;
}
.mainpage-group {
  top: 319.86114501953125px;
  left: 1509.65625px;
  width: 568px;
  height: 133px;
  position: absolute;
  box-sizing: border-box;
  object-fit: cover;
  border-color: transparent;
}
.mainpage-group1 {
  top: 327.2320251464844px;
  left: 83.77505493164062px;
  width: 824px;
  height: 131px;
  position: absolute;
  box-sizing: border-box;
  object-fit: cover;
  border-color: transparent;
}
.mainpage-group2 {
  top: 354.6556396484375px;
  left: 1841.3360595703125px;
  width: 294px;
  height: 96px;
  position: absolute;
  box-sizing: border-box;
  object-fit: cover;
  border-color: transparent;
}
.mainpage-group3 {
  top: 368.62481689453125px;
  left: 805.263916015625px;
  width: 786px;
  height: 79px;
  position: absolute;
  box-sizing: border-box;
  object-fit: cover;
  border-color: transparent;
}
.mainpage-group4 {
  top: 379.21441650390625px;
  left: -1.7098743915557861px;
  width: 486px;
  height: 84px;
  position: absolute;
  box-sizing: border-box;
  object-fit: cover;
  border-color: transparent;
}
.mainpage-group5 {
  top: 422.8743591308594px;
  left: -1.7098743915557861px;
  width: 2137px;
  height: 63px;
  position: absolute;
  box-sizing: border-box;
  object-fit: cover;
  border-color: transparent;
}
.mainpage-group6 {
  top: 447.43316650390625px;
  left: 1813.9808349609375px;
  width: 44px;
  height: 6px;
  position: absolute;
  box-sizing: border-box;
  object-fit: cover;
  border-color: transparent;
}
.mainpage-hatconference {
  top: 607px;
  left: 781px;
  width: 173px;
  height: 74px;
  position: absolute;
  box-sizing: border-box;
  object-fit: cover;
  border-color: transparent;
}
.mainpage-footer {
  top: 828px;
  left: 0px;
  width: 1920px;
  height: 72px;
  display: flex;
  position: absolute;
  box-sizing: border-box;
  align-items: flex-start;
  flex-shrink: 1;
  border-color: transparent;
  background-color: rgba(255, 255, 255, 1);
}
.mainpage-link {
  display: contents;
}
.mainpage-giticon {
  top: 10.285720825195312px;
  left: 1834px;
  width: 60px;
  height: 51px;
  position: absolute;
  box-sizing: content-box;
  object-fit: cover;
  border-color: transparent;
}
.mainpage-text10 {
  top: 29.142852783203125px;
  left: 531px;
  color: rgba(0, 0, 0, 1);
  width: 861px;
  height: auto;
  position: absolute;
  font-size: 24px;
  align-self: auto;
  font-style: normal;
  text-align: center;
  font-family: Inter;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
.mainpage-text14 {
  top: 10.285720825195312px;
  left: 635px;
  color: rgba(0, 0, 0, 1);
  width: 569px;
  height: auto;
  position: absolute;
  font-size: 24px;
  align-self: auto;
  font-style: normal;
  text-align: center;
  font-family: Inter;
  font-weight: 400px;
  line-height: normal;
  font-stretch: normal;
  text-decoration: none;
}
</style>