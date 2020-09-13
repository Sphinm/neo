import { Switch, Route, Redirect } from 'react-router'
import React from 'react'
import createLazy from '@/libs/lazy'
import Admin from './admin/routes'
import Payroll from './payroll/routes'
import Finance from './finance/routes'
import Sign from './sign/routes'
import Sole from './sole/routes'
import Flexible from './flexible/routes'

const RoutesAuth = () => {
  return (
    <Switch>
      <Route path="/main/dashboard" component={createLazy(() => import('@/pages/dashboard'))} />
      <Route path="/main/admin" component={Admin} />
      <Route path="/main/payroll" component={Payroll} />
      <Route path="/main/finance" component={Finance} />
      <Route path="/main/sign" component={Sign} />
      <Route path="/main/sole" component={Sole} />
      <Route path="/main/flexible" component={Flexible} />
      <Route render={() => <Redirect to="/main/dashboard" />} />
    </Switch>
  )
}

export default RoutesAuth
