<template>
  <div class="main">
    <el-form ref="accountFormRef" :model="account" :rules="rules" label-width="100" style="width: 50%;">
      <el-form-item prop="oldPwd" label="旧密码" required>
         <el-input v-model="account.oldPwd"  type="password" show-password placeholder="输入旧密码"/>
      </el-form-item>
      <el-form-item prop="newPwd" label="新密码" required>
         <el-input v-model="account.newPwd"  type="password" show-password placeholder="输入新密码"/>
      </el-form-item>
      <el-form-item prop="rePwd" label="确认密码" required>
         <el-input v-model="account.rePwd"  type="password" show-password placeholder="确认新密码"/>
      </el-form-item>
      <div class="footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="confirm" :loading="loading">提交</el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import {ref, reactive} from 'vue'
import { invokeApi } from '@/api/index.js'
import { useRouter } from 'vue-router'

const router = useRouter()
const accountFormRef = ref(null)
const account = reactive({
  oldPwd: '',
  newPwd: '',
  rePwd: ''
})
const rules = {
  oldPwd: [
    { required: true, message: '请输入原密码', trigger: 'blur' },
    { min: 6, max: 16, message: '密码长度为6-16个字符', trigger: 'blur' }
  ],
  newPwd: [
    { required: true, message: '请输入新密码', trigger: 'blur' },
    { min: 6, max: 16, message: '密码长度为6-16个字符', trigger: 'blur' }
  ],
  rePwd: [
    { required: true, message: '请确认新密码', trigger: 'blur' },
    { min: 6, max: 16, message: '密码长度为6-16个字符', trigger: 'blur' }
  ]
}

const cancel = () => {
  accountFormRef.value?.resetFields()
}
const loading = ref(false)
const confirm = () => {
  if (account.newPwd !== account.rePwd) return ElMessage.warning('两次输入密码不一致！')
  accountFormRef.value?.validate(async valid => {
    if (valid) {
      const params = {
        old_pwd: account.oldPwd,
        new_pwd: account.newPwd,
        re_pwd: account.rePwd,
      }
      loading.value = true
      try {
        const res = await invokeApi('patch', '/updatePassword', params)
        if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
        ElMessage.success('修改密码成功，请重新登录')
        router.replace('/login')
      } catch(err) {
        
      } finally {
        loading.value = false
      }
    }
  })
}

</script>

<style lang="scss" scoped>
.main {
  .footer {
    margin-top: 30px;
    padding-left: 100px;
  }
}
</style>