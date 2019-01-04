<template>
  <div>
    <p v-for="(item,index) in menu" :key="index" @click="changeIndex(index)">{{item}}</p>
  </div>
</template>

<script>
  export const TYPE_DAN = 0;
  export const TYPE_SHUANG = 1;
  export const TYPE_DA = 2;
  export const TYPE_XIAO = 3;
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
        if (this.)
          this.changeIndexCallback(this.checkIndex);
      }
    }
  }
</script>

<style lang="less" scoped>

</style>
