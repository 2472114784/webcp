<template>
  <div id="lottery-content" class="global-box-column-flex-start global-layout-width">
    <v-login/>
    <!--<v-register/>-->
    <!--<div class="global-flex-row-content-start">
      <v-lottery-class-item style="height: 150pt" v-for="item in [1,2,3,4]"/>
    </div>-->
    <v-lottery-menu class="global-flex-row-content-start global-layout-width" :lotteryEntity="lotteryEntity"
                    :changeMenuIndexCallback="changeMenuIndexCallback"
                    :selectedIndex="selectedIndexForLotteryChildClassEntity"/>
    <v-lottery-child-menu class="global-flex-row-content-start global-layout-width"
                          :lotteryChildClassEntity="lotteryChildClassEntity"
                          :onClickCallback="onLotteryChildChangeCallBack"/>
    <div class="global-flex-row-content-start">
      <div class="global-flex-column-content-start ball-container">
        <div id="balls" v-if="lotteryChildEntity">
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
        <v-lottery-pre-order :createOrderEntities="createOrderEntities"/>
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
  import IMModule from '../../../../../common/im/IMModule'
  import RegisterView from '../../../../login/register/RegisterView'
  import LotteryChildMenuView from "./LotteryChildMenuView";
  import LotteryPreOrderView from "./LotteryPreOrderView";


  export default {
    data() {
      return {
        lotteryEntity: null,
        lotteryChildClassEntity: null,
        lotteryChildEntity: null,
        createOrderEntities: [],
        selectedIndexForLotteryChildClassEntity: 0,
        selectedIndexForLotteryChildEntity: 0,
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
      'v-register': RegisterView,
      'v-lottery-child-menu': LotteryChildMenuView,
      'v-lottery-pre-order': LotteryPreOrderView,
    },
    computed: {
      getOrderNum: function () {
        if (!this.lotteryChildEntity) {
          return 0;
        }
        return dataUtils.computeOrderNumForLotteryChildEntity(this.lotteryChildEntity)
      },
      getTotalMoney: function () {
        if (!this.lotteryChildEntity) {
          return 0;
        }
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
        return dataUtils.computeOrderNumForLotteryChildEntity(this.lotteryChildEntity) * scale * this.num;
        // switch () {
        //
        // }
      }
    },
    methods: {
      http_lottery: function () {
        this.$http(LotteryOrderApi.getLotteryById(300000000)).then(data => {
          this.lotteryEntity = data;
          dataUtils.createLotteryChildChooseDataForLotteryEntity(this.lotteryEntity);
          this.lotteryChildClassEntity = this.lotteryEntity.lotteryChildClassEntities[this.selectedIndexForLotteryChildClassEntity];
          this.lotteryChildEntity = this.lotteryChildClassEntity.lotteryChildList[this.selectedIndexForLotteryChildEntity];

          console.log("http结果数据处理", this.lotteryEntity, this.lotteryChildClassEntity, this.lotteryChildEntity)
        })
      },

      changeMenuIndexCallback: function (index, lotteryChildClassEntity) {
        if (typeof (index) == "undefined") {
          return;
        }
        this.selectedIndexForLotteryChildClassEntity = index;
        this.selectedIndexForLotteryChildEntity = 0;
        this.lotteryChildClassEntity = lotteryChildClassEntity;
        this.lotteryChildEntity = this.lotteryChildClassEntity.lotteryChildList[this.selectedIndexForLotteryChildEntity];
        console.log('index', index, lotteryChildClassEntity)
      },
      onLotteryChildChangeCallBack: function (lotteryChildEntity) {
        if (lotteryChildEntity) {
          this.lotteryChildEntity = lotteryChildEntity;
        }
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
        let commitOrderData = dataUtils.createCommitOrderData(this.lotteryEntity.lotteryNo, this.num, this.lotteryChildEntity);
        console.log("提交数据", [commitOrderData]);
        this.$http(LotteryOrderApi.createLotteryOrder([commitOrderData])).then(data => {
          console.log("提交订单返回数据", data);
        })
      },
      /**
       * 添加订单
       */
      addOrder: function () {
        let commitOrderData = dataUtils.createCommitOrderData(this.lotteryEntity.lotteryNo, this.num, this.lotteryChildEntity);
        if (commitOrderData) {
          this.createOrderEntities.push(commitOrderData);
        }
      }


    },
    mounted() {
      this.http_lottery();
      console.log("请求数据");
      IMModule.init()
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
