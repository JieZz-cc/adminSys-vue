<template>
    <el-container class="layout">
      <el-aside class="aside" :style="{width: isCollapse ? '65px' : '210px'}">
        <div class="logo">
          <img src="@/assets/images/vite.svg" alt="log" />
          <span v-show="!isCollapse" class="logo-text">JieZz Admin</span>
        </div>
        <el-scrollbar>
          <el-menu
            :router="false"
            :default-active="activeMenu"
            :collapse="isCollapse"
            :unique-opened="true"
            :collapse-transition="true"
          >
          <subMenu :menu-list="menuList" />
        </el-menu>
        </el-scrollbar>
      </el-aside>
      <el-container>
        <el-header>
          <leftBar />
          <rightBar />
        </el-header>
        <Main></Main>
      </el-container>
    </el-container>
    <ThemeDrawer />
</template>

<script setup>
import {useGlobalStore} from '@/stores/modules/global'
import {userMenuAuth} from '@/stores/authMenu'
import { computed } from 'vue';
import { useRoute } from 'vue-router'   // 当前页面路由信息，参数，路径等， useRouter返回路由实例，用来控制路由跳转
import subMenu from './components/Menus/subMenu.vue'
import leftBar from './components/Headers/leftBar.vue';
import rightBar from './components/Headers/rightBar.vue';
import Main from './components/Main/index.vue'
import ThemeDrawer from './components/ThemeDrawer/index.vue'

const route = useRoute()

const globalStore = useGlobalStore()
const authMenu = userMenuAuth()

const menuList = computed(() => authMenu.showMenuListGet)

const isCollapse = computed(() => globalStore.isCollapse)

const activeMenu = computed(() => route.path)

</script>

<style lang="scss" scoped>
@import url('./index.scss');
</style>