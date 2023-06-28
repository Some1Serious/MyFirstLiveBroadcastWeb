import { defineStore } from 'pinia'
import request from "@/utils/request";

export const useCourseStore = defineStore('course', {

    actions:{

        getCourseClass(val){

            const x={
                description: "" ,
                endTime: ""  ,
                id: '' ,
                name: ""       ,
                startTime: ""   ,
                teacherId: ''    ,
                status:'',
                courseTime:''
            }

            if (val){
                Object.assign(x, val)
                return x
            }
            return x
        },

        getCourseByClassId(classId){

            return request.get('/course/classAll/' + classId)
                .then(res=>{

                    const lst = res.data.data

                    for (let i = 0; i < lst.length; i++) {

                        lst[i].startTime = lst[i].startTime.slice(0, 10)
                        lst[i].endTime = lst[i].endTime.slice(0, 10)


                    }
                    return res.data.data
                })

        },

        courseTimeToString(val){

            const timeLst = val.split(" ");
            const dic = {
                '1' : '周一',
                '2' : '周二',
                '3' : '周三',
                '4' : '周四',
                '5' : '周五',
                '6' : '周六',
                '7' : '周日',
            }

            let res = ""

            for (let i = 0; i < timeLst.length; i++) {
                res= res+" "
                const weekAndRow = timeLst[i].split("-");

                if (weekAndRow[1][1] === "1"){
                    res = res+dic[weekAndRow[0]]+`:第${weekAndRow[1][0]}节`
                }else {
                    res = res+dic[weekAndRow[0]]+`:第${weekAndRow[1][0]}-${ parseInt(weekAndRow[1][1])-1+ parseInt(weekAndRow[1][0])}节`
                }



            }
            return res.slice(1)

        }

    }


})