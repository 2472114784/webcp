<template>
  <div>
    <el-table
      :data="createOrderEntities"
      style="width: 100%">
      <el-table-column
        prop="lotteryChildName"
        label="彩种">
      </el-table-column>
      <el-table-column
        prop="lotteryNo"
        label="期数">
      </el-table-column>
      <el-table-column
        prop="lotteryValueShow"
        label="选择">
      </el-table-column>
      <el-table-column
        prop="money"
        label="总金额">
      </el-table-column>

    </el-table>
  </div>
</template>
return {money: money, lotteryChildId: lotteryChildEntity.lotteryChildId, lotteryValue: orderValue,lotteryChildName:lotteryChildEntity.lotteryChildName}
<script>
  export default {
    name: "LotteryPreOrderView",
    props: {
      createOrderEntities: null,
    }
  }
</script>

<style scoped>

</style>
