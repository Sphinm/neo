import ajax from '@/libs/axios'

/** 获取用户详细信息 */
export const fetchUserInfo = () => {
  return ajax.get(`/get/userInfo`)
}

/** 添加用户详细信息 */
export const insertUserInfo = (params: any) => {
  return ajax.post(`/insert/userInfo`, params)
}

/** 更新用户详细信息 */
export const updateUserInfo = (params: any) => {
  return ajax.post(`/update/userInfo`, params)
}