<template>

  <modal-dialog :my-id="myId" title="创建班级">

    <template v-slot:body>
      <input class="form-control mr-sm-2 bg-dark text-white" type="number" placeholder="请输入数字" v-model="roomId">
    </template>

    <template v-slot:button>
      <button type="button" class="btn btn-primary" data-dismiss="modal" @click="createRoom">创建</button>
    </template>


  </modal-dialog>

<!--  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">-->
<!--    <div class="modal-dialog modal-dialog-centered">-->
<!--      <div class="modal-content bg-dark">-->
<!--        <div class="modal-header">-->
<!--          <h5 class="modal-title" id="exampleModalLabel">创建房间</h5>-->
<!--          <button type="button" class="close" data-dismiss="modal" aria-label="Close">-->
<!--            <span aria-hidden="true">&times;</span>-->
<!--          </button>-->
<!--        </div>-->
<!--        <div class="modal-body">-->
<!--          <input class="form-control mr-sm-2 bg-dark text-white" type="number" placeholder="请输入数字" v-model="roomId">-->
<!--        </div>-->
<!--        <div class="modal-footer">-->
<!--          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>-->
<!--          <button type="button" class="btn btn-primary" data-dismiss="modal" @click="createRoom">创建</button>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
</template>

<script>
import request from "@/utils/request";
import {useUtilsStore} from "@/store/utils";
import $ from 'jquery'
import ModalDialog from "@/components/modalDialog.vue";
export default {
  name: "createRoomWindow",
  components: {ModalDialog},
  data(){
    return{
      roomId:''
    }
  },

  props:{

    myId: String

  },

  methods:{

    createRoom(){

      if (this.roomId !== ''){

        request.get('/Room/create/' + this.roomId)
            .then(res=>{
              useUtilsStore().message(res.data.message, 'success')
              this.$router.push({name:'video', query:{roomId:this.roomId}})

            }).catch(err=>{
              useUtilsStore().message(err.data.message, 'danger')
        })

      }

    }

  }


}
</script>

<style scoped>

</style>