import React, { useState } from 'react'
import { Card, Table, Popconfirm, Badge, Button, Divider, Modal, Input, Form } from 'antd'

export const Merchant = () => {
  const [form] = Form.useForm()
  const [visible, setVisible] = useState(false)
  const [isEdit, setEdit] = useState(false)

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: '代理商名称',
      dataIndex: 'username',
      key: 'username',
    },
    {
      title: '代理商地址',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '联系人',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '联系人电话',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '费率',
      key: 'task',
      render: (text: any, record: any) => <div>绑定任务</div>,
    },
    {
      title: '账号',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '密码',
      key: 'task',
      render: (text: any, record: any) => {
        return <Badge status="processing" text="等待审核"></Badge>
      },
    },
    {
      title: '操作',
      key: 'task',
      render: (text: any, record: any) => {
        return (
          <>
            <Button type="link" onClick={() => changeMerchant(record)}>
              编辑
            </Button>

            <Popconfirm
              title="将该代理商从列表中移除"
              onConfirm={() => deleteMerchant(record.id)}
              okText="确认"
              cancelText="取消"
            >
              <Button type="link">删除</Button>
            </Popconfirm>
          </>
        )
      },
    },
  ]

  const data = [
    {
      id: '1312312',
      name: 'John Brown',
      age: 32,
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer'],
    },
    {
      id: '13122312',
      name: 'Jim Green',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    },
    {
      id: '13123121',
      name: 'Joe Black',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
      tags: ['cool', 'teacher'],
    },
    {
      id: '1312313',
      name: 'John Brown1',
      age: 32,
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer'],
    },
    {
      id: '1312316',
      name: 'Jim Green1',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    },
  ]

  const changeMerchant = (values: any) => {
    form.setFieldsValue({
      username: values.name,
      status: values.status,
      type: values.type,
      code: values.code,
    })
    setVisible(true)
    setEdit(true)
  }

  const deleteMerchant = (id: any) => {
    console.log('deleteMerchant', id)
  }

  const handleOk = async () => {
    const values = await form.validateFields()
    console.log(11, values)
    setVisible(false)
  }

  const createMerchant = () => {
    setVisible(true)
    setEdit(false)
    form.resetFields()
  }

  return (
    <>
      <Card title="代理商档案">
        <Button type="primary" onClick={createMerchant}>
          新增代理商
        </Button>
        <Divider />
        <Table bordered rowKey="name" columns={columns as any} dataSource={data} />
      </Card>
      <Modal
        getContainer={false}
        title={isEdit ? '编辑代理商信息' : '创建代理商信息'}
        visible={visible}
        onOk={handleOk}
        onCancel={() => setVisible(false)}
        footer={[
          <Button key="back" onClick={() => setVisible(false)}>
            取消
          </Button>,
          <Button key="submit" htmlType="submit" type="primary" onClick={handleOk}>
            提交
          </Button>,
        ]}
      >
        <Form form={form}>
          <Form.Item label="公司名称" name="username" rules={[{ required: true, message: '请填写公司名称' }]}>
            <Input placeholder="请输入公司名称"></Input>
          </Form.Item>
          <Form.Item label="公司地址" name="username1" rules={[{ required: true, message: '请填写公司地址' }]}>
            <Input placeholder="请输入公司地址"></Input>
          </Form.Item>
          <Form.Item label="联系人" name="username2" rules={[{ required: true, message: '请填写公司联系人' }]}>
            <Input placeholder="请输入联系人"></Input>
          </Form.Item>
          <Form.Item label="联系电话" name="username3" rules={[{ required: true, message: '请填写公司联系电话' }]}>
            <Input placeholder="请输入联系电话"></Input>
          </Form.Item>
          <Form.Item label="费率" name="username4" rules={[{ required: true, message: '请填写费率' }]}>
            <Input placeholder="请输入费率"></Input>
          </Form.Item>
          <Form.Item label="账号" name="username" rules={[{ required: true, message: '请填写代理商账号' }]}>
            <Input placeholder="请输入账号"></Input>
          </Form.Item>
          <Form.Item label="密码" name="username" rules={[{ required: true, message: '请填写代理商密码' }]}>
            <Input placeholder="请输入密码"></Input>
          </Form.Item>
        </Form>
      </Modal>
    </>
  )
}
