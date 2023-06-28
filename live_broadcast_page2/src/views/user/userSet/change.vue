<template>

  <form novalidate class="needs-validation" @submit.prevent="submitFunction" >
    <div class="row justify-content-center">

      <div class="form-group w-75 mb-3">
        <label for="nickname">昵称</label>
        <input type="text" class="form-control form-control-lg" id="nickname" placeholder="请输入昵称..."
               :class="{'is-valid':nickname_is_valid, 'is-invalid':!nickname_is_valid}"
               v-model="user.nickname">
        <div class="valid-feedback">
          牛的
        </div>
        <div class="invalid-feedback">
          昵称必须在一位到十位之间
        </div>
      </div>
      
      <div class="form-group w-75 mb-3">
        <label for="email">邮箱</label>
        <input type="email" class="form-control form-control-lg"
               :class="{'is-valid':email_is_valid, 'is-invalid':!email_is_valid}"
               v-model="user.email" id="email" placeholder="请输入邮箱...">
        <div class="valid-feedback">
          牛的
        </div>
        <div class="invalid-feedback">
          邮箱的格式错误! ***@***.com
        </div>
      </div>

      <div class="form-group w-75 mb-3">
        <label for="school">学校</label>
        <input type="text" class="form-control form-control-lg" id="school" placeholder="请输入学校..."
               v-model="user.school">
      </div>

      <div class="form-group w-75 mb-3">
        <label for="sex">性别</label>
        <select v-model="user.sex" class="form-control" id="sex">
          <option value="男">男</option>
          <option value="女">女</option>
          <option value="未知">未知</option>
        </select>
      </div>

      <div class="form-group w-75 mb-3">
        <label for="telephone">电话</label>
        <input type="number" class="form-control form-control-lg" id="telephone" placeholder="请输入电话..."
               v-model="user.telephone">
      </div>

      <div class="form-group w-75 mb-3">
        <label for="age">年龄</label>
        <input type="number" class="form-control form-control-lg" id="age" placeholder="请输入年龄..."
               v-model="user.age">
      </div>

      <div class="form-group w-75 mb-3">
        <label for="personalProfile">个人简介</label>

        <textarea class="form-control form-control-lg" style="min-height: 10rem" id="personalProfile" placeholder="请输入简介.." v-model="user.personalProfile"></textarea>
      </div>




    </div>

    <div class="row justify-content-center mt-3" style="margin-bottom: 3rem">
      <div class="col-7 text-center">
        <button class="btn-dark btn btn-lg w-75" @click="postUser" >确定</button>
      </div>
    </div>



  </form>

</template>

<script>
import {useUserStore} from "@/store/userStore";
import {useUtilsStore} from "@/store/utils";

export default {
  name: "change",

  data(){
    return {
      user: useUserStore().getUserClass()

    }
  },

  computed:{

    showList(){
      return{

        "学号(公告): ": this.user.accountNumber,
        "昵称: ":this.user.nickname,
        "邮箱: ": this.user.email,
        "学校: " : this.user.school,
        "性别: " :this.user.sex,
        "年龄: " :this.user.age,
        "电话: " :this.user.telephone,
        "个人简介: " :this.user.personalProfile,
      }
    },

    email_is_valid(){
      return useUserStore().email_is_valid(this.user.email)
    },

    nickname_is_valid(){
      return useUserStore().nickname_is_valid(this.user.nickname)
    },

    number_is_valid(){
      return useUserStore().number_is_valid(this.user.accountNumber)
    }


  },

  methods:{

    postUser(){

      if (this.email_is_valid && this.nickname_is_valid && this.number_is_valid){
        useUserStore().postUser(this.user)
            .then( ()=>{
              this.$router.push({name:"showUserInformation"})
            } )
      }else {
        useUtilsStore().alert("格式错误!", 'danger')
      }
      }



  },

  mounted() {

    useUserStore().getUser()
        .then(res=>{
          Object.assign(this.user, res)
        })

    console.log(this.user)

  }
}
</script>

<style scoped>
.form-control{

  font-size: 1.4rem;
  background-color: #3c4146;
  box-shadow: inset 0px 0 1rem 0 black;
  color: white;
  border: 1px solid black;
  border-radius: 0;
}

label{
  font-size: 1.4rem;
}

</style>