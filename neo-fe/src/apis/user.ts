import { AuthType } from '@/enums/role'
import ajax from '@/libs/axios'

/** 创建用户 */
export const createNewUser = (params: any, type: AuthType) => {
  return ajax.post(`/create/user?type=${type}`, params)
}

/** 添加用户详细信息 */
export const insertUserInfo = (params: any, type: AuthType) => {
  return ajax.post(`/insert/userInfo?type=${type}`, params)
}

/** 更新用户详细信息 */
export const updateUserInfo = (params: any) => {
  return ajax.post(`/update/userInfo`, params)
}

/** 查询代理商详细信息 */
export const fetchMerchant = (type: AuthType) => {
  return ajax.get(`/fetch/merchant?type=${type}`)
}