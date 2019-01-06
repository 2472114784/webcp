<template>
  <el-row>
    <el-col :span="4">
      <div class="global-font-small-gray">{{lotteryOrderEntity.createTime}}</div>
    </el-col>
    <el-col :span="3">
      <div class="global-font-small-gray">{{lotteryOrderEntity.lotteryTypeName}}</div>
    </el-col>
    <el-col :span="3">
      <div class="global-font-small-gray">{{lotteryOrderEntity.lotteryChildName}}</div>
    </el-col>
    <el-col :span="3">
      <div class="global-font-small-gray">{{lotteryOrderEntity.lotteryNo}}</div>
    </el-col>
    <el-col :span="3">
      <div class="global-font-small-gray">{{lotteryOrderEntity.lotteryResult}}</div>
    </el-col>
    <el-col :span="3">
      <div class="global-font-small-gray">{{lotteryOrderEntity.lotteryValue}}</div>
    </el-col>
    <el-col :span="2">
      <div class="global-font-small-gray">{{lotteryOrderEntity.totalMoney}}</div>
    </el-col>
    <el-col :span="3">
      <div class="global-font-small-gray">{{lotteryOrderEntity.orderStatus}}</div>
    </el-col>
  </el-row>
</template>

<script>

  export default {
    name: "LotteryOrderItemView",
    props: {
      lotteryOrderEntity: {
        lotteryNo: 0,
        lotteryResult: String,
        orderNum: 0,
        orderItemMoney: 0,
        orderType: 0,
        orderStatus: 0,
        userId: 0,
        lotteryTypeId: 0,
        lotteryTypeName: String,
        lotteryChildId: 0,
        lotteryChildName: String,
        lotteryValue: String,
        orderChildJson: String,
        totalMoney: 0,
        winMoney: 0,
        createTime: String,
        updateTime: String,
      }
    }
  }
</script>

<style lang="less" scoped>

  .common-text:extend(.global-font-small-gray) {
    display: block;
  }

  .el-row {
    margin-bottom: 5pt;

    &:last-child {
      margin-bottom: 0;
    }
  }

  .el-col {
    display: flex;
    justify-content: flex-start;
  }

</style>
