<template>
  <div id="home_content">
    <img src="../../../../../assets/image/home_head_img.jpeg"/>
    <div id="notice" class="global-layout-width">
      <p class="notice_text ">通知:xxxxxxxxxxxxxxxxxxxx</p>
    </div>
  </div>
</template>
<script>
  import banner from './Banner'

  export default {
    data() {
      return {
        time: "00:00:00",
        init_time: 0,
        countDown: 0,
      }
    },
    components: {
      'v-banner': banner,
    },
    methods: {
      getServerTime: function () {
        this.time = this.$timeManager.getServerTime();
      },
      setInitTime: function () {
        this.$timeManager.setServerTime(this.init_time);
      },
      registerCountDown: function () {
        console.log('点击开始倒计时')
        this.countDown++;
        this.$countDownManager.register(function () {
        })
      }
    }
  }
</script>
<style scoped>
  #home_content {
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: white;
    border-bottom: 1pt grey solid;
  }

  #notice {
    display: inline-flex;
    justify-content: flex-start;
    height: 50pt;
  }

  .notice_text {
    color: black;
    font-size: 12pt;
  }
</style>

