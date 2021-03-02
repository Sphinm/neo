import ajax from '@/libs/axios'

// /** 更改密码 */
// export const changePwd = (params: { oldPwd: string; newPwd: string }) => {
//   return ajax.post(`/change/password`, params)
// }

/** 获取代理商返佣 */
export const fetchMerchantRebate = () => {
  return ajax.get(`/rebate`)
}

/** 获取代理商可提现金额 */
export const fetchMerchantBalance = () => {
  return ajax.get(`/merchant/balance`)
}