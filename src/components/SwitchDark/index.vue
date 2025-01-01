
<template>
    <el-switch v-model="globalStore.isDark" inline-prompt  @change="switchDark">
          <template #active-action>
            <el-icon><Moon/></el-icon>
          </template>
          <template #inactive-action>
            <el-icon><Sunny/></el-icon>
          </template>
        </el-switch>
</template>

<script setup>
import { Sunny, Moon } from '@element-plus/icons-vue'
import {changeTheme} from '@/theme/theme'
import {useGlobalStore} from '@/stores/modules/global'
  


const {switchDark} = changeTheme()
const globalStore = useGlobalStore()

</script>

<style lang="scss" scoped>

</style>