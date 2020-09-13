import React, { useEffect } from 'react'
import { RoleStore } from '@/store/roleStore'
import { useObserver } from 'mobx-react'
// 作为登录的所有权限都能看的页面
const Dashboard = () => {
  useEffect(() => {
    RoleStore.fetchCurrentRole()
  }, [])
  return useObserver(() => (
    <div>
      <h1>Welcome NEO</h1>
    </div>
  ))
}

export default Dashboard
