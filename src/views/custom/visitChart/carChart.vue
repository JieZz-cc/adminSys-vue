<template>
  <div class="chart-box">
    <ECharts :option="option" :resize="true" />
  </div>
</template>


<script setup>
import ECharts from '@/components/Echarts/index.vue'
// import { ECOption } from '@/components/ECharts/config'

const option = {
  color: ['#F34480', '#315FF3'],
  title: {
    text: '男女比例',
    show: true,
    left: 'center',
    top: '45%',
    textStyle: {
      color: '#000',
      textBorderColor: '#fff', // 文字边缘颜色
      textBorderWidth: 1, // 文字边缘宽度
      fontWeight: 600,
      fontSize: 16
    }
  },
  grid: {
    top: '5%'
  },
  tooltip: {
    trigger: 'item'
  },
  series: [
    {
      type: 'pie',
      radius: ['45%', '70%'],
      avoidLabelOverlap: false,
      itemStyle: {
        borderWidth: 2
      },
      label: {
        show: true,
        fontSize: 14,
        formatter: '{b}:{c} 人' //自定义显示格式(b:name, c:value, d:百分比)
      },
      data: [
        { value: 56, name: '女' },
        { value: 64, name: '男' }
      ]
    }
  ]
}
</script>
<style lang="scss" scoped>
.chart-box {
  width: 100%;
  height: 100%;
}
</style>
