<template>
  <div :id="props.id" :style="style" ref="chartsRef">

  </div>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue';

import * as echarts from 'echarts'
const props = defineProps({
  options: {
    type: Object, 
    default: () => {
      return {}
    }
  },
  id: {
    type: String, 
    default: () => {
      return ''
    }
  }
})

const chartsRef = ref(null)

let style = computed(() => {
  return props.width || props.height
    ? { height: props.height + 'px', width: props.width + 'px' }
    : { height: '100%', width: '100%' }
})

const init = () => {
  chartsRef.value = echarts.init(document.getElementById(props.id))
  chartsRef.value.setOption(props.options)
}

onMounted(() => {
  init()
})

defineExpose({
  getInstance: () => chartsRef.value
})

</script>

<style lang="scss" scoped>

</style>