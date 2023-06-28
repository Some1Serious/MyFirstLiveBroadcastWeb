import axios from 'axios'
import router from '../router'
import {useUserStore} from "@/store/userStore";
import {useUtilsStore} from "@/store/utils";

const request = axios.create({
    baseURL: '/backend',
    timeout: 5000,
})

request.interceptors.request.use(


    (config) => {

        console.log("useUserStore")
        console.log(useUserStore().userId)

        if (useUserStore().userId == null) {

            console.log("request interceptors")
            router.push({name:'logIn'})
            useUtilsStore().alert("未登录!", 'danger')
            return false

        } else {
            // 应该加密, 存在安全隐患
            config.headers.userId = useUserStore().userId
            return config
        }

    },
    (error) => Promise.reject(error),
)

request.interceptors.response.use(
    (response) => {

        return response
    },
    ({ response }) => {

        if (!response){
            console.log("request.interceptors.response")
            return response
        }

        console.log(response)

        const {status} = response

        if (status == 401){

            console.log("response interceptors")
            useUserStore().setUserId(null)
            router.push({name:'logIn'})
            location.reload();
            useUtilsStore().alert("未登录!", 'danger')
            return false

        }

        else {
            return Promise.reject(response)
        }


    },
)

export default request
