<template>

  <div class="h-100 w-25" >
    <div class="border text-center" style="height: 3rem; border-bottom: 0">
      <ul class="nav nav-tabs h-100" id="myTab" role="tablist">
        <li class="nav-item " role="presentation">
          <button class="nav-link active" id="chatRoom" data-toggle="tab" data-target="#home" type="button" role="tab" aria-controls="home" aria-selected="true">聊天室</button>
        </li>
        <li class="nav-item" role="presentation">
          <button class="nav-link" id="userRoom" data-toggle="tab" data-target="#profile" type="button" role="tab" aria-controls="profile" aria-selected="false">用户列表</button>
        </li>
        <li class="nav-item" role="presentation">
          <button class="nav-link" id="contact-tab" data-toggle="tab" data-target="#contact" type="button" role="tab" aria-controls="contact" aria-selected="false">Contact</button>
        </li>
      </ul>
    </div>

    <div class="tab-content border overflow-auto" style="height: 70%; border-top: 0" id="myTabContent">
      <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="chatRoom">
<!--        <Dlog :character="dialog.from" :message="dialog.message" :is-self="dialog.isSelf" v-for="dialog in dialogs"></Dlog>-->
      </div>
      <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="userRoom">

        <div class="container p-md-3 ">
          
          

          <user v-if="creator" :nickname="creator.nickname" :is-creator=true :sex="creator.sex" :id="creator.id" :role="creator.role"></user>


          <hr>
          

          <user v-for="user in audienceIdList" :nickname="user.nickname" :is-user=true :sex="user.sex" :id="user.id" :role="user.role"></user>


        </div>



      </div>
      <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">...</div>
    </div>



    <!--    <div id="" class="border overflow-auto" style="height: 70%; border-top: 0">-->




    <!--      -->

    <!--    </div>-->
    <div class="border" style="height: 2rem">
      <button class="btn-primary float-right" @click="getMessage">测试</button>

    </div>
    <div class="border " style="height: calc(30% - 5rem)">
      <!--      <text class="p-md-1 h-100 w-100 text-white bg-dark" placeholder="请输入..." />-->
      <textarea id="sendArea" class="p-md-1 h-100 w-100 text-white bg-dark" placeholder="请输入..." @keyup.enter="sendMessage" v-model="message"></textarea>
    </div>


  </div>



</template>

<script>

import Room from "@/store/pojoClass/room/roomClass";
import Request from "@/utils/request";
import request from "@/utils/request";
import User from "@/views/videoDemo/chat/userList/user.vue";

export default {
  name: "test1",
  components: {User},
  data(){
    return{
      creator:{},
      audienceIdList:[]
    }
  },

  methods:{

    fade(){

      $("#target").fadeOut("slow")

    },

    countPlus(){

      let a = this.$data.count += 1
      a += 1

    },

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

    // console.log("this.$config")
    // console.log(this.$stream.ip)
    console.log(window.location.host)

    // Room.getUserList('0').then()



  }


}
</script>


<style scoped>

.nav-link{
  color: white;
}

</style>