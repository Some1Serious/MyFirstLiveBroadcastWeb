<script setup>

import TopBorder from "@/components/topBorder.vue";

</script>

<template>

  <div class="container mt-3">
    <top-border></top-border>
  </div>

  <div class="container mt-3 border bg-dark p-3" style="min-height: 500px; margin-bottom: 5rem">
    <div class="row text-center">
      <h1 class="text-white col">{{title}} </h1>
    </div>

    <div class="row text-right ">
      <small class="h2 text-muted col mr-3 ">{{ subtitle }}</small>
    </div>



    <hr>

    <pre class="text-light ">
{{content}}
    </pre>

    <hr>

    <div class="row justify-content-center">
      <button class="btn border col-1" :class="{'btn-dark':!have_read, 'btn-success':have_read}" @click="read_click">已阅</button>
      <button class="btn btn-dark border col-1 offset-2">回复</button>
    </div>


  </div>

</template>

<script>
export default {
  name: "announcement_view",
  data(){
    return{
      title: "公告1",
      subtitle: "王老师2023-1-4",
      content: "   一头忙着蟹苗投放扫尾工作，一头给村民、社员介绍参加全国两会的情况，连日来，全国人大代表，江苏固城湖青松水产专业合作联社党委书记、理事长邢青松忙得不亦乐乎。\n" +
          "\n" +
          "螃蟹养殖是南京市高淳区的一大富民产业，青松水产专业合作联社已覆盖20多家成员合作社、3000多家螃蟹养殖户。去年，联社帮助社员推销螃蟹5.99亿元，社员户均增收1.26万元，成为全国农民合作社示范社。做好螃蟹这个“土特产”文章，高淳成立省河蟹产业研究中心、南京固城湖河蟹产业技术研究院，打造固城湖螃蟹产业集聚区，螃蟹全产业链工作经验成为全省农村工作会议典型案例，创成国家级水产健康养殖和生态养殖示范区。\n" +
          "\n" +
          "茶叶是高淳另一条富民产业。今年，高淳将加快引进“加新高”项目，确保水产、茶叶两条产业链完成2个亿元项目签约、1个亿元项目开工。2月份，多位茶产业专家聚首高淳，带来新思路、新技术、新成果。依托当地自然资源，高淳因地制宜、找准特色，打造具有高淳特色的农业产业金字招牌。\n" +
          "\n" +
          "作为南京市重点涉农区，高淳全域乡村振兴的旋律曲曲动人：漆桥街道荣获首批省级现代农业产业高质量发展示范园称号；砖墙镇几乎家家养蟹，螃蟹等水产品产业带来的收入占全镇居民可支配收入的50%以上；东坝街道青山村是我省首批特色田园乡村建设试点，脱贫重点村“变身”茶艺文创基地，小茅山脚村等特色田园乡村、美丽乡村接连绽放；全区城乡居民收入比缩小至1.95∶1，百姓在家门口就能挣到钱，农民腰包鼓了起来……",

      have_read: false,
    }
  },

  methods:{
    read_click:function (){
      this.have_read = true
    },
  }


}


</script>

<style scoped>
pre {
  white-space: pre-wrap;
  white-space: -moz-pre-wrap;

  white-space: -o-pre-wrap;
  *word-wrap: break-word;
  *white-space : normal ;

  font-size: 1.3rem;


}
</style>