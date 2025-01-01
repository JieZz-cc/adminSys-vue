<template>
  <div class="collapse-icon" @click="changeCollapse">
    <i class="toolbar-icon icon iconfont" :class="isCollapse ? 'icon-zhankai' : 'icon-shouqi'"></i>
  </div>
</template>

<script setup>
import {useGlobalStore} from '@/stores/modules/global'
import { computed } from 'vue'
const globalStore = useGlobalStore()

const isCollapse = computed(() => globalStore.isCollapse)

const changeCollapse = () => {
  globalStore.setCollapse(!isCollapse.value)
}
</script>

<style lang="scss" scoped>
.collapse-icon {
  margin-right: 20px;
  cursor: pointer;
}
</style>