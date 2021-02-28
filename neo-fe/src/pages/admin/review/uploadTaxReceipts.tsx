import React, { useEffect, useState } from 'react'
import { CloudUploadOutlined } from '@ant-design/icons'
import { Card, Table, Button, Popconfirm, Badge, Divider, Modal, Form, Input, Upload, message } from 'antd'
import { handleError } from '@/libs/axios'
import { fetchReviewTax, reviewTax } from '@/apis/review'
import { RcFile } from 'antd/lib/upload/interface'

export const UploadTaxReceipts = () => {
  const [form] = Form.useForm()
  const [visible, setVisible] = useState(false)
  const [tableData, setTableData] = useState([])
  const [uploadPath, setUploadPath] = useState('')
  
  useEffect(() => {
    fetchBillInfo()
  }, []);

  const fetchBillInfo = async() => {
    try {
      const { data } = await fetchReviewTax();
      setTableData(data)
    } catch (error) {
      handleError(error)
    }
  }

  const beforeUpload = (file: RcFile, FileList: RcFile[]) => {
    const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png'
    if (!isJpgOrPng) {
      message.error('只能上传 JPG/PNG 图片!')
    }
    const isLt5M = file.size / 1024 / 1024 < 5
    if (!isLt5M) {
      message.error('文件大小不能超过 5M')
    }
    return isJpgOrPng && isLt5M
  }

  const options = {
    name: 'file',
    action: '/api/upload/tax',
    accept: "image/png, image/jpeg",
    headers: {
      'Authorization': `Bearer ` + localStorage.getItem('token'),
    },
    beforeUpload: beforeUpload,
    onChange(info: any) {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList)
      }
      if (info.file.status === 'done') {
        if (info.file.response.code === 'SUCCESS') {
          message.success(`${info.file.name} 上传成功`)
          setUploadPath(info.file.response.data)
        } else {
          message.error(`${info.file.name} 上传失败`)
        }
      } else if (info.file.status === 'error') {
        message.error(`${info.file.name} 上传失败`)
      }
    },
  }

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
    },
    {
      title: '编号',
      dataIndex: 'number',
    },
    {
      title: '创建时间',
      dataIndex: 'createDate',
    },
    {
      title: '完税凭证',
      dataIndex: 'taxReceive',
    },
    {
      title: '月份',
      dataIndex: 'month',
      render: (text: any, record: any) => <Badge status="processing" text="等待审核"></Badge>,
    },
    {
      title: '备注信息',
      dataIndex: 'remark',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '操作',
      render: (text: any, record: any) => {
        return (
          <Popconfirm title="请确认是否删除" onConfirm={() => checkRecharge(record.id)} okText="确认" cancelText="取消">
            <Button type="primary">删除</Button>
          </Popconfirm>
        )
      },
    },
  ]

  const checkRecharge = async(id: string) => {
    try {
      await reviewTax(id);
      fetchBillInfo()
    } catch (error) {
      handleError(error)
    }
  }

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
      <Table bordered rowKey="id" columns={columns as any} dataSource={tableData} />
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
