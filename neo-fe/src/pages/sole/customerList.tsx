import React from 'react'
import { Card, Table, Badge } from 'antd'

export const CustomerList = () => {
  const columns = [
    {
      title: '订单号',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '客户名称',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '客户手机号',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '产品',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '费用(总计)',
      key: 'task',
      render: (text: any, record: any) => <div>绑定任务</div>,
    },
    {
      title: '打款凭证',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '创建日期',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '审核状态',
      key: 'action',
      render: (text: any, record: any) => <Badge status="processing" text="等待审核"></Badge>,
    },
    {
      title: '审核时间',
      key: 'action',
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
    <Card title="客户列表">
      <Table bordered rowKey="name" columns={columns as any} dataSource={data} />
    </Card>
  )
}
