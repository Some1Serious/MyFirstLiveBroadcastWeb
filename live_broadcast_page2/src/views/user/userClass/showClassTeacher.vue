<template>

  <div class="container mt-3">
    <top-border :is_search_class_show=true :is_self_setting_show=true :is_student_stuff_show=true></top-border>

    <div class="row justify-content-end">
      <div class="col-md-2">
        <button class="btn btn-outline-info " @click="createClass">创建班级</button>
      </div>
      <div class="col-md-1">
        <button class="btn btn-outline-info " @click="flush">刷新</button>
      </div>

      <modal-dialog title="创建班级" my-id="createClass">

        <template v-slot:body>
          <div class="form-group">
            <label for="name" class="h5">输入班级名称</label>
            <input type="text" class="form-control bg-dark text-white" id="name" placeholder="班级名称" v-model="clazz.name">
          </div>

          <div class="form-group">
            <label for="startTime" class="h5">输入开始时间</label>
            <input type="date" class="form-control bg-dark text-white" id="startTime" placeholder="开始时间" v-model="clazz.startTime">
          </div>

          <div class="form-group">
            <label for="endTime" class="h5">输入结束时间</label>
            <input type="date" class="form-control bg-dark text-white" id="endTime" placeholder="结束时间" v-model="clazz.endTime">
          </div>

          <div class="form-group" >
            <label for="description" class="h5">输入班级简述</label>

            <textarea  v-model="clazz.description" placeholder="班级简述" id="description" class="form-control bg-dark text-white" style="height: 7rem">

            </textarea>

<!--            <input type="text" class="form-control bg-dark text-white" id="description" placeholder="班级简述" v-model="clazz.description">-->
          </div>

        </template>

        <template v-slot:button>
          <button class="btn btn-primary" @click="putClass">创建</button>
        </template>

      </modal-dialog>

    </div>

    <display_stuff>

      <div id="display">

        <div v-if="classList.length !== 0" class="row">

          <clazz :is-teacher=true v-for="item in classList" :key="item.id"
                 :clazzProp='item'></clazz>

        </div>
      </div>
    </display_stuff>

  </div>



</template>

<script>
import showClass from "@/views/user/userClass/class/showClass.vue";
import TopBorder from "@/components/topBorder.vue";
import ModalDialog from "@/components/modalDialog.vue";
import $ from 'jquery'
import {useUtilsStore} from "@/store/utils";
import {useUserStore} from "@/store/userStore";
import request from "@/utils/request";
import Display_stuff from "@/components/display_stuff.vue";
import clazz from "@/views/user/userClass/class/class.vue"

export default {
  name: "teacherClass",
  components:{clazz, Display_stuff, ModalDialog, showClass, TopBorder},

  data(){

    return{

      clazz:{

        startTime: '',
        endTime: '',
        name:'',
        description:'',

      },
      classList:[]

    }

  },

  methods:{
    flush(){
      location.reload()
    },

    createClass(){

      this.clazz = {}
      $('#createClass').modal('show')

    },

    putClass(){

      if (this.clazz.name && this.clazz.endTime && this.clazz.startTime && this.clazz.description){

        const dataClazz = this.clazz
        dataClazz.teacherId = useUserStore().userId
        request.post('/class', dataClazz)
            .then( res=>{

              useUtilsStore().message(res.data.message)

            }).catch(err=>{

          useUtilsStore().message(err.data.message)

        }).finally(()=>{
          $('#createClass').modal('hide')
          location.reload()
        })

      }else {

      }

    }

  },

  mounted() {

    request.get('/class/get/teacher/' +useUserStore().userId )
        .then( req=>{

          this.classList = req.data.data

        }).catch(err=>{

          if (err.data.status == 404){
            $("#display").append(`

            <h1 class="mt-md-5 text-center text-danger">404!</h1>
            <h1 class="mt-md-5 text-center ">${err.data.message}</h1>

            `);
          }
    })

  }

}
</script>

<style scoped>

</style>