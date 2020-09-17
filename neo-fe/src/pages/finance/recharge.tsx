import React, { useState } from 'react'
import { Card, Form, Input, Button, Upload, message, Divider, Descriptions } from 'antd'
import { LoadingOutlined, PlusOutlined } from '@ant-design/icons'
import styles from './index.styl'
import { Link } from 'react-router-dom'

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
}

export const Recharge = () => {
  const [form] = Form.useForm()
  const [loading, setLoading] = useState(false)
  const [imageUrl, setImageUrl] = useState('')
  const [totalMoney, setTotalMoney] = useState(0)

  const onFinish = (values: any) => {
    console.log('onFinish:', values)
  }

  function beforeUpload(file: any) {
    const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png'
    if (!isJpgOrPng) {
      message.error('只能上传 JPG/PNG 类型图片!')
    }
    const isLt2M = file.size / 1024 / 1024 < 2
    if (!isLt2M) {
      message.error('Image must smaller than 2MB!')
    }
    return isJpgOrPng && isLt2M
  }

  const getBase64 = (img: Blob, callback: Function) => {
    const reader = new FileReader()
    reader.addEventListener('load', () => callback(reader.result))
    reader.readAsDataURL(img)
  }

  const handleChange = (info: any) => {
    if (info.file.status === 'uploading') {
      setLoading(true)
      return
    }
    if (info.file.status === 'done') {
      getBase64(info.file.originFileObj, (imageUrl: any) => {
        setImageUrl(imageUrl)
        setLoading(false)
      })
    }
  }

  const uploadButton = (
    <div>
      {loading ? <LoadingOutlined /> : <PlusOutlined />}
      <div style={{ marginTop: 8 }}>上传凭证</div>
    </div>
  )

  const handleChangeMoney = (value: any) => {
    // const rate = form.getFieldsValue(['rate'])
    form.setFieldsValue({ leftmoney: value - value * 0.1 })
    setTotalMoney(value - value * 0.1 + 0.2)
  }

  return (
    <Card title="充值">
      <div className={styles['recharge']}>
        <Form className={styles['recharge-form']} form={form} {...layout} onFinish={onFinish}>
          <Form.Item label="当前可用余额" name="username">
            <div style={{ textAlign: 'left' }}>￥0.20</div>
          </Form.Item>
          <Form.Item label="打款金额" name="username" rules={[{ required: true, message: '请输入实际打款金额' }]}>
            <Input allowClear placeholder="打款金额（元）" onChange={e => handleChangeMoney(e.target.value)} />
          </Form.Item>
          <Form.Item label="费率" name="rate">
            <span style={{ fontSize: 20, fontWeight: 500 }}>10%</span>
          </Form.Item>
          <Form.Item
            label="打款凭证"
            name="credit"
            valuePropName="credit"
            rules={[{ required: true, message: '请上传打款凭证' }]}
          >
            <Upload
              name="credit"
              listType="picture-card"
              className="avatar-uploader"
              showUploadList={false}
              action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
              beforeUpload={beforeUpload}
              onChange={handleChange}
            >
              {imageUrl ? <img src={imageUrl} alt="avatar" style={{ width: '100%' }} /> : uploadButton}
            </Upload>
          </Form.Item>
          <Link className={styles['muban']} target="_blank" to={require('@/assets/images/example.jpg')}>
            打款凭证模板图
          </Link>
          <Form.Item label="实际充值金额" name="leftmoney">
            <Input placeholder="打款金额（元）" disabled />
          </Form.Item>
          <Form.Item label="充值后可用余额" name="totalmoney">
            <span style={{ fontSize: 20, fontWeight: 500 }}>{totalMoney}</span>
          </Form.Item>
          <Form.Item style={{ marginLeft: 200 }}>
            <Button type="primary" htmlType="submit">
              提交
            </Button>
          </Form.Item>
        </Form>
        <Divider />
        <div className={styles['recharg-desc']}>
          <Descriptions title="说明">
            <Descriptions.Item>转账金额为实际打款金额，扣除费率后为充值金额。</Descriptions.Item>
          </Descriptions>
          <Descriptions title="打款账户">
            <Descriptions.Item label="公司名称">金华市云赋信息科技有限公司</Descriptions.Item>
            <Descriptions.Item label="开户行">中国农业银行金华金三角支行</Descriptions.Item>
            <Descriptions.Item label="银行账户">19655601040011135</Descriptions.Item>
          </Descriptions>
        </div>
      </div>
    </Card>
  )
}
