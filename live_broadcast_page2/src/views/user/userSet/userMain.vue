<script setup>
  import TopBorder from "@/components/topBorder.vue";
  import Show from "@/views/user/userSet/show.vue";
</script>

<template>
  <div class="container mt-3">
    <top-border :is_search_class_show=true :is_student_stuff_show=true></top-border>
  </div>

  <div class="container">

    <div class="row justify-content-center">

      <div class="col-10 border">
        <div class="h1 text-info text-center user-select-none mb-3 mt-3">个人主页</div>
        <div class="row ">
          <div class="col offset-10">
            <button class="btn btn-info" @click="$router.push({name:'changeUserInformation'})" v-if="$route.name === 'showUserInformation'">修改</button>
          </div>
          <div class="col offset-10">
            <button class="btn btn-info" @click="$router.push({name:'showUserInformation'})" v-if="$route.name === 'changeUserInformation'">返回</button>
          </div>

        </div>

        <hr>

        <router-view/>

      </div>

    </div>

  </div>

</template>

<script>
import {useUserStore} from "@/store/userStore";


export default {
  name: "userMain",



  methods:{

  },


}
</script>

<style scoped>

</style>