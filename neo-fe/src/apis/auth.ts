import ajax from '@/libs/axios'

/** 登录 */
export const login = (params: { mobile: string; password: string; verifyCode: string }) => {
  return ajax.post(`/login`, params)
}

/** 退出登录 */
export const logout = () => {
  return ajax.get(`/logout`)
}
