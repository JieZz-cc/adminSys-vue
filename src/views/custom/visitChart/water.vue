<template>
  <div class="chart-box">
    <ECharts :option="option" />
  </div>
</template>

<script setup name="water">
import ECharts from '@/components/ECharts/index.vue'

const option = {
  series: [
    {
      type: 'liquidFill',
      data: [0.3, 0.5, 0.4, 0.3]
    }
  ]
}
</script>

<style scoped lang="scss">
.chart-box {
  height: 100%;
}
</style>
