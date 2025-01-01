<template>
    <div>
      <el-breadcrumb separator="/">
      <template v-for="item in breadcrumbList" :key="item.path">
        <el-breadcrumb-item :to="{path: item.path}" @click="onClick(item.path)">
          {{ item.title }}
        </el-breadcrumb-item>
      </template>
    </el-breadcrumb>
    </div>
</template>

<script setup>
import router from '@/router'
import { useRoute} from 'vue-router'
import { computed } from 'vue';
import {userMenuAuth} from '@/stores/authMenu'

const menuAuth = userMenuAuth()

const route = useRoute()    // 当前路由信息

const breadcrumbList = computed(() => {
  const HOME_URL = '/dashboard'
  // console.log(route.matched[route.matched.length - 1], 'qqq');
  // console.log(useRoute(), 'route');
  let breadcrumbData = menuAuth.breadcrumbListGet[route.matched[route.matched.length - 1].path]
  if (breadcrumbData && breadcrumbData[0] && breadcrumbData[0].path !== HOME_URL) {
    breadcrumbData = [{ path: HOME_URL, meta: { title: '首页' } }, ...breadcrumbData]
  }
  return breadcrumbData
})


const onClick = path => {
  router.push(path)
  
}
</script>

<style lang="scss" scoped>

</style>