<template>
  <el-dialog v-model="props.showDialog" 
    :title="!props.isEdit ? '新增' : '编辑'"
    width="780"
    :before-close="handleClose"
    :close-on-click-modal="false"
    :draggable="true"
  >
    <el-form ref="noticeFormRef" :model="noticeForm" class="mg-t-15" label-width="100px">
      <el-form-item label="公告标题" prop="title" required>
        <el-input v-model="noticeForm.title" placeholder="请输入标题" clearable show-word-limit maxlength="100"></el-input>
      </el-form-item>
      <el-form-item label="公告类型" prop="noticeType">
        <el-radio-group v-model="noticeForm.noticeType">
          <el-radio :value="0" label="通知" />
          <el-radio :value="1" label="公告" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="公告级别" prop="priority">
        <el-radio-group v-model="noticeForm.priority">
          <el-radio-button :value="0" label="低" />
          <el-radio-button :value="1" label="中" />
          <el-radio-button :value="2" label="高" />
          <el-radio-button :value="3" label="紧急" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="公告范围" prop="toRange">
        <el-radio-group v-model="noticeForm.toRange">
          <el-radio :value="0" label="全部用户" />
          <el-radio :value="1" label="指定用户" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="指定用户" prop="toPerson" v-if="noticeForm.toRange === 1" required>
        <el-popover
          placement="bottom-start"
          width="650"
          trigger="click"
        >
          <template #reference>
            <!-- <div><el-icon><Search /></el-icon></div> -->
            <el-input-tag v-model="noticeForm.toPerson" placeholder="请选择" clearable >
              <template #suffix>
                <el-icon><Search /></el-icon>
              </template>
            </el-input-tag>
          </template>
          <selectReceiver @handleSelect="handleSelect"/>
          
        </el-popover>
      </el-form-item>
      <el-form-item label="内容" prop="content" required>
        <el-input v-model="noticeForm.content" type="textarea" :rows="6" placeholder="请输入内容" clearable show-word-limit maxlength="500" />
      </el-form-item>
      <div class="footer">
        <el-button @click="handleClose">取消</el-button>
        <el-button type="primary" :loading="loading" @click="confirm">提交</el-button>
      </div>
    </el-form>
  </el-dialog>
</template>
<script setup>
import {Search}from '@element-plus/icons-vue'
import { onMounted, reactive, ref } from 'vue'
import {invokeApi} from '@/api'
import { ElMessage } from 'element-plus'
import {userStore} from '@/stores/userStore'
import selectReceiver from './selectReceiver.vue'

const userInfo = userStore().getUserInfo
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

const noticeForm = reactive({
  id: null,
  title: '',
  priority: 0,
  noticeType: 0,
  toRange: 0,
  toPerson: [],
  content: ''
})

const noticeFormRef = ref(null)
const handleClose = () => { 
  noticeFormRef.value?.resetFields()
  emits('closeDialog') 
}

const loading = ref(false)
const confirm = () => {
  noticeFormRef.value.validate(async valid => {
    if (valid) {
      try {
        let url, method, params
        if (props.isEdit) {
          url = 'editNoticeById'
          method = 'put'
          params = {
            id: noticeForm.id,
            title: noticeForm.title,
            priority: noticeForm.priority,
            noticeType: noticeForm.noticeType,
            toRange: noticeForm.toRange,
            content: noticeForm.content,
            receiverList: noticeForm.toRange === 0 ? null : noticeForm.toPerson
          }
        } else {
          url = 'addNewNotice'
          method = 'post'
          params = {
            publisher: userInfo.userId,
            title: noticeForm.title,
            priority: noticeForm.priority,
            noticeType: noticeForm.noticeType,
            toRange: noticeForm.toRange,
            status: 0,
            content: noticeForm.content,
            receiverList: noticeForm.toRange === 0 ? null : noticeForm.toPerson
          }
        }
        loading.value = true
        const res = await invokeApi(method, url, params)
        if (res.code !== 200) return ElMessage.error(res.message)
        ElMessage.success(res.message)
        emits('refresh')
        handleClose()
      } catch (error) {
        // console.error(error)
      } finally {
        loading.value = false
      }
    }
  })
}

const handleEditForm = async () => {
  noticeForm.id = props.row.id
  noticeForm.title = props.row.title
  noticeForm.priority = props.row.priority
  noticeForm.noticeType = props.row.noticeType
  noticeForm.toRange = props.row.toRange
  noticeForm.content = props.row.content
  noticeForm.toPerson = props.row.receiverList
}

const handleSelect = (data) => {
  noticeForm.toPerson = data
}

onMounted(() => {
  if (props.isEdit) {
    handleEditForm()
  }
})

</script>
<style lang="scss" scoped>
.footer {
  text-align: right;
  padding: 5px 0;
}
</style>