<template>

  <div class="row" v-for="(value, key) in showList">
    <div class="col-md-3 offset-md-3">
      <h3 class="mb-3">{{ key }} </h3>
    </div>
    <div class="col h3">
      {{value ? value:"未知"}}
    </div>
  </div>

</template>

<script>


import {useUserStore} from "@/store/userStore";

export default {
  name: "show",

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
    }

  },

  methods:{

  },

  mounted() {

    useUserStore().getUser()
        .then(res=>{
          Object.assign(this.user, res)
        })

  }

}

// const showList = {
//   "个人昵称: " : user.nickname,
//   "学号(工号): ": user.accountNumber,
//   "学校: ": user.school,
//   "电话号码: ": user.telephone
// }

</script>

<style scoped>

h3{
  color: #10707f;
}

</style>
