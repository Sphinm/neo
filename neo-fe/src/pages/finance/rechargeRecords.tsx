import React from 'react'
import { Card, Table, Button } from 'antd'

export const RechargeRecords = () => {
  const columns = [
    {
      title: '订单号',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '申请时间',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '收款人',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '收款账号',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '收款金额',
      key: 'task',
      render: (text: any, record: any) => <div>绑定任务</div>,
    },
    {
      title: '发放时间',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '发放状态',
      key: 'task',
      render: (text: any, record: any) => <div>绑定任务</div>,
    },
    {
      title: '操作',
      key: 'task',
      align: 'center',
      render: (text: any, record: any) => {
        return (
          <Button
            type="link"
            onClick={() => {
              handleSelectRow(record)
            }}
          >
            下载回单
          </Button>
        )
      },
    },
  ]

  const handleSelectRow = (record: any) => {
    console.log('handleSelectRow', record)
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
    <Card title="充值记录">
      <Table bordered rowKey="name" columns={columns as any} dataSource={data} />
    </Card>
  )
}
