import React from 'react'
import { Card, Select, Button, Space, message, Upload } from 'antd'
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
      <Card>xxxx</Card>
    </>
  )
}
