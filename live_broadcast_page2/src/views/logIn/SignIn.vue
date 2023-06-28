<script setup>
import TopBorder from "@/components/topBorder.vue";
</script>

<template>

  <div class="container mt-3">
    <top-border :is_log-in_show="true"></top-border>
  </div>

  <div class="container" style="margin-bottom: 5rem">

    <div class="row justify-content-center">

      <div class="col-md-6 shadow-lg mt-3" id="logIn" >

        <h1 class="text-center mt-3 mb-3" style="color: rgb(167, 175, 232); font-size: 4rem; user-select: none" >注册</h1>
        <hr>



        <form novalidate class="needs-validation" @submit.prevent="submitFunction" >
          <div class="row justify-content-center">
            <div class="form-group w-75 mb-3">
              <label for="identity">身份选择</label>
              <select v-model="role" class="form-control" id="identity">
                <option value="teacher">教师</option>
                <option value="student">学生</option>
              </select>
            </div>


            <div class="form-group w-75 mb-3">
              <label for="email">邮箱</label>
              <input type="email" class="form-control form-control-lg"
                     :class="{'is-valid':email_is_valid, 'is-invalid':!email_is_valid}" id="email"
                     v-model="email" placeholder="请输入邮箱...">
              <div class="valid-feedback">
                牛的
              </div>
              <div class="invalid-feedback">
                邮箱的格式错误! ***@***.com
              </div>
            </div>

            <div class="form-group w-75 mb-3">
              <label for="password">密码</label>
              <input type="password" class="form-control form-control-lg" id="password" placeholder="请输入密码..." autocomplete="off"
                     :class="{'is-valid':password_is_valid, 'is-invalid':!password_is_valid}"
                     v-model="password">

              <div class="valid-feedback">
                牛的
              </div>
              <div class="invalid-feedback">
                密码格式错误! 必须由至少8位字母和数字组成.
              </div>
            </div>

            <div class="form-group w-75 mb-3">
              <label for="confirm_password">确认密码</label>
              <input type="password" class="form-control form-control-lg" id="confirm_password" placeholder="确认密码..." autocomplete="off"
                     :class="{'is-valid':confirm_is_valid, 'is-invalid':!confirm_is_valid}"
                     v-model="confirm">
              <div class="valid-feedback">
                牛的
              </div>
              <div class="invalid-feedback">
                两次输入的密码不相同!
              </div>
            </div>

            <div class="form-group w-75 mb-3">
              <label for="username">昵称</label>
              <input type="text" class="form-control form-control-lg" id="username" placeholder="请输入昵称..."
                     :class="{'is-valid':nickname_is_valid, 'is-invalid':!nickname_is_valid}"
                     v-model="nickname">
              <div class="valid-feedback">
                牛的
              </div>
              <div class="invalid-feedback">
                昵称必须在一位到十位之间
              </div>
            </div>
            <div class="form-group w-75 mb-3">
              <label for="number">学号(工号)</label>
              <input type="text" class="form-control form-control-lg" id="number" placeholder="请输入学号(工号)..."
                     :class="{'is-valid':number_is_valid, 'is-invalid':!number_is_valid}"
                     v-model="accountNumber">
              <div class="valid-feedback">
                牛的
              </div>
              <div class="invalid-feedback">
                学号位数不能大于20
              </div>
            </div>

          </div>

          <div class="row justify-content-center mt-3" style="margin-bottom: 3rem">
            <div class="col-7 text-center">
              <button class="btn-dark btn btn-lg w-75"  type="submit">注册</button>
            </div>
          </div>



        </form>



      </div>

    </div>

  </div>

</template>

<script>
import axios from "axios";
import {useUserStore} from "@/store/userStore";
import {useUtilsStore} from "@/store/utils";
import $ from 'jquery'
export default {
  name: "SignIn",

  data(){
    return{

      role:"student",
      email:'',
      password:'',
      confirm:'',
      nickname:'',
      accountNumber:''

    }
  },

  computed:{

    email_is_valid(){
      return useUserStore().email_is_valid(this.email)
    },

    password_is_valid(){
      return useUserStore().password_is_valid(this.password)
    },

    confirm_is_valid(){
      return this.confirm === this.password && this.confirm !== ''
    },

    nickname_is_valid(){
      return useUserStore().nickname_is_valid(this.nickname)
    },

    number_is_valid(){
      return useUserStore().number_is_valid(this.accountNumber)
    }


  },

  mounted() {
  },

  methods:{

    submitFunction(){

      if (this.confirm_is_valid && this.number_is_valid && this.email_is_valid && this.password_is_valid
          && this.nickname_is_valid){
        // {
        //   "role": this.role,
        //     "email:" : this.email,
        //     "password": this.password,
        //     "nickname": this.nickname,
        //     "accountNumber" : this.accountNumber
        //
        // }

        axios.post('/backend/user/register',this.$data)
            .then( (response) => {
              // 注意, 一定要用箭头函数, 要不然this就变了.

              console.log(response.data)

              this.$router.push({name:"logIn", query:{"accountNumber": this.accountNumber}})
            })
            .catch(function (error) {
              console.log(error);
            });

      }else {
        useUtilsStore().alert("表单未设置好", 'danger')
      }

    }



  }
}
</script>

<style scoped>
#logIn{
  min-height: 50rem;
  width: 100%;
  border-radius: 2rem;
}

.shadow-lg{
  box-shadow: 0 1rem 2rem rgb(167, 175, 232) !important;
}

.form-control{

  font-size: 1.4rem;
  background-color: #3c4146;
  box-shadow: inset 0px 0 1rem 0 black;
  color: white;
  border: 1px solid black;
  border-radius: 0;
}


.form-group{


}
</style>