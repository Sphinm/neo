import React, { useState } from 'react'
import { Card, Table, Button, Input, Space, Upload, message, Popconfirm, Modal } from 'antd'
import { downloadExcel } from '@/libs/download-excel'
import { CloudUploadOutlined } from '@ant-design/icons'
import styles from './index.styl'

const { Search } = Input

export const UnSigned = () => {
  const [selectedRowKeys, setSelectedRowKeys] = useState<string[]>([])
  const [visible, setVisible] = useState(false)

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
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '姓名',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '身份证号',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '手机号',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '实名认证',
      key: 'task',
      render: (text: any, record: any) => <div>绑定任务</div>,
    },
    {
      title: '操作',
      key: 'task',
      align: 'center',
      render: (text: any, record: any) => {
        return (
          <>
            <Button type="link" onClick={() => setVisible(true)}>
              修改
            </Button>
            <Popconfirm title="删除后，该员工将从列表中彻底移除" onConfirm={deleteItem} okText="确认" cancelText="取消">
              <Button type="link">删除</Button>
            </Popconfirm>
          </>
        )
      },
    },
  ]

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
    {
      name: 'Jim Green1',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    },
    {
      name: 'Joe Black1',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
      tags: ['cool', 'teacher'],
    },
    {
      name: 'John Brown2',
      age: 32,
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer'],
    },
    {
      name: 'Jim Green2',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    },
    {
      name: 'Joe Black2',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
      tags: ['cool', 'teacher'],
    },
    {
      name: 'John Brown3',
      age: 32,
      address: 'New York No. 1 Lake Park',
      tags: ['nice', 'developer'],
    },
    {
      name: 'Jim Green3',
      age: 42,
      address: 'London No. 1 Lake Park',
      tags: ['loser'],
    },
    {
      name: 'Joe Black3',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
      tags: ['cool', 'teacher'],
    },
  ]

  const deleteItem = (record: any) => {
    console.log('deleteItem', record)
  }

  const checkSign = () => {
    console.log(1121232)
  }

  const downLoadReport = async () => {
    downloadExcel(data)
  }

  const onSelectChange = (selectedRowKeys: any, selectedRows: any) => {
    setSelectedRowKeys(selectedRowKeys)
  }

  const rowSelection = {
    selectedRowKeys,
    onChange: onSelectChange,
  }

  const handleOk = () => {
    setVisible(false)
  }

  return (
    <>
      <Card title="待签约人员">
        <Space size="middle" style={{ marginBottom: 50 }}>
          <Button type="primary" disabled={!selectedRowKeys.length} onClick={checkSign}>
            提醒签约
          </Button>

          <Search placeholder="身份证" enterButton="搜索" onSearch={value => console.log(value)} />

          <Button type="default" onClick={downLoadReport}>
            下载模板
          </Button>

          <Upload {...options}>
            <Button type="primary" icon={<CloudUploadOutlined />}>
              上传发放列表
            </Button>
          </Upload>
        </Space>

        <Table rowSelection={rowSelection} bordered rowKey="name" columns={columns as any} dataSource={data} />
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
        <div className={styles['fix-mobile']}>
          <span>修改手机号: </span>
          <Input style={{ flex: 1, marginLeft: 20 }} placeholder="请输入手机号"></Input>
        </div>
      </Modal>
    </>
  )
}
