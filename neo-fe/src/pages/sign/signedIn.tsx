import React, { useState } from 'react'
import { Card, Table, Button, Input, Space, Modal } from 'antd'
import { downloadExcel } from '@/libs/download-excel'
import styles from './index.styl'

const { Search } = Input

export const SignedIn = () => {
  const [visible, setVisible] = useState(false)

  const columns = [
    {
      title: '编号',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '姓名',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '身份证号',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '手机号',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '实名认证',
      key: 'task',
      render: (text: any, record: any) => <div>绑定任务</div>,
    },
    {
      title: '操作',
      key: 'task',
      align: 'center',
      render: (text: any, record: any) => {
        return (
          <>
            <Button type="link" onClick={() => setVisible(true)}>
              修改
            </Button>
            <Button type="link" onClick={() => downLoadReportItem}>
              下载合同
            </Button>
          </>
        )
      },
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

  const downLoadReportItem = () => {
    downloadExcel(data)
  }

  const downLoadReport = () => {
    downloadExcel(data)
  }

  const handleOk = () => {
    setVisible(false)
  }

  return (
    <>
      <Card title="已签约人员">
        <Space size="middle" style={{ marginBottom: 50 }}>
          <Search placeholder="身份证" enterButton="搜索" onSearch={value => console.log(value)} />
          <Button type="primary" onClick={downLoadReport}>
            导出表格
          </Button>
        </Space>
        <Table bordered rowKey="name" columns={columns as any} dataSource={data} />
      </Card>
      <Modal title="修改手机号" visible={visible} onOk={handleOk} onCancel={() => setVisible(false)}>
        <div className={styles['fix-mobile']}>
          <span>修改手机号: </span>
          <Input style={{ flex: 1, marginLeft: 20 }} placeholder="请输入手机号"></Input>
        </div>
      </Modal>
    </>
  )
}
