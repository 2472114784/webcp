<template>
  <div>
    <el-table
      :data="lotteryOrderEntity"
      style="width: 900pt">
      <el-table-column
        prop="createTime"
        label="创建时间"
        width="200">
      </el-table-column>
      <el-table-column
        prop="lotteryTypeName"
        label="彩种"
        width="100">
      </el-table-column>
      <el-table-column
        prop="lotteryChildName"
        label="类型"
        width="100">
      </el-table-column>
      <el-table-column
        prop="lotteryNo"
        label="期数"
        width="100">
      </el-table-column>
      <el-table-column
        prop="lotteryResult"
        label="开奖号码"
        width="100">
      </el-table-column>
      <el-table-column
        prop="lotteryValue"
        label="选择"
        width="100">
      </el-table-column>
      <el-table-column
        prop="totalMoney"
        label="总金额"
        width="100">
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
