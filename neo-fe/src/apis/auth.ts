import ajax from '@/libs/axios'

/** 登录 */
export const login = (params: { mobile: string; password: string }) => {
  return ajax.post(`/login`, params)
}

/** 退出登录 */
export const logout = () => {
  return ajax.post(`/logout`)
}

/** 获取用户信息 */
export const fetchInfo = () => {
  return ajax.get(`/me`)
}
