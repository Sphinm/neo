import React, { useEffect, useState } from 'react'
import { Card, Table, Popconfirm, Button, Divider, Modal, Input, Form, Spin, Row, Col } from 'antd'
import { AuthType } from '@/enums/role'
import { createNewUser, fetchMerchant, deleteMerchant, updateMerchant } from '@/apis/user'
import { handleError } from '@/libs/axios'

export const Company = () => {
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
      title: '公司名称',
      dataIndex: 'companyName',
    },
    {
      title: '公司税号',
      dataIndex: 'companyTax',
    },
    {
      title: '公司地址',
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
      title: '开户行',
      dataIndex: 'companyBankName',
    },
    {
      title: '银行账号',
      dataIndex: 'companyBankNumber',
    },
    {
      title: '账号',
      render: (text: any, record: any) => <div>{record.userInfo.mobile}</div>,
    },
    {
      title: '绑定任务',
      dataIndex: 'task',
    },
    {
      title: '收件人',
      dataIndex: 'recipientName',
    },
    {
      title: '收件人电话',
      dataIndex: 'recipientTel',
    },
    {
      title: '收件人地址',
      dataIndex: 'recipientAddress',
    },
    {
      title: '操作',
      key: 'operation',
      fixed: 'right',
      render: (text: any, record: any) => {
        return (
          <>
            <Button type="link" onClick={() => changeCompay(record)}>
              编辑
            </Button>

            <Popconfirm
              title="将该公司从列表中移除"
              onConfirm={() => deleteCompanyFn(record.id)}
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
    fetchCompanyInfo()
  }, [])

  const fetchCompanyInfo = async() => {
    try {
      setLoaded(true)
      const { data } = await fetchMerchant(AuthType.COMPANY);
      const newData = data.map((item: { companyInfo: any; userInfo: any }) => {
        return {
          ...item.companyInfo,
         userInfo: item.userInfo,
        }
      })
      setTableData(newData)
    } catch (error) {
      handleError(error)
    } finally {
      setLoaded(false)
    }
  }

  const changeCompay = (values: any) => {
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
      email: values.userInfo.email,
    })
    setVisible(true)
    setEdit(true)
  }

  const deleteCompanyFn = async(id: any) => {
    try {
      await deleteMerchant(id)
      fetchCompanyInfo()
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
        await updateMerchant(params, AuthType.COMPANY)
      } else {
        const params = {
          userInfo,
          companyInfo,
        }
        await createNewUser(params, AuthType.COMPANY)
      }
      fetchCompanyInfo()
    } catch (error) {
      handleError(error)
    }
    setVisible(false)
  }

  const createCompany = () => {
    setVisible(true)
    setEdit(false)
    formUser.resetFields()
    formConpany.resetFields()
  }

  return (
    <Spin spinning={isLoaded}>
      <Card title="公司档案">
        <Button type="primary" onClick={createCompany}>
          新增公司
        </Button>
        <Divider />
        <Table scroll={{ x: 2000 }} bordered rowKey="id" columns={columns as any} dataSource={tableData} />
      </Card>
      <Modal
        getContainer={false}
        title={isEdit ? '编辑公司信息' : '创建公司信息'}
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
        <Form form={formConpany}>
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
