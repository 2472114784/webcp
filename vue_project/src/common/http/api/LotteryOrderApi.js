//TODO
export const LotteryOrderApi = {
  /**
   *  创建订单
   * @param requestTime 请求时间（需同步时间）
   * @param orders  json
   * @returns {{method: string, data: {request_time: *, orders: *}, url: string}}
   */
  createLotteryOrder: function (requestTime, orders) {
    return {
      method: 'get',
      url: '/createLotteryOrder',
      data: {
        'request_time': requestTime,
        'orders': orders,
      }
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
