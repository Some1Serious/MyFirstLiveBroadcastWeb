<template>
  {{classId}}
</template>

<script>
export default {
  name: "classUser",

  data(){
    return{
      classId : this.$route.query.id
    }
  },

  computed:{

  }
}
</script>

<style scoped>

</style>