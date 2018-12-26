export default function (router) {
  router.addRoutes([
    {path:'/demo',component: resolve => require(['./vue/demo.vue'], resolve)},
    {path:'/demo2',component: resolve => require(['./vue/demo2.vue'], resolve)}
  ])
}
