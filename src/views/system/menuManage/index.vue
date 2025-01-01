<template>
  <div class="menu-manage">
    <div class="top card mg-b-10">
      <span>菜单：</span>
      <el-input v-model="searchVal" placeholder="请输入" clearable style="width: 240px;margin-right: 16px;"></el-input>
      <span>启用：</span>
      <el-select v-model="isEnable" style="width: 150px;margin-right: 16px;">
        <el-option 
          v-for="item in enableList" 
          :key="item.value" 
          :label="item.label" 
          :value="item.value" />
      </el-select>
      <span>缓存：</span>
      <el-select v-model="isKeepAlive" style="width: 150px;margin-right: 16px;">
        <el-option 
          v-for="item in aliveList" 
          :key="item.value" 
          :label="item.label" 
          :value="item.value" />
      </el-select>
      <span>链接：</span>
      <el-select v-model="isLink" style="width: 150px;margin-right: 16px;">
        <el-option 
          v-for="item in linkList" 
          :key="item.value" 
          :label="item.label" 
          :value="item.value" />
      </el-select>
      <el-button type="primary" @click="search">检索</el-button>
      <el-button type="default" @click="reset">重置</el-button>
    </div>
    <div class="card table-container">
      <div class="mg-b-10">
        <el-button type="primary" @click="addMenu" style="width: 50px;">新增</el-button>
      </div>
      <el-table class="table-content" :data="tableData" stripe row-key="id" style="width: 100%">
        <el-table-column prop="title" label="菜单名称" show-overflow-tooltip width="150" />
        <el-table-column prop="sort" label="排序" width="80" />
        <el-table-column prop="path" label="菜单路径" show-overflow-tooltip width="300" />
        <el-table-column prop="isEnable" label="是否启用">
          <template #default="{ row }">
            <el-tag :type="row.isEnable ? 'success' : 'danger'">{{ row.isEnable ? '是' : '否' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="isAffix" label="是否固定">
          <template #default="{ row }">
            <el-tag :type="row.isAffix ? 'success' : 'danger'">{{ row.isAffix ? '是' : '否' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="isKeepAlive" label="是否缓存">
          <template #default="{ row }">
            <el-tag :type="row.isKeepAlive ? 'success' : 'danger'">{{ row.isKeepAlive ? '是' : '否' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="isLink" label="是否链接" >
          <template #default="{ row }">
            <el-tag :type="row.isLink ? 'success' : 'danger'">{{ row.isLink ? '是' : '否' }}</el-tag>
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
    <menu-dialog v-if="showDialog" :showDialog="showDialog" :isEdit="isEdit" :row="rowSelected" 
                 @refresh="initTable" @closeDialog="closeDialog"/>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import {invokeApi} from '@/api'
import menuDialog from './components/menuDialog.vue';
import {userMenuAuth} from '@/stores/authMenu'
const menuAth = userMenuAuth()

const searchVal = ref('')
const isEnable = ref(2)
const isLink = ref(2)
const isKeepAlive=ref(2)
const enableList = [
  {value: 2, label: '全部'},
  {value: 1, label: '是'},
  {value: 0, label: '否'}
]
const linkList = [
  {value: 2, label: '全部'},
  {value: 1, label: '是'},
  {value: 0, label: '否'}
]
const aliveList = [
  {value: 2, label: '全部'},
  {value: 1, label: '是'},
  {value: 0, label: '否'}
]

const search = () => { initTable() }
const reset = () => {
  searchVal.value = ''
  isEnable.value = 2
  isLink.value = 2
  isKeepAlive.value = 2
  initTable()
}
const showDialog = ref(false)
const isEdit = ref(false)

const addMenu = () => { 
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
    const res = await invokeApi('delete', '/deleteMenuById', {id: id})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    ElMessage.success(res.message || '删除成功')
    initTable()
    menuAth.getAuthMenuList()  // 更新菜单
  } catch (error) {
    
  }
}

const tableData = ref([])
const initTable = async () => {
  try {
    const params = {
      name: searchVal.value,
      isEnable: isEnable.value === 2 ? null : isEnable.value,
      isKeepAlive: isKeepAlive.value === 2 ? null : isKeepAlive.value,
      isLink: isLink.value === 2 ? null : isLink.value
    }
    const res = await invokeApi('get','/getAllMenu', {}, params)
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