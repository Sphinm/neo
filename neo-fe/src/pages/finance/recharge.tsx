import React, { useEffect, useState } from 'react'
import { Card, Form, Input, Button, Upload, Divider, Descriptions, message } from 'antd'
import { LoadingOutlined, PlusOutlined } from '@ant-design/icons'
import styles from './index.styl'
import { Link } from 'react-router-dom'
import { companyCharge, fetchCompanyChargeInfo } from '@/apis/compnay'
import { handleError } from '@/libs/axios'
import { fetchMerchantBalance } from '@/apis/merchant'
import { beforeUpload } from '@/libs/utils'

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
}

export const Recharge = () => {
  const [form] = Form.useForm()
  const [loading, setLoading] = useState(false)
  const [imageUrl, setImageUrl] = useState('')
  const [totalMoney, setTotalMoney] = useState(0)
  const [companyInfo, setCompanyInfo] = useState<any>({})
  const [balance, setBalance] = useState(0)

  useEffect(() => {
    fetchCompanyInfo()
    fetchBalanceMoney()
  }, [])

  const fetchCompanyInfo = async () => {
    try {
      const { data } = await fetchCompanyChargeInfo()
      setCompanyInfo(data)
    } catch (error) {
      handleError(error)
    }
  }

  const fetchBalanceMoney = async () => {
    try {
      const { data } = await fetchMerchantBalance()
      setBalance(data)
    } catch (error) {
      handleError(error)
    }
  }
  const onFinish = async (values: any) => {
    if (!values.amount) return
    setLoading(true)
    try {
      const body = {
        amount: Number(values.amount),
        virtualPath: imageUrl,
      }
      const {data} = await companyCharge(body)
      message.success(data)
      window.location.reload()
    } catch (error) {
      handleError(error)
    } finally {
      setLoading(false)
    }
  }

  const handleChange = (info: any) => {
    if (info.file.status === 'uploading') {
      setLoading(true)
      return
    }
    if (info.file.status === 'done') {
      if (info.file.response.code === 'SUCCESS') {
        setImageUrl(info.file.response.data)
        message.success(`${info.file.name} 上传成功`)
      } else {
        message.error(`${info.file.name} 上传失败`)
      }
      setLoading(false)
    } else if (info.file.status === 'error') {
      message.error(`${info.file.name} 上传失败`)
      setLoading(false)
    }
  }

  const uploadButton = (
    <div>
      {loading ? <LoadingOutlined /> : <PlusOutlined />}
      <div style={{ marginTop: 8 }}>上传凭证</div>
    </div>
  )

  const handleChangeMoney = (value: any) => {
    form.setFieldsValue({ leftmoney: value - value * companyInfo.companyRate * 0.01 })
    setTotalMoney(value - value * companyInfo.companyRate * 0.01  + balance)
  }

  const options = {
    name: "file",
    className: "avatar-uploader",
    listType: "picture-card" as any,
    showUploadList: false,
    action: '/api/upload/tax',
    accept: "image/png, image/jpeg",
    data: {
      path: "charge"
    },
    headers: {
      'Authorization': `Bearer ` + localStorage.getItem('token'),
    },
    beforeUpload: beforeUpload,
    onChange: handleChange
  }

  return (
    <Card title="充值">
      <div className={styles['recharge']}>
        <Form className={styles['recharge-form']} form={form} {...layout} onFinish={onFinish}>
          <Form.Item label="当前可用余额" name="balance">
            <div style={{ textAlign: 'left' }}>￥{balance.toFixed(2)}</div>
          </Form.Item>
          <Form.Item label="打款金额" name="amount" rules={[{ required: true, message: '请输入实际打款金额' }]}>
            <Input allowClear placeholder="打款金额（元）" onChange={e => handleChangeMoney(e.target.value)} />
          </Form.Item>
          <Form.Item label="费率" name="rate">
            <span style={{ fontSize: 20, fontWeight: 500 }}>{companyInfo.companyRate}%</span>
          </Form.Item>
          <Form.Item
            label="打款凭证"
            name="credit"
            valuePropName="credit"
            rules={[{ required: true, message: '请上传打款凭证' }]}
          >
            <Upload {...options}>
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
            <span style={{ fontSize: 20, fontWeight: 500 }}>{totalMoney.toFixed(2)}</span>
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
