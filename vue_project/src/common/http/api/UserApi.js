import {BUCKET_HTTP_PRE} from './QiniuApi'
//用户类型
export const USER_TYPE_NORMAL = 0;//普通正常玩家
export const USER_TYPE_TEST = 1;//测试玩家


//默认头像  http://pflfreg6m.bkt.clouddn.com/avatar_1.png
export const DEFAULT_PORTRAIT = BUCKET_HTTP_PRE + "avatar_1.png";

export const PORTRAITS = [BUCKET_HTTP_PRE + "avatar_1.png", BUCKET_HTTP_PRE + "avatar_2.png", BUCKET_HTTP_PRE + "avatar_3.png", BUCKET_HTTP_PRE + "avatar_4.png", BUCKET_HTTP_PRE + "avatar_5.png", BUCKET_HTTP_PRE + "avatar_6.png", BUCKET_HTTP_PRE + "avatar_7.png", BUCKET_HTTP_PRE + "avatar_8.png", BUCKET_HTTP_PRE + "avatar_9.png", BUCKET_HTTP_PRE + "avatar_10.png"];

/** 用户session key */
export const KEY_USER = "cp_user";
/**
 * 用户账户记录
 */
export const RECODE_SHOPPING_CP_ORDER = 0;//购买彩票
export const RECODE_CANCEL_CP_ORDER = 5;//取消订单彩票
export const RECODE_PAIJIANG = 1;//派奖（中奖）
export const RECODE_TIXIAN = 2;//提现
export const RECODE_PERSON_CHONGZHI = 3;//人工充值
export const RECODE_BANK_CHONGZHI = 4;//银行卡充值

export default {
  register: function (account, password, inviteCode) {
    return {
      method: 'get',
      url: '/register',
      data: {
        'account': account,
        'password': password,
        'inviteCode': inviteCode,
      }
    }
  },
  login: function (account, password) {
    return {
      method: 'get',
      url: '/login',
      data: {
        'account': account,
        'password': password,
      }
    }
  },
  getUserInfo: function () {
    return {
      method: 'get',
      url: '/getUserInfo',
      data: {}
    }
  },
  updateUserInfo: function (userName, portrait) {
    return {
      method: 'get',
      url: '/updateUserInfo',
      data: {
        'userName': userName,
        'portrait': portrait
      }
    }
  },
  setWithdrawPassword: function (password) {
    return {
      method: 'get',
      url: '/setWithdrawPassword',
      data: {
        'password': password,
      }
    }
  },
  updateWithdrawPassword: function (oldPassword, newPassword) {
    return {
      method: 'get',
      url: '/updateWithdrawPassword',
      data: {
        'oldPassword': oldPassword,
        'newPassword': newPassword
      }
    }
  }
}
