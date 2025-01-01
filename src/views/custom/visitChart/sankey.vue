<template>
  <div class="sankey-box">
    <ECharts :option="option"></ECharts>
  </div>
</template>

<script setup name="sankey">
import ECharts from '@/components/ECharts/index.vue'

const option = {
  // title: {
  //   text: '员工出差流向'
  // },
  series: [
    {
      type: 'sankey',
      focusNodeAdjacency: 'allEdges',
      nodeGap: 40,
      nodeWidth: 8,
      layoutIterations: 0,
      emphasis: {
        focus: 'adjacency'
      },
      itemStyle: {
        borderWidth: 1,
        color: '#1b6199',
        borderColor: '#fff',
      },
      label: {
        show: true, 
        left: 50,
        textStyle: {
          rich: {
            a: {
              // color: '#333333',
              left: '20px'
            },
            b: {
              // color: '#999999',
              bottom: '20%'
            }
          }
        },
        formatter: (params) =>{
          let tips = params.data.name + '\n'
          tips = '{b|' + tips + '}' + params.value
          return tips
        }
      },
      data: [
        {name: '上海总公司'},
        {name: '杭州分公司'},
        {name: '北京'},
        {name: '广州'},
        {name: '成都'},
        {name: '深圳'}
      ],
      links: [
        {source: '杭州分公司', target: '北京', value: 300},
        {source: '杭州分公司', target: '广州', value: 150},
        {source: '杭州分公司', target: '深圳', value: 100},
        {source: '杭州分公司', target: '成都', value: 50},
        {source: '上海总公司', target: '北京', value: 200},
        {source: '上海总公司', target: '广州', value: 300},
        {source: '上海总公司', target: '深圳', value: 250},
        {source: '上海总公司', target: '成都', value: 250},
      ]
    }
  ]
}

</script>

<style lang="scss" scoped>
.sankey-box {
  height: 100%;
  width: 100%;
}

</style>