import cloneDeep from 'clone-deep';
import {ORDER_TYPE_NORMAL, ORDER_TYPE_DUPLEX, ORDER_TYPE_POSITION} from '../../common/http/api/LotteryOrderApi'

//数据处理utils
class DataUtils {
  /**
   * 创建显示下单操作数据
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

  /**
   * 计算订单数量
   * @param lotteryEntity
   */

  computeLotteryOrderNum(lotteryEntity) {
    let count = 0;
    let lotteryChilds = lotteryEntity.lotteryChilds;
    if (lotteryChilds) {
      for (let i = 0; i < lotteryChilds.length; i++) {
        let lotteryChild = lotteryChilds[i];
        count += this.computeLotteryChildOrderNum(lotteryChild)
      }
    }
    return count;
  }

  computeLotteryChildOrderNum(lotteryChildEntity) {
    let count = 0;
    if (lotteryChildEntity) {
      switch (lotteryChildEntity.lotteryChildType) {
        case ORDER_TYPE_NORMAL:
          count = this.computeLotteryChildOrderNumForNormal(lotteryChildEntity);
          break;
        case ORDER_TYPE_POSITION:
          count = this.computeLotteryChildOrderNumForPosition(lotteryChildEntity);
          break;
        case ORDER_TYPE_DUPLEX:
          count = this.computeLotteryChildOrderNumForDuplex(lotteryChildEntity);
          break;
      }
    }
    return count;
  }

  computeLotteryChildOrderNumForNormal(lotteryChildEntity) {
    let count = 0;
    let orderList = lotteryChildEntity.orderList;
    for (let i = 0; i < orderList.length; i++) {
      let lotteryItemEntityies = orderList[i];
      if (lotteryItemEntityies) {
        count += this.computeLotteryItemNumForSelected(lotteryItemEntityies);
      }
    }
    return count;
  }

  computeLotteryChildOrderNumForPosition(lotteryChildEntity) {
    let count = 0;
    let orderList = lotteryChildEntity.orderList;
    for (let i = 0; i < orderList.length; i++) {
      let lotteryItemEntityies = orderList[i];
      if (lotteryItemEntityies) {
        count += this.computeLotteryItemNumForSelected(lotteryItemEntityies);
      }
    }
    return count;
  }

  computeLotteryChildOrderNumForDuplex(lotteryChildEntity) {
    let count = 0;
    let orderList = lotteryChildEntity.orderList;
    for (let i = 0; i < orderList.length; i++) {
      let lotteryItemEntityies = orderList[i];
      if (lotteryItemEntityies) {
        count *= this.computeLotteryItemNumForSelected(lotteryItemEntityies);
      }
    }
    return count;
  }

  computeLotteryItemNumForSelected(lotteryItemEntities) {
    let count = 0;
    if (lotteryItemEntities) {
      for (let i = 0; i < lotteryItemEntities.length; i++) {
        let lotteryItemEntity = lotteryItemEntities[i];
        if (lotteryItemEntity.selected) {
          count++;
        }
      }
    }
    return count;
  }

  /**
   * 设置金额
   * @param lotteryEntity
   * @param amount
   */
  setMoneyForLotteryEntity(lotteryEntity, amount) {
    if (lotteryEntity) {
      let lotteryChilds = lotteryEntity.lotteryChilds;
      if (lotteryChilds) {
        for (let i = 0; i < lotteryChilds.length; i++) {
          let lotteryChild = lotteryChilds[i];
          if (lotteryChild) {
            lotteryChild.money = amount;
            this.setMoneyForLotteryChildEntity(lotteryChild, amount);
          }
        }
      }
    }
  }

  setMoneyForLotteryChildEntity(lotteryChildEntity, amount) {
    if (lotteryChildEntity) {
      let orderList = lotteryChildEntity.orderList;
      for (let i = 0; i < orderList.length; i++) {
        let lotteryItemEntityies = orderList[i];
        if (lotteryItemEntityies) {
          this.setMoneyForLotteryItemEntity(lotteryItemEntityies, amount);
        }
      }
    }
  }

  setMoneyForLotteryItemEntity(lotteryItemEntityies, amount) {
    if (lotteryItemEntityies) {
      for (let i = 0; i < lotteryItemEntityies.length; i++) {
        let lotteryItemEntity = lotteryItemEntityies[i];
        if (lotteryItemEntity) {
          lotteryItemEntity.money = amount;
        }
      }
    }
  }

  /**
   * 获取总金额
   * @param lotteryEntity
   * @returns {number}
   */
  getTotalMoney(lotteryEntity) {
    let totalMoney = 0;
    let lotteryChilds = lotteryEntity.lotteryChilds;
    if (lotteryChilds) {
      for (let i = 0; i < lotteryChilds.length; i++) {
        let lotteryChild = lotteryChilds[i];
        let count = this.computeLotteryChildOrderNum(lotteryChild);
        totalMoney += count * lotteryChild.money;
      }
    }
    return totalMoney;
  }
}

const dataUtils = new DataUtils();
export default dataUtils;
