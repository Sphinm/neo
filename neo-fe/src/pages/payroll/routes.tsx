import React from 'react'
import { Redirect, Route, Switch } from 'react-router'

import { PayrollDetails } from './payrollDetails'
import { UploadPayrollList } from './uploadPayrollList'

const Payroll = () => {
  return (
    <Switch>
      <Route exact path="/main/payroll/details" component={PayrollDetails} />
      <Route exact path="/main/payroll/list" component={UploadPayrollList} />
      <Route render={() => <Redirect to="/main/dashbord" />} />
    </Switch>
  )
}

export default Payroll
