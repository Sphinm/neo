import React, { useState } from 'react'
import { CloudUploadOutlined } from '@ant-design/icons'
import { Card, Table, Button, Popconfirm, Badge, Divider, Modal, Form, Input, Upload, message } from 'antd'

export const UploadTaxReceipts = () => {
  const [form] = Form.useForm()
  const [visible, setVisible] = useState(false)

  const options = {
    name: 'file',
    action: 'https://www.mocky.io/v2/5cc8019d300000980a055e76',
    headers: {
      authorization: 'authorization-text',
    },
    onChange(info: any) {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList)
      }
      if (info.file.status === 'done') {
        message.success(`${info.file.name} 上传成功`)
      } else if (info.file.status === 'error') {
        message.error(`${info.file.name} 上传失败`)
      }
    },
  }

  const columns = [
    {
      title: '编号',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: '创建时间',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '完税凭证',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '月份',
      key: 'status',
      render: (text: any, record: any) => <Badge status="processing" text="等待审核"></Badge>,
    },
    {
      title: '备注信息',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '操作',
      key: 'task',
      render: (text: any, record: any) => {
        return (
          <Popconfirm title="请确认是否删除" onConfirm={() => checkRecharge(record.id)} okText="确认" cancelText="取消">
            <Button type="primary">删除</Button>
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
  ]

  const uploadTaxReceipts = () => {
    setVisible(true)
    form.resetFields()
  }

  const handleOk = async () => {
    const values = await form.validateFields()
    console.log(11, values)
    setVisible(false)
  }

  return (
    <Card title="上传完税凭证">
      <Button type="primary" onClick={uploadTaxReceipts}>
        上传完税凭证
      </Button>
      <Divider />
      <Table bordered rowKey="name" columns={columns as any} dataSource={data} />
      <Modal
        getContainer={false}
        title="上传完税凭证"
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
          <Form.Item name="credit" valuePropName="credit" rules={[{ required: true, message: '请上传打款凭证' }]}>
            <Upload {...options}>
              <Button type="primary" icon={<CloudUploadOutlined />}>
                上传完税凭证
              </Button>
            </Upload>
          </Form.Item>
          <Form.Item label="公司名称" name="username" rules={[{ required: true, message: '请填写公司名称' }]}>
            <Input placeholder="请输入公司名称"></Input>
          </Form.Item>
          <Form.Item label="月份" name="username1" rules={[{ required: true, message: '请填写月份' }]}>
            <Input placeholder="请输入月份"></Input>
          </Form.Item>
          <Form.Item label="备注" name="username2">
            <Input placeholder="请输入备注信息"></Input>
          </Form.Item>
        </Form>
      </Modal>
    </Card>
  )
}
