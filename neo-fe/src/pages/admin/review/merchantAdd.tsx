import React from 'react'
import { Card, Table, Button, Popconfirm, Badge } from 'antd'

export const MerchantAdd = () => {
  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: '公司名称',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '公司税号',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '公司地址',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '联系人',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '联系电话',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '开户行',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '银行账号',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '费率',
      dataIndex: 'address',
      key: 'address',
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
      fixed: 'right',
      render: (text: any, record: any) => {
        return (
          <Popconfirm
            title="通过该新增用户申请"
            onConfirm={() => checkRecharge(record.id)}
            okText="确认"
            cancelText="取消"
          >
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
    <Card title="代理商新增客户审核">
      <Table scroll={{ x: 1500 }} bordered rowKey="name" columns={columns as any} dataSource={data} />
    </Card>
  )
}
