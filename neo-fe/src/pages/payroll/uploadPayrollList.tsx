import React, { useEffect, useState } from 'react'
import { Card, Select, Button, Space, message, Upload, Table, Badge, Modal } from 'antd'
import { taskOptions } from '@/setting/constantVar'
import { CloudUploadOutlined } from '@ant-design/icons'
import style from './index.styl'
import { deleteProvideItem, fetchProvideList } from '@/apis/compnay'
import moment from 'moment'
import history from '@/libs/history'

export const UploadPayrollList = () => {
  const [task, setTask] = useState('')
  const [tableData, setTableData] = useState<any>([])
  const [loading, setLoading] = useState<boolean>(false)
  const [visible, setVisible] = useState(false)
  const [provideItem, setProvideItem] = useState<any>()

  const props = {
    name: 'provideExcel',
    action: '/api/provide/upload-provide-list',
    data: {
      taskName: task,
    },
    accept: ".xls, .xlsx",
    showUploadList: false,
    headers: {
      'Authorization': `Bearer ` + localStorage.getItem('token'),
    },
    onChange(info: any) {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList)
      }
      if (info.file.status === 'done') {
        if (info.file.response.code === 'SUCCESS') {
          message.success(`${info.file.name} 上传成功`)
          // setUploadPath(info.file.response.data)
        } else {
          message.error(`${info.file.response.message}`)
        }
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
      dataIndex: 'orderNumber',
    },
    {
      title: '申请时间',
      dataIndex: 'createDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
    },
    {
      title: '发放金额',
      dataIndex: 'amount',
    },
    {
      title: '发放状态',
      dataIndex: 'status',
      render: (text: any, record: any) => <Badge status={text ? 'success' : 'processing'} text={text ? '发放完成' : '等待发放'}></Badge>,
    },
    {
      title: '任务',
      dataIndex: 'taskName',
    },
    {
      title: '操作',
      align: 'center',
      render: (text: any, record: any) => {
        return (
          <>
            <Button
              type="link"
              onClick={() => history.push('/main/finance/recharge')}
            >
              充值
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
      const { data } = await fetchProvideList()
      setTableData(data)
    } catch (error) {
    } finally {
      setLoading(false)
    }
  }

  const handleSelectRow = (record: any) => {
    setVisible(true)
    setProvideItem(record)
  }

  const changeTask = (value: string) => {
    setTask(value)
  }

  const handleOk = async () => {
    try {
      await deleteProvideItem(provideItem.id)
      message.success("删除成功")
      fetchRecords()
      setVisible(false)
    } catch (error) {
    }
  }


  return (
    <>
      <Card className={style['header-payroll']}>
        <div className={style['left-btn']}>
          <Space size="middle">
            <Button type="default">
              <a download="发放模板.xlsx" href="/file/发放模板.xlsx">下载模板</a>
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
        <Table rowKey="orderNumber" loading={loading} columns={columns as any} dataSource={tableData} />
      </Card>
      <Modal
        title="删除订单"
        visible={visible}
        onOk={handleOk}
        onCancel={() => setVisible(false)}
        footer={[
          <Button key="back" onClick={() => setVisible(false)}>
            取消
          </Button>,
          <Button key="submit" type="primary" onClick={handleOk}>
            确定
          </Button>,
        ]}
      >
        是否删除该订单？
      </Modal>
    </>
  )
}
