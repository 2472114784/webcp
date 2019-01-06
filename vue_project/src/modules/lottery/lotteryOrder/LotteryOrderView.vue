<template>
  <div class="global-flex-column-content-start">
    <v-lottery-order-item v-for="(item,index) in lotteryOrderEntity" :key="index" :lotteryOrderEntity="item"/>
  </div>
</template>

<script>
  import LotteryOrderItemView from './LotteryOrderItemView'
  import LotteryOrderApi, * as LotteryOrderApiAll from '../../../common/http/api/LotteryOrderApi'
  export default {
    name: "LotteryOrderView",
    data() {
      return {
        lotteryOrderEntity: Array,
      }
    },
    components: {
      'v-lottery-order-item': LotteryOrderItemView,
    },
    mounted() {
      this.$http(LotteryOrderApi.selectLotteryOrder(LotteryOrderApiAll.ORDER_STATUS_ALL, 0)).then(data => {
        console.log('订单请求结果', data)
        this.lotteryOrderEntity = data;
      })
    }
  }
</script>

<style scoped>

</style>
