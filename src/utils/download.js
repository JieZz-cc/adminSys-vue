import axios from "axios"
import { userStore } from '@/stores/userStore'
import { ElMessage } from "element-plus"

const BASE_URL = '/api'
const service = axios.create({
  baseURL: BASE_URL,
  timeout: 3000
})
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
    // if (response.data.code === 200) return response.data
    // ElMessage({
    //   message: response.data.message ? response.data.message : '服务异常',
    //   type: 'error',
    // })
    // return Promise.reject(response.data)
    return response.data
  },
  err => {
    return Promise.reject(err)
  }
)

// a链接方式下载
export function downLoadByLink(url = '', fileName = '') {
  const baserUrl = 'http://localhost:8080'
  const fileUrl = baserUrl + url
  const link = document.createElement('a')
  link.style.display = 'none'
  link.href = fileUrl
  // link.target = '_blank'
  link.setAttribute('download', fileName)
  link.click()
  link.remove()
}


export function exportApi(api, data,  params, fileName, method = 'POST', suffix = 'xlsx') {
  // const useUserStore = userStore()
  // let headers = { 'Content-Type': 'application/json' }
  // headers.Authorization = useUserStore.getToken

  return service({
    method: method,
    url:  api,
    data: data,     // 参数在请求体中
    params: params,  // 参数拼接在url后面
    // headers: headers,
    responseType: 'blob', // 返回格式
    withCredentials: true,
    // 对config中的data进行转换处理，如加密
    // transformRequest: [
    //   function (data) {
    //     if (headers['Content-Type'] !== 'application/x-www-form-urlencoded') {
    //       return JSON.stringify(data)
    //     } else {
    //       return Qs.stringify(data)
    //     }
    //   }
    // ],
  }).then(res => {
    const data = res.data
    const headers = res.headers
    console.log(headers);
    
    let reader = new FileReader() // 创建读取文件对象
    reader.onload = function () {
      try {
        let resData = JSON.parse(reader.result)
        if (resData && resData instanceof Object) {
          ElMessage.error('下载错误')
        } else {
          throw new Error(data)
        }
      } catch (error) {
        if (suffix === 'pdf') {
          const blob = new Blob([data], { type: 'application/pdf;charset=UTF-8' })
          const url = window.URL.createObjectURL(blob)
          window.open(url)
        } else {
          const blob = new Blob([data], { type: 'application/octet-stream;charset=UTF-8' })
          // const outFileName = '111'
          FileSaver.saveAs(blob, fileName)
        }
      }
    }
    reader.readAsText(data)
  }).catch(e => {
    ElMessage.error('服务异常')
  })

}

// get 方式
export function downLoadApi(api, params, callback, failCallback) {
  const useUserStore = userStore()
  let headers = { 'Content-Type': 'application/json' }
  headers.Authorization = useUserStore.getToken

  return service({
    method: 'GET',
    url: 'http://localhost:8080/api' + api,
    params: params,
    headers: headers,
    responseType: 'blob',
    withCredentials: true
  }).then(res => {
    const data = res.data
    const headers = res.headers

    let reader = new FileReader()
    reader.onload = function () {
      try {
        let resData = JSON.parse(reader.result)
        if (resData && resData instanceof Object) {
          failCallback()
        } else {
          throw new Error(data)
        }
      } catch (error) {
        callback(data, headers)
      }
    }
    reader.readAsText(data)
  }).catch(e => {
    ElMessage.error('服务异常')
  })
}




// 文件上传 
export function uploadApi(api, params, method = 'POST') {
  const useUserStore = userStore()
  let headers = { 'Content-Type': 'multipart/form-data' }
  headers.Authorization = useUserStore.getToken

  return service({
    method: method,
    url: 'http://localhost:8080/api' + api,
    data: params,
    headers: headers,
    withCredentials: true
  }).then(response => {
    return response
  }).catch(error => {
    ElMessage.error('服务异常')
  })
}



