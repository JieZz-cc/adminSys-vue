<template>
    <Tabs />
    <el-main>
      <router-view v-slot="{ Component, route }">
        <transition name="fade-transform" mode="out-in" appear>
          <keep-alive :include="keepAliveNames">
            <component
              :is="createComponentWrapper(Component, route)"
              :key="route.fullPath"
            ></component>
          </keep-alive>
        </transition>
      </router-view>
    </el-main>
</template>

<script setup>
import Tabs from '@/layout/components/Tabs/index.vue'
import {usekeepAliveStore} from '@/stores/keepAliveStore'
import {useGlobalStore} from '@/stores/modules/global'
import { computed, ref, onBeforeUnmount, h } from 'vue'
import {useDebounceFn} from '@vueuse/core'

const globalStore = useGlobalStore()
const isCollapse = computed(() => globalStore.isCollapse )
const breadcrumb = computed(() => globalStore.breadcrumb)

const keepAliveStore = usekeepAliveStore()
const keepAliveNames = computed(() => keepAliveStore.keepAliveNames)

// keepAlive缓存 将组件路径fullPath作为key 设置为Component 识别名称
const wrapperMap = new Map()
const createComponentWrapper = (component, route) => {
  if (!component) return
  const wrapperName = route.fullPath
  let wrapper = wrapperMap.get(wrapperName)
  if (!wrapper) {
    wrapper = {
      name: route.fullPath,
      render: () => h(component)
    }
     wrapperMap.set(wrapperName, wrapper)
  }
  return h(wrapper)
}


// 监听窗口大小变化，折叠侧边栏, 控制面包屑导航
const screenWidth = ref(0)
const windowListening = useDebounceFn(() => {
  screenWidth.value = document.body.clientWidth
  if (!isCollapse.value && screenWidth.value < 1200) globalStore.setCollapse(true)
  if (isCollapse.value && screenWidth.value > 1200) globalStore.setCollapse(false)
  if (breadcrumb.value && screenWidth.value < 768) globalStore.setBreadcrumb(false)
  if (!breadcrumb.value && screenWidth.value > 768) globalStore.setBreadcrumb(true)
})
window.addEventListener('resize', windowListening, false)
onBeforeUnmount(() => {
  window.removeEventListener('resize', windowListening)
})

</script>

<style lang="scss" scoped>
@import url('./index.scss');
</style>