<template>
  <div>
    <el-drawer v-model="drawerVisible" title="布局设置" size="290px">
      <div class="theme-item">
        <span>主题颜色</span>
        <el-color-picker v-model="primary" :predefine="colorList" @change="switchDark" />
      </div>
      <div class="theme-item">
        <span>暗黑模式</span>
        <SwitchDark />
      </div>
    </el-drawer>
  </div>
</template>

<script setup>
import {useGlobalStore} from '@/stores/modules/global'
import mittBus from '@/utils/mittBus'
import {storeToRefs} from 'pinia'
import {ref} from 'vue'
import {changeTheme} from '@/theme/theme'
import SwitchDark from '@/components/SwitchDark/index.vue'

const DEFAULT_PRIMARY = '#2254F4'

const globalStore = useGlobalStore()
const { primary } = storeToRefs(globalStore)

const {switchDark} = changeTheme()
// 预定义主题颜色
const colorList = [
  DEFAULT_PRIMARY,
  '#daa96e',
  '#0c819f',
  '#409eff',
  '#27ae60',
  '#ff5c93',
  '#e74c3c',
  '#fd726d',
  '#f39c12',
  '#9b59b6'
]


const drawerVisible = ref(false)
mittBus.on('openThemeDrawer', () => (drawerVisible.value = true))

</script>

<style lang="scss" scoped>
@import url('./index.scss');
</style>