<template>
  <el-dialog v-model="props.showDialog"
    :title="!props.isEdit ? '新增菜单' : '编辑菜单'"
    width="720"
    :before-close="handleClose"
    :close-on-click-modal="false"
    :draggable="true"
  >
    <el-form ref="menuFormRef" :model="menuForm" class="mg-t-15" label-width="100px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="菜单id" prop="menuId" required>
            <el-input v-model="menuForm.menuId" placeholder="请输入" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="菜单名" prop="title" required>
            <el-input v-model="menuForm.title" placeholder="请输入" clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="路径" prop="path" required>
            <el-input v-model="menuForm.path" placeholder="请输入" clearable />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="父级菜单" prop="parentId" required>
              <el-tree-select
                v-model="menuForm.parentId"
                :data="menuData"
                check-strictly
                node-key="id"
                :render-after-expand="false"
                :props="{ label: 'title' }"
              />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="排序" prop="sort">
            <el-input-number v-model="menuForm.sort" :min="1" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="菜单图标" prop="icon">
            <el-input v-model="menuForm.icon" placeholder="请输入" clearable />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="是否启用" prop="isEnable">
            <el-radio-group v-model="menuForm.isEnable">
              <el-radio :value="1" size="large">是</el-radio>
              <el-radio :value="0" size="large">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否缓存" prop="isKeepAlive">
            <el-radio-group v-model="menuForm.isKeepAlive">
              <el-radio :value="1" size="large">是</el-radio>
              <el-radio :value="0" size="large">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="是否链接" prop="isLink">
            <el-radio-group v-model="menuForm.isLink">
              <el-radio :value="1" size="large">是</el-radio>
              <el-radio :value="0" size="large">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否固定" prop="isAffix">
            <el-radio-group v-model="menuForm.isAffix">
              <el-radio :value="1" size="large">是</el-radio>
              <el-radio :value="0" size="large">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
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
import {userMenuAuth} from '@/stores/authMenu'
const menuAth = userMenuAuth()


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
  row: {            
    type: Object,
    default: () => {
      return {}
    }
  }
})

const menuFormRef = ref(null)
const menuForm = reactive({
  menuId: '',
  title: '',
  path: '',
  parentId: 0,
  icon: '',
  sort: 1,
  isEnable: 0,
  isKeepAlive: 0,
  isLink: 0,
  isAffix: 0,
  id: ''
})

const handleClose = () => { 
  menuFormRef.value?.resetFields()
  emits('closeDialog') 
}

const handleEditForm = () => {
  menuForm.menuId = props.row.menuId
  menuForm.title = props.row.title
  menuForm.path = props.row.path
  menuForm.parentId = props.row.parentId
  menuForm.isAffix = props.row.isAffix
  menuForm.icon = props.row.icon
  menuForm.sort = props.row.sort
  menuForm.isEnable = props.row.isEnable
  menuForm.isKeepAlive = props.row.isKeepAlive
  menuForm.isLink = props.row.isLink
  menuForm.id = props.row.id
}

const loading = ref(false)
const confirm = async () => {
  menuFormRef.value?.validate(async valid => {
    if (valid) {
      try {
        let params = {
          menuId: menuForm.menuId,
          title: menuForm.title,
          path: menuForm.path,
          parentId: menuForm.parentId,
          sort: menuForm.sort,
          icon: menuForm.icon,
          isEnable: menuForm.isEnable,
          isAffix: menuForm.isAffix,
          isKeepAlive: menuForm.isKeepAlive,
          isLink: menuForm.isLink
        }
        let url = props.isEdit ? '/updateMenu' : '/addMenu'
        let method =  props.isEdit ? 'put' : 'post'
        if (props.isEdit) params.id = menuForm.id
        loading.value = true
        const res = await invokeApi(method, url, params)
        if (res.code !== 200) ElMessage.error(res.message)
        handleClose()
        emits('refresh')
        menuAth.getAuthMenuList()
      } catch (error) {
        
      } finally {
        loading.value = false
      }
    }
  })
}

const menuData = ref([{id: 0, title: '根目录', parentId: -1, children: []}])
const getMenuData = async () => {
  try {
    const res = await invokeApi('get','/getAllMenu')
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    menuData.value[0].children = res.data
  } catch (error) {
    
  }
}

onMounted(() => {
  getMenuData()
  if (props.isEdit) {
    handleEditForm()
  }
})

</script>

<style lang="scss" scoped>
.footer {
  text-align: right;
  margin-top: 15px;
}
</style>