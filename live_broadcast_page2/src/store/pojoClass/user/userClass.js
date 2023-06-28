import request from "@/utils/request";

export default class User{

    user = null

    constructor() {
        this.user = null
    }

    role = {
        student:'student',
        teacher:'teacher'
    }

    static getUserId(){

        if (!localStorage.getItem("userId")){
            return null
        } else {
            return localStorage.getItem("userId")
        }
    }

    static getUserRole(){

        if (!localStorage.getItem("user")){
            return null
        } else {
            return JSON.parse(localStorage.getItem("user")).role
        }

    }

    static setUserId(val){


        // 这里有一个陷阱, 如果直接设置为null, 它会变成字符串null (真王八蛋)
        if (val === null){
            localStorage.removeItem("userId")
        }
        else {
            localStorage.setItem("userId", val)
            return localStorage.getItem("userId")
        }

    }

    static setUser(val){

        if (!val){
            localStorage.removeItem("user")
        }else {
            localStorage.setItem("user", JSON.stringify(val))
        }

    }



    static async getUser(){

        let res;
        await res = request.get("/user/get")

        // 预处理
        res.sex = this.#sexTranslate(user.sex)
        return Object.assign(this.#getUserJson, res)


    }

    #sexTranslate(input){
        const sex = {
            "MALE" : "男",
            "FEMALE" : "女",
            "UNKNOWN": "未知"
        }

        return sex[input]

    }

    #sexTranslateBack(input){
        const sex = {
            "男" : "MALE"   ,
            "女" : "FEMALE" ,
            "未知":  "UNKNOWN"
        }

        return sex[input]
    }



    static async postUser(user){
        user.sex = this.#sexTranslateBack(user.sex)

        return request.post('/user/set',user)
            .then( (response) => {
                // 注意, 一定要用箭头函数, 要不然this就变了.
                return response
            })
            .catch(function (error) {
                console.log(error);
            });

    }



    #getUserJson={
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


}