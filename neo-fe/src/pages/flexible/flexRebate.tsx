import React from 'react'
import { Card, Table } from 'antd'

export const FlexRebate = () => {
  const columns = [
    {
      title: '订单号',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '金额',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '公司名称',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '充值金额',
      key: 'action',
    },
    {
      title: '费率',
      key: 'task',
    },
    {
      title: '创建时间',
      key: 'task',
    },
  ]

  const data = [
    {
      name: 'John Brown',
      age: 32,
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer'],
    },
    {
      name: 'Jim Green',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    },
    {
      name: 'Joe Black',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
      tags: ['cool', 'teacher'],
    },
  ]

  return (
    <Card title="返佣记录">
      <Table bordered rowKey="name" columns={columns as any} dataSource={data} />
    </Card>
  )
}
