import React from 'react'
import { Card, Table, Badge, Input, Divider } from 'antd'

const { Search } = Input

export const FlexList = () => {
  const columns = [
    {
      title: 'ID',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '公司名称',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '充值金额',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '发放金额',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '余额',
      key: 'task',
      render: (text: any, record: any) => <div>绑定任务</div>,
    },
    {
      title: '费率',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '创建时间',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '状态',
      key: 'action',
      render: (text: any, record: any) => <Badge status="processing" text="等待审核"></Badge>,
    },
    // {
    //   title: '操作',
    //   key: 'task',
    //   render: (text: any, record: any) => {
    //     return (
    //       <>
    //         <Button type="link" onClick={() => changeMerchant(record)}>
    //           编辑
    //         </Button>

    //       </>
    //     )
    //   },
    // },
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
    <Card title="灵活用工客户列表">
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
