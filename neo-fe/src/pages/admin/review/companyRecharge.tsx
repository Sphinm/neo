import React from 'react'
import { Card, Table, Button, Popconfirm, Badge } from 'antd'

export const CompanyRecharge = () => {
  const columns = [
    {
      title: '订单号',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: '申请时间',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '打款金额',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '充值金额',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '打款凭证',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '审核状态',
      key: 'status',
      render: (text: any, record: any) => <Badge status="processing" text="等待审核"></Badge>,
    },
    {
      title: '审核时间',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '操作',
      key: 'task',
      render: (text: any, record: any) => {
        return (
          <Popconfirm title="通过该充值申请" onConfirm={() => checkRecharge(record.id)} okText="确认" cancelText="取消">
            <Button disabled={record.status === 'SUCCESS'} type="primary">
              审核
            </Button>
          </Popconfirm>
        )
      },
    },
  ]

  const checkRecharge = (id: string) => {
    console.log('checkRecharge', id)
  }

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
    {
      name: 'John Brown1',
      age: 32,
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer'],
    },
    {
      name: 'Jim Green1',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    },
    {
      name: 'Joe Black1',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
      tags: ['cool', 'teacher'],
    },
    {
      name: 'John Brown2',
      age: 32,
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer'],
    },
    {
      name: 'Jim Green2',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    },
    {
      name: 'Joe Black2',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
      tags: ['cool', 'teacher'],
    },
    {
      name: 'John Brown3',
      age: 32,
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer'],
    },
    {
      name: 'Jim Green3',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    },
    {
      name: 'Joe Black3',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
      tags: ['cool', 'teacher'],
    },
  ]

  return (
    <Card title="公司充值记录">
      <Table bordered rowKey="name" columns={columns as any} dataSource={data} />
    </Card>
  )
}
