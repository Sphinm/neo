import React, { useEffect } from 'react'
import { RoleStore } from '@/store/roleStore'
import { useObserver } from 'mobx-react'
import { Card, Button, Row, Col } from 'antd'
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
  const goFinanceList = () => {
    history.push('/main/finance/recharge-records')
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
          <Button type="default" className={style['button-right']} onClick={goFinanceList}>
            查看明细
          </Button>
        </div>
      </Card>
      <div className={style['card-wrapper']}>
        <Row gutter={24}>
          <Col span={18}>
            <Card bordered={false}>Card content</Card>
          </Col>
          <Col span={6}>
            <Card title="Card title" bordered={false}>
              由于银行对公转账的相关限制，费用发放在工作日内：周一 至 周五 09:00 ~
              16:00之间进行，超过该时间顺延到第二个工作日办理，如需按时发放请提前做好相关工作准备。
            </Card>
            <Card style={{ margin: '20px 0' }} bordered={false}>
              <div className={style['money-title']}>最近一个月发放金额</div>
              <div className={style['money']}>￥100.24 元</div>
            </Card>
            <Card bordered={false}>
              <div className={style['money-title']}>最近一个月发放人数</div>
              <div className={style['money']}>￥100.24 人</div>
            </Card>
          </Col>
        </Row>
      </div>
    </>
  ))
}

export default Dashboard
