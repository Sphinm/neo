import React from 'react'
import { Card, Table } from 'antd'
import { Link } from 'react-router-dom'

export const TaxReceipts = () => {
  const columns = [
    {
      title: '编号',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '创建时间',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '公司名称',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '区域',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '完税凭证',
      key: 'task',
      render: (text: any, record: any) => {
        return (
          <Link target="_blank" to={'/main'}>
            完税凭证
          </Link>
        )
      },
    },
    {
      title: '月份',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '备注信息',
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
  ]

  return (
    <Card title="完税凭证">
      <Table bordered rowKey="name" columns={columns as any} dataSource={data} />
    </Card>
  )
}
