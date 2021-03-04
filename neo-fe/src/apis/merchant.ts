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
