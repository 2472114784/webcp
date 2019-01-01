import VueRouter from 'vue-router'
import routerInterceptor from './routerInterceptor'
import HomeRouter from '../../modules/home/router/router'

export default function () {
  // 路由配置
  let router = new VueRouter({
    routes:[]
  });

  //路由拦截
  routerInterceptor(router);

  //加载各模块的路由
  // demoRouter(router);
  HomeRouter(router);
  return router;
}
