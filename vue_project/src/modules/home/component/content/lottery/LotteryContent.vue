<template>
  <div id="lottery-content" class="global-box-column-flex-start global-layout-width">
    <v-lottery-order/>
    <!--<v-lottery-menu class="global-flex-row-content-start global-layout-width" :lotteryEntity="lotteryEntity"-->
    <!--:changeMenuIndexCallback="changeMenuIndexCallback" :selectedIndex="selectedIndex"/>-->
    <!--&lt;!&ndash;<v-lottery-menu-item />&ndash;&gt;-->
    <!--<div id="balls">-->
    <!--<v-betballs v-for="(item,index) in this.lotteryChildEntity.orderList" :lotteryBalls="item" :key="index"/>-->
    <!--</div>-->
  </div>
</template>
<script>
  import betballs from './Betballs'
  import dataUtils from '../../../../../common/util/DataUtils'
  import LotteryMenuView from './LotteryMenuView'
  import LotteryMenuItemView from './LotteryMenuItemView'
  import LotteryOrderView from '../../../../lottery/lotteryOrder/LotteryOrderView'
  import LotteryOrderApi from '../../../../../common/http/api/LotteryOrderApi'
  export default {
    data() {
      return {
        lotteryEntity: null,
        lotteryChildEntity: null,
        selectedIndex: 0,
      }
    },
    components: {
      'v-betballs': betballs,
      'v-lottery-menu': LotteryMenuView,
      'v-lottery-menu-item': LotteryMenuItemView,
      'v-lottery-order': LotteryOrderView,
    },
    methods: {
      http_lottery: function () {
        this.$http(LotteryOrderApi.getLotteryById(30000)).then(data => {

          this.lotteryEntity = data;
          let lotteryChilds = this.lotteryEntity.lotteryChilds;
          for (let i = 0; i < lotteryChilds.length; i++) {
            this.handlerLotteryChildEntity(lotteryChilds[i]);
            if (i === this.selectedIndex) {
              this.lotteryChildEntity = lotteryChilds[i];
            }
          }
        })
      },
      handlerLotteryChildEntity: function (lotteryChildEntity) {
        let orderList = dataUtils.createLotteryChildChooseData(lotteryChildEntity);
        lotteryChildEntity.orderList = orderList;
      },
      changeMenuIndexCallback: function (index) {
        if (typeof (index) == "undefined") {
          return;
        }
        this.selectedIndex = index;
        this.lotteryChildEntity = this.lotteryEntity.lotteryChilds[this.selectedIndex];
        console.log('index', index)
      }


    },
    mounted() {
      this.http_lottery();
      console.log("请求数据");
    }
  }
</script>
<style lang="less" scoped>
  .lotteryMenu {
    width: 1000pt;
    height: 200pt;
    background-color: #20A0FF;
  }
</style>
