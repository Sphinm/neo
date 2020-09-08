import React, { Suspense } from 'react'
import ReactDOM from 'react-dom'
import App from './App'
import '@/assets/stylus/index.styl'
import Loading from '@/components/Loading'
import { BrowserRouter as Router } from 'react-router-dom'
import * as serviceWorker from './serviceWorker'
import { RoleStore } from '@/store/roleStore'

if (window.location.pathname !== '/login') RoleStore.fetchCurrentRole()

ReactDOM.render(
  <Suspense fallback={<Loading />}>
    <Router>
      <App />
    </Router>
  </Suspense>,
  document.getElementById('root'),
)

if (module.hot) module.hot.accept()
// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister()
