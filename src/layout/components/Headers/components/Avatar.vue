<template>
  <div class="main">
    <el-popover placement="bottom" trigger="hover" :width="220">
      <template #reference>
        <!-- <el-avatar src="@/assets/images/71.png" /> -->
         <img  src="@/assets/images/71.png" alt="" style="width: 40px;height: 40px;border-radius: 50%;" />
      </template>
      <div class="top">
        <img  src="@/assets/images/71.png" alt="" style="width: 70px;height: 70px;border-radius: 50%;margin-right: 10px;" />
        <div >
          <p>用户：{{userInfo.username}}</p>
          <p>部门：{{ userInfo.deptName }}</p>
          <!-- <p>角色：{{ userInfo.roleName }}</p> -->
        </div>
      </div>
      <div>
        <div class="item" @click="toProfile">
          <p class="pd-l-5"><i class="iconfont icon-user"></i><span class="mg-l-8">个人中心</span></p>
        </div>
        <div class="item" @click="logout">
          <p class="pd-l-5"><i class="iconfont icon-tuichu"></i><span class="mg-l-8">退出登录</span></p>
        </div>
      </div>
    </el-popover>
  </div>
</template>

<script setup>
import { ElMessageBox, ElMessage } from 'element-plus'
import router from '@/router'
import {userStore} from '@/stores/userStore'
import {computed} from 'vue'

const useStroe = userStore()
const userInfo = computed(() => useStroe.getUserInfo)

const toProfile = () => {
  router.push('/layout/profile')
}

const logout = () => {
  ElMessageBox.confirm('是否确认退出？', '温馨提示', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    // useStroe.removeToken()
    router.push('/login')
    ElMessage.success('已成功退出登录！')
    // window.localStorage.clear()
  })
}


</script>

<style lang="scss" scoped>
.main {
  .img {
    width: 40px;
    height: 40px;
  }
}
.top {
  display: flex;
  // justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #ddd;
}
.item {
  border-bottom: 1px solid #ddd;
  height: 32px;
  line-height: 32px;
  &:hover {
    cursor: pointer;
    background-color: rgba(0,0,0,0.03);
  }
}

</style>