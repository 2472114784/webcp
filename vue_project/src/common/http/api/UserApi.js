//TODO
export const UserApi = {
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
