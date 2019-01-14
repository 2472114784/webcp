<template>
  <div id="header_items">
    <div v-for="(item,index) in titles" :key="index">
      <v-header-item :header-item="item" @itemclick="onHeaderItemClick"/>
    </div>
  </div>
</template>
<script>
  import headerItem from './HomeHeaderItemView'

  export default {
    name: "header_items",
    data() {
      return {
        titles: [
          {
            title: "首页",
            type: 1,
          },
          {
            title: "彩票",
            type: 2,
          },
          {
            title: "优惠活动",
            type: 3,
          },
          {
            title: "关于我们",
            type: 4,
          }
        ]
      }
    },
    components: {
      'v-header-item': headerItem,
    },
    methods: {
      onHeaderItemClick: function (selectedHeaderType) {
        console.log("点击了" + selectedHeaderType)
        switch (selectedHeaderType) {
          case 1://首页
            this.$router.push('/home')
            break;
          case 2://购彩
            this.$router.push('/lottery')
            break;
          case 3:
            this.$router.push('/chat')
            break;
          default:
            break;
        }
      }
    }

  }
</script>
<style scoped>
  #header_items {
    display: flex;
    justify-content: flex-start;
    vertical-align: baseline;
  }
</style>

