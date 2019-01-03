<template>
  <div id="lottery-content" class="global-box-column-flex-start global-layout-width">
    <div id="balls">
      <v-betballs v-for="(item,index) in lotteryData" :lotteryBalls="item" :key="index"/>
    </div>
  </div>
</template>
<script>
  import betballs from './Betballs'

  export default {
    data() {
      return {
        lotteryData: null,
      }
    },
    components: {
      'v-betballs': betballs,
    },
    methods: {
      http_lottery: function () {
        this.$server.examLottery().then(data => {
          console.log("data数据", data);
          this.lotteryData = data.lotteryChilds;
          console.log("数据", this.lotteryData);

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
