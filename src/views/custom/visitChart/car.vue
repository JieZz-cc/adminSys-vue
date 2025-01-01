<template>
  <div>
    <myCharts :options="options" id="'car'"></myCharts>
  </div>
</template>

<script setup>
import myCharts from '@/components/myCharts.vue'
import { reactive } from 'vue';


const options = reactive({
  color: ['#fa5247', '#2b5aed'],
  title: {
    text: '上周来访车辆',
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
        formatter: '{b}:{c} 辆' //自定义显示格式(b:name, c:value, d:百分比)
      },
      data: [
        { value: 12, name: '外来车辆' },
        { value: 68, name: '区内车辆' }
      ]
    }
  ]
})

</script>

<style lang="scss" scoped>

</style>