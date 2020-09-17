import React, { useState } from 'react'
import { Card, Form, Input, Button, Upload, message, Divider, Descriptions, Checkbox } from 'antd'
import { LoadingOutlined, PlusOutlined } from '@ant-design/icons'
import styles from './index.styl'
import { Link } from 'react-router-dom'

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
}

export const AddCustomer = () => {
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
    const total = value.length && value.reduce((pre: any, cur: any) => Number(pre) + Number(cur))
    setTotalMoney(total)
  }

  return (
    <Card title="个人独资企业">
      <div className={styles['recharge']}>
        <Form className={styles['recharge-form']} form={form} {...layout} onFinish={onFinish}>
          <Form.Item label="客户姓名" name="username" rules={[{ required: true, message: '请输入客户姓名' }]}>
            <Input allowClear placeholder="客户姓名）" />
          </Form.Item>
          <Form.Item label="客户手机号" name="rate">
            <Input allowClear placeholder="客户手机号）" />
          </Form.Item>
          <Form.Item label="选择产品" name="product">
            <Checkbox.Group onChange={handleChangeMoney}>
              <Checkbox value="4500">小规模 = ￥4500</Checkbox>
              <br />
              <Checkbox value="8800">一般纳税人 = ￥8800</Checkbox>
            </Checkbox.Group>
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
          <Form.Item label="费用(总计)" name="totalmoney">
            <span style={{ fontSize: 20, fontWeight: 500 }}>￥{totalMoney}</span>
          </Form.Item>
          <Form.Item style={{ marginLeft: 200 }}>
            <Button type="primary" htmlType="submit">
              提交
            </Button>
          </Form.Item>
        </Form>
        <Divider />
        <div className={styles['recharg-desc']}>
          <Descriptions title="打款账户">
            <Descriptions.Item label="账户名称">上海财联信息科技有限公司</Descriptions.Item>
            <Descriptions.Item label="开户行">中国工商银行股份有限公司上海市共江路支行</Descriptions.Item>
            <Descriptions.Item label="银行账户">1001036809000048145</Descriptions.Item>
            <Descriptions.Item label="支付宝账号">yqyunshui@yqyunshui.com</Descriptions.Item>
          </Descriptions>
        </div>
      </div>
    </Card>
  )
}
