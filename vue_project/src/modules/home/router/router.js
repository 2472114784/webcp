import Home from '../HomeView'
import HomeContent from '../component/content/home/HomeContent'
import LotteryContent from '../component/content/lottery/LotteryContent'
import LotteryChat from '../component/content/chat/LotteryChat'

export default function (router) {
  router.addRoutes([
    {
      path: '/',
      component: Home,
      children: [{
        path: 'home',
        component: HomeContent,
        meta: {
          keepAlive: true // 需要被缓存
        }
      },
        {
          path: 'lottery',
          component: LotteryContent,
          meta: {
            keepAlive: true // 需要被缓存
          }
        },
        {
          path: 'chat',
          component: LotteryChat,
          meta: {
            keepAlive: true,
          }
        }
      ]
    }
  ])
}

