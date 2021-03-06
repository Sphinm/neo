import ajax from '@/libs/axios'

/** 获取代理商返佣 */
export const fetchMerchantRebate = () => {
  return ajax.get(`/rebate`)
}

/** 获取代理商可提现金额 */
export const fetchMerchantBalance = () => {
  return ajax.get(`/merchant/balance`)
}

/** 获取代理商创建的公司 */
export const fetchMerchantCreateCompany = () => {
  return ajax.get(`/create/company-list`)
}


/** 根据公司名称搜索 */
export const searchByCompanyName = (name: string) => {
  return ajax.get(`/search/company?name=${name}`)
}

/** 根据公司名称搜索发放公司 */
export const searchByCompanyAssign = (id: string) => {
  return ajax.get(`/search/company/assign?id=${id}`)
}

/** 获取代理商发放记录 */
export const fetchMerchantAssignRecords = () => {
  return ajax.get(`/fetch/assign/records`)
}

/** 获取代理商发放记录 */
export const fetchMerchantRebateRecords = () => {
  return ajax.get(`/fetch/rebate/records`)
}