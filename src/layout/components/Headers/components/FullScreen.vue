<template>
  <div class="fullscreen">
    <i
      :class="isFull ? 'icon-suoxiao' : 'icon-fangda'"
      class="toolbar-icon icon iconfont"
      @click="handleFullScreen"
    ></i>
  </div>
</template>

<script setup>
import screenfull from 'screenfull';    // 全屏插件
import { ElMessage } from 'element-plus';
import { onMounted, ref } from 'vue';

const isFull = ref(screenfull.isFullscreen) 

onMounted(() => {
  screenfull.on('change', () => {
    if (screenfull.isFullscreen) isFull.value = true
    else isFull.value = false
  })
})

const handleFullScreen = () => {
  if (!screenfull.isEnabled) ElMessage.warning('当前浏览器不支持全屏 ❌')
  screenfull.toggle()
}

</script>

<style lang="scss" scoped>

.toolbar-icon:hover {
  color: var(--el-color-primary);
  cursor: pointer;
}
</style>