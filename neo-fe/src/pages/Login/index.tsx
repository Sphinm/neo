import React from 'react'
import { useObserver } from 'mobx-react'

const Login = () => {
  return useObserver(() => (
    <div>
      <h1>Welcome Login</h1>
    </div>
  ))
}

export default Login
