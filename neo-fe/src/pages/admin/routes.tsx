import React from 'react'
import { Redirect, Route, Switch } from 'react-router'
// 用户信息
import { Merchant } from './userInfo/merchant'
import { Company } from './userInfo/company'
import { Employee } from './userInfo/employee'
// 待审核项目
import { CompanyBillRecord } from './review/companyBillRecord'
import { CompanyPayrollRecord } from './review/companyPayrollRecord'
import { CompanyRecharge } from './review/companyRecharge'
import { UploadTaxReceipts } from './review/uploadTaxReceipts'

const Admin = () => {
  return (
    <Switch>
      <Route exact path="/main/admin/merchant" component={Merchant} />
      <Route exact path="/main/admin/company" component={Company} />
      <Route exact path="/main/admin/employee" component={Employee} />
      <Route exact path="/main/admin/reviewbill" component={CompanyBillRecord} />
      <Route exact path="/main/admin/reviewpayroll" component={CompanyPayrollRecord} />
      <Route exact path="/main/admin/reviewrecharge" component={CompanyRecharge} />
      <Route exact path="/main/admin/reviewuploadtax" component={UploadTaxReceipts} />
      <Route render={() => <Redirect to="/main/dashbord" />} />
    </Switch>
  )
}

export default Admin
