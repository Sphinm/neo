import ajax from '@/libs/axios'

// /** 获取公司发放记录 */
// export const fetchCompanyIssues = () => {
//   return ajax.get(`/company/issues`)
// }

/** 公司开票接口 */
export const companyInvoice = (body: any) => {
  return ajax.post(`/company/create-invoice`, body)
}

/** 获取公司充值信息 */
export const fetchCompanyChargeInfo = () => {
  return ajax.get(`/company/charge-info`)
}

/** 获取公司充值记录 */
export const fetchCompanyChargeList = () => {
  return ajax.get(`/company/charge-list`)
}

/** 公司充值接口 */
export const companyCharge = (body: any) => {
  return ajax.post(`/company/charge/post`, body)
}

/** 获取公司申请开票记录 */
export const fetchCompanyInvoiceList = () => {
  return ajax.get(`/company/invoice-list`)
}

/** 获取公司完税凭证记录 */
export const fetchCompanyRecepits = () => {
  return ajax.get(`/company/receipts`)
}