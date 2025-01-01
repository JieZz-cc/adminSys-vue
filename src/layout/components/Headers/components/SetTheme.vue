<template>
  <div class="theme-setting">
     <i class="icon-yifu toolbar-icon icon iconfont" @click="openDrawer"></i>
  </div>
</template>

<script setup>
import mittBus from '@/utils/mittBus'
// import mitt from 'mitt'



const openDrawer = () => {
  mittBus.emit('openThemeDrawer')
}

</script>

<style lang="scss" scoped>
.theme-setting {
  cursor: pointer;
}
</style>