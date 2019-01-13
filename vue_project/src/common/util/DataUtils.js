import cloneDeep from 'clone-deep';
import {ORDER_TYPE_NORMAL, ORDER_TYPE_DUPLEX, ORDER_TYPE_POSITION} from '../http/api/LotteryOrderApi'

//数据处理utils
class DataUtils {
  /**
   *
   * @param lotteryChildEntity
   */
  createLotteryChildChooseData(lotteryChildEntity) {
    let duplexNum = lotteryChildEntity.lotteryChildDuplexNum;
    let result = []
    for (let i = 0; i < duplexNum; i++) {
      result[i] = cloneDeep(lotteryChildEntity.lotteryItemEntityList)
    }
    ;
    console.log("result", result)
    return result;
  }

  computeOrderNumForLotteryEntity(lotteryEntity) {
    let count = 0;
    let lotteryChilds = lotteryEntity.lotteryChilds;
    for (let i = 0; i < lotteryChilds.length; i++) {
      let lotteryChild = lotteryChilds[i];
      count += this.computeOrderNumForLotteryChildEntity(lotteryChild);
    }
    return count;
  }

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
    let count = 0;
    for (let i = 0; i < lotteryItemEntitiesArr.length; i++) {
      let lotteryItemEntities = lotteryItemEntitiesArr[i];
      count += this.computeSelectedNumForLotteryItemEntities(lotteryItemEntities);
    }
    return count;
  }

  computeOrderNumForOrderListByPosition(lotteryItemEntitiesArr) {
    let count = 0;
    for (let i = 0; i < lotteryItemEntitiesArr.length; i++) {
      let lotteryItemEntities = lotteryItemEntitiesArr[i];
      count += this.computeSelectedNumForLotteryItemEntities(lotteryItemEntities);
    }
    return count;
  }

  computeOrderNumForOrderListByDuplex(lotteryItemEntitiesArr) {
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
}

const dataUtils = new DataUtils();
export default dataUtils;
