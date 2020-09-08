import React from 'react'
import { Route, Switch, withRouter, RouteComponentProps } from 'react-router-dom'
import Layout from '@/pages/Layout'
import { routeList } from '@/router'

const App: React.FC<RouteComponentProps> = (props: RouteComponentProps) => {
  const childrenRouteList = routeList.filter(item => item.name !== 'Layout')
  return (
    <div>
      <Switch>
        {childrenRouteList.map(route => {
          return (
            <Route key={route.name} exact={route.exact} path={route.path}>
              <Route component={route.component}></Route>
            </Route>
          )
        })}
        <Route path="/layout">
          <Layout></Layout>
        </Route>
        {/* <Redirect from="/" to="/layout" /> */}
        {/* <Redirect from="*" to="/404"></Redirect> */}
      </Switch>
    </div>
  )
}
export default withRouter(App)
