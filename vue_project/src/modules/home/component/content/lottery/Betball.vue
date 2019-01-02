<template>
  <div v-if="this.lotteryItemBall" id="bet_ball"
       :class="['bet-ball-normal',{'bet-ball-clicked':this.lotteryItemBall.selected},{'bet-ball-hover':!this.lotteryItemBall.selected&&isMouseOver}]"
       @click="change" @mouseover="mouseOverStatus" @mouseout="mouseOutStatus">
    <p>{{this.lotteryItemBall.lotteryItemName}}</p>
  </div>
</template>

<script>
  export const TYPE_NUMBER = 0;//数字
  export const TYPE_PLUG = 1;//塞子

  export const TYPE_LOTTERY_NORMAL = 0;//单式
  export const TYPE_LOTTERY_DUPLEX = 1;//复式
  export const TYPE_LOTTERY_POSITION = 2;//定位

  export default {
    name: 'betball',
    props: {
      'lotteryItemBall': {
        'lotteryItemId': 0,
        'lotteryItemName': String,
        'desc': String,
        'lotteryItemValue': String,
        'lotteryDuplexNum': 0,
        'money': 0,
        'selected': false,
        'odds': 0,
        'lotteryOrderNum': 0,
        'lotteryChildType': 0,
      }
    },
    data() {
      return {
        isMouseOver: false
      }
    },
    methods: {
      change: function () {
        this.lotteryItemBall.selected = !this.lotteryItemBall.selected;
      },
      mouseOverStatus: function () {
        this.isMouseOver = true;
      },
      mouseOutStatus: function () {
        this.isMouseOver = false;
      }
    }
  }
</script>
<style lang="less" scoped>

  .bet-ball-normal:extend(.global-font-big-gray) {
    display: inline-flex;
    justify-content: center;
    border-radius: 50%;
    width: 30pt;
    height: 30pt;
    margin: 3pt;
    align-items: center;
    border: 2px solid @globalGray;
    background-color: @globalLightGray;
  }

  .bet-ball-clicked {
    border: 2px solid @colorMain;
    color: white;
    background-color: @colorMain;
  }

  .bet-ball-hover {
    border: 2px solid @colorMain;
    color: @colorMain;
  }
</style>


