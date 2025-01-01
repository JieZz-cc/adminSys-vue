<template>
  <div class="menu-manage">
    <div class="card table-container">
      <div class="mg-b-10">
        <el-button type="primary" @click="addDepartment" style="width: 50px;">新增</el-button>
      </div>
      <el-table class="table-content" :data="tableData" default-expand-all stripe row-key="id" style="width: 100%">
        <el-table-column prop="name" label="部门名称" show-overflow-tooltip />
        <el-table-column prop="id" label="部门ID" />
        <el-table-column prop="sort" label="排序" show-overflow-tooltip  />
        <el-table-column prop="isEnable" label="是否启用" >
          <template #default="{ row }">
            <el-tag :type="row.isEnable ? 'success' : 'danger'">{{ row.isEnable ? '是' : '否' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column fixed="right" prop="operation" label="操作" width="160" align="center">
          <template #default="{ row }">
              <el-button link type="primary" size="small" @click="edit(row)">
                <i class="btn-icon mr4 iconfont icon-xiugai"></i> <span>编辑</span>
              </el-button>
              <el-popconfirm
                @confirm="del(row.id)"
                confirm-button-text="是"
                cancel-button-text="否"
                title="确认删除?"
              >
                <template #reference>
                  <el-button link type="primary" size="small">
                    <i class="btn-icon mr4 iconfont icon-shanchu"></i><span>删除</span>
                  </el-button>
                </template>
              </el-popconfirm>
            </template>
        </el-table-column>
      </el-table>
    </div>
    <depart-dialog v-if="showDialog" :showDialog="showDialog" :isEdit="isEdit" :row="rowSelected" 
                 @refresh="initTable" @closeDialog="closeDialog"/>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import {invokeApi} from '@/api'
import departDialog from './departDialog.vue';


const showDialog = ref(false)
const isEdit = ref(false)

const addDepartment = () => { 
  isEdit.value = false
  showDialog.value = true 
}

const closeDialog = () => { showDialog.value = false }

let rowSelected = {}
const edit = async row => {
  showDialog.value = true
  isEdit.value = true
  rowSelected = row
}

const del = async id => {
  try {
    const res = await invokeApi('delete', '/deleteDepartment', {id: id})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    ElMessage.success(res.message || '删除成功')
    initTable()
  } catch (error) {
    
  }
}

const tableData = ref([])
const initTable = async () => {
  try {
    const res = await invokeApi('get','/getDepartmentList')
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    tableData.value = res.data
  } catch (error) {
    console.log(error);
    
  }
}


onMounted(() => {
  initTable()
})

</script>

<style lang="scss" scoped>
// @import url('./index.scss');
</style>