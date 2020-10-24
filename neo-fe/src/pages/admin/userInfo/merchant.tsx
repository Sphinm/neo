import React, { useEffect, useState } from 'react'
import { Card, Table, Popconfirm, Badge, Button, Divider, Modal, Input, Form, Row, Col, Spin } from 'antd'
import { createNewUser, fetchMerchant } from "@/apis/user"
import { AuthType } from '@/enums/role'
import { handleError } from '@/libs/axios'
import styles from './index.styl'

export const Merchant = () => {
  const [formConpany] = Form.useForm()
  const [formUser] = Form.useForm()
  const [visible, setVisible] = useState(false)
  const [isEdit, setEdit] = useState(false)
  const [isLoaded, setLoaded] = useState<boolean>(false)

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: '代理商公司',
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

  useEffect(() => {
    fetchMerchantInfo()
  }, [])

  const fetchMerchantInfo = async() => {
    try {
      setLoaded(true)
      const { data } = await fetchMerchant(AuthType.MERCHANT);
      console.log('1312312312',data)
    } catch (error) {
      
    } finally {
      setLoaded(false)
    }
  }

  const changeMerchant = (values: any) => {
    formConpany.setFieldsValue({
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
    const companyInfo = await formConpany.validateFields()
    const userInfo = await formUser.validateFields()
    console.log(11, userInfo, companyInfo)
    try {
      const params = {
        userInfo,
        companyInfo,
      }
      const { data } = await createNewUser(params, AuthType.MERCHANT)
      console.log(222, data)
      // 查询 merchant 列表
    } catch (error) {
      handleError(error)
    }
    setVisible(false)
  }

  const createMerchant = () => {
    setVisible(true)
    setEdit(false)
    formUser.resetFields()
    formConpany.resetFields()
  }

  return (
    <Spin spinning={isLoaded}>
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
        <h1>账户信息</h1>
        <Form form={formUser}>
          <Row gutter={24}>
            <Col span={8}>
              <Form.Item label="用户名" name="username" rules={[{ required: true, message: '请输入用户名' }]}>
                <Input placeholder="请输入用户名"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="登录手机号" name="mobile" rules={[{ required: true, message: '请输入手机号' }]}>
                <Input placeholder="请输入手机号"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="用户邮箱" name="email" rules={[{ required: true, message: '请输入邮箱' }]}>
                <Input placeholder="请输入邮箱"></Input>
              </Form.Item>
            </Col>
          </Row>
        </Form>
        <Divider />
        <h1>公司信息</h1>
        <Form form={formConpany} className={styles['conpany-form']}>
          <Row gutter={24}>
            <Col span={8}>
              <Form.Item label="对接人" name="contactName" rules={[{ required: true, message: '请输入对接人' }]}>
                <Input placeholder="请输入对接人"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="对接人手机号" name="contactTel" rules={[{ required: true, message: '请输入对接人手机号' }]}>
                <Input placeholder="请输入对接人手机号"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="公司名称" name="companyName" rules={[{ required: true, message: '请输入公司名称' }]}>
                <Input placeholder="请输入公司名称"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="公司税号" name="companyTax" rules={[{ required: true, message: '请输入公司税号' }]}>
                <Input placeholder="请输入公司税号"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="公司固话"
                name="companyFixedTel"
                rules={[{ required: true, message: '请输入公司固定电话' }]}
              >
                <Input placeholder="请输入公司固定电话"></Input>
              </Form.Item>
            </Col>

            <Col span={8}>
              <Form.Item label="费率" name="companyRate" rules={[{ required: true, message: '请输入费率' }]}>
                <Input placeholder="请输入费率"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="所属行业" name="companyIndustry" rules={[{ required: true, message: '请输入所属行业' }]}>
                <Input placeholder="请输入所属行业"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="公司地址" name="companyLocation" rules={[{ required: true, message: '请输入公司地址' }]}>
                <Input placeholder="请输入公司地址"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="收件人" name="recipientName" rules={[{ required: true, message: '请输入收件人姓名' }]}>
                <Input placeholder="请输入收件人姓名"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="收件人手机号"
                name="recipientTel"
                rules={[{ required: true, message: '请输入收件人手机号' }]}
              >
                <Input placeholder="收件人手机号"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="开户行" name="companyBankName" rules={[{ required: true, message: '请输入开户行' }]}>
                <Input placeholder="请输入开户行"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="银行账号" name="companyBankNumber" rules={[{ required: true, message: '请输入银行账号' }]}>
                <Input placeholder="请输入银行账号"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="收件地址" name="recipientAddress" rules={[{ required: true, message: '请输入收件人地址' }]}>
                <Input placeholder="请输入收件人地址"></Input>
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    </Spin>
  )
}
