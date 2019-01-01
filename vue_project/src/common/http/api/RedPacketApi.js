//TODO
export const RedPacketApi = {
  sendRedPacket: function (password, type, money, num, remark) {
    return {
      method: 'get',
      url: '/sendRedPacket',
      data: {
        'password': password,
        'type': type,
        'money': money,
        'num': num,
        'remark': remark,
      }
    }
  },
  receiveRedPacket: function (id) {
    return {
      method: 'get',
      url: '/receiveRedPacket',
      data: {
        'id': id,
      }
    }
  },
  getListRedPacketRecode: function (type, page) {
    return {
      method: 'get',
      url: '/getListRedPacketRecode',
      data: {
        'type': type,
        'page': page,
      }
    }
  },
  getRedPacketDetail: function (redPacketId) {
    return {
      method: 'get',
      url: '/getRedPacketDetail',
      data: {
        'redPacketId': redPacketId,
      }
    }
  },
  getRedPacketDetailListByRedPacketId: function (redPacketId, page) {
    return {
      method: 'get',
      url: '/getRedPacketDetailListByRedPacketId',
      data: {
        'redPacketId': redPacketId,
        'page': page,
      }
    }
  }

}
