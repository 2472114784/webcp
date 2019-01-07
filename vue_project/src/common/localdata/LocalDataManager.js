import LocalStorage from './LocalStorage'
import SessionStorage from './SessionStorage'

class LocalDataManager {
  init = function (Vue) {
    LocalStorage.init(Vue);
    SessionStorage.init(Vue);
  }
}

export default new LocalDataManager();

