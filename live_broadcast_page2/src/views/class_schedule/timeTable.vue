<template>

  <div class="table-responsive">
    <table class="table table-striped table-dark table-bordered" id="timeTable" style="position: relative">
      <thead >
      <tr>
        <th scope="col">#</th>
        <th scope="col">周一</th>
        <th scope="col">周二</th>
        <th scope="col">周三</th>
        <th scope="col">周四</th>
        <th scope="col">周五</th>
        <th scope="col">周六</th>
        <th scope="col">周日</th>
      </tr>
      </thead>
      <tbody >

      <tr >
        <!--      <span class="d-inline-block" role="button" data-toggle="popover" data-content="popover"></span>-->
        <th data-boundary="window" data-trigger="hover" style="cursor: default" data-toggle="popover" data-content="08:00-08:50" id="row1">1</th>

        <td v-for="n in 7" rowspan="1" class="courseCol" :id=" 'week'+n+'row1' ">
          
        </td>

<!--        role="button" data-toggle="popover" data-trigger="focus" title="课程" data-content="And here's some amazing content. It's very engaging. Right?"-->
<!--        role="button" data-toggle="popover" data-trigger="focus" title="课程" data-content="And here's some amazing content. It's very engaging. Right?"-->
<!--        role="button" data-toggle="popover" data-trigger="focus" title="课程" data-content="And here's some amazing content. It's very engaging. Right?"-->
<!--        role="button" data-toggle="popover" data-trigger="focus" title="课程" data-content="And here's some amazing content. It's very engaging. Right?"-->
<!--        role="button" data-toggle="popover" data-trigger="focus" title="课程" data-content="And here's some amazing content. It's very engaging. Right?"-->
<!--        role="button" data-toggle="popover" data-trigger="focus" title="课程" data-content="And here's some amazing content. It's very engaging. Right?"-->




      </tr>
      <tr >
        <th scope="row" ><div data-trigger="hover" style="cursor: default" data-toggle="popover" data-content="09:00-09:50" id="row2">2</div></th>
        <td v-for="n in 7" rowspan="1" class="courseCol" :id=" 'week'+n+'row2' ">

        </td>
      </tr>
      <tr >
        <th scope="row" ><div data-trigger="hover" style="cursor: default" data-toggle="popover" data-content="10:10-11:00" id="row3">3</div></th>
        <td v-for="n in 7" rowspan="1" class="courseCol" :id=" 'week'+n+'row3' ">

        </td>
      </tr>

      <tr >
        <th scope="row"><div data-placement="right" data-trigger="hover" style="cursor: default" data-toggle="popover" data-content="11:10-12:00" id="row4">4</div></th>
        <td v-for="n in 7" rowspan="1" class="courseCol" :id=" 'week'+n+'row4' ">

        </td>
      </tr>

      <tr style="height: 1rem">
        <th scope="row"  class="bg-secondary" ></th>
        <th scope="row"  class="bg-secondary" ></th>
        <th scope="row"  class="bg-secondary" ></th>
        <th scope="row"  class="bg-secondary" ></th>
        <th scope="row"  class="bg-secondary" ></th>
        <th scope="row"  class="bg-secondary" ></th>
        <th scope="row"  class="bg-secondary" ></th>
        <th scope="row"  class="bg-secondary" ></th>
      </tr>

      <tr >
        <th scope="row"><div data-trigger="hover" style="cursor: default" data-toggle="popover" data-content="13:30-14:20" id="row5">5</div></th>
        <td v-for="n in 7" rowspan="1" class="courseCol" :id=" 'week'+n+'row5' ">

        </td>
      </tr>
      <tr >
        <th scope="row"><div data-trigger="hover" style="cursor: default" data-toggle="popover" data-content="14:30-15:20" id="row6">6</div></th>
        <td v-for="n in 7" rowspan="1" class="courseCol" :id=" 'week'+n+'row6' ">

        </td>
      </tr>
      <tr >
        <th scope="row" ><div data-trigger="hover" style="cursor: default" data-toggle="popover" data-content="15:40-16:30" id="row7">7</div></th>
        <td v-for="n in 7" rowspan="1" class="courseCol" :id=" 'week'+n+'row7' ">

        </td>
      </tr>
      <tr >
        <th scope="row" ><div data-trigger="hover" style="cursor: default" data-toggle="popover" data-content="16:40-17:30" id="row8">8</div></th>
        <td v-for="n in 7" rowspan="1" class="courseCol" :id=" 'week'+n+'row8' ">

        </td>
      </tr>
      </tbody>
    </table>
  </div>


</template>

<script>
import {useClassStore} from "@/store/classStore";
import {useCourseStore} from "@/store/courseStore";
import $ from 'jquery'
import request from "@/utils/request";

export default {
  name: "timeTable",

  data(){
    return{


      courses:[
        // {
        //   name:'形势与政策',
        //   week:2,
        //   row: 7,
        //   time: 2,
        //   teacher: '李老师',
        //   description:'形势与政策必修课'
        //
        // },

      ]

    }
  },

  methods:{

    dealCourse(res){

      const courseLst = []

      for (let i = 0; i < res.length; i++) {

        const clazz = res[i]

        for (let j = 0; j < clazz.courses.length; j++) {
          const c1 = clazz.courses[j]

          const courseTag = {
            name: c1.name,
            week:0,
            row: 0,
            time: 0,
            teacher: clazz.teacherName,
            description:c1.description,
          }

          // 1-12 5-61  week-rowTime
          const courseTime = c1.courseTime.split(" ")

          for (let k = 0; k < courseTime.length; k++) {

            const courseRes= Object.assign({}, courseTag);

            const weekRowTime = courseTime[k].split("-");

            courseRes.week = parseInt(weekRowTime[0])
            courseRes.row = parseInt(weekRowTime[1][0])
            courseRes.time = parseInt(weekRowTime[1][1])

            courseLst.push(courseRes)

          }


        }


      }

      this.courses = courseLst

    }

  },
  props:{

    isTeacher:{
      type:Boolean,
      default:false
    }
  },


  mounted() {

    if (!this.isTeacher){
      useClassStore().getAllClassAndCourse()
          .then(res=>{
            this.dealCourse(res)

          })
    }else {

      request.get('/class/getAllTeacher')
          .then(res=>{
            this.dealCourse(res.data.data)
          })

    }


    // popover初始化
    $(function () {
      $('[data-toggle="popover"]').popover()
    })


  },

  watch: {
    // 每当course 改变时，就会动态渲染课程表
    courses(newQuestion, oldQuestion) {

      // 创建课程表初始化
      for (let i = 0; i < this.courses.length; i ++){

        const course = this.courses[i]

        const div =`
        <td rowspan="${course.time}" id="course" style="background-color: #68838B;" class="text-center"
                role="button" data-toggle="popover" data-trigger="hover" title="${course.name}" data-content="教师:${course.teacher},${course.description}"
        >
            ${course.name}
        </td>
    `

        $(div).insertBefore(`#week${course.week}row${course.row}`)

        for (let j = course.row; j < course.row + course.time; j++){
          $(`#week${course.week}row${j}`).remove()
        }

      }


      // popover初始化
      $(function () {
        $('[data-toggle="popover"]').popover()
      })


    }
  },
}

</script>

<style scoped>
#timeTable{

  table-layout:fixed;
}

table td{

  overflow: hidden;

  text-overflow:ellipsis;

  /*white-space: nowrap;*/

}

.courseCol{
  padding: 0;
  height: 100%;
}

tr{
  height: 3.5rem;
}


</style>