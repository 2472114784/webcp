<template>
  <div id="bet_balls">
    <v-betball-menu :change-index-callback="changeIndexCallback"/>
    <div class="global-flex-row-content-start-items-center">
      <v-betball v-for="(item,index) in this.lotteryBalls" :key='index' :lotteryItemBall='item'/>
    </div>
  </div>

</template>
<script>
  import betball from "./Betball";
  import BetballMenu, * as BetballMenuViewConfig from './BetballMenuView'

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
      'v-betball-menu': BetballMenu,
    },

    computed: {

      /**
       * TODO
       *
       * @param index
       */
      changeIndexCallback: function (index) {
        console.log("changeIndexCallback", this.lotteryBalls.length, typeof this.lotteryBalls);
        for (let i = 0; i < this.lotteryBalls.length; i++) {
          let lotteryBall = this.lotteryBalls[i];
          console.log(lotteryBall)
          let number = parseInt(lotteryBall.lotteryItemName);
          switch (index) {
            case BetballMenuViewConfig.TYPE_DAN:
              lotteryBall.selected = this.changeIndexForDan(number);
              break;
            case BetballMenuViewConfig.TYPE_SHUANG:
              lotteryBall.selected = this.changeIndexForShuang(number);
              break;
            case BetballMenuViewConfig.TYPE_DA:
              lotteryBall.selected = this.changeIndexForDa(number);
              break;
            case BetballMenuViewConfig.TYPE_XIAO:
              lotteryBall.selected = this.changeIndexForXiao(number);
              break;
            case BetballMenuViewConfig.TYPE_CLEAR:
              lotteryBall.selected = false;
              break;
          }
        }
      },
      changeIndexForDan: function (number) {
        return number & number / 2 != 0;
      },
      changeIndexForShuang: function (number) {
        return number & number / 2 == 0;
      },
      changeIndexForDa: function (number) {
        return number & number >= 5;
      },
      changeIndexForXiao: function (number) {
        return number & number < 5;
      }

    }

  }
</script>
<style scoped>
</style>


