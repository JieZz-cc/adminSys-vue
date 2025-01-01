<template>
  <el-config-provider >
    <!-- :locale="zhCn" -->
    <router-view />
  </el-config-provider>
</template>

<script setup>
// import {RouterView} from 'vue-router'
import {changeTheme} from '@/theme/theme'
// 初始化主题
const { initTheme } = changeTheme()
initTheme()

// 测试第一次提交
</script>

<style lang="scss" scoped>

</style>