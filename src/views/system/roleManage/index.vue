<template>
  <div class="role-manage">
    <div class="top card mg-b-10">
      <span>角色：</span>
      <el-input v-model="searchVal" placeholder="输入名称或id" clearable style="width: 240px;margin-right: 16px;"></el-input>
      <span>管理员：</span>
      <el-select v-model="isSuper" style="width: 240px;margin-right: 16px;">
        <el-option 
          v-for="item in selectList" 
          :key="item.value" 
          :label="item.label" 
          :value="item.value" />
      </el-select>
      <el-button type="primary" @click="search">检索</el-button>
      <el-button type="default" @click="reset">重置</el-button>
    </div>
    <div class="card table-container">
      <div>
        <el-button type="primary" @click="addRole" style="width: 50px;">新增</el-button>
        <el-button type="default" @click="delInBatch" :loading="loading" style="width: 80px;">批量删除</el-button>
      </div>
      <el-table class="table-content" :data="tableData" @selection-change="handleSelectionChange" style="width: 100%;margin-top: 20px;">
        <el-table-column type="selection" width="50" />
        <el-table-column prop="roleName" label="角色名" width="150" />
        <el-table-column prop="roleId" label="角色ID" width="150" />
        <el-table-column prop="isSuper" label="超级管理员" width="150">
            <template #default="{ row }">
              <el-tag :type="row.isSuper === 1 ? 'success': 'danger' ">
                {{ row.isSuper === 1 ? '是' : '否' }}
              </el-tag>
            </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column prop="updateTime" label="更新时间" width="180" />
        <el-table-column prop="remark" label="备注" />
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
      <el-pagination
        class="table-pagination mg-t-15"
        :total="pagination.total"
        :current-page="pagination.currentPage"
        :page-size="pagination.pageSize"
        :page-sizes="[10, 20, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="sizeChange"
        @current-change="pageChange"
      />
    </div>
    <role-dialog v-if="showDialog" :showDialog="showDialog" :isEdit="isEdit" :row="rowSelected" 
                 @refresh="initTable" @closeDialog="closeDialog"/>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import {invokeApi} from '@/api'
import roleDialog from './components/roleDialog.vue';


const searchVal = ref('')
const isSuper = ref(2)
const selectList = [
  {value: 2, label: '全部'},
  {value: 1, label: '是'},
  {value: 0, label: '否'}
]
const pagination = reactive({
  total: 0,
  currentPage: 1,
  pageSize: 10
})

const search = () => { initTable() }
const reset = () => {
  searchVal.value = ''
  isSuper.value = 2
  initTable()
}
const showDialog = ref(false)
const isEdit = ref(false)

const addRole = () => {
  isEdit.value = false
  showDialog.value = true
}

const closeDialog = () => {
  showDialog.value = false
}

let rowSelected = {}
const edit = row => {
  showDialog.value = true
  isEdit.value = true
  rowSelected = row
}

const del = async id => {
  try {
    const res = await invokeApi('delete', '/deleteRole', {id: id})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    ElMessage.success(res.message || '删除成功')
    initTable()
  } catch (error) {
    
  }
}


const multiSelection = ref([])    // 多选数据
const handleSelectionChange = (val) => {
  multiSelection.value = val
}
const loading = ref(false)
const delInBatch = async () => {
  if (multiSelection.value.length == 0) return ElMessage.warning('请至少选中一条数据！')
  try {
    const ids =  multiSelection.value.map(i => i.id)
    loading.value = true
    const res = await invokeApi('delete', '/deleteRoleInBatch',{ids: ids})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    ElMessage.success(res.message || '删除成功')
    initTable()
  } catch (error) {
    
  } finally {
    loading.value = false
  }
}

const sizeChange = (val) => {
  pagination.pageSize = val
  initTable()
}
const pageChange = (val) => {
  pagination.currentPage = val
  initTable()
}

const tableData = ref([])
const initTable = async () => {
  try {
    const params = {
      name: searchVal.value,
      isSuper: isSuper.value === 2 ? '' : isSuper.value,
      pageNum: pagination.pageNum,
      pageSize: pagination.pageSize
    }
    const res = await invokeApi('get','/getRoleList', {}, params)
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    tableData.value = res.data.rows
    pagination.total = res.data.total
  } catch (error) {
    console.log(error);
    
  }
}



onMounted(() => {
  initTable()
})

</script>

<style lang="scss" scoped>
@import url('./index.scss');
</style>