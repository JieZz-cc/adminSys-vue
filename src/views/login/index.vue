<template>
  <div class="login-container flex-center">
    <div class="swtich-change">
      <SwitchDark />
    </div>
    <div class="login-box">
      <div class="login-left">
        <h1>JieZz后台权限管理系统</h1>
        <h2>基于SpringBoot 3.X和Vue 3.X</h2>
      </div>
      <div class="login-form">
        <div class="login-logo">
          <h2 class="logo-text">欢迎回来~</h2>
        </div>
        <el-form ref="loginFormRef" :model="account" :rules="rules">
          <el-form-item prop="username">
            <el-input v-model="account.username" :prefix-icon="User" placeholder="用户名"/>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="account.password" :prefix-icon="Lock" type="password" show-password placeholder="密码"/>
          </el-form-item>
        </el-form>
        <div class="btns">
          <el-button type="default" class="btn" size="large" @click="reset">重置</el-button>
          <el-button type="primary" class="btn" size="large" @click="login">登录</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {  User, Lock } from '@element-plus/icons-vue'
import { reactive, ref, getCurrentInstance } from "vue"
import { userStore } from '@/stores/userStore'
import { ElMessage } from 'element-plus';
// import router from '@/router'
import { useRouter } from 'vue-router';
import SwitchDark from '@/components/SwitchDark/index.vue'
import {userMenuAuth} from '@/stores/authMenu'
import { initDynamicRouter } from '@/router/dynamicRouter'
import {useTabStore} from '@/stores/tabs'

const router = useRouter()
const useStore = userStore()
const menuAuth = userMenuAuth()
const tabStore = useTabStore()


const loginFormRef = ref(null)
const account = reactive({
  username: '',
  password: ''
})
const rules = {
  username: [
    { required: true, message: '请输入用户名称', trigger: 'blur' },
    { min: 2, max: 10, message: '账号名称长度为2-10个字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    {
      pattern: /^[a-z0-9]{6,}$/,
      message: '密码必须是6位以上的字母或数字',
      trigger: 'blur'
    }
  ]
}

const globalProperties = getCurrentInstance().appContext.config.globalProperties
const invokeApi = globalProperties.invokeApi
const isLoading = ref(false)
// 登录
const login =  () => {
  loginFormRef.value?.validate(valid => {
    if (valid) {
      isLoading.value = true
      const params = {
        userId: account.username,
        password: account.password
      }
      invokeApi('post', '/login', params).then(async res => {
        isLoading.value = false
        if (res.code === 200) {
          ElMessage.success('登录成功')
          useStore.setToken(res.data.token)
          useStore.setUserInfo(res.data.user)
          const flag = await menuAuth.getAuthMenuList()
          // console.log(flag, 'flag');
          if (flag) {
          // 动态加载路由
            await initDynamicRouter()
            // userStore.getUserInfo(res.data.id)
            // // 清空 tabs
            tabStore.setTabs([])
            // 跳转主
            router.push('/dashboard')
          }
        } else {
          ElMessage.error(res.message)
        }
      })
    }

  })
}

//重置
const reset =  () => {
  loginFormRef.value?.resetFields()
  
}


// 作为组件暴露出去
// defineExpose({
//   login,
//   reset
// })

</script>

<style lang="scss" scoped>
// @import './index.scss';
@import url('./index.scss');
</style>