import React, { Suspense } from 'react'
import ReactDOM from 'react-dom'
import App from './App'
import '@/assets/stylus/index.styl'
import Loading from '@/components/Loading'
import * as serviceWorker from './serviceWorker'
import { RoleStore } from '@/store/roleStore'
import 'moment/locale/zh-cn'

if (window.location.pathname !== '/login') RoleStore.fetchCurrentRole()

ReactDOM.render(
  <Suspense fallback={<Loading />}>
    <App />
  </Suspense>,
  document.getElementById('root'),
)

if (module.hot) module.hot.accept()
// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister()
