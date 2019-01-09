import UserManager from "./module/UserManager";


class DataManager {
  init(session, store) {
    console.log("初始化DataManager", 'session:', session, 'store:', store);
    UserManager.init(session, store)
  }
}

export default new DataManager();
