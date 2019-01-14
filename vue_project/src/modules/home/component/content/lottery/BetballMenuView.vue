<template>
  <div class="global-flex-row-content-center-items-center betball-container">
    <p class="global-font-normal-white betball-item" v-for="(item,index) in menu" :key="index"
       @click="changeIndex(index)">{{item}}</p>
  </div>
</template>

<script>
  export const TYPE_DAN = 2;
  export const TYPE_SHUANG = 3;
  export const TYPE_DA = 0;
  export const TYPE_XIAO = 1;
  export const TYPE_CLEAR = 4;
  export default {
    name: "BetballMenuView",
    props: {
      changeIndexCallback: Function,
    },
    data() {
      return {
        checkIndex: 0,
        menu: ['大', '小', '单', '双', '清'],
      }
    },
    methods: {
      changeIndex: function (index) {
        this.checkIndex = index;
        this.changeIndexCallback(this.checkIndex);
      }
    }
  }
</script>

<style lang="less" scoped>
  .betball-container {
    height: 20pt;
    border-radius: 10pt;
    background-color: @globalGray;
  }

  .betball-item {
    width: 50pt;
  }

  .betball-item:hover {

    color: @globalBlue;
  }
</style>
