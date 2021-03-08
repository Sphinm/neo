import ajax from '@/libs/axios'

// /** 获取公司发放记录 */
// export const fetchCompanyIssues = () => {
//   return ajax.get(`/company/issues`)
// }

// /** 获取公司剩余金额 */
// export const fetchCompanyBalance = () => {
//   return ajax.get(`/company/balance`)
// }

/** 获取公司充值信息 */
export const fetchCompanyChargeInfo = () => {
  return ajax.get(`/company/charge-info`)
}

/** 获取公司充值记录 */
export const fetchCompanyChargeList = () => {
  return ajax.get(`/company/charge-list`)
}

/** 公司充值接口 */
export const companyCharge = () => {
  return ajax.post(`/company/charge/post`)
}