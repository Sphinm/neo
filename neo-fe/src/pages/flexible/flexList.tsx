import React, { useEffect, useState } from 'react'
import { Card, Table, Badge, Input, Divider, Button, Form, Spin, Modal, Row, Col } from 'antd'
import { AuthType } from '@/enums/role'
import { createNewUser } from '@/apis/user'
import { handleError } from '@/libs/axios'
import { fetchMerchantCreateCompany, searchByCompanyName } from '@/apis/merchant'
import moment from 'moment'

const { Search } = Input

export const FlexList = () => {
  const [formConpany] = Form.useForm()
  const [formUser] = Form.useForm()
  const [visible, setVisible] = useState(false)
  // const [isEdit, setEdit] = useState(false)
  const [isLoaded, setLoaded] = useState<boolean>(false)
  const [tableData, setTableData] = useState([])

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
      title: '联系电话',
      dataIndex: 'contactTel',
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
      title: '费率',
      dataIndex: 'companyRate',
    },
    {
      title: '收件人',
      dataIndex: 'recipientName',
    },
    {
      title: '收件电话',
      dataIndex: 'recipientTel',
    },
    {
      title: '收件地址',
      dataIndex: 'recipientAddress',
    },
    {
      title: '创建时间',
      dataIndex: 'createDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
    },
    {
      title: '状态',
      dataIndex: 'companyStatus',
      render: (text: any, record: any) => <Badge status={text ? 'success' : 'processing'} text={text ? '审核通过' : '等待审核'}></Badge>,
    },
    // {
    //   title: '操作',
    //   key: 'task',
    //   render: (text: any, record: any) => {
    //     return (
    //       <>
    //         <Button type="link" onClick={() => changeMerchant(record)}>
    //           编辑
    //         </Button>

    //       </>
    //     )
    //   },
    // },
  ]


  useEffect(() => {
    fetchCompanyInfo()
  }, [])

  const fetchCompanyInfo = async() => {
    try {
      setLoaded(true)
      const { data } = await fetchMerchantCreateCompany();
      setTableData(data)
    } catch (error) {
      handleError(error)
    } finally {
      setLoaded(false)
    }
  }

  const createCompany = () => {
    setVisible(true)
    // setEdit(false)
    formUser.resetFields()
    formConpany.resetFields()
  }

  const handleOk = async () => {
    const companyInfo = await formConpany.validateFields()
    const userInfo = await formUser.validateFields()
    try {
      const params = {
        userInfo,
        companyInfo,
      }
      await createNewUser(params, AuthType.COMPANY)
      fetchCompanyInfo()
    } catch (error) {
      handleError(error)
    }
    setVisible(false)
  }


  const searchByName = async (name: string) => {
    try {
      setLoaded(true)
      const { data } = await searchByCompanyName(name)
      setTableData(data)
     } catch (error) {
      handleError(error)
    } finally {
      setLoaded(false)
    }
  }


  return (
    <Spin spinning={isLoaded}>
    <Card title="灵活用工客户列表">
      <Search
        style={{ width: 300 }}
        placeholder="请输入公司名称"
        enterButton="搜索"
        onSearch={value => searchByName(value)}
      />
      <Button style={{ marginLeft: 30 }} type="primary" onClick={createCompany}>
          新增客户
      </Button>
      <Divider />
      <Table bordered rowKey="id" columns={columns as any} dataSource={tableData} />
    </Card>
    <Modal
    getContainer={false}
    title={'创建公司信息'}
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
