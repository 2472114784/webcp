import cloneDeep from 'clone-deep';
import * as LotteryOrderApiAll from '../http/api/LotteryOrderApi'
import LotteryOrderApi from "../http/api/LotteryOrderApi";

//数据处理utils
class DataUtils {

  createLotteryChildChooseDataForLotteryEntities(lotteryEntities) {
    if (lotteryEntities) {
      for (let i = 0; i < lotteryEntities.length; i++) {
        let tempLotteryEntity = lotteryEntities[i];
        this.createLotteryChildChooseDataForLotteryEntity(tempLotteryEntity);
      }
    }
  }

  createLotteryChildChooseDataForLotteryEntity(lotteryEntity) {
    if (lotteryEntity) {
      let tempLotteryChildClassEntities = lotteryEntity.lotteryChildClassEntities;
      for (let i = 0; i < tempLotteryChildClassEntities.length; i++) {
        let tempLotteryChildEntity = tempLotteryChildClassEntities[i];
        this.createLotteryChildChooseDataForLotteryChildClassEntity(tempLotteryChildEntity);
      }
    }
  }

  createLotteryChildChooseDataForLotteryChildClassEntity(lotteryChildClassEntity) {
    if (lotteryChildClassEntity) {
      let tempLotteryChildEntities = lotteryChildClassEntity.lotteryChildList;
      for (let i = 0; i < tempLotteryChildEntities.length; i++) {
        let tempLotteryChildEntity = tempLotteryChildEntities[i];
        tempLotteryChildEntity.orderList = this.createLotteryChildChooseDataForLotteryChildEntity(tempLotteryChildEntity);

      }
    }
  }

  /**
   *
   * @param lotteryChildEntity
   */
  createLotteryChildChooseDataForLotteryChildEntity(lotteryChildEntity) {
    if (lotteryChildEntity) {
      let result = [];
      switch (lotteryChildEntity.lotteryChildType) {
        case LotteryOrderApiAll.LOTTERY_CHILD_TYPE_NORMAL:
          result[0] = cloneDeep(lotteryChildEntity.lotteryItemEntityList);
          break;
        case LotteryOrderApiAll.LOTTERY_CHILD_TYPE_POSITION:
        case LotteryOrderApiAll.LOTTERY_CHILD_TYPE_DUPLEX:
          let duplexNum = lotteryChildEntity.lotteryChildDuplexNum;
          for (let i = 0; i < duplexNum; i++) {
            result[i] = cloneDeep(lotteryChildEntity.lotteryItemEntityList)
          }
          break;
      }
      return result;
    }
  }


  // computeOrderNumForLotteryEntity(lotteryEntity) {
  //   let count = 0;
  //   let lotteryChilds = lotteryEntity.lotteryChilds;
  //   for (let i = 0; i < lotteryChilds.length; i++) {
  //     let lotteryChild = lotteryChilds[i];
  //     count += this.computeOrderNumForLotteryChildEntity(lotteryChild);
  //   }
  //   return count;
  // }

  computeOrderNumForLotteryChildEntity(lotteryChildEntity) {
    let count = 0;
    switch (lotteryChildEntity.lotteryChildType) {
      case LotteryOrderApiAll.ORDER_TYPE_NORMAL:
        count = this.computeOrderNumForOrderListByNormal(lotteryChildEntity.orderList);
        break;
      case LotteryOrderApiAll.ORDER_TYPE_POSITION:
        count = this.computeOrderNumForOrderListByPosition(lotteryChildEntity.orderList);
        break;
      case LotteryOrderApiAll.ORDER_TYPE_DUPLEX:
        count = this.computeOrderNumForOrderListByDuplex(lotteryChildEntity.orderList);
        break;
    }
    return count;
  }

  computeOrderNumForOrderListByNormal(lotteryItemEntitiesArr) {
    if (!lotteryItemEntitiesArr) {
      return 0;
    }
    let count = 0;
    for (let i = 0; i < lotteryItemEntitiesArr.length; i++) {
      let lotteryItemEntities = lotteryItemEntitiesArr[i];
      count += this.computeSelectedNumForLotteryItemEntities(lotteryItemEntities);
    }
    return count;
  }

  computeOrderNumForOrderListByPosition(lotteryItemEntitiesArr) {
    if (!lotteryItemEntitiesArr) {
      return 0;
    }
    let count = 0;
    for (let i = 0; i < lotteryItemEntitiesArr.length; i++) {
      let lotteryItemEntities = lotteryItemEntitiesArr[i];
      count += this.computeSelectedNumForLotteryItemEntities(lotteryItemEntities);
    }
    return count;
  }

  computeOrderNumForOrderListByDuplex(lotteryItemEntitiesArr) {
    if (!lotteryItemEntitiesArr) {
      return 0
    }
    let count = 1;
    for (let i = 0; i < lotteryItemEntitiesArr.length; i++) {
      let lotteryItemEntities = lotteryItemEntitiesArr[i];
      let tempCount = this.computeSelectedNumForLotteryItemEntities(lotteryItemEntities);
      if (tempCount == 0) {
        return 0;
      }
      count *= tempCount;
    }
    return count;
  }


  computeSelectedNumForLotteryItemEntities(lotteryItemEntities) {
    let count = 0;
    for (let i = 0; i < lotteryItemEntities.length; i++) {
      let lotteryItemEntity = lotteryItemEntities[i];
      if (lotteryItemEntity.selected) {
        count++;
      }
    }
    return count;
  }

  /**
   * 生成订单数据
   * @param money
   * @param lotteryChildEntity
   */
  createCommitOrderData(lotteryNo, money, lotteryChildEntity) {
    console.log("需处理的数据", lotteryChildEntity);
    let orderValue;
    let orderValueShow;
    let orderList = lotteryChildEntity.orderList;
    let tempLotteryValue = [];
    let tempLotteryValueShow = [];
    for (let i = 0; i < orderList.length; i++) {
      let lotteryItemEntities = orderList[i];
      let tempItemValues = [];
      let tempItemValuesShow = [];
      for (let j = 0; j < lotteryItemEntities.length; j++) {
        let lotteryItemEntity = lotteryItemEntities[j];
        if (lotteryItemEntity.selected) {
          tempItemValues.push(lotteryItemEntity.lotteryItemId);
          tempItemValuesShow.push(lotteryItemEntity.lotteryItemName);
        }
      }
      if (tempItemValues && tempItemValues.length > 0) {
        let tempItemsResult = tempItemValues.join(",");
        tempLotteryValue.push(tempItemsResult);

        let tempItemsShowResult = tempItemValuesShow.join(",");
        tempLotteryValueShow.push(tempItemsShowResult);
      } else {
        tempLotteryValue.push("");
        tempLotteryValueShow.push("");
      }
    }
    orderValue = tempLotteryValue.join("|");
    orderValueShow = tempLotteryValueShow.join("|");

    return {
      money: money,
      lotteryChildId: lotteryChildEntity.lotteryChildId,
      lotteryValue: orderValue,
      lotteryValueShow: orderValueShow,
      lotteryChildName: lotteryChildEntity.lotteryChildName,
      lotteryNo: lotteryNo
    }
  }

}

const dataUtils = new DataUtils();
export default dataUtils;
