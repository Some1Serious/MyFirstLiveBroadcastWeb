<script setup>
import Announcement from "@/views/announcement/announcement.vue";
import class_schedule from "@/views/class_schedule/class_schedule.vue";
import Live_card from "@/views/class_schedule/live_card.vue";
import Note from "@/views/class_schedule/note.vue";
import TopBorder from "@/components/topBorder.vue";
import CreateRoomWindow from "@/views/videoDemo/createJoin/createRoomWindow.vue";
import JoinRoom from "@/views/videoDemo/createJoin/joinRoom.vue";

</script>

<template>

  <div class="container mt-3">
    <top-border :is_search_class_show=true :is_self_setting_show=true :is_student_stuff_show=true></top-border>
  </div>

  <div class="container">


    <button class="btn btn-primary" @click="()=>{$('#joinWindow').modal('show')}">加入房间</button>
    <join-room my-id="joinWindow"></join-room>


    <div class="row">

      <div class="col-md-3 border rounded" style="min-height: 500px">
        <Announcement></Announcement>
      </div>
      <div class="col-md-8 offset-1 border rounded p-3" style="min-height: 500px">
      <class_schedule></class_schedule>

        <hr>

        <p class="text-info">现在正在直播:  </p>
        <br>
        <div class="row">
          <live_card class="mb-2 ml-2"></live_card>
          <live_card class="mb-2 ml-2"></live_card>
        </div>

        <hr>

        <p class="text-info">我的笔记:</p>
        <br>
        <div class="row">
          <note class="mb-2 ml-2"></note>
        </div>

      </div>

    </div>

  </div>
</template>

<script>

import request from "@/utils/request";
import $ from 'jquery'

export default {
  data(){
    return{

    }
  },

  mounted() {

    // request.get("/test/test2")
    //     .then(req=>{
    //       console.log(req)
    //     })

  }
}

</script>
