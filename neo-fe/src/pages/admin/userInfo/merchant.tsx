import React, { useEffect, useState } from 'react'
import { Card, Table, Popconfirm, Button, Divider, Modal, Input, Form, Row, Col, Spin, notification } from 'antd'
import { createNewUser, fetchMerchant, updateMerchant, deleteMerchant } from "@/apis/user"
import { AuthType } from '@/enums/role'
import { handleError } from '@/libs/axios'
import styles from './index.styl'

export const Merchant = () => {
  const [formConpany] = Form.useForm()
  const [formUser] = Form.useForm()
  const [visible, setVisible] = useState(false)
  const [isEdit, setEdit] = useState(false)
  const [isLoaded, setLoaded] = useState<boolean>(false)
  const [tableData, setTableData] = useState([])
  const [selectValue, setSelectValue] = useState<any>();

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
    },
    {
      title: '代理商公司',
      dataIndex: 'companyName',
    },
    {
      title: '代理商地址',
      dataIndex: 'companyLocation',
    },
    {
      title: '联系人',
      dataIndex: 'contactName',
    },
    {
      title: '联系人电话',
      dataIndex: 'contactTel',
    },
    {
      title: '费率',
      dataIndex: 'companyRate',
    },
    {
      title: '账号',
      render: (text: any, record: any) => <div>{record.userInfo.mobile}</div>,
    },
    {
      title: '操作',
      render: (text: any, record: any) => {
        return (
          <>
            <Button type="link" onClick={() => changeMerchant(record)}>
              编辑
            </Button>

            <Popconfirm
              title="将该代理商从列表中移除"
              onConfirm={() => deleteMerchantFn(record.id)}
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

  useEffect(() => {
    fetchMerchantInfo()
  }, [])

  const fetchMerchantInfo = async() => {
    try {
      setLoaded(true)
      const { data } = await fetchMerchant(AuthType.MERCHANT);
      const newData = data.map((item: { companyInfo: any; userInfo: any }) => {
        return {
          ...item.companyInfo,
         userInfo: item.userInfo,
        }
      })
      setTableData(newData)
    } catch (error) {
      
    } finally {
      setLoaded(false)
    }
  }

  const changeMerchant = (values: any) => {
    setSelectValue(values)
    formConpany.setFieldsValue({
      contactName: values.contactName,
      contactTel: values.contactTel,
      companyName: values.companyName,
      companyTax: values.companyTax,
      companyFixedTel: values.companyFixedTel,
      companyRate: values.companyRate,
      companyIndustry: values.companyIndustry,
      companyLocation: values.companyLocation,
      recipientName: values.recipientName,
      recipientTel: values.recipientTel,
      companyBankName: values.companyBankName,
      companyBankNumber: values.companyBankNumber,
      recipientAddress: values.recipientAddress,
    })
    formUser.setFieldsValue({
      username: values.userInfo.username,
      mobile: values.userInfo.mobile,
      // password: values.userInfo.password,
      email: values.userInfo.email,
    })
    setVisible(true)
    setEdit(true)
  }

  const deleteMerchantFn = async(id: any) => {
    try {
      await deleteMerchant(id)
      fetchMerchantInfo()
    } catch (error) {
      handleError(error)
    }
  }

  const handleOk = async () => {
    const companyInfo = await formConpany.validateFields()
    const userInfo = await formUser.validateFields()
    try {
      if (isEdit) {
        const params = {
          userInfo: { ...selectValue?.userInfo, ...userInfo },
          companyInfo: { ...selectValue, ...companyInfo },
        }
        console.log(params)
        await updateMerchant(params, AuthType.MERCHANT)
      } else {
        const params = {
          userInfo,
          companyInfo,
        }
        await createNewUser(params, AuthType.MERCHANT)
      }
      fetchMerchantInfo()
    } catch (error) {
      notification.error({
        message: "用户手机号已存在",
      })
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
        <Table bordered rowKey="id" columns={columns as any} dataSource={tableData} />
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
              <Form.Item label="用户密码" name="password">
                <Input placeholder="请输入密码，不填默认为 123456"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="用户邮箱" name="email">
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
