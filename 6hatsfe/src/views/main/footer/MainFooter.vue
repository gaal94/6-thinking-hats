<template>
  <footer class="footer">
    <p class="team-contact">Team Contact : 010-8756-2387</p>
    <p class="team-github">Team Github : 
      <a href="https://lab.ssafy.com/s07-webmobile1-sub2/S07P12A709">https://lab.ssafy.com/s07-webmobile1-sub2/S07P12A709</a>
    </p>
    <a class="git-icon" href="https://lab.ssafy.com/s07-webmobile1-sub2/S07P12A709">
      <img src="@/assets/giticon.png" alt="giticon">
    </a>
  </footer>
</template>

<script>
export default {
	name: 'MainFooter',
	components: {
	},
	data: () => {
		return {
		}
	},
	computed: {
	},
	methods: {
	},
}
</script>

<style>
  .footer {
    width: 100%;
    height: 72px;
    bottom: 0px;
  }

  .team-contact, .team-github {
    font-size: 24px;
  }
</style>