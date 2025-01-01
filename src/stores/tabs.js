import { defineStore } from "pinia";
import router from "@/router";
import {usekeepAliveStore} from './keepAliveStore'
// import { reactive } from "vue";

const keepAliveStore = usekeepAliveStore()

export const useTabStore = defineStore('useTabStore', {
    state: () => ({
      tabsMenuList: []
    }),
    persist: true,
    actions: {
      async addTab(tab) {
        // every, 为数组中每个元素都执行一次，有一个不满足条件返回false，否则true
        if (this.tabsMenuList.every(item => item.path !== tab.path))  this.tabsMenuList.push(tab)
        if (!keepAliveStore.keepAliveNames.includes(tab.path) && tab.isKeepAlive) keepAliveStore.addKeepAlive(tab.path)
      },
      async removeTab(tabPath, isCurrent = true) {
        if (isCurrent) {
          this.tabsMenuList.forEach((item, index) => {
            if (item.path !== tabPath) return
            const nextTab = this.tabsMenuList[index + 1] || this.tabsMenuList[index - 1]
            if (!nextTab) return
            router.push(nextTab.path)
          })
        }

        const tabItem = this.tabsMenuList.find((item) => item.path === tabPath)        
        tabItem?.isKeepAlive && keepAliveStore.removeKeepAlive(tabItem.path)

        this.tabsMenuList = this.tabsMenuList.filter(i => i.path !== tabPath)
      },
      async closeTabsOnSide(tabPath, type = 'left' | 'right') {
        const currentIndex = this.tabsMenuList.findIndex((item) => item.path === tabPath)
        if (currentIndex !== -1) {
          const range = type === 'left' ? [0, currentIndex] : [currentIndex + 1, this.tabsMenuList.length]
          this.tabsMenuList = this.tabsMenuList.filter((ele, ind) => {
            return ind < range[0] || ind >= range[1] || !ele.close
          })
        }
        const keepAliveList = this.tabsMenuList.filter((item) => item.isKeepAlive)
        keepAliveStore.setKeepAliveNames(keepAliveList.map((item) => item.path))
      },
      async closeMultipleTab(tabPath) {
        this.tabsMenuList = this.tabsMenuList.filter(i => {
          return i.path === tabPath || !i.close
        })
        const keepAliveList = this.tabsMenuList.filter((item) => item.isKeepAlive)
        keepAliveStore.setKeepAliveNames(keepAliveList.map((item) => item.path))
      },
      async setTabs(tabList = []) {
        this.tabsMenuList = tabList
      }
    }
  }
)