<script setup>

</script>

<template>

    <div class="bg-dark shadow-lg" id="board">
      <slot></slot>
    </div>

</template>

<script>
export default {
  name: "display_stuff",

  methods:{



  }
}
</script>

<style scoped>
#board{
  min-height: 30rem;
  border-radius: 1rem;
  padding: 1rem;
}
.shadow-lg{
  box-shadow: 0rem 1rem 1rem rgb(167, 175, 232) !important;
}

</style>