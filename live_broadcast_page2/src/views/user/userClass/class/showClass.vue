
<template>
  <display_stuff>
    <div id="display">

      <div v-if="classList.length !== 0" class="row">

        <clazz v-for="item in classList" :key="item.id"
               :clazzProp='item'></clazz>

      </div>
    </div>
  </display_stuff>
</template>

<script>

import display_stuff from "@/components/display_stuff.vue";
import clazz from "@/views/user/userClass/class/class.vue"
import {useClassStore} from "@/store/classStore";
import $ from "jquery";

export default {
  name: "showClass",

  components:{display_stuff,clazz},

  data(){
    return{
      classList : []
    }
  },

  mounted() {

    useClassStore().getAllClass()
        .then( req=>{

          console.log(req)
          this.classList = req

        })
        .catch( error=>{

          if (error.status == 404){
            $("#display").append(`

            <h1 class="mt-md-5 text-center text-danger">404!</h1>
            <h1 class="mt-md-5 text-center ">${error.data.message}</h1>

            `);
          }

        })
  }
}
</script>

<style scoped>

</style>