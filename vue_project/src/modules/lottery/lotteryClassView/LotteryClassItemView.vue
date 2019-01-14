<template>
  <div class="global-flex-row-content-start ">
    <div>ssc</div>
    <div class="global-flex-column-content-start lottery-class-items">
      <p v-for="(item,index) in arr" :key="index">{{item}}</p>
    </div>
  </div>
</template>

<script>
  export default {
    name: "LotteryClassItemView",
    data() {
      return {
        arr: ['1111', '2222', '3333', '4444', '5555', '6666', '7777', '8888', '9999']
      }
    },
  }
</script>

<style scoped>
  .lottery-class-items {
    flex-wrap: wrap;

  }
</style>
