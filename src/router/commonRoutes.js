const HOME_URL = '/dashboard'

const routes = [
  { path: '/', redirect: HOME_URL },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/index.vue')
  },
  // {   // 不能有!!!!,否则一直展示dashboard
  //   path: '/dashboard',
  //   name: 'dashboard',
  //   component: () => import('@/views/dashboard/index.vue')
  // },
  {
    path: '/layout',
    redirect: HOME_URL, // 重定向主页,
    name: 'layout',
    component: () => import('@/layout/index.vue'),  // dashboard页面实际展示layout的内容
    children: [
      // -----非全屏页面动态引入-----
      // 个人中心
      {
        path: '/layout/profile',
        name: 'profile',
        component: () => import('@/views/custom/personProfile/profile.vue'),
        meta: {
          icon: '',
          isAffix: 0,
          isEnable: 1,
          isKeepAlive: 1,
          isLink: 0,
          title: '个人中心'
        }
      },
    ]
  },
  // 错误页面
  {
    path: '/403',
    name: '403',
    component: () => import('@/components/ErrorPage/403.vue'),
    meta: {
      title: '403页面'
    }
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/components/ErrorPage/404.vue'),
    meta: {
      title: '404页面'
    }
  },
  {
    path: '/500',
    name: '500',
    component: () => import('@/components/ErrorPage/500.vue'),
    meta: {
      title: '500页面'
    }
  },
  {
    path: '/:pathMatch(.*)',
    component: () => import('@/components/ErrorPage/404.vue')
  }
]

export default routes