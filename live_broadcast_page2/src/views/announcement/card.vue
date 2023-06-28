<template>
  <div class="card" style="max-height: 350px">
    <div class="card-body bg-dark">
      <h5 class="card-title">你有新的作业!</h5>
      <h6 class="card-subtitle mb-2 text-muted">张老师</h6>
      <p class="card-text small" id="content">赶紧写作业, 赶紧写作业(恶魔的低语), 赶紧写作业,赶紧写作业,赶紧写作业,赶紧写作业,赶紧写作业</p>
      <div class="row">
        <button class="btn btn-info col-4 offset-1">查看</button>
        <button class="btn btn-info col-4 offset-2">删除</button>
      </div>


    </div>
  </div>
</template>

<script>
export default {
  name: "card"
}
</script>

<style scoped>
#content{
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 4;
  overflow: hidden;
}

</style>