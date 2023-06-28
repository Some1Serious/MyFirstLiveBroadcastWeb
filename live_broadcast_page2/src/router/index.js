import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import {useUserStore} from "@/store/userStore";
import {useUtilsStore} from "@/store/utils";
import {useWebsocketStore} from "@/store/websocket";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',

      component: () => import('../views/AboutView.vue')
    },

    {
      path: '/logIn',
      name: 'logIn',

      component: () => import('../views/logIn/logIn.vue')
    },

    {
      path: '/signIn',
      name: 'signIn',

      component:()=> import('../views/logIn/SignIn.vue')
    },

    {
      path: '/userMain',
      name: 'userMain',

      component:()=> import('../views/user/userSet/userMain.vue'),
      children: [
        {
          // 当 /user/:id/profile 匹配成功
          // UserProfile 将被渲染到 User 的 <router-view> 内部
          path: 'show',
          name: 'showUserInformation',
          component: ()=>import('../views/user/userSet/show.vue'),
        },

        {
          path: 'change',
          name: 'changeUserInformation',
          component: ()=>import('../views/user/userSet/change.vue'),
        },
      ],
      redirect:{name:"showUserInformation"}
    },

    {
      path: '/signIn',
      name: 'signIn',

      component:()=> import('../views/logIn/SignIn.vue')
    },

    {
      path: '/controllerClass',
      name: 'controllerClass',

      component:()=> import('../views/user/userClass/controllerClass.vue')
    },

    {
      path: '/video',
      name: 'video',

      component:()=> import('../views/videoDemo/videoDemo.vue'),

    },


    {
      path: '/test',
      name: 'test',
      component: () => import("@/views/test/test1.vue"),
    },
    //
    // {
    //   path: '/draw',
    //   name: 'draw',
    //   component: () => import("@/views/videoDemo/SvgDraw/draw.html"),
    // },

      // teacher------------------------------------
      // 路径格式, 原路径开头加teacher,
      // 命名格式, 原命名后加Teacher

    {
      path: '/teacher',
      name: 'homeTeacher',
      component: () => import("@/views/teacher/HomeViewTeacher.vue"),
    },

    {
      path: '/teacher/controllerClass',
      name: 'controllerClassTeacher',
      component: () => import("@/views/user/userClass/showClassTeacher.vue"),
    },

    {
      path:'/userClass',
      name:'userClass',
      component: ()=>import("@/views/user/userClass/classUserController/classUser.vue")
    }




  ]
})

//----------------全局守卫------------------------

import {nextTick} from 'vue'
router.beforeEach(  (to, from)=>{
  const userStore = useUserStore()
  const utilStore = useUtilsStore()

  console.log("router:" + useUserStore())
  console.log("router:" + to.name)

  function needToLogin(){
    console.log("router beforeEach")
    // utilStore.alert("未登录", "danger")
    utilStore.message("未登录", "danger")
    return { name: 'logIn' }
  }

  function checkLogin(){

    if (to.name === 'logIn' ||
        to.name === 'signIn') return true

    if (
        // 检查用户是否已登录
        // (!localStorage.getItem("userId")) &&
        !localStorage.getItem('userId')
        // ❗️ 避免无限重定向

    )  return needToLogin()
  }

   function checkRole(){

    const role = userStore.userRole

    if (!role) return needToLogin()

    // 判断是否有学生想进入教师页面
    if (role === userStore.role.student && to.path.split("/").includes('teacher')) {
      useUtilsStore().message('学生不可以进入教师管理页面', 'error')
      return {name:'home'}
    }

    if (role === userStore.role.teacher && router.hasRoute(to.name + 'Teacher')) return {name: to.name + 'Teacher'}

  }

    // main

    let temp

    temp = checkLogin()
    if (temp) return temp

    temp = checkRole()
    if (temp) return temp










    })

export default router
