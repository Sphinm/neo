import React, { useState } from 'react'
import { Card, Form, Input, Button, Divider, Descriptions } from 'antd'
import styles from './index.styl'

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
}

export const Withdraw = () => {
  const [form] = Form.useForm()
  const [totalMoney, setTotalMoney] = useState(0)

  const onFinish = (values: any) => {
    console.log('onFinish:', values)
    if (!totalMoney) return
  }

  const handleChangeMoney = (value: any) => {
    setTotalMoney(200 - value > 0 ? 200 - value : 0)
  }

  return (
    <Card title="提现">
      <div className={styles['recharge']}>
        <Form className={styles['recharge-form']} form={form} {...layout} onFinish={onFinish}>
          <Form.Item label="当前可用余额" name="leftMoeny">
            <div style={{ textAlign: 'left' }}>￥200</div>
          </Form.Item>
          <Form.Item label="提现金额" name="withdrawMoney" rules={[{ required: true, message: '请输入提现金额' }]}>
            <Input allowClear placeholder="请输入提现金额（元）" onChange={e => handleChangeMoney(e.target.value)} />
          </Form.Item>
          <Form.Item label="快递公司" name="deliveryCompany" rules={[{ required: true, message: '请输入快递公司' }]}>
            <Input placeholder="请输入快递公司" />
          </Form.Item>
          <Form.Item label="快递单号" name="deliveryNumber" rules={[{ required: true, message: '请输入快递单号' }]}>
            <Input placeholder="请输入快递单号" />
          </Form.Item>
          <Form.Item label="提现后可用余额" name="totalmoney">
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
          <Descriptions title="说明">
            <Descriptions.Item>在发起提现之前，请确保开出并寄送了有效的发票。</Descriptions.Item>
          </Descriptions>
          <Descriptions title="开票信息">
            <Descriptions.Item label="公司名称">金华市云赋信息科技有限公司</Descriptions.Item>
            <Descriptions.Item label="税号">91310110MA1G86FM93</Descriptions.Item>
            <Descriptions.Item label="单位地址">上海市宝山区呼兰西路100号永谊大厦11楼C/D座</Descriptions.Item>
          </Descriptions>
          <Descriptions title="邮寄至">
            <Descriptions.Item label="接收人">金华市云赋信息科技有限公司</Descriptions.Item>
            <Descriptions.Item label="接收电话">13636378846</Descriptions.Item>
            <Descriptions.Item label="邮寄地址">上海市宝山区呼兰西路100号永谊大厦11楼C/D座</Descriptions.Item>
          </Descriptions>
        </div>
      </div>
    </Card>
  )
}
