<template>
  <div id="lottery-content" class="global-box-column-flex-start global-layout-width">
    <v-login/>
    <div class="global-flex-row-content-start">
      <v-lottery-class-item style="height: 150pt" v-for="item in [1,2,3,4]"/>
    </div>
    <v-lottery-menu class="global-flex-row-content-start global-layout-width" :lotteryEntity="lotteryEntity"
                    :changeMenuIndexCallback="changeMenuIndexCallback" :selectedIndex="selectedIndex"/>
    <div class="global-flex-row-content-start">
      <div class="global-flex-column-content-start ball-container">
        <div id="balls">
          <v-betballs v-for="(item,index) in this.lotteryChildEntity.orderList" :lotteryBalls="item" :key="index"/>
        </div>
        <div class="global-flex-row-content-start-items-center">
          <v-unit :unitChangeSelectedCallback="unitChangeSelectedCallback"/>
          <v-num :numChangeCallback="numChangeCallback"/>
          <p>订单注数总共:{{getOrderNum}} </p>
          <p>总金额:{{getTotalMoney}}</p>
          <el-button type="primary" @click="commitOrder">快速投注</el-button>
          <el-button type="primary" @click="addOrder">添加号码</el-button>
        </div>
      </div>
      <v-lottery-result/>
    </div>
    <v-lottery-order/>

  </div>
</template>
<script>
  import betballs from './Betballs'
  import dataUtils from '../../../../../common/util/DataUtils'
  import LotteryMenuView from './LotteryMenuView'
  import LotteryMenuItemView from './LotteryMenuItemView'
  import LotteryOrderView from '../../../../lottery/lotteryOrder/LotteryOrderView'
  import LotteryOrderApi from '../../../../../common/http/api/LotteryOrderApi'
  import LoginView from '../../../../login/login/LoginView'
  import LotteryResultView from '../../../../lottery/lotteryResult/LotteryResultView'
  import UnitView, {TYPE_YUAN, TYPE_JIAO, TYPE_FEN} from './UnitView'
  import NumView from './NumView'
  import LotteryClassItemView from '../../../../lottery/lotteryClassView/LotteryClassItemView'

  export default {
    data() {
      return {
        lotteryEntity: null,
        lotteryChildEntity: null,
        selectedIndex: 0,
        unitType: TYPE_YUAN,
        num: 1,
      }
    },
    components: {
      'v-betballs': betballs,
      'v-lottery-menu': LotteryMenuView,
      'v-lottery-menu-item': LotteryMenuItemView,
      'v-lottery-order': LotteryOrderView,
      'v-login': LoginView,
      'v-lottery-result': LotteryResultView,
      'v-unit': UnitView,
      'v-num': NumView,
      'v-lottery-class-item': LotteryClassItemView,
    },
    computed: {
      getOrderNum: function () {
        return dataUtils.computeOrderNumForLotteryEntity(this.lotteryEntity)
      },
      getTotalMoney: function () {
        let scale = 1.0;
        switch (this.unitType) {
          case TYPE_YUAN:
            scale = 1.0;
            break;
          case TYPE_JIAO:
            scale = 0.1;
            break;
          case TYPE_FEN:
            scale = 0.01;
            break;
        }
        return dataUtils.computeOrderNumForLotteryEntity(this.lotteryEntity) * scale * this.num;
        // switch () {
        //
        // }
      }
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
      },
      numChangeCallback: function (num) {
        this.num = num;
      },
      unitChangeSelectedCallback: function (type) {
        this.unitType = type;
      },
      /**
       * 提交订单
       */
      commitOrder: function () {
        this.$http(LotteryOrderApi.createLotteryOrder(lotteryEntity))
      },
      /**
       * 添加订单
       */
      addOrder: function () {

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

  .ball-container {
    width: 800pt;
    height: 250pt;
  }
</style>
