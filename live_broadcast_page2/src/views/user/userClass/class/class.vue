<template>
  <div class="card m-md-2" id="classCard">
    <div class="card-body bg-dark border rounded">
      <h4 class="card-title">{{ clazz.name }} <span class="h3 small text-muted">{{clazz.teacher}}</span></h4>
      <p>id:{{clazz.id}}</p>
      <p class="card-text" id="content">{{clazz.description}}</p>

      <div v-if="isTeacher">
        <button class="btn btn-info p-1" @click="showDetails">详情</button>
        <button class="btn btn-info ml-2 p-1" @click="showAddCourseWindow">添加课程</button>
        <button class="btn btn-info ml-2 p-1" @click="removeTeacher(clazz.id)">删除</button>
      </div>

      <div v-if="!isTeacher">
        <button class="btn btn-info" @click="showDetails">详情</button>
        <button class="btn btn-info ml-2">公告</button>
        <button class="btn btn-info ml-2" @click="remove(clazz.id)">删除</button>
      </div>

    </div>
  </div>

  <modal-dialog title="创建课程" my-id="createCourse">
    <template v-slot:body>
      <div class="form-group">
        <label for="name" class="h5">输入课程名称:</label>
        <input type="text" class="form-control bg-dark text-white" id="name" placeholder="课程名称" v-model="course.name">
      </div>

      <div class="form-group">
        <label for="startTime" class="h5">输入开始时间:</label>
        <input type="date" class="form-control bg-dark text-white" id="startTime" placeholder="开始时间" v-model="course.startTime">
      </div>

      <div class="form-group">
        <label for="endTime" class="h5">输入结束时间:</label>
        <input type="date" class="form-control bg-dark text-white" id="endTime" placeholder="结束时间" v-model="course.endTime">
      </div>



      <div class="form-group ">
        <label class="h5">输入课程时间:</label>
        <div class="container">
          <div class="row">
            <div class="input-group-prepend">
              <span class="input-group-text" id="addon-wrapping">星期</span>
            </div>

            <select class="custom-select bg-dark text-white col-2" v-model="course.week">
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
            </select>

            <div class="input-group-prepend offset-1">
              <span class="input-group-text" id="addon-wrapping">第</span>
            </div>

            <select class="custom-select bg-dark text-white col-2" v-model="course.begin">
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
            </select>
             ___

            <select class="custom-select bg-dark text-white col-2" v-model="course.end">
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
              <option value="6">6</option>
              <option value="7">7</option>
              <option value="8">8</option>
            </select>

            <span class="input-group-text" id="addon-wrapping">节</span>
          </div>
        </div>


      </div>

      <div class="form-group">
        <label for="description" class="h5">输入简述:</label>
        <textarea  v-model="course.description" placeholder="班级简述" id="description" class="form-control bg-dark text-white" style="height: 7rem">

        </textarea>
      </div>

    </template>

    <template v-slot:button>

      <button class="btn btn-primary" @click="createCourse">创建</button>

    </template>
  </modal-dialog>

  <ModalDialog :title="clazz.name" :id="'detail'">

    <template v-slot:body>

      <div style="font-size: 1.5rem">
        <p class="font-weight-bold">班级id: <span class="text-reset">{{clazz.id}}</span> </p>
        <p class="font-weight-bold">教师id: <span class="text-reset">{{clazz.teacherId}}</span> </p>
        <p class="font-weight-bold">教师名称: <span class="text-reset">{{clazz.teacherName}}</span> </p>
        <p class="font-weight-bold">班级时间: <span class="text-reset">{{clazz.startTime}}</span> </p>
        <p class="font-weight-bold">结束时间: <span class="text-reset">{{clazz.endTime}}</span> </p>
        <p class="font-weight-bold">简述: <br><span class="text-reset">{{clazz.description}}</span> </p>
        <hr>
        <p>下面是班级包含的课程:</p>
        <div class="accordion" id="accordionExample">
          <div v-for="(course,index) in courses" :key="index">

            <div class="card bg-dark border-primary">
              <div class="card-header border-info" :id="'heading' + index">



                <p class="font-weight-bold float-left">{{course.name}} </p>

                <div>

                  <button class="btn btn-outline-primary small float-right ml-md-1" @click="$router.push({name:'userClass', query:{id:course.id}})">
                    管理人员
                  </button>

                  <button class="btn btn-outline-danger small float-right ml-md-1" @click="deleteCourse(course.id)">
                    删除
                  </button>

                  <button class="btn btn-outline-info small float-right " type="button" data-toggle="collapse" :data-target="'#collapse'+index" aria-expanded="true" :aria-controls="'collapse'+index">
                    详情
                  </button>

                </div>



              </div>

              <div :id="'collapse'+index" class="collapse" :aria-labelledby="'heading'+index" data-parent="#accordionExample">
                <div class="card-body">

                  <p>
                    课程简述: {{course.description}}
                  </p>

                  <p>
                    上课时间: {{course.courseTime}}
                  </p>
                  <p>
                    课程开始时间: {{course.startTime}}
                  </p>
                  <p>
                    课程结束时间: {{course.endTime}}
                  </p>

                </div>
              </div>
            </div>
        </div>


        </div>

      </div>



    </template>

  </ModalDialog>

