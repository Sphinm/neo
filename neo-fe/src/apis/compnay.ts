import ajax from '@/libs/axios'

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

/** 获取未签约员工记录 */
export const fetchUnSignUpList = () => {
  return ajax.get(`/sign/un-sign-up`)
}

/** 获取签约员工记录 */
export const fetchSignUpList = () => {
  return ajax.get(`/sign/sign-up`)
}

/** 通过身份证查询员工用户 */
export const searchByIdCard = (id: string) => {
  return ajax.get(`/sign/search-by-idCard?id=${id}`)
}

/** 员工更改手机号 */
export const changeMobileEmp = (body: any) => {
  return ajax.post(`/sign/change-mobile`, body)
}

/** 获取发放记录 */
export const fetchProvideList = () => {
  return ajax.get(`/provide/list`)
}

/** 获取发放详情 */
export const fetchProvideDetail = () => {
  return ajax.get(`/provide/detail`)
}

/** 发放记录删除 */
export const deleteProvideItem = (id: string) => {
  return ajax.delete(`/provide/delete/${id}`)
}