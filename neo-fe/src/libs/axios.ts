import { notification } from 'antd'
import axios, { AxiosError, AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios'

const instance: AxiosInstance = axios.create({
  baseURL: '/api',
  timeout: 60000,
})

export const handleError = (e: AxiosError) => {
  // notification.error({
  //   message: e.message,
  //   description: '',
  // })
  console.error(e)
}

const requestInterceptor = (config: AxiosRequestConfig) => {
  if (config.url === '/newLogin') return config
  const headers = {
    'Authorization': `Bearer ` + localStorage.getItem('token'),
  }
  config.headers = { ...config.headers, ...headers }
  return config
}

const responseInterceptor = (response: AxiosResponse) => {
  // 拦截 200 的错误
  const { data } = response

  if (data?.code !== 'SUCCESS') {
    notification.error({
      message: '系统错误，请联系管理员',
    })
  } else {
    return response.data
  }
}

const responseErrorInterceptor = (error: AxiosError) => {
  // 拦截非200的错误
  if (error.response) {
    const { status, data } = error.response
    const statusError = () => {
      return notification.error({
        message: `CODE: ${data.code ?? data.status}`,
        description: data?.message ? data.message : 'Server Error',
      })
    }
    switch (status) {
      case 401:
        if (process.env.NODE_ENV === 'production') window.location.href = data.data
        statusError()
        break
      case 403:
        // window.location.href = '/layout'
        statusError()
        break
      default:
        throw new Error(data.error ? data.error : 'Server Error')
    }
  } else {
    throw new Error('Request Error')
  }
}

instance.interceptors.request.use(requestInterceptor)
instance.interceptors.response.use(responseInterceptor, responseErrorInterceptor)

export default instance
