
import { ElMessage } from "element-plus"
import {userStore} from '@/stores/userStore'

import axios from "axios"
import router from "@/router"


const BASE_URL = '/api'
const service = axios.create({
  baseURL: BASE_URL,
  timeout: 3000
})

// 请求
service.interceptors.request.use(
  config => {
    const useUserStore = userStore()
    config.headers["Content-Type"] = 'application/json'
    if (useUserStore.getToken) config.headers.Authorization = useUserStore.getToken

    return config
  },
  err => {
    ElMessage.err('请求异常！')
    return Promise.reject(err)
  }
)

//响应
service.interceptors.response.use(
  response => {
    if (response.data.code === 200) return response.data
    ElMessage({
      message: response.data.message ? response.data.message : '服务异常',
      type: 'error',
    })
    return Promise.reject(response.data)
  },
  err => {
    if (err.response.status === 401) {
      ElMessage.error('请先登录！')
      router.push('./login')
    } else {
      ElMessage.error('服务异常！')
    }
    return Promise.reject(err)
  }
)

export default service;