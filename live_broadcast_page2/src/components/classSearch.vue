<script setup>

import ModalDialog from "@/components/modalDialog.vue";
</script>

<template>
  <div class="form-inline my-2 my-lg-0">
    <input class="form-control mr-sm-2 bg-dark text-white" type="search" placeholder="输入班级id..." aria-label="Search" v-model="classId">
    <button class="btn btn-info my-2 my-sm-0" @click="search">查找班级</button>
  </div>

  <ModalDialog title="是否要加入以下班级">

    <template v-slot:body>

      <p>
        班级Id:{{clazz.id}}
        <br>
        班级名称:{{clazz.name}}
        <br>
        教师:{{clazz.teacherName}}
        <br>
        开课时间:{{clazz.startTime}}
        <br>
        结束时间:{{clazz.endTime}}
        <br>
        简介:
        <br>
        {{clazz.description}}

      </p>

    </template>

    <template v-slot:button>
      <button class="btn bg-primary text-white" @click="add">加入</button>
    </template>

  </ModalDialog>

  <ModalDialog my-id="notFound" title="未找到班级!">

    <template v-slot:body>

      <h2 class="text-danger">404!</h2>
      <p>
        请检查你的输入, 这里只支持id搜索.
      </p>

    </template>

  </ModalDialog>



</template>



<script>
import request from "@/utils/request";
import {useUtilsStore} from "@/store/utils";
import {useClassStore} from "@/store/classStore";

export default {
  name: "classSearch",

  data(){
    return{
      classId:'',

      clazz: useClassStore().getClassClass()

    }
  },

  methods:{

    search(){
      // request.
      // $('#modal').modal('show')
      request.post('/class/get',
          {'id':this.classId})
          .then( (req)=>{
            const classClass = useClassStore().getClassClass(req.data.data);

            classClass.startTime = classClass.startTime.slice(0, 10)
            classClass.endTime = classClass.endTime.slice(0, 10)

            this.clazz = classClass
            $('#modal').modal('show')
          })
          .catch((error)=>{
            if (error.status === 404){
              $('#notFound').modal('show')
            }else {
              useUtilsStore().alert('未知错误!', 'danger')
            }
          })

    },

    add(){
      request.post('/class/join',
          {'id':this.classId})
          .then( (req)=>{
            $('#modal').modal('hide')

            if (req.status == 202){
              useUtilsStore().alert(req.data.message, 'warning')
            }else {
              location.reload()
              useUtilsStore().alert('成功!', 'success')
              console.log(req)
            }
          })
          .catch((error)=>{
            useUtilsStore().alert('未知错误!', 'danger')
          })
    }

  }
}
</script>

<style scoped>

</style>