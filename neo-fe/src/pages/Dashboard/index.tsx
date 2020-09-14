import React, { useEffect } from 'react'
import { RoleStore } from '@/store/roleStore'
import { useObserver } from 'mobx-react'
import { Card, Button } from 'antd'
import history from '@/libs/history'
import style from './index.styl'

// 员工无法看到该页面
const Dashboard = () => {
  useEffect(() => {
    RoleStore.fetchCurrentRole()
  }, [])
  const goFinance = () => {
    history.push('/main/finance/recharge')
  }
  return useObserver(() => (
    <>
      <Card>
        <div className={style['money-title']}>账户可用余额</div>
        <div className={style['money']}>￥100.24</div>
        <div className={style['button-group']}>
          <Button type="primary" onClick={goFinance}>
            立即充值
          </Button>
          <Button type="default" className={style['button-right']}>
            查看明细
          </Button>
        </div>
      </Card>
    </>
  ))
}

export default Dashboard
