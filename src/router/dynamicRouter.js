import router from "@/router/index"
import {userMenuAuth} from '@/stores/authMenu'

// 引入 views 文件夹下所有 vue 文件
const modules = import.meta.glob('@/views/**/*.vue')
// console.log(modules);

export const initDynamicRouter = () => {
  const authMenu = userMenuAuth()
  // console.log(authMenu, 'xxx');
  
  try {
    authMenu.flatMenuListGet.forEach(item => {
       if (item.children && item.children.length > 0) {
        item.redirect = item.children[0].path
       } 
       item.children && delete item.children
       if (item.path && typeof item.path == 'string') {
        
        item.component = modules['/src/views' + item.path + '/index.vue']
       }
       item.meta = {
        icon: item.icon,
        isAffix: item.isAffix,
        isEnable: item.isEnable,
        isKeepAlive: item.isKeepAlive,
        isLink: item.isLink,
        title: item.title
       }
        // 添加路由 
      router.addRoute('layout', item)
    })
  } catch (error) {
    return Promise.reject(error)
  }
}