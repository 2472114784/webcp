<template>
  <div id="lottery-content" class="global-box-column-flex-start global-layout-width">
    <div id="balls">
      <v-betballs v-for="(item,index) in this.orderList" :lotteryBalls="item" :key="index"/>
    </div>
  </div>
</template>
<script>
  import betballs from './Betballs'
  import dataUtils from '../../../../../common/util/DataUtils'
  export default {
    data() {
      return {
        lotteryData: null,
        orderList: Array,
      }
    },
    components: {
      'v-betballs': betballs,
    },
    methods: {
      http_lottery: function () {
        this.$server.examLottery().then(data => {
          this.lotteryData = data.lotteryChilds[0];
          this.orderList = dataUtils.createLotteryChildChooseData(this.lotteryData);
          data.lotteryChilds[0].orderList = this.orderList;
          console.log("orderList", this.orderList, "child", this.lotteryData)
        })
      }
    },
    mounted() {
      this.http_lottery();
      console.log("请求数据");
    }
  }
</script>
<style scoped>
</style>
