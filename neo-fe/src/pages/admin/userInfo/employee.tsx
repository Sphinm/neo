import React from 'react'
import { Card, Table, Button, Popconfirm } from 'antd'

export const Employee = () => {
  const columns = [
    {
      title: '用户ID',
      dataIndex: 'id',
    },
    {
      title: '姓名',
      dataIndex: 'name',
    },
    {
      title: '身份证号',
      dataIndex: 'age',
    },
    {
      title: '手机号',
      dataIndex: 'address',
    },
    {
      title: '关联公司',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '是否签约',
      dataIndex: 'age',
    },
    {
      title: '操作',
      render: (text: any, record: any) => {
        return (
          <Popconfirm
            title="将该员工从列表中移除"
            onConfirm={() => deleteUser(record.id)}
            okText="确认"
            cancelText="取消"
          >
            <Button type="primary">删除</Button>
          </Popconfirm>
        )
      },
    },
  ]

  const deleteUser = (id: string) => {
    console.log('delete', id)
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
    <Card title="员工档案">
      <Table bordered rowKey="id" columns={columns as any} dataSource={data} />
    </Card>
  )
}
