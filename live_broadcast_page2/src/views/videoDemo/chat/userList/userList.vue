<template>

  <div class="container p-md-3 ">

    <button  class="btn" style=" left: 90%;" @click="getMessage" >
      <font-awesome-icon icon="fa-solid fa-arrows-rotate" size="lg" style="color: #10707f" />
    </button>
    <hr class="mt-0">

    <user v-if="creator" :nickname="creator.nickname" :is-creator=true :sex="creator.sex" :id="creator.id" :role="creator.role"></user>


    <hr>


    <user v-for="user in audienceIdList" :nickname="user.nickname" :is-user=true :sex="user.sex" :id="user.id" :role="user.role"></user>


  </div>

</template>

<script>
import request from "@/utils/request";
import user from '@/views/videoDemo/chat/userList/user.vue'

export default {
  name: "userList",

  components:{user},

  data(){
    return{

      creator:{},
      audienceIdList:[]

    }
  },

  methods:{
    getMessage(){

      request.get("/Room/list/10")
          .then(res=>{
            const data = res.data.data
            this.creator = data.creator
            this.audienceIdList = data.audienceIdList

          })

    }
  },

  mounted() {

    this.getMessage()

  }

}
</script>

<style scoped>

</style>