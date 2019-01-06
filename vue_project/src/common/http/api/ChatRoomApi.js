//TODO
/**
 * 此处替换成您的appKey
 * */
export const appKey = "z3v5yqkbz1jv0";
/**
 * 此处替换成您的appSecret
 * */
export const appSecret = "KuuOMpJM9w";
/**
 * 自定义api地址
 * */
export const api = "http://api.cn.ronghub.com";
/**
 * 禁言时间 单位：分钟
 */
export const GAG_MUNITE = 40000;

/**
 * 频道类型
 */
export const ROOM_CHANNEL_TYPE_CHAT = 0;//融云聊天
export const ROOM_CHANNEL_TYPE_LIVE = 1;//融云直播
/**
 * 频道角色
 */
export const ROOM_CHANNEL_RULE_BROADCASTER = 1;//主播
export const ROOM_CHANNEL_RULE_CLIENT = 2;//观众
/**
 * 红包超时时间
 */
export const RED_PACKET_TIMEOVER = 86400000;
/**
 * 红包默认remark
 */
export const RED_PACKET_DEFAULT_REMARK = "恭喜发财,好运连绵";
export default {
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
