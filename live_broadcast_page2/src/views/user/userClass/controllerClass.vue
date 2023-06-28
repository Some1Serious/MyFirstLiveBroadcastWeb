<script setup>
  import TopBorder from "@/components/topBorder.vue";
  import Display_stuff from "@/components/display_stuff.vue";
  import Class from "@/views/user/userClass/class/class.vue";
  import ShowClass from "@/views/user/userClass/class/showClass.vue";
</script>

<template>
  <div class="container mt-3">
    <top-border :is_search_class_show=true :is_self_setting_show=true :is_student_stuff_show=true></top-border>

    <div class="row">
      <button class="btn btn-outline-info offset-md-10" @click="flush">刷新</button>
    </div>
    <show-class>

    </show-class>
  </div>




</template>

<script>

export default {
  name: "controllerClass",

  data(){
    return{

    }
  },

  methods:{
    flush(){
      location.reload()
    }
  },

  mounted() {

  }

}
</script>

<style scoped>

</style>