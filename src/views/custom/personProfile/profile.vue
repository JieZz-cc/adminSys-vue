<template>
  <div class="main">
    <el-card shadow="never">
      <el-row>
        <el-col :span="4">
          <el-menu default-active="userInfo">
            <el-menu-item index="userInfo" class="menu-item" @click="handleClickMenu('userInfo')">个人信息</el-menu-item>
            <el-menu-item index="editPwd" class="menu-item" @click="handleClickMenu('editPwd')">修改密码</el-menu-item>
          </el-menu>
        </el-col>
        <el-col :span="20">
          <!-- is 绑定的是组件 -->
          <transition name="fade-transform" mode="out-in" appear>
            <component :is="activeComponent" class="mg-l-5"></component>
          </transition>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script setup>
import {ref, markRaw, reactive, onMounted} from 'vue'
import userInfo from './userInfo.vue'
import editPwd from './editPwd.vue'


// markRaw 将响应式对象转为非响应式对象，优化性能
const activeComponent = ref(markRaw(userInfo))
// 注册子组件
const allComponents = ref([
  {
    name: 'userInfo',
    com: markRaw(userInfo)
  },
  {
    name: 'editPwd',
    com: markRaw(editPwd)
  }
])

const handleClickMenu = val => {
  const target = allComponents.value.filter(item => item.name === val)[0]
  activeComponent.value = target.com
}

</script>

<style lang="scss" scoped>
.main {
  // padding: 20px;
  
  :deep(.el-card) {
    border-radius: 10px;
  }
  // .menu {
  //   padding-right: ;
  // }
  .menu-item {
    border-radius: 10px;
    height: 44px;
    margin-bottom: 5px;
    margin-right: 5px;
  }
}
</style>