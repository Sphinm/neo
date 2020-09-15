import React from 'react'
import { Card, Select, Button, Space, message, Upload, Table } from 'antd'
import { taskOptions } from '@/setting/constantVar'
import { CloudUploadOutlined } from '@ant-design/icons'
import style from './index.styl'

export const UploadPayrollList = () => {
  const props = {
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

  const textMessage = [
    '1. 请按照模版所示的说明填写并上传，不符合格式无法提交;',
    '2. 发放人数超过 1000 人可能会有较长的等待时间;',
    '3. 上传完成后，列表中的人员会依次通过系统的风控审核，请耐心等待审核完成后发放。',
  ]

  const columns = [
    {
      title: '订单号',
      dataIndex: 'name',
      key: 'name',
    },
    {
      title: '申请时间',
      dataIndex: 'age',
      key: 'age',
    },
    {
      title: '发放金额',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '订单状态',
      key: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '任务',
      key: 'task',
      render: (text: any, record: any) => <div>绑定任务</div>,
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

  return (
    <>
      <Card className={style['header-payroll']}>
        <div className={style['left-btn']}>
          <Space size="middle">
            <Button type="default">下载模板</Button>
            <Select allowClear placeholder="请先选择一个任务">
              {taskOptions.map(item => (
                <Select.Option key={item} value={item}>
                  {item}
                </Select.Option>
              ))}
            </Select>
            <Upload {...props}>
              <Button type="primary" icon={<CloudUploadOutlined />}>
                上传发放列表
              </Button>
            </Upload>
          </Space>
        </div>
        <div className={style['right-text']}>
          {textMessage.map(item => {
            return <div className={style['item-text']}>{item}</div>
          })}
        </div>
      </Card>
      <Card style={{ marginTop: 20 }}>
        <Table rowKey="name" columns={columns} dataSource={data} />
      </Card>
    </>
  )
}
