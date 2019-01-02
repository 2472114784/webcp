/**
 * 充值/提现 类型
 */
export const ORDER_MONEY_TYPE_TIXIAN = 1;  // 提现
export const ORDER_MONEY_TYPE_CHONGZHI = 2;  // 充值
/**
 * 充值/提现 订单状态
 */
export const MONEY_ORDER_STATUS_CANCEL = 1;  // 已拒绝/取消
export const MONEY_ORDER_STATUS_PENDING = 2;  // 待处理
export const MONEY_ORDER_STATUS_COMPLETED = 3;  // 已处理
export const MONEY_ORDER_STATUS_REFUSE = 4;//订单已拒绝
export const MONEY_ORDER_STATUS_ERROR = 5;//订单错误
/**
 * 渠道支付类型
 */
export const CHANNEL_PAY_TYPE_BANK = 1;
export const CHANNEL_PAY_TYPE_ZHIFUBAO = 2;
export const CHANNEL_PAY_TYPE_WECHAT = 3;
/**
 * 渠道类型
 */
export const CHANNEL_TYPE_WITHDRAW = 1;
export const CHANNEL_TYPE_RECHARGE = 2;


export const MoneyOrderApi = {
  createMoneyOrder: function (password, channelId, orderType, money, orderUserName, orderCertificate) {
    return {
      method: 'get',
      url: '/createMoneyOrder',
      data: {
        'password': password,
        'channelId': channelId,
        'orderType': orderType,
        'money': money,
        'orderUserName': orderUserName,
        'orderCertificate': orderCertificate,
      }
    }
  },
  selectMoneyOrder: function (orderStatus, page) {
    return {
      method: 'get',
      url: '/selectMoneyOrder',
      data: {
        'orderStatus': orderStatus,
        'page': page,
      }
    }
  },
  getMoneyChannel: function (channelType) {
    return {
      method: 'get',
      url: '/getMoneyChannel',
      data: {
        'channelType': channelType,
      }
    }
  },
  createMoneyChannel: function (moneyChannel) {
    return {
      method: 'get',
      url: '/createMoneyChannel',
      data: {
        'moneyChannel': moneyChannel,
      }
    }
  },
  findAllByOrderTypeAndOrderStatus: function (orderType, orderStatus, page) {
    return {
      method: 'get',
      url: '/findAllByOrderTypeAndOrderStatus',
      data: {
        'orderType': orderType,
        'orderStatus': orderStatus,
        'page': page,
      }
    }
  }
}
