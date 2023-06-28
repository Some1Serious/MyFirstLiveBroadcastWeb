<template>
  <div class="text-center h1 text-info">课程表</div>
  <hr>
  <div class="position-relative timeTable">
    <TimeTable :is-teacher="isTeacher"></TimeTable>
  </div>


</template>

<script setup>
  import TimeTable from "@/views/class_schedule/timeTable.vue";


</script>

<script>
  export default {
    props:{
      isTeacher:{
        type:Boolean,
        default:false
      }
    }
  }

</script>

<style scoped>

.timeTable{
  height: 35rem;
  width: 100%;
}

</style>