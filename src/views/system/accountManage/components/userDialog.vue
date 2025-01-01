<template>
  <div>
    <el-dialog
      v-model="props.dialogVisible"
      :title="!props.isEdit ? '新增用户' : '编辑用户'"
      width="720"
      :before-close="handleClose"
      :close-on-click-modal="false"
      :draggable="true"
    >
      <template v-if="!props.isEdit">
        <el-form
          ref="userFormRef"
          :model="userForm"
          :rules="userFormRules"
          label-width="80px"
          class="user-form mg-t-15"
        >
          <el-row>
            <el-col :span="12">
              <el-form-item label="用户" prop="userId">
                <el-input v-model="userForm.userId" placeholder="输入名称" clearable />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="昵称" prop="username">
                <el-input v-model="userForm.username" placeholder="输入昵称" clearable />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="部门" prop="deptId" required>
                <el-tree-select
                  v-model="userForm.deptId"
                  :data="deptData"
                  check-strictly
                  :render-after-expand="false"
                  style="width: 265px"
                  node-key="id"
                  filterable
                  :props="{ label: 'name' }"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="角色" prop="roleId" required>
                <el-select v-model="userForm.roleId" placeholder="选择角色">
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
          <el-row>
            <el-col :span="12">
              <el-form-item label="手机号" prop="phone">
                <el-input v-model="userForm.phone" placeholder="输入手机号" clearable />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="邮箱" prop="email">
                <el-input v-model="userForm.email" placeholder="输入邮箱" clearable />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="密码" prop="password">
                <el-input
                  v-model="userForm.password"
                  type="password"
                  show-password
                  placeholder="输入密码"
                  clearable
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="备注" prop="remark">
                <el-input
                  v-model="userForm.remark"
                  type="textarea"
                  placeholder="输入备注"
                  clearable
                />
              </el-form-item>
            </el-col>
          </el-row>
          <div class="dialog-footer">
              <el-button @click="handleCancel">取消</el-button>
              <el-button type="primary" :loading="loading" @click="handleConfirm"> 确认 </el-button>
          </div>
        </el-form>
      </template>
      <template v-else>
        <el-form
          ref="editUserFormRef"
          :model="editUserForm"
          :rules="editUserFormRules"
          label-width="80px"
          class="user-form mg-t-15"
        >
          <el-row>
            <el-col :span="12">
              <el-form-item label="用户" prop="userId">
                <el-input v-model="editUserForm.userId" placeholder="输入名称" clearable />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="昵称" prop="username">
                <el-input v-model="editUserForm.username" placeholder="输入昵称" clearable />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="部门" prop="deptId" required>
                <el-tree-select
                  v-model="editUserForm.deptId"
                  :data="deptData"
                  check-strictly
                  :render-after-expand="false"
                  style="width: 265px"
                  node-key="id"
                  filterable
                  :props="{ label: 'name' }"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="角色" prop="roleId" required>
                <el-select v-model="editUserForm.roleId" placeholder="选择角色">
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
          <el-row>
            <el-col :span="12">
              <el-form-item label="手机号" prop="phone">
                <el-input v-model="editUserForm.phone" placeholder="输入手机号" clearable />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="邮箱" prop="email">
                <el-input v-model="editUserForm.email" placeholder="输入邮箱" clearable />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="备注" prop="remark">
                <el-input
                  v-model="editUserForm.remark"
                  type="textarea"
                  placeholder="输入备注"
                  clearable
                />
              </el-form-item>
            </el-col>
          </el-row>
          <div class="dialog-footer">
            <el-button @click="handleCancel">取消</el-button>
            <el-button type="primary" :loading="loading" @click="handleConfirm">确认</el-button>
          </div>
        </el-form>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { invokeApi } from '@/api/index.js'

const emits = defineEmits(['refresh', 'closeDialog'])
const props = defineProps({
  dialogVisible: {
    type: Boolean,
    default: () => {
      return false
    }
  },
  deptData: {
    type: Array,
    default: () => {
      return []
    }
  },
  isEdit: {
    type: Boolean,
    default: () => {
      return false
    }
  },
  row: {            // 编辑数据回显
    type: Object,
    default: () => {
      return {}
    }
  }
})

