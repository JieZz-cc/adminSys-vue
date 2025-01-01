<template>
  <div class="chart-box">
    <ECharts :option="option" />
  </div>
</template>

<script setup name="bar">
import ECharts from '@/components/ECharts/index.vue'


const mockData = [
  {
    label: '研发部',
    value: 1314
  },
  {
    label: '行政部',
    value: 520
  },
  {
    label: '营销部',
    value: 700
  }
]

const option = {
  grid: {
    left: '1%',
    right: '3%',
    top: '6%',
    bottom: '3%',
    containLabel: true
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  xAxis: {
    type: 'value',
    boundaryGap: [0, 0.01]
  },
  yAxis: {
    type: 'category',
    axisTick: {
      show: false
    },
    axisLine: {
      show: false
    },
    data: mockData.map((item) => item.label)
  },
  series: [
    {
      type: 'bar',
      barWidth: 20,
      itemStyle: {
        color: '#1e84f0',
        borderRadius: [30, 30, 30, 30]
      },
      data: mockData.map((item) => item.value)
    }
  ]
}
</script>

<style scoped lang="scss">
.chart-box {
  height: 100%;
}
</style>
