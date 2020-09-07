import React, { useEffect } from 'react'
import { useObserver } from 'mobx-react'
import { findAllUser } from '../../apis/auth'
// 作为登录的所有权限都能看的页面
const Dashboard = () => {
  useEffect(() => {
    fetchAllUser()
  }, [])

  const fetchAllUser = async () => {
    const user = await findAllUser()
    console.log('user', user)
  }

  return useObserver(() => (
    <div>
      <h1>Welcome NEO</h1>
    </div>
  ))
}

export default Dashboard
