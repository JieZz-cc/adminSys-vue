<template>
  <div class="account-manage">
    <div class="card left-card">
      <el-tree
        ref="treeRef"
        class="left-tree"
        empty-text="暂无数据"
        :data="treeData"
        :props="defaultProps"
        :default-expand-all="true"
        :expand-on-click-node="false"
        :highlight-current="true"
        :filter-node-method="filterNode"
        @node-click="handleNodeClick"
      >
      </el-tree>
    </div>
    <div class="card right-card">
      <div class="search-container">
        <span>用户：</span>
        <el-input v-model="searchVal" clearable style="width: 240px;margin-right: 16px;" placeholder="请输入用户名" />
        <span>管理员：</span>
        <el-select
          v-model="typeSelected"
          style="width: 240px;margin-right: 16px;"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
        <el-button type="primary" @click="searchUser">
          <i class="btn-icon mr4 iconfont icon-sousuo" /><span>检索</span>
        </el-button>
        <el-button type="default" @click="reset">
          <i class="btn-icon mr4 iconfont icon-重置" /><span>重置</span>
        </el-button>
      </div>
      <div style="display: flex;">
        <el-button type="primary" style="width: 90px;" @click="addNew">
        新增用户
        </el-button>
        <el-button type="default" style="width: 90px;" :loading="delloading" @click="deleteInBatch">
          批量删除
        </el-button>
        <el-button type="default" style="width: 90px;"  @click="exportTable">
          导出
        </el-button>
        <el-upload
          v-model:file-list="fileList"
          class="upload-demo"
          action="http://localhost:5173/api/importUsers"
          :method="'post'"
          :multiple="false"
          :headers="headers"
          :limit="1"
          :show-file-list="false"
          :on-success="onSuccess"
          :on-error="onError"
        >
          <el-button type="default" style="width: 90px;"  @click="importUsers">
            导入
          </el-button>

        </el-upload>
      </div>
      <!-- <div class="table-content"> -->
        <el-table class="table-content" :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" />
          <el-table-column prop="userId" label="用户" width="150" />
          <el-table-column prop="username" label="昵称" width="150" />
          <el-table-column prop="deptName" label="部门" width="150" />
          <el-table-column prop="roleName" label="角色" width="130" />
          <el-table-column prop="isSuper" label="超级管理员" width="120">
            <template #default="{ row }">
              <el-tag :type="row.isSuper === 1 ? 'success': 'danger' ">
                {{ row.isSuper === 1 ? '是' : '否' }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="email" label="邮箱" width="180" />
          <el-table-column prop="phone" label="电话" width="120" />
          <el-table-column prop="createTime" label="创建时间" width="180" />
          <el-table-column prop="remark" label="备注" width="180" />
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
          class="table-pagination"
          :total="pagination.total"
          :current-page="pagination.currentPage"
          :page-size="pagination.pageSize"
          :page-sizes="[5, 10, 20, 50, 100]"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="sizeChange"
          @current-change="pageChange"
        />
      <!-- </div> -->
    </div>
    <user-dialog v-if="dialogVisible" :isEdit="dialogType" :row="rowClicked" :dialogVisible="dialogVisible" :deptData="treeData" @refresh="initTable" @closeDialog="closeDialog"></user-dialog>
    
    <!-- 导出弹窗 -->
    <el-dialog v-model="exportBox" title="导出" width="420"
      
      :close-on-click-modal="false"
      :draggable="true"
    >
      <div class="flex-center">
        <el-button type="primary" @click="downLoad('0')"> 导出当前</el-button>
        <el-button type="default" @click="downLoad('1')"> 导出全部</el-button>
      </div>
      <p style="font-size: 12px;color: #999;">1.导出文件格式默认为xlsx;</p>
      <p style="font-size: 12px;color: #999;">2.导出当前以当前检索条件为准;</p>
      <p style="font-size: 12px;color: #999;">3.导出文件位置为浏览器下载路径;</p>
    </el-dialog>
  </div>
</template>

<script setup>
import {  onMounted, ref, reactive, watch } from 'vue'
import { invokeApi } from '@/api/index.js'
import { ElMessage } from 'element-plus'
import userDialog from './components/userDialog.vue'
import { downLoadByLink, exportApi } from '@/utils/download'
import {userStore} from '@/stores/userStore'

const useUserStore = userStore()
const treeRef = ref(null)
const treeData = ref([])
const tableData = ref([])
const defaultProps = {
  children: 'children',
  label: 'name'       // 接口返回的name字段
}
const searchVal = ref('')
const deptSelected = ref(1)
const multiSelection = ref([])    // 多选数据
const pagination = reactive({     // 分页数据
  total: 0,
  pageSize: 10,
  currentPage: 1
})

const typeSelected = ref(3)
const options = [
  {label:'全部', value: 3},
  {label:'是', value: 1},
  {label:'否', value: 0}
]

const filterNode = (value, data) => {
  if (!value) return true
  return data.name.includes(value)
}

// 查询部门列表
const initTree = async () => {
  try {
    const param  = {isEnable: 1}
    const res = await invokeApi('get', '/getDepartmentList', {}, param)
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    treeData.value = res.data
    deptSelected.value = res.data[0].id
  } catch (error) {
    console.log(error);
  }
}

const initTable = async () => {
  try {
    let params = {
      deptId: deptSelected.value,
      name: searchVal.value,
      isSuper: typeSelected.value === 3 ? null : typeSelected.value,
      pageSize: pagination.pageSize,
      pageNum: pagination.currentPage
    }
    tableData.value = []
    const res = await invokeApi('get', '/getUserList', {}, params)
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    tableData.value = res.data.rows
    pagination.total = res.data.total
  } catch (error) {
    console.log(error);
  }
}

const searchUser = () => {
  initTable()
}

const reset = () => {
  searchVal.value = ''
  typeSelected.value = 3
  initTable()
}

const handleNodeClick = (node) => {
  deptSelected.value = node.id
  initTable()
}

const dialogVisible = ref(false)
const dialogType = ref(false)
const addNew = () => {
  dialogType.value = false
  dialogVisible.value = true
}
const closeDialog = () => {
  dialogVisible.value = false
}

const handleSelectionChange = (val) => {
  multiSelection.value = val
}

let rowClicked = {}
const edit = (row) => {
  rowClicked = row
  dialogType.value = true
  dialogVisible.value = true
}

const del = async id => {
  try {
    const res = await invokeApi('delete', '/deleteUser', {id: id})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    ElMessage.success(res.message || '删除成功')
    initTable()
  } catch (error) {
    
  }
}

const delloading = ref(false)
const deleteInBatch = async () => {
  if (multiSelection.value.length == 0) return ElMessage.warning('请至少选中一条数据！')
  try {
    const ids =  multiSelection.value.map(i => i.id)
    delloading.value = true
    const res = await invokeApi('delete', '/deleteUserInBatch',{ids: ids})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    delloading.value = false
    ElMessage.success(res.message || '删除成功')
    initTable()
  } catch (error) {
    console.log(error);
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

const exportBox = ref(false)
const exportTable = () => {
  exportBox.value = true
}
const downLoad = async (val) => {
  try {
    let params = {}
    if (val === '0') {
      params = {
        deptId: 1
      }
    } else {
      params = {
        deptId: deptSelected.value,
        name: searchVal.value,
        isSuper: typeSelected.value === 3 ? null : typeSelected.value,
        pageSize: pagination.pageSize,
        pageNum: pagination.currentPage
      }
    }
    let param = ''
    const paramArr = Object.keys(params)
    paramArr.forEach((e, index) => {
      if (params[e]) {
        param += e + '=' + params[e] + (index < paramArr.length - 1 ? '&' : '')
      }
    })
    // Object.values(params).forEach(e => console.log(e))
    downLoadByLink('/exportUsers?' + param, '用户表')
    exportBox.value = false
    // const res = await exportApi('/exportUsers', {}, {deptId: 1}, '用户表','get')
    
  } catch (error) {
    console.log(error);
  }
}

const fileList = ref([])
const headers = {
  // 'Content-Type': 'multipart/form-data',  // 文件上传类型
  'Authorization': useUserStore.getToken
}
const importUsers = async () => {
  // const res = await invokeApi('post', '/importUsers', {})
  // console.log(res);
  
}
const onError = () => {
  ElMessage.error('文件上传失败，请检查')
}

const onSuccess = () => {
  ElMessage.success('文件上传成功')
  initTable()
}


onMounted(() => {
  initTree()
  initTable()
})

</script>

<style lang="scss" scoped>
@import url('./index.scss');
.upload-demo {
  margin-left: 10px;
}
</style>