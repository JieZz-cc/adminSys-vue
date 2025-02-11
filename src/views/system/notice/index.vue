<template>
  <div class="notice-manage">
    <div class="top card mg-b-10">
      <span>标题：</span>
      <el-input v-model="searchVal" placeholder="输入公告标题" clearable style="width: 240px;margin-right: 16px;"></el-input>
      <span>类型：</span>
      <el-select v-model="noticeType" style="width: 150px;margin-right: 16px;">
        <el-option 
          v-for="item in typeList" 
          :key="item.value" 
          :label="item.label" 
          :value="item.value" />
      </el-select>
      <span>状态：</span>
      <el-select v-model="status" style="width: 150px;margin-right: 16px;">
        <el-option 
          v-for="item in statusList" 
          :key="item.value" 
          :label="item.label" 
          :value="item.value" />
      </el-select>
      <!-- <span>发布范围：</span>
      <el-select v-model="toRange" style="width: 150px;margin-right: 16px;">
        <el-option 
          v-for="item in toList" 
          :key="item.value" 
          :label="item.label" 
          :value="item.value" />
      </el-select> -->
      <span>优先级：</span>
      <el-select v-model="priority" style="width: 150px;margin-right: 16px;">
        <el-option 
          v-for="item in priorityList" 
          :key="item.value" 
          :label="item.label" 
          :value="item.value" />
      </el-select>
      <el-button type="primary" @click="search">查询</el-button>
      <el-button type="default" @click="reset">重置</el-button>
    </div>
    <div class="card table-container">
      <div class="mg-b-10">
        <el-button type="primary" @click="add" >新增</el-button>
        <el-button type="default" @click="delInBatch" >批量删除</el-button>
      </div>
      <el-table v-loading="loading" class="table-content" :data="tableData" @selection-change="handleSelectionChange" stripe style="width: 100%">
        <el-table-column type="selection" width="50" />
        <el-table-column prop="title" label="公告标题" show-overflow-tooltip width="250" />
        <el-table-column prop="parsedPublisher" label="发布人" show-overflow-tooltip />
        <el-table-column prop="noticeType" label="公告类型">
          <template #default="{ row }">
            <el-tag :type="row.noticeType ? 'primary' : 'success'">{{ typeDict(row.noticeType) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="公告状态">
          <template #default="{ row }">
            <el-tag :type="row.status === 0 ? 'primary' : (row.status === 1 ? 'success' : 'danger')">{{ statusDict(row.status) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="toRange" label="发布范围">
          <template #default="{ row }">
            <el-tag :type="row.toRange === 0 ? 'primary' : 'success'">{{ rangeDict(row.toRange) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="priority" label="优先级">
          <template #default="{ row }">
            <el-tag :type="row.priority === 0 ? 'success' : (row.priority === 1 ? 'primary' : (row.priority === 2 ? 'warning' : 'danger'))">{{ priorityDict(row.priority) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column fixed="right" prop="operation" label="操作" width="260" align="center">
          <template #default="{ row }">
              <el-button link type="primary" size="small" :disabled="row.status === 1" @click="edit(row)">
                <i class="btn-icon mr4 iconfont icon-xiugai"></i> <span>编辑</span>
              </el-button>
              <el-button v-if="row.status !== 1" link type="primary" size="small" @click="publish(row)">
                <el-icon><Promotion /></el-icon>
                 <!-- <i class="btn-icon mr4 iconfont icon-fabu-"></i> -->
                <span>发布</span>
              </el-button>
              <el-button v-else link type="primary" size="small" @click="quash(row)">
                <el-icon><RefreshLeft /></el-icon>
                <!-- <i class="btn-icon mr4 iconfont icon-chexiao"></i>  -->
                <span>撤销</span>
              </el-button>
              <el-popconfirm
                @confirm="del(row.id)"
                confirm-button-text="是"
                cancel-button-text="否"
                title="确认删除?"
              >
                <template #reference>
                  <el-button link type="primary" size="small" :disabled="row.status === 1">
                    <i class="btn-icon mr4 iconfont icon-shanchu del"></i><span class="del">删除</span>
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
    <notice-dialog v-if="showDialog" :showDialog="showDialog" :isEdit="isEdit" :row="rowSelected" 
                 @refresh="initTable" @closeDialog="closeDialog"/>
  </div>
</template>

<script setup>
import {Promotion, RefreshLeft}from '@element-plus/icons-vue'
import { onMounted, ref, reactive } from 'vue'
import {invokeApi} from '@/api'
import noticeDialog from './components/noticeDialog.vue'

const searchVal = ref('')
const noticeType = ref(2)
const status=ref(3)
const priority = ref(4)
const typeList = [
  {value: 2, label: '全部'},
  {value: 0, label: '通知'},
  {value: 1, label: '公告'}
]
const statusList = [
  {value: 3, label: '全部'},
  {value: 0, label: '待发布'},
  {value: 1, label: '已发布'},
  {value: 2, label: '已撤销'}
]
const priorityList = [
  {value: 4, label: '全部'},
  {value: 3, label: '紧急'},
  {value: 2, label: '高'},
  {value: 1, label: '中'},
  {value: 0, label: '低'}
]

const pagination = reactive({
  total: 0,
  currentPage: 1,
  pageSize: 10
})

const search = () => { initTable() }
const reset = () => {
  searchVal.value = ''
  noticeType.value = 2
  status.value = 3
  priority.value = 4
  initTable()
}
const showDialog = ref(false)
const isEdit = ref(false)

const add = () => { 
  isEdit.value = false
  showDialog.value = true 
}

const closeDialog = () => { showDialog.value = false }

let rowSelected = {}
const edit = async row => {
  
  isEdit.value = true
  
  // console.log(rowSelected);
  let userIds = await getUsersByNoticeId(row.id)
  
  rowSelected = row
  rowSelected.receiverList = userIds
  showDialog.value = true
  
}
const getUsersByNoticeId = async (id) => {
  try {
    const res = await invokeApi('post', '/getUsersByNoticeId', {id: id})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    return res.data
  } catch (error) {
    console.log(error);
  }
}
const del = async id => {
  try {
    const res = await invokeApi('delete', '/deleteNoticeById', {id: id})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    ElMessage.success(res.message || '删除成功')
    initTable()
  } catch (error) {
    
  }
}
const publish = async (row) => {
  try {
    const res = await invokeApi('put', '/publish', {id: row.id})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    row.status = 1
    ElNotification({
      title: '发布公告',
      message: '已成功发布该通知公告！',
      type: 'success',
    })
  } catch (error) {
    
  }
}
const quash = async (row) => {
  try {
    const res = await invokeApi('put', '/quash', {id: row.id})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    row.status = 2
    ElNotification({
      title: '撤销公告',
      message: '已成功撤销该通知公告！',
      type: 'success',
    })
  } catch (error) {
    
  }
}
const typeDict = (val) => {
  switch (val) {
    case 0:
      return '通知'
    case 1:
      return '公告'
    default:
      return '全部'
  }
}
const statusDict = (val) => {
  switch (val) {
    case 0:
      return '待发布'
    case 1:
      return '已发布'
    case 2:
      return '已撤销'
    default:
      return '全部'
  }
}
const rangeDict = (val) => {
  switch (val) {
    case 0:
      return '全部用户'
    case 1:
      return '指定用户'
    default:
      return '全部用户'
  }
}
const priorityDict = (val) => {
  switch (val) {
    case 0:
      return '低'
    case 1:
      return '中'
    case 2:
      return '高'
    case 3:
      return '紧急'
    default:
      return '全部'
  }
}
const multiSelection = ref([])    // 多选数据
const handleSelectionChange = val => multiSelection.value = val
const delInBatch = async () => {
  if (multiSelection.value.length == 0) return ElMessage.warning('请至少选中一条数据！')
  ElMessageBox.confirm('此操作将永久删除选中数据, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    const ids = multiSelection.value.map(item => item.id)
    const res = await invokeApi('delete', '/deleteNoticeInBatch', {ids: ids})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    ElMessage.success(res.message || '删除成功')
    initTable()
  }).catch(() => {
    ElMessage.info('已取消删除')
  })
}

const sizeChange = (val) => {
  pagination.pageSize = val
  initTable()
}
const pageChange = (val) => {
  pagination.currentPage = val
  initTable()
}

const loading = ref(false)
const tableData = ref([])
const initTable = async () => {
  try {
    const params = {
      pageNum: pagination.currentPage,
      pageSize: pagination.pageSize,
      title: searchVal.value,
      noticeType: noticeType.value === 2 ? null : noticeType.value,
      status: status.value === 3 ? null : status.value,
      // toRange: toRange.value === 0 ? null : toRange.value,
      priority: priority.value === 4 ? null : priority.value
    }
    loading.value = true
    const res = await invokeApi('post','/getNotices', params)
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    tableData.value = res.data.rows
    pagination.total = res.data.total
  } catch (error) {
    console.log(error);
    
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  initTable()
})

</script>

<style lang="scss" scoped>
@import url('./index.scss');
</style>