</template>

<script>
import request from "@/utils/request";
import ModalDialog from "@/components/modalDialog.vue";
import {useClassStore} from "@/store/classStore";
import {useCourseStore} from "@/store/courseStore";
import $ from 'jquery'
import {useUtilsStore} from "@/store/utils";

export default {
  name: "class",
  components: {ModalDialog},

  data(){

    return{

      courses: [],

      course:{
        name:'',
        description:'',
        startTime:'',
        endTime:'',

        week:'',
        begin:'',
        end:'',


        courseTime:'',
        status:'published'

      }

    }

  },

  computed:{

    clazz(){
      const classClass = useClassStore().getClassClass();
      Object.assign(classClass, this.clazzProp)
      classClass.startTime = classClass.startTime.slice(0, 10)
      classClass.endTime = classClass.endTime.slice(0, 10)
      return classClass
    }

  },

  props:{

    clazzProp:{
      type:Object,
      default:useClassStore().getClassClass(null)
    },

    isTeacher:{
      type:Boolean,
      default:false
    }


  },

  methods:{

    remove(id){

      request.delete("/class/" + id)
          .then((res)=>{
            location.reload()
          })

    },

    removeTeacher(id){

      request.delete("/class/" + id + "/teacher")
          .then(res=>{
            location.reload()
          })
          .catch(err=>{
            useUtilsStore().message(err.date.message)
          })

    },

    showDetails(){
      $('#detail').modal('show')

      useCourseStore().getCourseByClassId(this.clazz.id)
          .then(res=>{
            console.log(res)

            for (let i = 0; i < res.length; i++) {
              res[i].courseTime =  useCourseStore().courseTimeToString(res[i].courseTime)
            }
            this.courses = res
          })

    },

    showAddCourseWindow(){

      this.course = {}
      $('#createCourse').modal('show');
    },

    createCourse(){

      const data = this.course
      data.courseTime = this.course.week + "-" + this.course.begin + (this.course.end - this.course.begin +1)
      data.classId = this.clazz.id

      console.log(data)

      request.post('/course', data)
          .then(res=>{
            $('#createCourse').modal('hide')
            useUtilsStore().message(res.data.message, 'success')
          }).catch(err=>{
        $('#createCourse').modal('hide')
        useUtilsStore().message(err.data.message, 'warning')
      })

    },

    deleteCourse(id){

      request.delete('/course/' + id)
          .then(res=>{
            $('#detail').modal('hide')
            useUtilsStore().message(res.data.message, 'success')
          }).catch(err=>{
        $('#detail').modal('hide')
        useUtilsStore().message(err.data.message, 'warning')
      })

    }

  }
}
</script>

<style scoped>

#classCard{
  width: 16rem;

}
#content{
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 4;
  overflow: hidden;
}

</style>