import Vue from 'vue'

const KEY_USER = 'user'

class UserManager {

  get = function () {
    Vue.$session.get(KEY_USER)
  }
  set = function (user) {
    Vue.$session.set(KEY_USER, user)
  }

}
