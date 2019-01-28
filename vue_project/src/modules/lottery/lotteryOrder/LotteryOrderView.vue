<template>
  <div>
    <el-table
      :data="lotteryOrderEntity"
      style="width: 100%">
      <el-table-column
        prop="createTime"
        label="创建时间">
      </el-table-column>
      <el-table-column
        prop="lotteryTypeName"
        label="彩种">
      </el-table-column>
      <el-table-column
        prop="lotteryChildName"
        label="类型">
      </el-table-column>
      <el-table-column
        prop="lotteryNo"
        label="期数">
      </el-table-column>
      <el-table-column
        prop="lotteryResult"
        label="开奖号码">
      </el-table-column>
      <el-table-column
        prop="lotteryValueShow"
        label="选择">
      </el-table-column>
      <el-table-column
        prop="totalMoney"
        label="总金额">
      </el-table-column>
      <el-table-column
        prop="orderStatus"
        label="状态">
      </el-table-column>

    </el-table>
  </div>
</template>

<script>
  import LotteryOrderApi, * as LotteryOrderApiAll from '../../../common/http/api/LotteryOrderApi'
  export default {
    name: "LotteryOrderView",
    data() {
      return {
        lotteryOrderEntity: [],
      }
    },
    components: {
    },
    mounted() {
      this.$http(LotteryOrderApi.selectLotteryOrder(LotteryOrderApiAll.ORDER_STATUS_ALL, 0)).then(data => {
        console.log('订单请求结果', data)
        this.lotteryOrderEntity = data;
      })
    }
  }
</script>

<style lang="less" scoped>
  @import url("//unpkg.com/element-ui@2.4.11/lib/theme-chalk/index.css");

</style>
