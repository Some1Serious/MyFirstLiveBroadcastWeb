import { defineStore } from 'pinia'
import axios from "axios";
import request from "@/utils/request";

// 你可以对 `defineStore()` 的返回值进行任意命名，但最好使用 store 的名字，同时以 `use` 开头且以 `Store` 结尾。(比如 `useUserStore`，`useCartStore`，`useProductStore`)
// 第一个参数是你的应用中 Store 的唯一 ID。
export const useUserStore = defineStore('user', {
    // 其他配置...

    //data
    state: () => ({

        role:{
            student:'student',
            teacher:'teacher'
        },

    }),

    //computed
    getters: {
        userId(){

            if (!localStorage.getItem("userId")){
                return null
            } else {
                return localStorage.getItem("userId")
            }
        },

        userRole(){

            if (!localStorage.getItem("user")){
                return null
            } else {
                return JSON.parse(localStorage.getItem("user")).role
            }

        }


    },

    //methods
    actions: {

        setUserId(val){


            // 这里有一个陷阱, 如果直接设置为null, 它会变成字符串null (真王八蛋)
            if (val === null){
                localStorage.removeItem("userId")
            }
            else {
                localStorage.setItem("userId", val)
                return localStorage.getItem("userId")
            }

        },

        setUser(val){

            if (!val){
                localStorage.removeItem("user")
            }else {
                localStorage.setItem("user", JSON.stringify(val))
            }

        },

        getUserClass : function (){

            return {
                email:'',
                school:'',
                confirm:'',
                nickname:'',
                accountNumber:'',
                age:'',
                sex:'',
                personalProfile:'',
                telephone:'',
            }

        },
        //api
        getUser : function (){

            return request.get("/user/get")
                .then( (res)=>{

                        const user = res.data.data

                        // 处理输出
                        console.log(res)
                        user.sex = useUserStore().sexTranslate(user.sex)

                        return user
                    }
                )

        },

        postUser(user){
            user.sex = useUserStore().sexTranslateBack(user.sex)

            return request.post('/user/set',user)
                .then( (response) => {
                    // 注意, 一定要用箭头函数, 要不然this就变了.
                    return response
                })
                .catch(function (error) {
                    console.log(error);
                });

        },


        // 数据检测

        sexTranslate: function (input){
            const sex = {
                "MALE" : "男",
                "FEMALE" : "女",
                "UNKNOWN": "未知"
            }

            return sex[input]

        },

        sexTranslateBack(input){
            const sex = {
                "男" : "MALE"   ,
                "女" : "FEMALE" ,
                "未知":  "UNKNOWN"
            }

            return sex[input]
        },

        // 表单验证:

        email_is_valid: function (input){

            //编写正则表达式, 必须有一个大写字母, 一个小写字母, 一个数字, 其他随意
            const patt = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/

            return patt.test(input)
        },

        password_is_valid: function (input){
            const patt = /^[\d\w]{8,20}$/

            return patt.test(input)

        },

        nickname_is_valid:function (input){

            const patt = /^.{1,10}$/
            return patt.test(input)

        },

        number_is_valid: function (input){

            const patt = /^[\d\w]{3,20}$/
            return patt.test(input)

        }



    },
})
