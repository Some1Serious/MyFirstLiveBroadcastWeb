<template>

  <div class="h-100">
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
        <Dlog :character="dialog.from" :message="dialog.message" :is-self="dialog.isSelf" v-for="dialog in dialogs"></Dlog>
      </div>
      <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="userRoom">...</div>
      <div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">...</div>
    </div>

<!--    <div id="" class="border overflow-auto" style="height: 70%; border-top: 0">-->




<!--      -->

<!--    </div>-->
    <div class="border" style="height: 2rem">
      <button class="btn-primary float-right" @click="sendMessage">发送</button>

    </div>
    <div class="border " style="height: calc(30% - 5rem)">
<!--      <text class="p-md-1 h-100 w-100 text-white bg-dark" placeholder="请输入..." />-->
      <textarea id="sendArea" class="p-md-1 h-100 w-100 text-white bg-dark" placeholder="请输入..." @keyup.enter="sendMessage" v-model="message"></textarea>
    </div>


  </div>



</template>

<script>
import {useUserStore} from "@/store/userStore";
import {useUtilsStore} from "@/store/utils";
import Dlog from "@/views/videoDemo/chat/dialog/dLog.vue";
import {useWebsocketStore} from "@/store/websocket";

export default {
  name: "chatRoom",
  components: {Dlog},
  data(){
    return{

      message:"123",

      websocket : useWebsocketStore().websocket,

      // dialog:[ {message:"123", isSelf: Bool, from: ""} ]
      dialogs:[],

      test:1234

    }
  },

  props:{
    user:Object
  },

  methods:{

    sendMessage(){

      if (this.message === "" || this.message === "\n"){
        return
      }

      let message = {id:this.user.id, from: this.user.nickname, message: this.message, type: 3}
      useWebsocketStore().sendMessage(JSON.stringify(message))
      this.message = '';

    },

    getMessage(data){

      const messageType = useWebsocketStore().messageType

      if (data.type === messageType.sendMessage) {

        let dialog = {from: data.from, message: data.message}


        if (data.id === this.user.id) {
          dialog.isSelf = true
        }

        this.dialogs.push(dialog)

      }

    },

  },

  mounted() {


  },

}
</script>

<style scoped>

.nav-link{
  color: white;
}

</style>