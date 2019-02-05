<template>
  <div class="global-flex-row-content-center-items-center lottery-child-menu-container">
    <p :class="['lottery-child-menu-item',{'lottery-child-menu-item-hover':index==selectedIndex}]"
       v-for="(item,index) in lotteryChildClassEntity.lotteryChildList" :key="index"
       @click="onChangeClickListener(index,item)">{{item.lotteryChildName}}</p>
  </div>
</template>

<script>
  export default {
    name: "LotteryChildMenuView",
    data() {
      return {
        selectedIndex: 0,
        lotteryChildEntity: null,
      }
    },
    props: {
      lotteryChildClassEntity: null,
      onClickCallback: null,
    },
    methods: {
      onChangeClickListener: function (index, lotteryChildEntity) {
        console.log("点击了selectedIndex", index);
        this.selectedIndex = index;
        this.lotteryChildEntity = lotteryChildEntity;
        if (this.onClickCallback) {
          this.onClickCallback(lotteryChildEntity)
        }
      }
    }
  }
</script>

<style lang="less" scoped>
  lottery-child-menu-container {
    height: 30px;
    background-color: @globalLightGray;
  }

  lottery-child-menu-item {
    color: @globalBlue;
    width: 50px;
  }

  lottery-child-menu-item:hover {
    color: #cc0031;
  }
</style>
