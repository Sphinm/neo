import React, { useEffect, useState } from 'react'
import { Card, Select, Button, Space, message, Upload, Table } from 'antd'
import { taskOptions } from '@/setting/constantVar'
import { CloudUploadOutlined } from '@ant-design/icons'
import style from './index.styl'
import { downloadExcel } from '@/libs/download-excel'

export const UploadPayrollList = () => {
  const [task, setTask] = useState('')
  const [tableData, setTableData] = useState<any>([])
  const [loading, setLoading] = useState<boolean>(false)

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
    {
      title: '操作',
      key: 'task',
      align: 'center',
      render: (text: any, record: any) => {
        return (
          <>
            <Button
              type="link"
              onClick={() => {
                handleSelectRow(record)
              }}
            >
              提醒签约
            </Button>
            <Button
              type="link"
              onClick={() => {
                handleSelectRow(record)
              }}
            >
              申请发放
            </Button>
            <Button
              type="link"
              onClick={() => {
                handleSelectRow(record)
              }}
            >
              删除
            </Button>
          </>
        )
      },
    },
  ]

  useEffect(() => {
    fetchRecords()
  }, [])

  const fetchRecords =  async () => {
    try {
      setLoading(true)
      // const { data } = await fetchUnSignUpList()
      setTableData([])
    } catch (error) {
      // handleError(error)
    } finally {
      setLoading(false)
    }
  }

  const handleSelectRow = (record: any) => {
    console.log('handleSelectRow', record)
  }


  const downLoadReport = async () => {
    downloadExcel(tableData, )
  }

  const changeTask = (value: string) => {
    setTask(value)
  }

  return (
    <>
      <Card className={style['header-payroll']}>
        <div className={style['left-btn']}>
          <Space size="middle">
            <Button type="default" onClick={downLoadReport}>
              下载模板
            </Button>

            <Select allowClear placeholder="请先选择一个任务" onChange={changeTask}>
              {taskOptions.map(item => (
                <Select.Option key={item} value={item}>
                  {item}
                </Select.Option>
              ))}
            </Select>

            <Upload {...props}>
              <Button disabled={!task?.length} type="primary" icon={<CloudUploadOutlined />}>
                上传发放列表
              </Button>
            </Upload>
          </Space>
        </div>
        <div className={style['right-text']}>
          {textMessage.map(item => {
            return (
              <div key={item} className={style['item-text']}>
                {item}
              </div>
            )
          })}
        </div>
      </Card>
      <Card style={{ marginTop: 20 }}>
        <Table rowKey="name" loading={loading} columns={columns as any} dataSource={tableData} />
      </Card>
    </>
  )
}
