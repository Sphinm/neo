import React from 'react'
import { Redirect, Route, Switch } from 'react-router'

import { FlexList } from './flexList'
import { FlexPayroll } from './flexPayroll'
import { FlexRebate } from './flexRebate'

const Flexible = () => {
  return (
    <Switch>
      <Route exact path="/main/flexible/list" component={FlexList} />
      <Route exact path="/main/flexible/payroll" component={FlexPayroll} />
      <Route exact path="/main/flexible/rebates" component={FlexRebate} />
      <Route render={() => <Redirect to="/main/dashbord" />} />
    </Switch>
  )
}

export default Flexible
