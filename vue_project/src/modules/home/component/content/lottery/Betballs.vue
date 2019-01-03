<template>
  <div id="bet_balls">
    <div class="global-flex-row-content-start-items-center">
      <v-betball v-for="(item,index) in this.lotteryBalls.lotteryItemEntityList" :key='index' :lotteryItemBall='item'/>
    </div>
  </div>

</template>
<script>
  import betball from "./Betball";

  export default {
    name: 'betballs',
    props: {
      lotteryBalls: Array,
    },
    data() {
      return {}
    },
    components: {
      'v-betball': betball,
    }
  }
</script>
<style scoped>
</style>


