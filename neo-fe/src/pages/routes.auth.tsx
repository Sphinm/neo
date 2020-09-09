import { Switch, Route, Redirect } from 'react-router'
import React from 'react'
import createLazy from '@/libs/lazy'
// import Marketing from './marketing/routes';
// import UserGroup from './user-group/routes';

const RoutesAuth = () => {
  return (
    <Switch>
      <Route path="/main/dashbord" component={createLazy(() => import('@/pages/Dashboard'))} />
      {/* <Route path="/main/users" component={createLazy(() => import('@/pages/users/routes'))} />
      <Route path="/main/merchants" component={createLazy(() => import('@/pages/merchants/routes'))} />
      <Route path="/main/marketing" component={Marketing} />
      <Route path="/main/user-group" component={UserGroup} /> */}
      <Route render={() => <Redirect to="/main/dashbord" />} />
    </Switch>
  )
}

export default RoutesAuth
