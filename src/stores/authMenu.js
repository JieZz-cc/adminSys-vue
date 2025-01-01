import { defineStore } from "pinia"
import {getFlatMenuList, getShowMenuList, getAllBreadcrumbList} from '@/utils'
import { ElMessage } from 'element-plus'
// import { getAuthMenuListApi } from '@/api/login'
import {invokeApi} from '@/api'
import {userStore} from '@/stores/userStore'



export const userMenuAuth = defineStore('userMenuAuth', {
  state: () => ({
      authButtonList: {},     // 按钮权限列表
      authMenuList: [],       // 菜单权限列表
      routeName: ''           // 当前页面的 router name，用来做按钮权限筛选
  }), 
  
  // persist: false,

  getters: {
    // 菜单权限列表 ==> 这里的菜单没有经过任何处理
    authMenuListGet: (state) => state.authMenuList,
    // 菜单权限列表 ==> 扁平化之后的一维数组菜单，主要用来添加动态路由
    flatMenuListGet: (state) => getFlatMenuList(state.authMenuList),
    // 菜单权限列表 ==> 左侧菜单栏渲染，需要剔除 isEnable === 0
    showMenuListGet: (state) => getShowMenuList(state.authMenuList),
    // 递归处理后的所有面包屑导航列表
    breadcrumbListGet: (state) => getAllBreadcrumbList(state.authMenuList)
  },

  actions: {
    async getAuthMenuList() {
      try {
        const useStore = userStore()
        // const res = await getAuthMenuListApi()
        
        const res = await invokeApi('post', '/getAuthMenuById', {userId: useStore.userInfo.userId})
        if (res.code === 200) {
          if (res.data.length === 0) {
            // 菜单列表为空，则跳转到登录页
            ElMessage.error('角色菜单列表为空，联系管理员')
            return false
          } else {
            // 成功获取菜单列表
            this.authMenuList = res.data
            return true
          }
        } else {
          ElMessage.error(res.msg)
          // 获取菜单失败 则跳转到登录页
          return false
        }
      } catch (error) {
        console.log(error)
        return false
      }
    },
    async removeMenus() {
      this.authMenuList = []
    }
  }
}
)