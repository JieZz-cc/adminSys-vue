<template>
  <el-dialog v-model="props.showDialog" 
    :title="!props.isEdit ? '新增角色' : '编辑角色'"
    width="720"
    :before-close="handleClose"
    :close-on-click-modal="false"
    :draggable="true"
    >
        <el-form ref="roleFormRef" :model="roleForm" class="mg-t-15" label-width="100px">
          <el-form-item label="角色Id" prop="roleId" required>
            <el-input v-model="roleForm.roleId" placeholder="请输入" clearable></el-input>
          </el-form-item>
          <el-form-item label="角色名" prop="roleName" required>
            <el-input v-model="roleForm.roleName" placeholder="请输入" clearable></el-input>
          </el-form-item>
          <el-form-item label="管理员" prop="isSuper">
            <el-radio-group v-model="roleForm.isSuper">
              <el-radio :value="1" size="large">是</el-radio>
              <el-radio :value="0" size="large">否</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="菜单权限" prop="menus">
            <div>
              <el-checkbox v-model="treeExpand" @change="expandTree(treeExpand)">
                展开</el-checkbox>
              <el-checkbox v-model="checkAll" @change="selectAll(checkAll)">
                全选</el-checkbox>
              <el-checkbox v-model="checkStrictly">
                联动</el-checkbox>
            </div>
            <div class="menu-tree">
              <el-tree
                ref="treeRef"
                show-checkbox
                node-key="menuId"
                :check-strictly="!checkStrictly"
                :data="treeData"
                :props="{children: 'children', label: 'title'}"
              />
            </div>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="roleForm.remark" placeholder="请输入" type="textarea" clearable />
          </el-form-item>
          <div class="footer">
            <el-button @click="handleClose">取消</el-button>
            <el-button type="primary" :loading="loading" @click="confirm">确认</el-button>
          </div>
        </el-form>
  </el-dialog>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import {invokeApi} from '@/api'
import { ElMessage } from 'element-plus';

const emits = defineEmits(['refresh', 'closeDialog'])
const props = defineProps({
  showDialog: {
    type: Boolean,
    default: () => {
      return false
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

const roleFormRef = ref(null)
const roleForm = reactive({
  id: '',
  roleId: '',
  roleName: '',
  isSuper: 0,
  menus: [],
  remark: ''
})
const formRules = {
  roleId: [
    { required: true, message: '请输入角色role', trigger: 'blur' },
    {
      pattern: /^[A-Za-z0-9-_]*$/,
      message: '必须是字母或数字,特殊符号允许-和_',
      trigger: 'blur'
    }
  ],
  roleName: [{ required: true, message: '请输入角色名称', trigger: 'blur' }]
}

const treeExpand = ref(false)     // 菜单是否展开
const checkAll = ref(false)       // 全选
const checkStrictly = ref(true)   // 是否联动
const treeData = ref([])
const treeRef = ref(null)

// object.keys: 将对象所有键作为数组返回；object.values: 将对象所有键对应的值作为数组返回；
const expandTree = val => {
    if (treeRef.value) {
      if (val) {
        Object.values(treeRef.value.store.nodesMap).forEach(i => i.expand())
      } else {
        Object.values(treeRef.value.store.nodesMap).forEach(i => i.collapse())
      }
    }
}

const selectAll = val => {
  const menuIds = getMenuIds(treeData.value)
  treeRef.value?.setCheckedKeys(val ? menuIds : [])
}

const getMenuIds = (menus) => {
  let ids = []
  menus.forEach(ele => {
    ids.push(ele.menuId)
    if (ele.children) {
      ids.push(...getMenuIds(ele.children))
    }
  })
  return ids
}

const confirm = () => {
  if (!props.isEdit) {
    addRole()
  } else {
    editRole()
  }
}

const getCheckedMenus = () => {
  const checkedMenus = treeRef.value?.getCheckedKeys(false)
  const halfCheckedMenus = treeRef.value?.getHalfCheckedKeys()
  const menus = [...checkedMenus, ...halfCheckedMenus]
  return menus
}

const loading = ref(false)
const addRole = async () => {
  const params = {
      roleId: roleForm.roleId,
      roleName: roleForm.roleName,
      isSuper: roleForm.isSuper,
      remark: roleForm.remark,
      menuList: getCheckedMenus()
  }
  try {
    loading.value = true
    const res = await invokeApi('post', '/addRole', params)
    if (res.code !== 200) return ElMessage.error(res.message)
    ElMessage.success(res.message)
    handleClose()
    emits('refresh')
  } catch (error) {
    
  } finally {
    loading.value = false
  }
}

const editRole = async () => {
  try {
    const params = {
      id: roleForm.id,
      roleId: roleForm.roleId,
      roleName: roleForm.roleName,
      remark: roleForm.remark,
      isSuper: roleForm.isSuper,
      menuList: getCheckedMenus()
    }
    loading.value = true
    const res = await invokeApi('put', '/updateRole', params)
    if (res.code !== 200) return ElMessage.error(res.message)
    ElMessage.success(res.message)
    handleClose()
    emits('refresh')
  } catch (error) {
    
  } finally {
    loading.value = false
  }
}

const handleEditForm = async () => {
  roleForm.id = props.row.id
  roleForm.roleId = props.row.roleId
  roleForm.roleName = props.row.roleName
  roleForm.isSuper = props.row.isSuper
  roleForm.remark = props.row.remark
  try {
    const param = {
      roleId: props.row.roleId
    }
    const res = await invokeApi('get', '/getMenusById', {}, param)
    if (res.code !== 200) return ElMessage.error(res.message)
    treeRef.value?.setCheckedKeys(res.data)
  } catch (error) {
    
  }
}

const getTeeData = async () => {
  try {
    const res = await invokeApi('get', '/getAllMenu')
    if (res.code !== 200) return ElMessage.error(res.message)
    treeData.value = res.data
  } catch (error) {
    
  }
}


const handleClose = () => { 
  roleFormRef.value?.resetFields()
  emits('closeDialog') 
}

onMounted(() => {
  getTeeData()
  if (props.isEdit) {
    handleEditForm()
  }
})

</script>

<style lang="scss" scoped>
.menu-tree {
  width: 100%;
  border: 1px solid var(--el-border-color-light);
  border-radius: 4px;
  height: 180px;
  overflow-y: auto;

  .el-tree-node__content:hover {
    background-color: var(--el-tree-node-hover-bg-color);
  }

  .el-tree-node__content {
    --el-checkbox-height: var(--el-tree-node-content-height);

    display: flex;
    align-items: center;
    height: var(--el-tree-node-content-height);
    cursor: pointer;
  }
}
.footer {
  text-align: right;
  margin-bottom: 5px;
}
</style>