import React from 'react'
import { Card, Form, Input, Button } from 'antd'
import styles from '../sole/index.styl'

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
}
export const FlexAddCustomer = () => {
  const [form] = Form.useForm()

  const onFinish = (values: any) => {
    console.log('onFinish:', values)
  }

  return (
    <Card title="新增灵活用工客户">
      <div className={styles['recharge']}>
        <Form className={styles['recharge-form']} form={form} {...layout} onFinish={onFinish}>
          <Form.Item label="公司名称" name="username" rules={[{ required: true, message: '请输入公司名称' }]}>
            <Input allowClear placeholder="请输入公司名称" />
          </Form.Item>
          <Form.Item label="公司税号" name="rate" rules={[{ required: true, message: '请输入公司税号' }]}>
            <Input allowClear placeholder="请输入公司税号" />
          </Form.Item>
          <Form.Item label="公司地址" name="username" rules={[{ required: true, message: '请输入公司地址' }]}>
            <Input allowClear placeholder="请输入公司地址" />
          </Form.Item>
          <Form.Item label="联系人" name="rate" rules={[{ required: true, message: '请输入联系人' }]}>
            <Input allowClear placeholder="请输入联系人" />
          </Form.Item>
          <Form.Item label="联系电话" name="username" rules={[{ required: true, message: '请输入联系电话' }]}>
            <Input allowClear placeholder="请输入联系电话" />
          </Form.Item>
          <Form.Item label="开户行" name="rate" rules={[{ required: true, message: '请输入开户行' }]}>
            <Input allowClear placeholder="请输入开户行" />
          </Form.Item>
          <Form.Item label="银行账号" name="username" rules={[{ required: true, message: '请输入银行账号' }]}>
            <Input allowClear placeholder="请输入银行账号" />
          </Form.Item>
          <Form.Item label="费率" name="rate" rules={[{ required: true, message: '请输入费率' }]}>
            <Input allowClear placeholder="请输入费率" />
          </Form.Item>
          <Form.Item label="收件人" name="username" rules={[{ required: true, message: '请输入收件人' }]}>
            <Input allowClear placeholder="请输入收件人" />
          </Form.Item>
          <Form.Item label="收件人电话" name="rate" rules={[{ required: true, message: '请输入收件人电话' }]}>
            <Input allowClear placeholder="请输入收件人电话" />
          </Form.Item>
          <Form.Item label="收件人地址" name="username" rules={[{ required: true, message: '请输入收件人地址' }]}>
            <Input allowClear placeholder="请输入收件人地址" />
          </Form.Item>

          <Form.Item style={{ marginLeft: 200 }}>
            <Button type="primary" htmlType="submit">
              提交
            </Button>
          </Form.Item>
        </Form>
      </div>
    </Card>
  )
}
