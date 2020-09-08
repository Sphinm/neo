import React from 'react'
import { useObserver } from 'mobx-react'
// 作为登录的所有权限都能看的页面
const Dashboard = () => {
  return useObserver(() => (
    <div>
      <h1>Welcome NEO</h1>
    </div>
  ))
}

export default Dashboard
