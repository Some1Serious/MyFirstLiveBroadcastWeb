<script setup>
import TopBorder from "@/components/topBorder.vue";
</script>

<template>

  <div class="container mt-3">
    <top-border :is_-sign-in_show="true"></top-border>
  </div>

  <div class="container mt-5">
    <div class="row">

<!--      下面是封面设置-->
      <div class="col-md-5 " id="title">
        <div class="container">
          <div class="row justify-content-center">
            <p id="title">
              直播上课
            </p>

          </div>
          <p class="row justify-content-center" id="title">
              好摸鱼
          </p>
        </div>
      </div>

<!--      下面是登录页面设置-->
      <div class="col-md-5 offset-md-1  shadow-lg" id="logIn">

        <h1 class="text-center mt-3 mb-3" style="color: rgb(167, 175, 232); font-size: 4rem; user-select: none" >登录</h1>
        <hr>
        <form onsubmit="return false">
        <div id="input" class="row justify-content-center">
          <div class="input-group mb-3 w-75 ">
            <div class="input-group-prepend">
              <button class="btn btn-outline-secondary dropdown-toggle text-white border-0" style="background-color: #2e2f33" type="button" data-toggle="dropdown" aria-expanded="false">
                {{ roleChinese }}</button>
              <div class="dropdown-menu">
                <a class="dropdown-item" @click="data.role='student'">学生</a>
                <a class="dropdown-item" @click="data.role='teacher'">教师</a>
                <a class="dropdown-item" @click="data.role='administrator'">管理员</a>
              </div>
            </div>
            <input type="text" class="form-control" v-model="data.accountNumber" placeholder="学号(工号)">
          </div>

          <div class="input-group mb-3 w-75 mt-3">

            <input type="password" autocomplete="off" class="form-control" :class="{'is-invalid':!valid.is_password_valid}" v-model="data.password" placeholder="密码">
            <div class="invalid-feedback">
              密码错误, 或账号不存在!
            </div>
          </div>

        </div>

        <div class="row mt-3">
          <div class="col-5 offset-2 ">
            <button class="btn-dark btn btn-lg w-100" @click="logIn">登录</button>
          </div>
          <div class="col-3 ">
            <button class="btn-dark btn btn-lg " @click="$router.push('/signIn')">注册</button>
          </div>

        </div>
        </form>

      </div>



      </div>
      <br>
    </div>

</template>

<script>
import router from "@/router";
import axios from "axios";
import {useUserStore} from "@/store/userStore";
import $ from 'jquery'


export default {

  name: "logIn",

  data(){
    return{

      data:{
        role:"student",
        accountNumber: "",
        password: "",
      },

      valid:{
        is_password_valid: true
      }



    }
  },

  computed:{

    roleChinese: function (){
      const j = {
        "student": "学生",
        "teacher": "教师",
        "administrator": "管理员"
      }
      return j[this.data.role]
    }
  },

  methods:{

    logIn:function (){

      axios.post("/backend/user/logIn", this.$data.data)

          .then(response =>{

            // status是后端定义的code, data是User
            console.log(response)
            const {status, data} = response.data

            if (status == 200){
              console.log(data)
              useUserStore().setUserId(data.id)
              useUserStore().setUser(data)

              console.log(useUserStore().getUser())
              this.valid.is_password_valid = true
              this.$router.push({name:'home'})
            } else {
              this.valid.is_password_valid = false
            }

          })
          .catch( error=>{
            console.log(error)
            console.log("login err")
          } )

    }

  },
  mounted() {

    const an = this.$route.query

    if (!$.isEmptyObject(an)){
      this.data.accountNumber = an.accountNumber
    }

  }


}
</script>

<style scoped>
  #title{
    font-family: 方正舒体,serif;
    font-size: 6rem;
    color: white;
    user-select: none;
  }

  #logIn{
    min-height: 28rem;
    width: 100%;
    border-radius: 2rem;
  }

  .shadow-lg{
    box-shadow: 0 1rem 2rem rgb(167, 175, 232) !important;
  }

  #input{
    --input-height : 3rem;
    --input-font-size: 1.4rem;

    margin-top: 3rem;


  }

  .input-group{
    height: var(--input-height);

  }


  .form-control{
    height: var(--input-height);
    font-size: var(--input-font-size);
    background-color: #3c4146;
    box-shadow: inset 0px 0 1rem 0 black;
    color: white;
    border: 1px solid black;
    border-radius: 0;
  }





</style>