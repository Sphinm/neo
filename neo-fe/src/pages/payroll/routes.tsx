import React from 'react'
import { Redirect, Route, Switch } from 'react-router'

import { PayrollApplication } from './payrollApplication'
import { PayrollDetails } from './payrollDetails'
import { PayrollRecord } from './payrollRecord'
import { UploadPayrollList } from './uploadPayrollList'

const Payroll = () => {
  return (
    <Switch>
      <Route exact path="/main/payroll/approve" component={PayrollApplication} />
      <Route exact path="/main/payroll/details" component={PayrollDetails} />
      <Route exact path="/main/payroll/records" component={PayrollRecord} />
      <Route exact path="/main/payroll/list" component={UploadPayrollList} />
      <Route render={() => <Redirect to="/main/dashbord" />} />
    </Switch>
  )
}

export default Payroll
