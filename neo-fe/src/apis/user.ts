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

/** 查询代理商详细信息 */
export const updateMerchant = (params: any, type: AuthType) => {
  return ajax.post(`/update/merchant?type=${type}`, params)
}

/** 删除代理商详细信息 */
export const deleteMerchant = (merchantId: string) => {
  return ajax.post(`/delete/merchant?merchantId=${merchantId}`)
}

/** 查询客户公司详细信息 */
export const deleteCompany = (merchantId: string) => {
  return ajax.post(`/delete/company?merchantId=${merchantId}`)
}

/** 查询代理商金额信息 */
export const fetchAllData = () => {
  return ajax.get(`/fetch/allData`)
}

/** 查询代理商底下公司的金额信息 */
export const fetchCompanyByMerchantId = (merchantId: string) => {
  return ajax.get(`/fetch/company-list?merchantId=${merchantId}`)
}

/** 查询所有的公司 */
export const fetchCompany = () => {
  return ajax.get(`/fetch/company`)
}

/** 管理员查询员工 */
export const fetchAllEmployee = (params: {pageNum: number, pageSize: number}) => {
  return ajax.get(`/fetch/employee?pageNum=${params.pageNum}&pageSize=${params.pageSize}`,)
}

/** 删除代理商详细信息 */
export const deleteEmployee = (employeeId: string) => {
  return ajax.post(`/delete/employee?employeeId=${employeeId}`)
}