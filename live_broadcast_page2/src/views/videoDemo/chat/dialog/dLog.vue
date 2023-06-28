<template>


  <div class="container">

    <div class="row p-md-2" v-if="isSelf">
      <div class="frameRight col " >

        <div class="triangleRight"></div>
        <span class="rotationtiaoRight">{{ message }}</span>

      </div>

      <div class="col-1 align-self-center justify-content-center p-0 text-center border">
        {{character}}
      </div>

    </div>

    <div class="row p-md-2" v-if="!isSelf">

      <div class="col-1 align-self-center justify-content-center p-0 text-center border">
        {{character}}
      </div>
      <div class="frameLeft col" >

        <div class="triangleLeft"></div>
        <span class="rotationtiaoLeft">{{ message }}</span>

      </div>

    </div>


  </div>



</template>

<script>

export default {
  name: "dLog",

  props:{

    character:{
      type: String,
      default: "系统提示"
    },

    message:{
      type: String
    },

    isSelf:{
      type:Boolean,
      default: false
    }

  },
}
</script>

<style>

.frameRight{
  color: black;
  width: 100%;
  float: left;
  position: relative;
  padding: 0 5px 0 0;
  margin: 0.5rem 0 0.5rem 0;
}
.triangleRight{
  width:0;
  height:0;
  border-top:5px solid transparent;
  border-bottom:5px solid transparent;
  border-left:5px solid #98E165;
  position: absolute;
  right: 0;
  top: 6px;
}
.rotationtiaoRight{
  float: right;
  padding: 5px 10px;
  background-color: #98E165;
  font-size: 14px;
  border-radius: 2px;
  max-width: 70%;
}

.frameLeft{
  color: black;
  width: 100%;
  float: left;
  position: relative;
  padding: 0 0 0 5px;
  margin: 0.5rem 0 0.5rem 0;
}
.triangleLeft{
  width:0;
  height:0;
  border-top:5px solid transparent;
  border-bottom:5px solid transparent;
  border-right:5px solid #98E165;
  position: absolute;
  left: 1px;
  top: 6px;
}
.rotationtiaoLeft{
  float: left;
  padding: 5px 10px;
  background-color: #98E165;
  font-size: 14px;
  border-radius: 2px;
  max-width: 70%;
}


</style>
