import ajax from '@/libs/axios'

/** 登录 */
export const login = (params: { userName: string; password: string }) => {
  return ajax.post(`/newLogin`, params)
}

/** 退出登录 */
export const logout = () => {
  return ajax.post(`/logout`)
}

/** 更改密码 */
export const changePwd = (params: { oldPwd: string; newPwd: string }) => {
  return ajax.post(`/change/password`, params)
}

/** 获取用户信息 */
export const fetchInfo = () => {
  return ajax.get(`/userInfo`)
}
