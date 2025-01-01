import { createRouter, createWebHistory } from 'vue-router'
import { initDynamicRouter } from './dynamicRouter'
import {userMenuAuth} from '@/stores/authMenu'
import {userStore} from '@/stores/userStore'
import NProgress from '@/utils/nprogress'
import routes from './commonRoutes'

const router = createRouter({
  // history: createWebHistory(import.meta.env.BASE_URL),
  history: createWebHistory(),
  routes: routes
})
router.beforeEach(async (to, from, next) => {
  const authMenu = userMenuAuth()
  const curUserStore = userStore()

  // nprogress 启动
  NProgress.start()

  // 判断是访问登陆页
  if (to.path === '/login') {
    resetRouter()
    // next()
  }

  // 判断是否有 Token，没有或者token过期 重定向到 login 页面
  if (!curUserStore.getToken) return next({ path: '/login', replace: true })
  
  // 如果没有菜单列表，就重新请求菜单列表并添加动态路由
  if (to.path !== '/login' && !authMenu.authMenuListGet.length) {
    const flag = await authMenu.getAuthMenuList()
    if (flag) {
      // 动态加载路由
      await initDynamicRouter()
      return next({ ...to, replace: true })    // next里面参数指向指定路由，并且会再次触发beforeEach
    } else {
      return next({ path: '/login', replace: true })
    }
  }
  next()
})

/**
 * @description 重置路由（全部清除）
 * */
export const resetRouter = () => {
  const menuAuth = userMenuAuth()
  menuAuth.flatMenuListGet.forEach((route) => {
    const { name } = route
    if (name && router.hasRoute(name)) router.removeRoute(name)
  })
}

/**
 * @description 路由跳转错误
 * */
router.onError((error) => {
  NProgress.done()
  console.warn('路由错误', error.message)
})

/**
 * @description 路由跳转结束
 * */
router.afterEach(() => {
  NProgress.done()
})

export default router
