import * as React from 'react'
import createLazy from '@/libs/lazy'
import Main from './main'
import { Redirect, Route, Switch } from 'react-router-dom'

const RoutesEntry = () => (
  <Switch>
    <Route exact path="/" render={() => <Redirect to="/login" />} />
    <Route exact path="/login" component={createLazy(() => import('@/pages/login'))} />
    <Route exact path="/main/:page/:subPage/:subPage2*" component={Main} />
    <Route exact path="/main/:page/:subPage*" component={Main} />
    <Route exact path="/main/:page*" component={Main} />
    <Route render={() => <Redirect to="/" />} />
  </Switch>
)

export default RoutesEntry
