import React from 'react'
import { Redirect, Route, Switch } from 'react-router'
import { DataQuery } from './index'

const DataQueryRoute = () => {
  return (
    <Switch>
      <Route exact path="/main/dataquery" component={DataQuery} />
      <Route render={() => <Redirect to="/main/dashbord" />} />
    </Switch>
  )
}

export default DataQueryRoute
