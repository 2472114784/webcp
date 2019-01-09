import VueSessionStorage from 'vue-sessionstorage'

class SessionStorage {
  init = function (Vue) {
    Vue.use(VueSessionStorage)

  }
}

export default new SessionStorage();

