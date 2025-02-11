<template>
  <div class="select-receiver">
    <div class="left-card">
      <el-tree
        ref="treeRef"
        class="left-tree"
        empty-text="暂无数据"
        :data="deptData"
        :props="defaultProps"
        :default-expand-all="true"
        :expand-on-click-node="false"
        :highlight-current="true"
        :filter-node-method="filterNode"
        @node-click="handleNodeClick"
      >
      </el-tree>
    </div>
    <div class="right-card">
      <div class="search-container">
        <el-input v-model="searchVal" size="small" clearable style="margin-right: 16px;width: 180px;" placeholder="输入用户名" />
        <el-button type="primary" size="small" @click="searchUser">查询</el-button>
      </div>
      <el-table class="table-content mg-t-10 mg-b-10" :data="tableData" current-row-key="username" :height="280" style="width: 100%" 
        @selection-change="handleSelectionChange" @select="rowClick">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="userId" label="用户ID" />
        <el-table-column prop="username" label="用户名" />
      </el-table>
      <el-pagination
        size="small"
          class="table-pagination"
          :total="pagination.total"
          :current-page="pagination.currentPage"
          :page-size="pagination.pageSize"
          :page-sizes="[5, 10, 20, 50, 100]"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="sizeChange"
          @current-change="pageChange"
        />
    </div>

  </div>
</template>

<script setup>
import { invokeApi } from '@/api'
import { onMounted, ref, reactive } from 'vue'

const emits = defineEmits(['handleSelect'])

const defaultProps = {
  children: 'children',
  label: 'name'       
}
const deptData = ref([])
const deptSelected = ref(1)
const initTree = async () => {
  try {
    const param  = {isEnable: 1}
    const res = await invokeApi('get', '/getDepartmentList', {}, param)
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    deptData.value = res.data
    deptSelected.value = res.data[0].id
  } catch (error) {
    console.log(error);
  }
}

const filterNode = (value, data) => {
  if (!value) return true
  return data.name.includes(value)
}
const handleNodeClick = (node) => {
  deptSelected.value = node.id
  initTable()
}

const searchVal = ref('')
const tableData = ref([])
const pagination = reactive({    
  total: 0,
  pageSize: 5,
  currentPage: 1
})
const searchUser = () => {
  initTable()
}
const initTable = async () => {
  try {
    let params = {
      deptId: deptSelected.value,
      name: searchVal.value,
      pageSize: pagination.pageSize,
      pageNum: pagination.currentPage
    }
    const res = await invokeApi('get', '/getUserList', {}, params)
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    tableData.value = res.data.rows
    pagination.total = res.data.total
  } catch (error) {
    console.log(error);
  }
}

const multiSelection = ref([])
const handleSelectionChange = (val) => {
  multiSelection.value = val
  // console.log(val);
  let userIds = val.map(item => item.userId)
  emits('handleSelect', userIds)
}
const rowClick = row => {
  // console.log(row, 'xxx');
  // let userIds = row.map(item => item.userId)
}

const sizeChange = (val) => {
  pagination.pageSize = val
  initTable()
}
const pageChange = (val) => {
  pagination.currentPage = val
  initTable()
}


onMounted(() => {
  initTree()
  initTable()
})

</script>

<style lang="scss" scoped>
.select-receiver {
  width: 100%;
  height: 320px;
  display: flex;
  .left-card {
    width: 28%;
    height: 100%;
    border-right: 1px solid #ebeef5;
    overflow: auto;
    .left-tree {
      height: 100%;
      overflow: auto;
    }
  }
  .right-card {
    width: 72%;
    height: 100%;
    margin-left: 10px;
    display: flex;
    flex-direction: column;
    flex: 1;
    .table-pagination {
      text-align: right;
      justify-content: flex-end;
    }
  }
}
</style>