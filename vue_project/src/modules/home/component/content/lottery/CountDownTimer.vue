<template>
  <div class="count_down_container">
    <p class="count_down_timer">{{sHour}}:{{sMinut}}:{{sSecond}}</p>
  </div>
</template>
<script>
  import {$timeManager} from '../providers/TimeManager.js'
  import {setInterval} from 'timers';

  export default {
    name: "countDownTimer",
    data() {
      return {
        endTimeStamp: 0,
        sHour: '00',
        sMinut: '00',
        sSecond: '00',
      }
    },
    methods: {
      countDown: function () {
        let serverTimeStamp = $timeManager.getServerTime();
        let offerTimeStamp = this.endTimeStamp - serverTimeStamp;
        console.log(offerTimeStamp)
        if (offerTimeStamp > 0) {
          //显示倒计时
          [this.sHour, this.sMinut, this.sSecond] = $timeManager.timetrans(offerTimeStamp);
        } else {
          //需要请求最新彩票数据
          [this.sHour, this.sMinut, this.sSecond] = ['00', '00', '00']
        }
        const that = this
        setTimeout(function () {
          that.countDown()
        }, 1000)
      }
    },
    mounted: function () {
      this.countDown();
    }
  }
</script>

<style scoped>
  .count_down_container {
    display: flex;
  }

  .count_down_timer {
    color: brown;
    font-size: 20px;
  }
</style>


