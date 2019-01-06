<template>
  <div class="global-flex-row-content-center-items-center">
    <p v-for="(item,index) in menu" :key="index" @click="changeIndex(index)">{{item}}</p>
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
        console.log(this.checkIndex)
        // if (this.changeIndexCallback) {
        console.log("回调")
        // this.$EventBus.$emit('change', this.checkIndex);
        this.changeIndexCallback(this.checkIndex);
        // }
      }
    }
  }
</script>

<style lang="less" scoped>

</style>
