<template>
  <div id="bet_balls">
    <v-betball-menu :changeIndexCallback="this.changeIndexCallback"/>
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
    methods: {
      /**
       * TODO
       *
       * @param index
       */
      changeIndexCallback: function (index) {
        console.log("index", index);
        for (let i = 0; i < this.lotteryBalls.length; i++) {
          let lotteryBall = this.lotteryBalls[i];
          let number = parseInt(lotteryBall.lotteryItemName);
          console.log('number', number)

          switch (index) {
            case BetballMenuViewConfig.TYPE_DAN:
              lotteryBall.selected = this.changeIndexForDan(number);
              console.log("dan", lotteryBall.selected)

              break;
            case BetballMenuViewConfig.TYPE_SHUANG:

              lotteryBall.selected = this.changeIndexForShuang(number);
              console.log("shuang", lotteryBall.selected)
              break;
            case BetballMenuViewConfig.TYPE_DA:

              lotteryBall.selected = this.changeIndexForDa(number);
              console.log("da", lotteryBall.selected)
              break;
            case BetballMenuViewConfig.TYPE_XIAO:
              lotteryBall.selected = this.changeIndexForXiao(number);
              console.log("xiao", lotteryBall.selected)
              break;
            case BetballMenuViewConfig.TYPE_CLEAR:
              lotteryBall.selected = false;
              console.log("clear", lotteryBall.selected)

              break;
          }
        }
      },
      changeIndexForDan: function (number) {
        return number % 2 != 0;
      },
      changeIndexForShuang: function (number) {
        return number % 2 == 0;
      },
      changeIndexForDa: function (number) {
        return number >= 5;
      },
      changeIndexForXiao: function (number) {
        return number < 5;
      }
    },
    mounted() {
      // this.$EventBus.$on('change',(index)=>{
      //   console.log('jjjjj',this.lotteryBalls)
      //   this.changeIndexCallback(index)})
    }

  }
</script>
<style scoped>
</style>


