<template>

  <div class="row">
    <div class="col-md-1 justify-content-center">
      <font-awesome-icon :icon="iconSwitch" size="lg" :style="colorChange" />
    </div>

    <div class="col">
      {{nickname}}
    </div>

  </div>


</template>

<script>
export default {
  name: "user",

  data(){
    return{

    }
  },

  computed:{
    iconSwitch(){

      if (this.isCreator){
        return "fa-solid fa-chalkboard-user"
      }

      switch (this.sex){
        case 'MALE':
          return "fa-solid fa-person"
        case 'FEMALE':
          return "fa-solid fa-person-dress"
        case 'UNKNOWN':
          return "fa-solid fa-snowman"
      }

    },
    colorChange(){

      switch (this.role){
        case 'student':
          return {}
        case 'teacher':
          return {color: 'red'}
      }

    },
  },

  methods:{





  },

  props:{

    sex:{
      type: String,
      default: 'UNKNOWN'
    },

    nickname:String,

    id:String,

    isCreator:{
      type:Boolean,
      default: false
    },

    role:{
      type:String,
      default: 'student'
    }

  }
}
</script>

<style scoped>

</style>