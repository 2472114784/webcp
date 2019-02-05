<template>
  <div class="global-flex-row-content-start-items-center" v-if="lotteryEntity">
    <v-lottery-menu-item-view v-for="(item,index) in lotteryEntity.lotteryChildClassEntities"
                              :lotteryChildClassEntity="item"
                              :key="index"
                              :lotteryMenuIndex="index" :selectedIndex="selectedIndex"
                              :changeMenuIndexCallback="changeMenuIndexCallback"/>
  </div>
</template>

<script>
  import LotteryMenuItemView from './LotteryMenuItemView'

  export default {
    name: "LotteryMenuView",
    data() {
      return {

      }

    },
    props: {
      lotteryEntity: null,
      selectedIndex: 0,
      changeMenuIndexCallback: Function,
    },
    components: {
      'v-lottery-menu-item-view': LotteryMenuItemView,
    }
    ,
    methods: {},

  }
</script>

<style scoped>

</style>
