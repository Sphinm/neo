import React from 'react'
import { Card, Table, Button, Form, Input, Row, Col, DatePicker } from 'antd'
import { downloadExcel } from '@/libs/download-excel'
const { Search } = Input

export const PayrollDetails = () => {
  const [form] = Form.useForm()

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

  const handleSelectRow = (record: any) => {
    console.log('handleSelectRow', record)
  }

  const downLoadReport = async () => {
    downloadExcel(data)
  }

  return (
    <Card title="人员发放明细">
      <Form form={form}>
        <Row gutter={24}>
          <Col span={7}>
            <Form.Item name="idcard">
              <Search placeholder="身份证" enterButton="搜索" onSearch={value => console.log(value)} />
            </Form.Item>
          </Col>
          <Col span={8}>
            <Form.Item name="time">
              <DatePicker.RangePicker allowClear format="YYYY-MM-DD" placeholder={['开始时间', '结束时间']} />
            </Form.Item>
          </Col>
          <Button type="primary" onClick={downLoadReport}>
            批量导出发放明细
          </Button>
        </Row>
      </Form>

      <Table bordered rowKey="name" columns={columns as any} dataSource={data} />
    </Card>
  )
}
