import ajax from '@/libs/axios'

/** 获取待审核公司列表 */
export const fetchReviewCompany = () => {
  return ajax.get(`/review/companyList`)
}
