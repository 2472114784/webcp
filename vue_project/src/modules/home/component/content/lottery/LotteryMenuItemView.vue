<template>
  <div>
    <p
      :class="['global-flex-row-content-center-items-center','lotteryMenuItemView',{'lotteryMenuItemSelected':selectedIndex==lotteryMenuIndex}]"
      @click="changeMenuItemIndex(lotteryMenuIndex)">{{lotteryChildEntity.lotteryChildName}}</p>
  </div>
</template>

<script>
  export default {
    name: "LotteryMenuItemView",

    props: {
      lotteryChildEntity: {},
      selectedIndex: 0,
      lotteryMenuIndex: 0,
      changeMenuIndexCallback: Function,
    },
    methods: {
      changeMenuItemIndex: function (index) {
        this.changeMenuIndexCallback(index)
      }
    },
    mounted() {
      console.log('lotteryChildEntity', this.lotteryChildEntity)
    }
  }
</script>

<style lang="less" scoped>
  .lotteryMenuItemView {
    width: 100pt;
    height: 50pt;
    background-color: #1D8CE0;
  }

  .lotteryMenuItemSelected {
    background-color: white;
  }
</style>
