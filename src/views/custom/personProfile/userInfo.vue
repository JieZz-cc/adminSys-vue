<template>
  <div class="user-info">
    <el-descriptions v-if="!showEdit" border title="基本资料" :column="2" label-width="100">
      <template #extra>
        <el-button type="primary" @click="edit">修改</el-button>
      </template>
      <el-descriptions-item label="用户名">
        {{ userInfo.username }}
      </el-descriptions-item>
      <el-descriptions-item label="用户ID">
        {{ userInfo.userId }}
      </el-descriptions-item>
      <el-descriptions-item label="手机">
        {{ userInfo.phone }}
      </el-descriptions-item>
      <el-descriptions-item label="邮箱">
        {{ userInfo.email }}
      </el-descriptions-item>
      <el-descriptions-item label="部门">
        {{ userInfo.deptName }}
      </el-descriptions-item>
      <el-descriptions-item label="角色">
        {{ userInfo.roleName }}
      </el-descriptions-item>
      <el-descriptions-item label="是否管理员">
        {{ userInfo.isSuper === 1 ? '是' : '否' }}
      </el-descriptions-item>
      <el-descriptions-item label="备注">
        {{ userInfo.remark }}
      </el-descriptions-item>
    </el-descriptions>
    <el-form v-else ref="formRef" :model="form" label-width="100" style="width: 80%;">
      <el-form-item label="用户ID" prop="userId" required>
        <el-input v-model="form.userId" placeholder="输入id" clearable />
      </el-form-item>
      <el-form-item label="用户名称" prop="username" required>
        <el-input v-model="form.username" placeholder="输入名称" clearable />
      </el-form-item>
      <el-form-item label="手机" prop="phone">
        <el-input v-model="form.phone" placeholder="输入手机号" clearable />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="form.email" placeholder="输入邮箱" clearable />
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="form.remark" placeholder="输入备注" clearable />
      </el-form-item>
      <el-row v-if="userInfo.isSuper === 1">
        <el-col :span="12">
          <el-form-item label="部门" prop="deptId" required>
            <el-tree-select
              v-model="form.deptId"
              :data="treeData"
              check-strictly
              :render-after-expand="false"
              style="width: 300px"
              node-key="id"
              filterable
              :props="{ label: 'name' }"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="角色" prop="roleId" required>
            <el-select v-model="form.roleId" placeholder="选择角色">
              <el-option
                v-for="item in roleOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <div class="footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" :loading="loading" @click="handleConfirm">提交</el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup>
import {userStore} from '@/stores/userStore'
import { computed, reactive, ref } from 'vue'
import { invokeApi } from '@/api/index.js'

const useUserStore = userStore()
const userInfo = computed(() => useUserStore.getUserInfo)

const showEdit = ref(false)

const formRef = ref(null)
const form = reactive({
  id: userInfo.value.id,
  username: '',
  userId: '',
  phone: '',
  email: '',
  remark: '',
  deptId: '',
  roleId: ''
})

const edit = () => {
  form.userId = userInfo.value.userId
  form.username = userInfo.value.username
  form.phone = userInfo.value.phone
  form.email = userInfo.value.email
  form.remark = userInfo.value.remark
  form.deptId = userInfo.value.deptId
  form.roleId = userInfo.value.roleId
  if (userInfo.value.isSuper === 1) {
    initTree()
    initRoles()
  }
  showEdit.value = true
}

const handleCancel = () => {
  showEdit.value = false
  formRef.value?.resetFields()
}

const loading = ref(false)
const handleConfirm = () => {
   formRef.value?.validate(async valid => {
    if (valid) {
      let params = {
        id: userInfo.value.id,
        userId: form.userId,
        username: form.username,
        email: form.email,
        phone: form.phone,
        remark: form.remark
      }
      if (userInfo.value.isSuper === 1) {
        params.deptId = form.deptId
        params.roleId = form.roleId
      }
      try {
        loading.value = true
        const res = await invokeApi('put', '/updateUserInfo', params)
        if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
        ElMessage.success(res.message || '修改成功')
        await getUserInfo()
        handleCancel()
        
      } catch (error) {
        
      } finally {
        loading.value = false
      }
    }
   })
}

const getUserInfo = async () => {
  try {
    const res = await invokeApi('get', '/getUserInfo', {},{userId: form.userId})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    if (res.data) useUserStore.setUserInfo(res.data)
  } catch (error) {
    
  }
}

// 查询部门列表
const treeData = ref([])
const initTree = async () => {
  try {
    const res = await invokeApi('get', '/getDepartmentList')
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    treeData.value = res.data
  } catch (error) {
    console.log(error);
  }
}
const roleOptions = ref([])
const initRoles = async () => {
  try {
    const res = await invokeApi('get', '/getRoleList')
    if (res && res.code !== 200) return ElMessage.error(res.message || '服务异常')
    roleOptions.value = res.data.rows.map(ele => {
      return {
        value: ele.roleId,
        label: ele.roleName
      }
    })
  } catch (error) {
    console.log(error);
  }
}


</script>

<style lang="scss" scoped>
.user-info {
  padding: 10px;
  .footer {
    margin-top: 15px;
    padding-left: 100px;
  }
}
</style>