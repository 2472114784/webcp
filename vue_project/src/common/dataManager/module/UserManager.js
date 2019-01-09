const KEY_USER = 'key_user';
import {KEY_SET_USER} from '../../store/modules/UserStoreModule'

class UserManager {

  init(session, store) {


    this.mSession = session;
    this.mStore = store;
    console.log("session", this.mSession);
    console.log("store", this.mStore);
  };

  setUser(user) {
    this.mSession.set(KEY_USER, user);
    this.mStore.commit(KEY_SET_USER, user);
  }

  getUser() {
    return this.mSession.get(KEY_USER);
  }

  getStateUser() {
    return this.mStore.state.UserStoreModule.user
  }
}

export default new UserManager();
