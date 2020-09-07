import lazy from './libs/lazy'

import Dashboard from './pages/Dashboard'
import Login from './pages/Login'

export const routeList = [
  // {
  //   name: 'Layout',
  //   path: '/layout',
  //   children: [
  //     {
  //       name: 'Dashboard',
  //       path: '/',
  //       exact: true,
  //       component: Dashboard,
  //     }
  //   ],
  // },
  {
    name: 'Login',
    path: '/login',
    exact: false,
    component: Login,
    children: [],
  },
  {
    name: 'NotFound',
    path: '/layout',
    exact: false,
    component: Dashboard,
    children: [],
  },
]
