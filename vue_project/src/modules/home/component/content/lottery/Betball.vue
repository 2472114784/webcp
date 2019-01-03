<template>
  <div v-if="this.lotteryItemBall" id="bet_ball"
       :class="['bet-ball-normal',{'bet-ball-clicked':isClicked},{'bet-ball-hover':!isClicked&&isMouseOver}]"
       @click="selectedChange" @mouseover="mouseOverStatus" @mouseout="mouseOutStatus">
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
        'isClicked': false,
        'isMouseOver': false
      }
    },
    methods: {
      selectedChange: function () {
        this.isClicked = !this.isClicked;
        console.log("isClicked", this.isClicked);
      },
      mouseOverStatus: function () {
        this.isMouseOver = true;
        console.log("进入", this.isMouseOver);
      },
      mouseOutStatus: function () {
        this.isMouseOver = false;
        console.log("离开", this.isMouseOver);
      }
    }
  }
</script>
<style lang="less" scoped>

  .bet-ball-normal:extend(.global-font-normal-gray) {
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


