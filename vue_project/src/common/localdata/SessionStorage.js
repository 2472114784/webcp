import VueSessionStorage from 'vue-sessionstorage'

class VueSessionStorage {
  init = function (Vue) {
    Vue.use(VueSessionStorage)

  }
}

export default new VueSessionStorage();

