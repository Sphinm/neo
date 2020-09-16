import React from 'react'
import { Table } from 'antd'

export const DataQuery = () => {
  const expandedRowRender = () => {
    const columns = [
      { title: '公司名称', dataIndex: 'name', key: 'name' },
      { title: '充值金额', dataIndex: 'upgradeNum', key: 'upgradeNum' },
      { title: '发放金额', dataIndex: 'sendMoeny', key: 'upgradeNum1' },
      { title: '剩余金额', dataIndex: 'leftMoney', key: 'upgradeNum2' },
    ]

    const data = []
    for (let i = 0; i < 3; ++i) {
      data.push({
        key: i,
        name: '马大哈公司',
        upgradeNum: 100000,
        sendMoeny: 80000,
        leftMoney: 30000,
      })
    }
    return <Table columns={columns} dataSource={data} pagination={false} />
  }

  const columns = [
    { title: '代理商名称', dataIndex: 'name', key: 'name' },
    { title: '账户余额', dataIndex: 'platform', key: 'platform' },
    { title: '已提现金额', dataIndex: 'version', key: 'version' },
    { title: 'Date', dataIndex: 'createdAt', key: 'createdAt' },
  ]

  const data = []
  for (let i = 0; i < 3; ++i) {
    data.push({
      key: i,
      name: '某某代理商',
      platform: 1000,
      version: 500,
      createdAt: '2014-12-24 23:12:00',
    })
  }

  return <Table columns={columns} expandable={{ expandedRowRender }} dataSource={data} />
}
