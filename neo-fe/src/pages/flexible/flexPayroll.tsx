import React from 'react'
import { Card, Table, Input, Divider } from 'antd'

const { Search } = Input

export const FlexPayroll = () => {
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
      title: '公司名称',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '发放金额（成功）',
      key: 'action',
    },
    {
      title: '佣金',
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
    <Card title="发放记录">
      <Search
        style={{ width: 300 }}
        placeholder="请输入公司名称"
        enterButton="搜索"
        onSearch={value => console.log(value)}
      />
      <Divider />
      <Table bordered rowKey="name" columns={columns as any} dataSource={data} />
    </Card>
  )
}
