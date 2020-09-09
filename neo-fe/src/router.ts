import lazy from '@/libs/lazy'

const Dashboard = lazy(() => import('@/pages/Dashboard'))
const Login = () => import('@/pages/Login')

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
