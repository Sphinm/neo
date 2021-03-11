import React, { useEffect, useState } from 'react'
import { Card, Table, Button, Input, Space, Upload, message, Popconfirm, Modal, Form } from 'antd'
import { downloadExcel } from '@/libs/download-excel'
import { CloudUploadOutlined } from '@ant-design/icons'
import { handleError } from '@/libs/axios'
import styles from './index.styl'
import { fetchUnSignUpList,searchByIdCard, changeMobileEmp } from '@/apis/compnay'
import { deleteEmployee } from '@/apis/user'

const { Search } = Input

export const UnSigned = () => {
  const [form] = Form.useForm()
  const [selectedRowKeys, setSelectedRowKeys] = useState<string[]>([])
  const [visible, setVisible] = useState(false)
  const [tableData, setTableData] = useState<any>([])
  const [loading, setLoading] = useState<boolean>(false)

  useEffect(() => {
    fetchRecords()
  }, [])

  const fetchRecords =  async () => {
    try {
      setLoading(true)
      const { data } = await fetchUnSignUpList()
      setTableData(data)
    } catch (error) {
      handleError(error)
    } finally {
      setLoading(false)
    }
  }

  const options = {
    name: 'file',
    action: 'https://www.mocky.io/v2/5cc8019d300000980a055e76',
    showUploadList: false,
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
            <Popconfirm title="删除后，该员工将从列表中彻底移除" onConfirm={() => deleteItem(record)} okText="确认" cancelText="取消">
              <Button type="link">删除</Button>
            </Popconfirm>
          </>
        )
      },
    },
  ]

  const deleteItem = async (record: any) => {
    try {
      await deleteEmployee(record.id)
      message.success("删除成功")
      fetchRecords()
    } catch (error) {
      handleError(error)
    }
  }

  const checkSign = () => {
    console.log(1121232)
  }

  const getColumnTitle = () => {
    return columns.map(item => {
      if (item?.dataIndex) {
        return item.title
      }
    }).filter(t => t)
  }

  const downLoadReport = async () => {
    console.log(11, getColumnTitle())
    downloadExcel(tableData, getColumnTitle())
  }

  const onSelectChange = (selectedRowKeys: any, selectedRows: any) => {
    setSelectedRowKeys(selectedRowKeys)
  }

  const rowSelection = {
    selectedRowKeys,
    onChange: onSelectChange,
  }

  const handleOk = async () => {
    const values = await form.validateFields()
    try {
      await changeMobileEmp(values)
      message.success("修改成功")
      fetchRecords()
      setVisible(false)
    } catch (error) {
      handleError(error)
    }
  }

  const searchEmployee = async (value: string) => {
    try {
      const { data } = await searchByIdCard(value)
      setTableData(data)
    } catch (error) {
      handleError(error)
    }
  }

  return (
    <>
      <Card title="待签约人员">
        <Space size="middle" style={{ marginBottom: 50 }}>
          <Button type="primary" disabled={!selectedRowKeys.length} onClick={checkSign}>
            提醒签约
          </Button>

          <Search placeholder="身份证" enterButton="搜索" onSearch={value => searchEmployee(value)} />

          <Button type="default" onClick={downLoadReport}>
            下载模板
          </Button>

          <Upload {...options}>
            <Button type="primary" icon={<CloudUploadOutlined />}>
              上传发放列表
            </Button>
          </Upload>
        </Space>

        <Table rowSelection={rowSelection} loading={loading} bordered rowKey="serialNumber" columns={columns as any} dataSource={tableData} />
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
        <Form className={styles['fix-mobile']} form={form}>
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
