//TODO
export const ChatRoomApi = {
  getRoomToken: function () {
    return {
      method: 'get',
      url: '/getRoomToken',
      data: {}
    }
  },
  createRoom: function (roomName) {
    return {
      method: 'get',
      url: '/createRoom',
      data: {
        'roomName': roomName,
      }
    }
  },
  destroyRoom: function (roomId) {
    return {
      method: 'get',
      url: '/destroyRoom',
      data: {
        'roomId': roomId,
      }
    }
  },
  getLotteryRoomByLotteryId: function (lotteryId) {
    return {
      method: 'get',
      url: '/getLotteryRoomByLotteryId',
      data: {
        'lotteryId': lotteryId,
      }
    }
  },
  joinChatRoom: function (roomId) {
    return {
      method: 'get',
      url: '/joinChatRoom',
      data: {
        'roomId': roomId,
      }
    }
  },
  exitChatRoom: function () {
    return {
      method: 'get',
      url: '/exitChatRoom',
      data: {
        'roomId': roomId,
      }
    }
  },
  gagAddUserChatRoom: function (roomId, userId) {
    return {
      method: 'get',
      url: '/gagAddUserChatRoom',
      data: {
        'roomId': roomId,
        'userId': userId
      }
    }
  },
  gagRemoveUserChatRoom: function (userId) {
    return {
      method: 'get',
      url: '/gagRemoveUserChatRoom',
      data: {
        'userId': userId
      }
    }
  },
  getListGagUserChatRoom: function () {
    return {
      method: 'get',
      url: '/getListGagUserChatRoom',
      data: {}
    }
  },
  checkRoomPassword: function (roomId, roomPassword) {
    return {
      method: 'get',
      url: '/checkRoomPassword',
      data: {
        'roomId': roomId,
        'roomPassword': roomPassword
      }
    }
  }
}