const userFormRef = ref(null)
const userForm = reactive({
  userId: '',
  password: '',
  username: '',
  deptId: '', // 默认部门顶级
  roleId: '', // 默认普通用户
  phone: '',
  email: '',
  remark: ''
})
const userFormRules = reactive({
  userId: [
    { required: true, message: '请输入用户名称', trigger: 'blur' },
    { min: 2, max: 10, message: '账号名称长度为2-10个字符', trigger: 'blur' },
    {
      pattern: /^[A-Za-z0-9-_]*$/,
      message: '必须是字母或数字,特殊符号允许-和_',
      trigger: 'blur'
    }
  ],
  password: [
    { required: true, message: '请输入用户密码', trigger: 'blur' },
    {
      pattern: /^[a-z0-9]{6,16}$/,
      message: '密码必须是6~16位的字母或数字',
      trigger: 'blur'
    }
  ],
  username: [
    { required: true, message: '请输入用户昵称', trigger: 'blur' },
    { min: 2, max: 10, message: '账号名称长度为2-10个字符', trigger: 'blur' }
  ]
})

const editUserFormRef = ref(null)
const editUserForm = reactive({
  id: 0,
  userId: '',
  username: '',
  deptId: null, // 默认部门顶级
  roleId: null, // 默认普通用户
  phone: '',
  email: '',
  remark: ''
})
const editUserFormRules = reactive({
  userId: [
    { required: true, message: '请输入用户名称', trigger: 'blur' },
    { min: 2, max: 10, message: '账号名称长度为2-10个字符', trigger: 'blur' },
    {
      pattern: /^[A-Za-z0-9-_]*$/,
      message: '必须是字母或数字,特殊符号允许-和_',
      trigger: 'blur'
    }
  ],
  username: [
    { required: true, message: '请输入用户昵称', trigger: 'blur' },
    { min: 2, max: 10, message: '账号名称长度为2-10个字符', trigger: 'blur' }
  ]
})

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


const handleNew = () => {
  // props.isEdit.value = false
  // dialogVisible.value = true
}

const handleEdit = (row) => {
  editUserForm.id = row.id
  editUserForm.userId = row.userId 
  editUserForm.username = row.username
  editUserForm.deptId = row.deptId
  editUserForm.roleId = row.roleId
  editUserForm.phone = row.phone || ''
  editUserForm.email = row.email || ''
  editUserForm.remark = row.remark || ''
}


const handleClose = () => {
  userFormRef.value?.resetFields()
  editUserFormRef.value?.resetFields()
  emits('closeDialog')
}

const handleCancel = () => {
  handleClose()
}

const loading = ref(false)
const handleConfirm = () => {
  if (!props.isEdit) {            // 新增
    userFormRef.value?.validate(async valid => {
      if (valid) {
        try {
          const params = {
            roleId: userForm.roleId,
            username: userForm.username,
            userId: userForm.userId,
            password: userForm.password,
            deptId: userForm.deptId,
            phone: userForm.phone,
            email: userForm.email,
            remark: userForm.remark
          }
          loading.value = true
          const res = await invokeApi('post', '/addNewUser', params)
          if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
          ElMessage.success(res.message || '新增成功')
          handleClose()
          emits('refresh')
        } catch (error) {
          
        } finally {
          loading.value = false
        }
      }
    })
  } else {                        // 编辑
    editUserFormRef.value?.validate(async valid => {
      if (valid) {
        try {
          const params = {
            id: editUserForm.id,
            userId: editUserForm.userId,
            username: editUserForm.username,
            deptId: editUserForm.deptId,
            roleId: editUserForm.roleId,
            email: editUserForm.email,
            phone: editUserForm.phone,
            avatar: editUserForm.avatar,
            remark: editUserForm.remark
          }
          loading.value = true
          const res = await invokeApi('put', '/updateUserInfo', params)
          if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
          ElMessage.success(res.message || '修改成功')
          handleClose()
          emits('refresh')
        } catch (error) {
          
        } finally {
          loading.value = false
        }
      }
    })
  }
}

onMounted(() => {
  initRoles()
  if (props.isEdit) {
    handleEdit(props.row)
  }
})

defineExpose({
  handleNew,
  handleEdit
})
</script>

<style scoped lang="scss">
.dialog-footer {
  text-align: right;
}
</style>
