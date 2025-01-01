<template>
  <div class="tabs-box">
    <div class="tabs-menu">
      <el-tabs v-model="currentTab" type="card" @tab-click="tabClick" @tab-remove="removeTab">
        <el-tab-pane
          v-for="item in tabsList"
          :key="item.path"
          :label="item.title"
          :name="item.path"
          :closable="item.close"
        >
        </el-tab-pane>
      </el-tabs>
      <MoreButton />
    </div>
  </div>
</template>

<script setup>
import MoreButton from './components/MoreButton.vue';
import { computed, onMounted, ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import {userMenuAuth} from '@/stores/authMenu'
import {useTabStore} from '@/stores/tabs'

const route = useRoute()
const router = useRouter()
const tabStore = useTabStore()
const menuAuth = userMenuAuth()

const currentTab = ref(route.fullPath)
const tabsList = computed(() => tabStore.tabsMenuList)


onMounted(() => {
  init()
})

// 监听路由，增加tabs
watch(() => route.fullPath,
  () => {
    currentTab.value = route.fullPath
    const tabParams = {
      icon: route.meta.icon,
      title: route.meta.title,
      path: route.fullPath,
      name: route.name,
      close: !route.meta.isAffix,
      isKeepAlive: route.meta.isKeepAlive
    }
    tabStore.addTab(tabParams)
  },
  {immediate: true}
)

const init = () => {
  
  menuAuth.flatMenuListGet.forEach(item => {
    if (item.isAffix && item.isEnable) {
      const tabParams = {
        icon: item.icon,
        title: item.title,
        path: item.path,
        name: item.name,
        close: !item.isAffix,
        isKeepAlive: item.isKeepAlive
      }
      tabStore.addTab(tabParams)
    }
  })
}

const tabClick = (tabItem) => {
  const fullPath = tabItem.props.name
  router.push(fullPath)
}

const removeTab = (fullPath) => {
  tabStore.removeTab(fullPath, fullPath == route.fullPath)
}

</script>

<style lang="scss" scoped>
@import url('./index.scss');
</style>