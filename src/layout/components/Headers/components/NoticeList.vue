<template>
  <div class="notice-list">
     <el-dropdown placement="bottom" trigger="click">
      <el-badge :value="total" :max="9" :offset="[2, -1]">
        <i class="icon iconfont icon-lingdang toolbar-icon"></i>
      </el-badge>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item v-for="item in noticeList" :key="item.id" @click="handleClick(item)">
            <div class="item">
              <div class="top">
                <el-icon>
                  <ChatDotRound v-if="item.noticeType === 0" />
                  <Bell v-else />
                </el-icon>
                <span style="font-size: 12px;">{{ item.noticeType === 0 ? '公告' : '通知' }}</span>
                <span class="time" >{{ item.publishTime || '-' }}</span>
                <span class="publisher" :title="item.parsedPublisher">{{ item.parsedPublisher || '-' }}</span>
              </div>
              <div class="botm">
                <el-tag :type="tagDict(item.priority)" size="small">{{ priorityDict(item.priority) }}</el-tag>
                <i class="is-read-dot" :class="{'un-read-dot': !item.isRead, 'readed-dot': item.isRead}" >·</i>
                <span class="title" :class="{'title-readed': item.isRead}" :title="item.title">{{ item.title }}</span>
              </div>
            </div>
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
     </el-dropdown>
    
  </div>
</template>

<script setup>
import {ChatDotRound, Bell} from '@element-plus/icons-vue'
import {invokeApi} from '@/api'
import {userStore} from '@/stores/userStore' 
import { ref, onMounted, computed } from 'vue'
// setNoticeRead  #f56c6c

const userInfo = userStore().getUserInfo

const tagDict = computed(() => (val) => {
  switch (val) {
    case 0:
      return 'success'
    case 1:
      return 'primary'
    case 2:
      return 'warning'
    case 3:
      return 'danger'
    default:
      return 'success'
  }
})

const priorityDict = computed(() => (val) => {
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
})

const handleClick = async (item) => {
  // console.log(item)
  ElMessageBox.alert(item.content, item.title, { confirmButtonText: '知道了'})
  if (!item.isRead) {
    try {
      const res = await invokeApi('post', '/setNoticeRead', {noticeId: item.id, userId: userInfo.userId})
      if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
      item.isRead = true
      total.value--
    } catch (error) {
      console.log(error);
    }
  }
}

const total = ref(0)
const noticeList = ref([])
const initNotice = async () => {
  try {
    const res = await invokeApi('post', '/getNoticeListByUserId', {userId: userInfo.userId})
    if (res.code !== 200) return ElMessage.error(res.message || '服务异常')
    noticeList.value = res.data
    res.data.forEach(item => {
      if (!item.isRead) total.value++
    })
    if (total.value > 0) {
      ElNotification({
      title: '提示',
      message: '您最近收到' + total.value +'条通知，请前往查看！',
      type: 'warning',
    })
    }
  } catch (error) {
    console.log(error);
  }
}
initNotice()

const eventSource  = new EventSource('http://localhost:8080/sse?clientId=123')
eventSource.onmessage = function(event) {
    console.log('Received message:', event.data);
}
eventSource.onerror = function(event) {
    console.error('EventSource failed:', event);
    eventSource.close()
}



</script>

<style lang="scss" scoped>
.notice-list {

  
  
}
  :deep(.el-dropdown-menu__item) {
    display: flex;
    align-items: center;
    .item {
      // border-bottom: 1px solid #ddd;
      padding-bottom: 8px;
      width: 100%;
      .top {
        display: flex;
        align-items: center;
        margin-bottom: 5px;
        .time {
          font-size: 12px;
          color: #999;
          margin-left: 10px;
          display: inline-block;
          width: 118px;
        }
        .publisher {
          font-size: 12px;
          color: #999;
          margin-left: 10px;
          display: inline-block;
          width: 60px;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
        }
      }
      .botm {
        display: flex;
        align-items: center;
        .is-read-dot {
          // color: #f56c6c;
          font-size: 30px;
          margin-left: 5px;
          // display: inline-block;
        }
        .un-read-dot {
          color: #f56c6c;
        }
        .readed-dot {
          color: #ddd;
        }
        .title {
          font-size: 14px;
          color: #333;
          margin-left: 5px;
          width: 120px;
          display: inline-block;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
        }
        .title-readed {
          color: #999;
        }
        
      }
    }
  }

</style>