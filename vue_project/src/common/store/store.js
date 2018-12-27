import Vuex from 'vuex'
import { sync } from 'vuex-router-sync'
import plugins from './globle/storePlugin'


export default function (router){
  const store = new Vuex.Store({
    plugins
  });

  //加载各个模块的状态
  // demoStore(store);

  // 路由状态加载到状态管理
  sync(store, router);

  return store;

}
