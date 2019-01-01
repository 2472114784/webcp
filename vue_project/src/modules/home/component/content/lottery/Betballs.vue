<template>
  <div id="bet_balls" v-if="lotteryBalls">
    <v-betball v-for="(item,index) in lotteryBalls.lotteryItemEntityList" :key='index' :lotteryItemBall='item'/>
  </div>
</template>
<script>
  import betball from "./Betball";

  export default {
    name: 'betballs',
    props: {
      'lotteryBalls': {
        'lotteryChildId': 0,
        'lotteryChildName': String,
        'desc': String,
        'lotteryChildType': 0,
        'lotteryChildDuplexNum': 0,
        'odds': 0,
        'money': 0,
        'lotteryItemEntityList': Array
      }
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
  #bet_balls {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    background-color: aquamarine;
  }
</style>


