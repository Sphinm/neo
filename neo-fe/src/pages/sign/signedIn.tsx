import React, { useEffect, useState } from 'react'
import { Card, Table, Button, Input, Space, Modal, Form, message } from 'antd'
import { downloadExcel } from '@/libs/download-excel'
import { fetchSignUpList,searchByIdCard, changeMobileEmp } from '@/apis/compnay'

const { Search } = Input

export const SignedIn = () => {
  const [form] = Form.useForm()
  const [visible, setVisible] = useState(false)
  const [tableData, setTableData] = useState<any>([])
  const [loading, setLoading] = useState<boolean>(false)

  useEffect(() => {
    fetchRecords()
  }, [])

  const fetchRecords =  async () => {
    try {
      setLoading(true)
      const { data } = await fetchSignUpList()
      setTableData(data)
    } catch (error) {
      
    } finally {
      setLoading(false)
    }
  }

  const columns = [
    {
      title: '编号',
      dataIndex: 'serialNumber',
    },
    {
      title: '姓名',
      dataIndex: 'name',
    },
    {
      title: '身份证号',
      dataIndex: 'idVerify',
    },
    {
      title: '手机号',
      dataIndex: 'tel',
    },
    {
      title: '实名认证',
      dataIndex: 'idCheck',
      render: (text: any, record: any) => <>{text ? '已认证' : '未认证'}</>,
    },
    {
      title: '操作',
      align: 'center',
      render: (text: any, record: any) => {
        return (
          <>
            <Button type="link" onClick={() => setVisible(true)}>
              修改
            </Button>
            <Button type="link" onClick={() => downLoadReportItem}>
              下载合同
            </Button>
          </>
        )
      },
    },
  ]

  const downLoadReportItem = () => {
    downloadExcel(tableData)
  }

  const downLoadReport = () => {
    downloadExcel(tableData)
  }

  const handleOk = async () => {
    const values = await form.validateFields()
    try {
      await changeMobileEmp(values)
      message.success("修改成功")
      fetchRecords()
      setVisible(false)
    } catch (error) {
    }
  }

  const searchEmployee = async (value: string) => {
    try {
      const { data } = await searchByIdCard(value)
      setTableData(data)
    } catch (error) {
    }
  }

  return (
    <>
      <Card title="已签约人员">
        <Space size="middle" style={{ marginBottom: 50 }}>
          <Search placeholder="身份证" enterButton="搜索" onSearch={value => searchEmployee(value)} />
          <Button type="primary" onClick={downLoadReport}>
            导出表格
          </Button>
        </Space>
        <Table bordered loading={loading} rowKey="serialNumber" columns={columns as any} dataSource={tableData} />
      </Card>
      <Modal
        title="修改手机号"
        visible={visible}
        onOk={handleOk}
        onCancel={() => setVisible(false)}
        footer={[
          <Button key="back" onClick={() => setVisible(false)}>
            取消
          </Button>,
          <Button key="submit" type="primary" onClick={handleOk}>
            提交
          </Button>,
        ]}
      >
        <Form form={form}>
          <Form.Item label="旧手机号" name="oldPhone">
            <Input type="number" placeholder="请输入旧手机号"></Input>
          </Form.Item>
          <Form.Item label="新手机号" name="newPhone">
            <Input type="number" placeholder="请输入新的手机号"></Input>
          </Form.Item>
        </Form>
      </Modal>
    </>
  )
}
