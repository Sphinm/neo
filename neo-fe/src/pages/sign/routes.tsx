import React from 'react'
import { Redirect, Route, Switch } from 'react-router'

import { SignedIn } from './signedIn'
import { UnSigned } from './unSigned'

const Sign = () => {
  return (
    <Switch>
      <Route exact path="/main/sign/signed" component={SignedIn} />
      <Route exact path="/main/sign/unsigned" component={UnSigned} />
      <Route render={() => <Redirect to="/main/dashbord" />} />
    </Switch>
  )
}

export default Sign
