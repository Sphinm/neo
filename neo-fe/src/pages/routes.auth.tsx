import { Switch, Route, Redirect } from 'react-router'
import React from 'react'
import createLazy from '@/libs/lazy'
import Admin from './admin/routes'
// import UserGroup from './user-group/routes';

const RoutesAuth = () => {
  return (
    <Switch>
      <Route path="/main/dashboard" component={createLazy(() => import('@/pages/dashboard'))} />
      {/* <Route path="/main/users" component={createLazy(() => import('@/pages/users/routes'))} />
      <Route path="/main/merchants" component={createLazy(() => import('@/pages/merchants/routes'))} />
      <Route path="/main/marketing" component={Marketing} /> */}
      <Route path="/main/admin" component={Admin} />
      <Route render={() => <Redirect to="/main/dashboard" />} />
    </Switch>
  )
}

export default RoutesAuth
