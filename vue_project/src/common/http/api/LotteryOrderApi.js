import TimeManager from '../../../common/serverTime/TimeManager'
/**
 * 订单样式
 */
export const ORDER_TYPE_NORMAL = 0;//单式订单
export const ORDER_TYPE_DUPLEX = 1;//复式订单
export const ORDER_TYPE_POSITION = 2;//定位订单

/**
 * 彩票类型
 */
export const LOTTERY_TYPE_PC28 = 10000;//幸运28
export const LOTTERY_TYPE_BJPK10 = 20000;//北京pk10
export const LOTTERY_TYPE_CQSSC = 30000;//重庆时时彩
export const LOTTERY_TYPE_TJSSC = 40000;//天津时时彩
export const LOTTERY_TYPE_JSK3 = 50000;//江苏快3


/**
 * 彩票时间状态
 */
export const TIME_TYPE_STOP_OF_DAY = 0;//当日封盘
export const TIME_TYPE_STOP_OF_TIME = 1;//当期封盘
export const TIME_TYPE_START_OF_TIME = 2;//可以下单

/**
 * 订单状态
 */
export const ORDER_STATUS_ALL = 0;  // 全部订单
export const ORDER_STATUS_PENDING = 1;  // 待开奖订单
export const ORDER_STATUS_COMPLETED_WIN = 2;  // 已兑奖订单(中奖)
export const ORDER_STATUS_COMPLETED_LOSE = 3;  // 已兑奖订单（未中奖）
export const ORDER_STATUS_CANCEL = 4;  // 取消pc订单
/**
 * 房间类型
 */
export const ROOM_TYPE_NORMAL = 0;//普通房间
export const ROOM_TYPE_LV = 1;//等级房间
export const ROOM_TYPE_PASSWORD = 2;//密码房间

/**
 * 彩票child类型 单式 复式
 */
export const LOTTERY_CHILD_TYPE_NORMAL = 0;
export const LOTTERY_CHILD_TYPE_DUPLEX = 1;
export const LOTTERY_CHILD_TYPE_POSITION = 2;//定位

export default {
  /**
   *  创建订单
   * @param requestTime 请求时间（需同步时间）
   * @param orders  json
   * @returns {{method: string, data: {request_time: *, orders: *}, url: string}}
   */
  createLotteryOrder: function (orders) {
    console.log("time=>", TimeManager.getServerTime());
    console.log("data=>", JSON.stringify(orders));
    return {
      method: 'post',
      url: '/createLotteryOrder',
      data: eval('(' + JSON.stringify(orders) + ')'),

    }
  },
  /**
   * 取消订单
   * @param orderId 订单id
   * @returns {{method: string, data: {orderId: *}, url: string}}
   */
  cancelLotteryOrder: function (orderId) {
    return {
      method: 'get',
      url: '/createLotteryOrder',
      data: {
        'orderId': orderId,
      }
    }
  },
  /**
   * 查看订单
   * @param orderStatus
   * @param page
   * @returns {{method: string, data: {orderStatus: *, page: *}, url: string}}
   */
  selectLotteryOrder: function (orderStatus, page) {
    return {
      method: 'get',
      url: '/selectLotteryOrder',
      data: {
        'orderStatus': orderStatus,
        'page': page,
      }
    }
  },
  /**
   *
   * @param lotteryId
   * @returns {{method: string, data: {lottery_id: *}, url: string}}
   */
  getLotteryById: function (lotteryId) {
    return {
      method: 'get',
      url: '/getLotteryById',
      data: {
        'lottery_id': lotteryId,
      }
    }
  },
  getLotteryList: function () {
    return {
      method: 'get',
      url: '/getLotteryList',
      data: {}
    }
  },
  /**
   *
   * @param lotteryId
   * @param items
   * @returns {{method: string, data: {items: *, lotteryId: *}, url: string}}
   */
  createEasyShoppingByLotteryId: function (lotteryId, items) {
    return {
      method: 'get',
      url: '/createEasyShoppingByLotteryId',
      data: {
        'lotteryId': lotteryId,
        'items': items,
      }
    }
  },
  /**
   *
   * @param lotteryId
   * @returns {{method: string, data: {lotteryId: *}, url: string}}
   */
  getEasyShoppingByLotteryId: function (lotteryId) {
    return {
      method: 'get',
      url: '/getEasyShoppingByLotteryId',
      data: {
        'lotteryId': lotteryId,
      }
    }
  },
  getEasyLotteryEntityByLotteryId: function (lotteryId) {
    return {
      method: 'get',
      url: '/getEasyLotteryEntityByLotteryId',
      data: {
        'lotteryId': lotteryId,
      }
    }
  }
}
