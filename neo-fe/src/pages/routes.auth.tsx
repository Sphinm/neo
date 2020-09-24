import { Switch, Route, Redirect } from 'react-router'
import React from 'react'
import createLazy from '@/libs/lazy'
import Admin from './admin/routes'
import Payroll from './payroll/routes'
import Finance from './finance/routes'
import Sign from './sign/routes'
import Sole from './sole/routes'
import Flexible from './flexible/routes'
import Report from './report/routes'
import DataQueryRoute from './dataQuery/routes'

const RoutesAuth = () => {
  return (
    <Switch>
      <Route path="/main/dashboard" component={createLazy(() => import('@/pages/dashboard/index'))} />
      <Route path="/main/admin" component={Admin} />
      <Route path="/main/dataquery" component={DataQueryRoute} />
      <Route path="/main/payroll" component={Payroll} />
      <Route path="/main/finance" component={Finance} />
      <Route path="/main/sign" component={Sign} />
      <Route path="/main/sole" component={Sole} />
      <Route path="/main/flexible" component={Flexible} />
      <Route path="/main/report" component={Report} />
      <Route render={() => <Redirect to="/main/dashboard" />} />
    </Switch>
  )
}

export default RoutesAuth
