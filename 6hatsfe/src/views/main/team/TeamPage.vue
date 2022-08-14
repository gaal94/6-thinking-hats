<template>
    <div class="team-body">
		
		<div class="team-row">
			<div class="polaroid" style="border-color: #EA4335;">
				<div>
					<img class="profile" src="@/assets/cbs.png" alt="최병성" >
				</div>
				<div>
					<p style="font-weight: bold;" >최병성</p>
					<p>Front-End 팀장</p>
				</div>	
			</div>

			<div class="polaroid" style="border-color: #FBBC05;">
				<div>
					<img class="profile" src="@/assets/ksr.png" alt="권수린" >
				</div>
				<div>
					<p style="font-weight: bold;" >권수린</p>
					<p>Front-End 팀원</p>
				</div>
			</div>

			<div class="polaroid" style="border-color: #34a853;">
				<div>
					<img class="profile" src="@/assets/hsy.png" alt="황석영" >
				</div>
				<div>
					<p style="font-weight: bold;" >황석영</p>
					<p>Front-End 팀원</p>
				</div>
			</div>
		
			<div class="polaroid" style="border-color: #4285f4;">
				<div>
					<img class="profile" src="@/assets/fsj.png" alt="오성준" >
				</div>
				<div>
					<p style="font-weight: bold;" >오성준</p>
					<p>프로젝트 팀장</p>
					<p>Back-End 팀장</p>
				</div>
		    </div>
		
			<div class="polaroid">
				<div>
					<img class="profile" src="@/assets/ksj.png" alt="김성진" >
				</div>
				<div>
					<p style="font-weight: bold;" >김성진</p>
					<p>Back-End 팀원</p>
				</div>
		    </div>

			<div class="polaroid" style="border-color: #C7C6C6;">
				<div>
					<img class="profile" src="@/assets/cjw.png" alt="최지원" >
				</div>
				<div>
					<p style="font-weight: bold;" >최지원</p>
					<p>Back-End 팀원</p>
				</div>
		    </div>
  	    </div>
		<div class="team-row">

			<div class="polaroid" style="border-color: #C7C6C6;" >
				<div> 
					<img src="@/assets/notion.png" alt="노션" onclick="window.open('https://www.notion.so/9fab16fcf2344d6bbc8076c9e4f85383')">
				</div>
			</div>

			<div class="polaroid" style="border-color: #C7C6C6;">
				<div> 
					<img src="@/assets/gitlab.png" alt="깃랩" onclick="window.open('https://lab.ssafy.com/s07-webmobile1-sub2/S07P12A709')">
				</div>
			</div>

			<div class="polaroid" style="border-color: #C7C6C6;">
				<div> 
					<img src="@/assets/jira.jpg" alt="지라" onclick="window.open('https://jira.ssafy.com/secure/RapidBoard.jspa?rapidView=12687&projectKey=S07P12A709&view=planning.nodetail&issueLimit=100')">
				</div>
			</div>

		</div>
    </div>
  
</template>

<script>
export default {
  name: 'TeamPage',
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
  @import url("@/assets/css/views/main/team/TeamPage.css");
</style>