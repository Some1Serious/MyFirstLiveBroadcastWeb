<template>


  <nav class="navbar navbar-expand-md bg-dark navbar-dark">

<!--    container是为了让导航栏随屏幕而移动, 注意是套在nav里面的-->
    <div class="container">
      <a href="#" class="navbar-brand" style="font-size: 40px">直播Demo</a>

      <!--    改动页面的汉堡按钮-->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navMenu" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <!--    <div class="container">-->
      <template v-if="is_home_show">
        <a class="nav-link text-primary" ref="home" href="/">Home</a>
      </template>


      <!--    下面的id按钮是为了与上面的汉堡按钮绑定-->
      <div class="collapse navbar-collapse" id="navMenu">

        <!--        margin left auto-->
        <ul class="navbar-nav ml-auto">

          <template v-if="is_search_class_show">
            <class-search></class-search>
          </template>

          <template v-if="is_SignIn_show">
            <router-link class="nav-link" to="/SignIn">注册</router-link>
          </template>

          <template v-if="is_logIn_show">
            <router-link class="nav-link" to="/logIn">登录/注册</router-link>
          </template>


          <template v-if="is_student_stuff_show">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle user-select-none" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                我的内容
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="#">我的公告</a>
                <a class="dropdown-item" role="button" @click="$router.push({name:'controllerClass'})">我的班级</a>
                <a class="dropdown-item" href="#">我的笔记</a>
              </div>
            </li>
          </template>


          <template v-if="is_self_setting_show">
            <router-link class="nav-link" :to="{name:'showUserInformation'}">我的设置</router-link>
          </template>

      </ul>
        <!--      </div>-->

      </div>
      </div>




  </nav>
  <br>






</template>


<script>
import ClassSearch from "@/components/classSearch.vue";
export default {
  components: {ClassSearch},
  data () {
    return {

    }
  },

  props:{
    is_home_show:{
      type:Boolean,
      default: true,
    },

    is_search_class_show: {
      type: Boolean,
      default: false
    },
    is_logIn_show: {
      type: Boolean,
      default: false
    },
    is_student_stuff_show: {
      type: Boolean,
      default: false
    },
    is_self_setting_show: {
      type: Boolean,
      default: false
    },

    is_SignIn_show:{
      type:Boolean,
      default: false
    }
  },


  mounted() {



  },
  methods: {

  }
}
</script>

<style scoped>

nav{
  border-radius: 20px;
  font-size: 20px;
}

</style>
