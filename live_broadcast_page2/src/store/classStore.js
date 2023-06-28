import { defineStore } from 'pinia'
import request from "@/utils/request";

export const useClassStore = defineStore('class', {

    actions:{

        getClassClass(val){

            const x={
                description: "" ,
                endTime: ""  ,
                id: '' ,
                name: ""       ,
                startTime: ""   ,
                teacherId: ''    ,
            }

            if (val){
                Object.assign(x, val)
                return x
            }
            return x
        },

        getAllClass(){
            return request.get('/class/getAll')
                .then( req=>{

                    return req.data.data

                })
        },

        getAllClassAndCourse(){
            return request.get('/class/getAllClassAndCourses')
                .then(req=>{
                    return req.data.data
                })
        }

    }


})