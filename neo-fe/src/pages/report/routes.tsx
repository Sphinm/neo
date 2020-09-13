import React from 'react'
import { Redirect, Route, Switch } from 'react-router'

import { Protocol } from './protocol'

const Payroll = () => {
  return (
    <Switch>
      <Route exact path="/main/report" component={Protocol} />
      <Route render={() => <Redirect to="/main/report" />} />
    </Switch>
  )
}

export default Payroll
