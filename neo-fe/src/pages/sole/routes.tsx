import React from 'react'
import { Redirect, Route, Switch } from 'react-router'

import { AddCustomer } from './addCustomer'
import { CustomerList } from './customerList'

const Sole = () => {
  return (
    <Switch>
      <Route exact path="/main/sole/add" component={AddCustomer} />
      <Route exact path="/main/sole/records" component={CustomerList} />
      <Route render={() => <Redirect to="/main/dashbord" />} />
    </Switch>
  )
}

export default Sole
