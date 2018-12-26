import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import initRouter from './common/router'
import Vuex from 'vuex'
import initStore from './common/store'
import ElementUI from 'element-ui'

import 'element-ui/lib/theme-chalk/index.css'; // 引入element-ui 样式
require('!style-loader!css-loader!less-loader!./assets/style/global.less'); //导入全局样式

Vue.use(ElementUI);
Vue.use(VueRouter);
Vue.use(Vuex);

const router = initRouter();
const store = initStore(router); //初始化状态，加载各个模块的状态

Vue.config.productionTip = false
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
});
