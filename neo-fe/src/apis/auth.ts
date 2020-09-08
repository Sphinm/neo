import ajax from '../libs/axios'

/** 登录 */
export const login = (params: { mobile: string; password: string }) => {
  return ajax.post(`/login`, params)
}

/** 退出登录 */
export const logout = () => {
  return ajax.get(`/logout`)
}

/** 获取所有用户 */
export const findAllUser = () => {
  return ajax.get(`/findAll`)
}
