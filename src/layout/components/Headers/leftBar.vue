<template>
  <div class="left-bar">
    <CollapseIcon />
    <BreadCrumb v-show="globalStore.breadcrumb" />
  </div>
</template>

<script setup>
import CollapseIcon from './components/CollapseIcon.vue';
import BreadCrumb from './components/BreadCrumb.vue';
import {useGlobalStore} from '@/stores/modules/global'

const globalStore = useGlobalStore()

</script>

<style lang="scss" scoped>
.left-bar {
  display: flex;
  align-items: center;
}
</style>