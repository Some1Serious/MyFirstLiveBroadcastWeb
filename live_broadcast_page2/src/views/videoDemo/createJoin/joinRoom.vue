<template>
  <modal-dialog :my-id="myId" title="加入班级">

    <template v-slot:body>
      <input class="form-control mr-sm-2 bg-dark text-white" type="number" placeholder="请输入数字" v-model="roomId">
    </template>

    <template v-slot:button>
      <button type="button" class="btn btn-primary" @click="joinRoom">加入</button>
    </template>


  </modal-dialog>
</template>

<script>
import ModalDialog from "@/components/modalDialog.vue";
import request from "@/utils/request";
import $ from "jquery";
import {useUtilsStore} from "@/store/utils";
export default {
  name: "joinRoom",
  components:{ModalDialog},
  props:{
    myId:String
  },

  data(){
    return{
      roomId:''
    }
  },

  methods:{

    joinRoom(){
      console.log('/Room/join/' + this.roomId)
      request.get('/Room/join/' + this.roomId)
          .then(res=>{
            useUtilsStore().message(res.data.message, 'success')
            $('#' + this.myId).modal('hide')
            this.$router.push({name:'video', query:{roomId:this.roomId}})
          }).catch(res=>{
        useUtilsStore().message(res.data.message, 'danger')
      })

    }

  }
}
</script>

<style scoped>

</style>