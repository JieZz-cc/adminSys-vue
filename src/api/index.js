import service from "./api"

export function invokeApi(method = 'post', url = '', data = {}, params = {} ) {
  return service({
    method: method,
    url: url,
    data: data,     // 参数在请求体中
    params: params  // 参数拼接在url后面
  })
}