<template>
    <div v-for="(item) in props.menuList" :key="item.path">
      <el-sub-menu v-if="item.children?.length" :index="item.path" class="menu-item">
        <template #title>
          <i class="menu-icon iconfont" :class="'icon-' + item.icon" />
          <span class="shenlue">{{ item.title }}</span>
        </template>
        <sub-menu :menu-list="item.children"></sub-menu>
      </el-sub-menu>
      <el-menu-item v-else :index="item.path" @click="handleClickMenu(item)" class="menu-item">
        <i class="menu-icon iconfont" :class="'icon-' + item.icon"></i>
        <template #title>
          <span class="shenlue">{{ item.title }}</span>
        </template>
      </el-menu-item>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

const props = defineProps({
  menuList: {
    type: Array,
    default: () => {
      return []
    }
  }
})

const handleClickMenu = (item) => {
  if (item.isLink) return window.open(subItem.path, '_blank')
  router.push(item.path)
}

</script>

<style lang="scss" scoped>
.menu-item {
  padding: 0 5px;
  border-radius: 5px;
  font-size: 14px;
  // background-color: #999;
}
.menu-icon {
  margin-right: 5px;
  font-size: 16px;
  text-align: center;
  vertical-align: middle;
}
:deep(.el-menu) {
  background-color: rgba( 0,0,0,  0.02);
}
.el-menu-item.is-active {
  color: #ffffff;
  background-color: var(--el-color-primary);
}
</style>