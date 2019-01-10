
<template>
  <el-table
    :data="lotteryResult"
    style="width: 100%">
    <el-table-column
      prop="lotteryId"
      label="彩种"
      width="180">
    </el-table-column>
    <el-table-column
      prop="openNo"
      label="期号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="openDate"
      label="开奖时间"
      width="180">
    </el-table-column>
    <el-table-column
      prop="openResult"
      label="开奖结果"
      width="180">
    </el-table-column>
  </el-table>
</template>

<script>
  /**
   *  private long openNo;
   private String openDate;
   private String openResult;
   @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
   private Timestamp createTime;
   @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
   private Timestamp updateTime;
   private long lotteryId;//所属于那个菜种
   private int lotteryResultType;//显示样式类型
   */
  import LotteryResultApi from '../../../common/http/api/LotteryResultApi'

  export default {
    name: "LotteryResultView",
    data() {
      return {
        lotteryResult: [],
      }
    },
    methods: {
      httpLotteryResult: function () {
        this.$http(LotteryResultApi.getListLotteryResultByLotteryId(30000, 0)).then(data => {
          console.log("请求开奖结果数据", data)
          this.lotteryResult = data;
        })
      }
    },
    mounted() {
      console.log("请求开奖结果")
      this.httpLotteryResult();
    }
  }
</script>

<style lang="less" scoped>
  @import url("//unpkg.com/element-ui@2.4.11/lib/theme-chalk/index.css");

</style>
