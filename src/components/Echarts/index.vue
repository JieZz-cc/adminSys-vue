<template>
  <div id="echarts" ref="chartRef" :style="echartsStyle"/>
</template>

<script setup>
import {
  ref,
  onMounted,
  onBeforeUnmount,
  watch,
  computed,
  markRaw,
  nextTick,
  onActivated
} from 'vue'
// import { EChartsType, ECElementEvent } from 'echarts/core'
import {useGlobalStore} from '@/stores/modules/global'
import { useDebounceFn } from '@vueuse/core'
import { storeToRefs } from 'pinia'
import * as echarts from 'echarts'
import 'echarts-liquidfill'       // 水滴图

const props = defineProps({
  option: {
    type: Object,
    default: () => {
      return {}
    }
  },
  resize: {
    type: Boolean,
    default: () => {
      return true
    }
  }
})

const echartsStyle = computed(() => {
  return props.width || props.height
    ? { height: props.height + 'px', width: props.width + 'px' }
    : { height: '100%', width: '100%' }
})

const chartRef = ref(null)
const chartInstance = ref(null)
const draw = () => {
  if (chartInstance.value) {
    chartInstance.value.setOption(props.option, { notMerge: true })
  }
}

watch(props, (newVal, oldVal) => {
  draw()
})

const handleClick = (event) => props.onClick && props.onClick(event)

const init = () => {
  if (!chartRef.value) return
  chartInstance.value = echarts.getInstanceByDom(chartRef.value)

  if (!chartInstance.value) {
    chartInstance.value = markRaw(
      echarts.init(chartRef.value, props.theme, {
        renderer: props.renderer
      })
    )
    chartInstance.value.on('click', handleClick)
    draw()
  }
}
const resize = () => {
  if (chartInstance.value && props.resize) {
    chartInstance.value.resize({ animation: { duration: 300 } })
  }
}

const debouncedResize = useDebounceFn(resize, 300, { maxWait: 800 })
const globalStore = useGlobalStore()
const { isCollapse } = storeToRefs(globalStore)

watch(
  () => [isCollapse],
  () => {
    debouncedResize()
  },
  { deep: true }
)

onBeforeUnmount(() => {
  chartInstance.value?.dispose()
  window.removeEventListener('resize', debouncedResize)
})

onMounted(() => {
  nextTick(() => init())
  window.addEventListener('resize', debouncedResize)
})

onActivated(() => {
  if (chartInstance.value) {
    chartInstance.value.resize()
  }
})



defineExpose({
  getInstance: () => chartRef.value,
  resize,
  draw
})
</script>

<style lang="scss" scoped>

</style>