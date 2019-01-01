export default function (router) {
  router.addRoutes([
    {path: '/demo', component: resolve => require(['./vue/Demo.vue'], resolve)},
    {path: '/demo2', component: resolve => require(['./vue/Demo2.vue'], resolve)}
  ])
}
