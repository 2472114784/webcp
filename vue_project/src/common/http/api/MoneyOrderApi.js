//TODO
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
