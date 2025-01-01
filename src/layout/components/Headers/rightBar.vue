<template>
  <div class="right-bar">
    <FullScreen class="tb-item" />
    <SetTheme class="tb-item" />
    <div class="tb-item username">{{ username }}</div>
    <Avatar />
  </div>
</template>

<script setup>
import FullScreen from './components/FullScreen.vue';
import SetTheme from './components/SetTheme.vue';
import Avatar from './components/Avatar.vue';
import {userStore} from '@/stores/userStore' 
import { computed } from 'vue';

const userInfo = userStore()

const username = computed(() => userInfo.getUserInfo.name)

</script>

<style lang="scss" scoped>
.right-bar {
  display: flex;
  align-items: center;
  justify-content: center;
  padding-right: 25px;
  .tb-item {
    margin-right: 14px;
  }

  .username {
    font-size: 15px;
    color: var(--el-header-text-color);
  }
}
</style>