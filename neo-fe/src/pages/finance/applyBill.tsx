import React, { useState } from 'react'
import { Card, Table, Button, Cascader, Select, Form, Input } from 'antd'
import { billOptions, typeOptions } from '@/setting/constantVar'
import styles from './index.styl'

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
}

export const ApplyBill = () => {
  const [form] = Form.useForm()
  const [totalMoney, setTotalMoney] = useState(0)
  const [selectedRowKeys, setSelectedRowKeys] = useState<string[]>([])

  const onFinish = (values: any) => {
    console.log('onFinish:', values)
  }

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
      title: '可开票金额',
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
      key: 'task',
      render: (text: any, record: any) => <div>绑定任务</div>,
    },
    {
      title: '审核时间',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
  ]

  const data = [
    {
      name: 'John Brown',
      age: 32,
      address: 100,
      tags: ['nice', 'developer'],
    },
    {
      name: 'Jim Green',
      age: 42,
      address: 6000,
      tags: ['loser'],
    },
  ]

  const onBillChange = (value: any) => {
    console.log('onBillChange', value)
  }

  const onSelectChange = (selectedRowKeys: any, selectedRows: any) => {
    const totalMoney = selectedRows.map((item: any) => item.address)
    const temp = totalMoney.length > 0 ? totalMoney.reduce((pre: any, cur: any) => pre + cur) : 0
    setSelectedRowKeys(selectedRowKeys)
    setTotalMoney(temp)
  }

  const rowSelection = {
    selectedRowKeys,
    onChange: onSelectChange,
  }

  return (
    <Card title="申请开票">
      <Table
        rowSelection={rowSelection}
        pagination={false}
        style={{ height: 160 }}
        size="small"
        bordered
        rowKey="name"
        columns={columns as any}
        dataSource={data}
      />
      <Form className={styles['bill-form']} form={form} {...layout} onFinish={onFinish}>
        <Form.Item label="开票内容" name="username">
          <Cascader options={billOptions} onChange={onBillChange} placeholder="请选择开票内容"></Cascader>
        </Form.Item>
        <Form.Item label="开票类型" name="username1">
          <Select allowClear placeholder="请选择开票类型">
            {typeOptions.map(item => (
              <Select.Option key={item} value={item}>
                {item}
              </Select.Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item label="收件人姓名" name="username2">
          <Input placeholder="请输入收件人姓名" />
        </Form.Item>
        <Form.Item label="收件人手机号" name="username3">
          <Input placeholder="请输入收件人手机号" />
        </Form.Item>
        <Form.Item label="收件人地址" name="username4">
          <Input placeholder="请输入收件人地址" />
        </Form.Item>
        <Form.Item label="开票金额总计" name="username5">
          <span style={{ fontSize: 20, fontWeight: 500 }}>￥{totalMoney.toFixed(2)}</span>
        </Form.Item>
        <Form.Item style={{ marginLeft: 200 }}>
          <Button disabled={!totalMoney} type="primary" htmlType="submit">
            提交
          </Button>
        </Form.Item>
      </Form>
    </Card>
  )
}
