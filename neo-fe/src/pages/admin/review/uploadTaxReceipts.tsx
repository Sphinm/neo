import React, { useEffect, useState } from 'react'
import { CloudUploadOutlined } from '@ant-design/icons'
import { Card, Table, Button, Popconfirm, Divider, Modal, Form, Input, Upload, message, Select, DatePicker, Image } from 'antd'
import { handleError } from '@/libs/axios'
import { fetchReviewTax, reviewTax, uploadTaxInfo } from '@/apis/review'
import { fetchCompany } from '@/apis/user'
import locale from 'antd/lib/date-picker/locale/zh_CN'
import moment from 'moment'
import { beforeUpload } from '@/libs/utils'

const { Option } = Select

export const UploadTaxReceipts = () => {
  const [form] = Form.useForm()
  const [visible, setVisible] = useState(false)
  const [tableData, setTableData] = useState<any>([])
  const [companyList, setCompanyList] = useState<any[]>([])
  const [uploadPath, setUploadPath] = useState('')
  
  useEffect(() => {
    fetchBillInfo()
    if (!localStorage.getItem('companyList')) {
      fetchAllCompanyList()
    } else {
      setCompanyList(JSON.parse(localStorage.getItem('companyList') as any))
    }
  }, []);

  const fetchAllCompanyList = async () => {
    try {
      const { data } = await fetchCompany()
      setCompanyList(data)
      localStorage.setItem('companyList', JSON.stringify(data))
    } catch (error) {
      handleError(error)
    }
  }

  const fetchBillInfo = async() => {
    try {
      const { data } = await fetchReviewTax()
      setTableData(data)
    } catch (error) {
      handleError(error)
    }
  }

  const options = {
    name: 'file',
    action: '/api/upload/tax',
    accept: "image/png, image/jpeg",
    data: {
      path: "tax"
    },
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
      title: '编号',
      dataIndex: 'number',
    },
    {
      title: '公司名称',
      dataIndex: 'companyName',
    },
    {
      title: '完税凭证',
      dataIndex: 'taxReceive',
      render: (text: any, record: any) => <Image width={80} src={text}></Image>,
    },
    {
      title: '月份',
      dataIndex: 'month',
      render: (text: any, record: any) => <>{moment(text).format('YYYY-MM')}</>,
    },
    {
      title: '备注信息',
      dataIndex: 'remark',
    },
    {
      title: '创建时间',
      dataIndex: 'createDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
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
    if (!uploadPath) {
      message.error('请先上传完税凭证')
      return
    }
    const params = {
      companyId: values.company,
      month: values.month,
      remarks: values.remarks,
      receipts: uploadPath,
    }
    try {
      const { code } = await uploadTaxInfo(params)
      if (code === 'SUCCESS') {
        message.success('上传完税凭证信息成功')
        setVisible(false)
        fetchBillInfo()
      }
    } catch (error) {
      handleError(error)
    }
  }

  return (
    <Card title="上传完税凭证">
      <Button type="primary" onClick={uploadTaxReceipts}>
        上传完税凭证
      </Button>
      <Divider />
      <Table bordered rowKey="companyId" columns={columns as any} dataSource={tableData} />
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
          <Form.Item label="公司名称" name="company" rules={[{ required: true, message: '请填写公司名称' }]}>
            <Select allowClear placeholder="公司名称">
            {companyList.map((item: any) => (
                <Option key={item.id} value={item.id}>
                  {item.companyName}
                </Option>
              ))}
            </Select>
          </Form.Item>
          <Form.Item label="月份" name="month" rules={[{ required: true, message: '请填写月份' }]}>
            <DatePicker placeholder="请输入月份" locale={locale} format={'YYYY/MM'} picker="month" />
          </Form.Item>
          <Form.Item label="备注" name="remarks">
            <Input placeholder="请输入备注信息"></Input>
          </Form.Item>
        </Form>
      </Modal>
    </Card>
  )
}
