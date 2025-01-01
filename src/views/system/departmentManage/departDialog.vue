<template>
  <el-dialog
    v-model="props.showDialog"
    :title="!props.isEdit ? '新增部门' : '编辑部门'"
    width="720"
    :before-close="handleClose"
    :close-on-click-modal="false"
    :draggable="true"
  >
    <el-form ref="departFormRef" :model="departForm" class="mg-t-15" label-width="100px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="部门名称" prop="name" required>
            <el-input v-model="departForm.name" placeholder="请输入" clearable></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="父级部门" prop="parentId" required>
              <el-tree-select
                v-model="departForm.parentId"
                :data="treeData"
                check-strictly
                node-key="id"
                :render-after-expand="false"
                :props="{ label: 'name' }"
              />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="是否启用" prop="isEnable">
            <el-radio-group v-model="departForm.isEnable">
              <el-radio :value="1" size="large">是</el-radio>
              <el-radio :value="0" size="large">否</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="排序" prop="sort">
            <el-input-number v-model="departForm.sort" :min="1" />
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
import {onMounted, reactive, ref} from 'vue'
import {invokeApi} from '@/api'

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


const departFormRef = ref(null)
const departForm = reactive({
  id: '',
  name: '',
  parentId: 1,
  sort: 1,
  isEnable: 0
})

const handleClose = () => { 
  departFormRef.value?.resetFields()
  emits('closeDialog') 
}


const treeData = ref([])
const getTreeData = async () => {
  try {
    const res = await invokeApi('get','/getDepartmentList')
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    treeData.value = res.data
  } catch (error) {
    console.log(error);
    
  }
}

const loading = ref(false)
const confirm = () => {
  departFormRef.value?.validate(async valid => {
    if (valid) {
      try {
        let params = {
          name: departForm.name,
          parentId: departForm.parentId,
          isEnable: departForm.isEnable,
          sort: departForm.sort
        }
        if (props.isEdit) params.id = departForm.id
        let url = props.isEdit ? 'updateDepartment' : 'addNewDepartment'
        let method = props.isEdit ? 'put' : 'post'
        loading.value = true
        const res = await invokeApi(method, url, params)
        if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
        handleClose()
        emits('refresh')
      } catch (error) {
        
      } finally {
        loading.value = false
      }
    }
  })
}

const handleEditForm = () => {
  departForm.id = props.row.id,
  departForm.name = props.row.name,
  departForm.parentId = props.row.parentId,
  departForm.isEnable = props.row.isEnable,
  departForm.sort = props.row.sort
}

onMounted(() => {
  getTreeData()
  if (props.isEdit) handleEditForm()
})

</script>

<style lang="scss" scoped>
.footer {
  text-align: right;
  margin-top: 15px;
}
</style>