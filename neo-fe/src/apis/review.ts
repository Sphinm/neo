import ajax from '@/libs/axios'

/** 获取待审核公司列表 */
export const fetchReviewCompany = () => {
  return ajax.get(`/review/companyList`)
}

/** 审核公司信息 */
export const reviewCompany = (id: string) => {
  return ajax.post(`/review/company?id=${id}`)
}

/** 获取充值列表 */
export const fetchReviewRecharge = () => {
  return ajax.get(`/review/rechargeList`)
}

/** 审核充值记录 */
export const reviewRecharge = (id: string) => {
  return ajax.post(`/review/recharge?id=${id}`)
}

/** 获取开票列表 */
export const fetchReviewInvoice = () => {
  return ajax.get(`/review/invoiceList`)
}

/** 审核开票记录 */
export const reviewInvoice = (id: string) => {
  return ajax.post(`/review/invoice?id=${id}`)
}

/** 获取发放列表 */
export const fetchReviewProvide = () => {
  return ajax.get(`/review/provideList`)
}

/** 审核发放记录 */
export const reviewProvide = (id: string) => {
  return ajax.post(`/review/provide?id=${id}`)
}

/** 获取提现列表 */
export const fetchReviewWithdraw = () => {
  return ajax.get(`/review/withdrawList`)
}

/** 审核提现记录 */
export const reviewWithdraw= (id: string) => {
  return ajax.post(`/review/withdraw?id=${id}`)
}

/** 获取完税列表 */
export const fetchReviewTax = () => {
  return ajax.get(`/review/taxList`)
}

/** 审核完税记录 */
export const reviewTax= (id: string) => {
  return ajax.post(`/review/tax?id=${id}`)
}

/** 上传完税记录 */
export const uploadTax= () => {
  return ajax.post(`/upload/tax`)
}

/** 上传完税凭证信息 */
export const uploadTaxInfo = (params: any) => {
  return ajax.post('/upload/taxInfo', params)
} 