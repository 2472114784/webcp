import cloneDeep from 'clone-deep';
import {ORDER_TYPE_NORMAL, ORDER_TYPE_DUPLEX, ORDER_TYPE_POSITION} from '../http/api/LotteryOrderApi'
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
    let duplexNum = lotteryChildEntity.lotteryChildDuplexNum;
    let result = []
    for (let i = 0; i < duplexNum; i++) {
      result[i] = cloneDeep(lotteryChildEntity.lotteryItemEntityList)
    }
    console.log("result", result)
    return result;
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
      case ORDER_TYPE_NORMAL:
        count = this.computeOrderNumForOrderListByNormal(lotteryChildEntity.orderList);
        break;
      case ORDER_TYPE_POSITION:
        count = this.computeOrderNumForOrderListByPosition(lotteryChildEntity.orderList);
        break;
      case ORDER_TYPE_DUPLEX:
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
  createCommitOrderData(money, lotteryChildEntity) {
    console.log("需处理的数据", lotteryChildEntity);
    let orderValue;
    let orderList = lotteryChildEntity.orderList;
    let tempLotteryValue = [];
    for (let i = 0; i < orderList.length; i++) {
      let lotteryItemEntities = orderList[i];
      let tempItemValues = [];
      for (let j = 0; j < lotteryItemEntities.length; j++) {
        let lotteryItemEntity = lotteryItemEntities[j];
        if (lotteryItemEntity.selected) {
          tempItemValues.push(lotteryItemEntity.lotteryItemId);
        }
      }
      if (tempItemValues && tempItemValues.length > 0) {
        let tempItemsResult = tempItemValues.join(",");
        tempLotteryValue.push(tempItemsResult);
      } else {
        tempLotteryValue.push("");
      }
    }
    orderValue = tempLotteryValue.join("|");

    return {money: money, lotteryChildId: lotteryChildEntity.lotteryChildId, lotteryValue: orderValue}
  }

}

const dataUtils = new DataUtils();
export default dataUtils;
