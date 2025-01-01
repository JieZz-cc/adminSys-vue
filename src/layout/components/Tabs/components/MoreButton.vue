<template>
  <div>
    <el-dropdown trigger="click" class="more-btns">
      <div class="down-box flex-center">
        <i class="iconfont icon-down"></i>
      </div>
      <template #dropdown>
        <el-dropdown-menu>
          <!-- <el-dropdown-item @click="closeCurrentTab">
            <span class="flex-center"><i class="iconfont icon-minus-circle"></i>关闭当前</span>
          </el-dropdown-item> -->
          <!-- <el-dropdown-item @click="tabStore.closeTabsOnSide(route.fullPath, 'left')">
            <span class="flex-center"><i class="iconfont icon-minus-circle"></i>关闭左侧</span>
          </el-dropdown-item>
          <el-dropdown-item @click="tabStore.closeTabsOnSide(route.fullPath, 'right')">
            <span class="flex-center"><i class="iconfont icon-minus-circle"></i>关闭右侧</span>
          </el-dropdown-item> -->
          <el-dropdown-item @click="tabStore.closeMultipleTab(route.fullPath)">
            <span class="flex-center"><i class="iconfont icon-minus-circle"></i>关闭其他</span>
          </el-dropdown-item>
          <el-dropdown-item @click="closeAllTab">
            <span class="flex-center"><i class="iconfont icon-minus-circle"></i>关闭全部</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script setup>
import {useTabStore} from '@/stores/tabs'
import {useRoute, useRouter} from 'vue-router'

const route = useRoute()
const router = useRouter()
const tabStore = useTabStore()

const closeCurrentTab = () => {
  if (route.isAffix) return
  tabStore.removeTab(route.fullPath)
}

const closeAllTab = () => {
  tabStore.closeMultipleTab()
  
  
  // router.push('/dashboard', {quer: '11'})
}

</script>

<style lang="scss" scoped>
.more-btns {
  position: absolute;
  top: 0;
  right: 0;
  .down-box {
    width: 50px;
    height: 40px;
    cursor: pointer;
    border-left: 1px solid var(--el-border-color-light);
  }
}
</style>