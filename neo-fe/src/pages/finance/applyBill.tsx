import React, { useEffect, useState } from 'react'
import { Card, Table, Button, Cascader, Select, Form, Input, Divider, Badge, message } from 'antd'
import { billOptions, typeOptions } from '@/setting/constantVar'
import styles from './index.styl'
import { handleError } from '@/libs/axios'
import { companyInvoice, fetchCompanyChargeList } from '@/apis/compnay'
import moment from 'moment'

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
}

export const ApplyBill = () => {
  const [form] = Form.useForm()
  const [totalMoney, setTotalMoney] = useState(0)
  const [selectedRowKeys, setSelectedRowKeys] = useState<string[]>([])
  const [loading, setLoading] = useState<boolean>(false)
  const [tableData, setTableData] = useState([])
  const info: any = JSON.parse(localStorage.getItem('userInfo')|| "")

  useEffect(() => {
    form.setFieldsValue({
      recipientName: info.userInfo.recipientName,
      recipientTel: info.userInfo.recipientTel,
      recipientAddress: info.userInfo.recipientAddress,
    })
  }, [form, info.userInfo])

  useEffect(() => {
    fetchRecords()
  }, [])

  const fetchRecords = async () => {
    try {
      setLoading(true)
      const {data} = await fetchCompanyChargeList()
      setTableData(data.list)
    } catch (error) {
      handleError(error)
    } finally {
      setLoading(false)
    }
  }


  const onFinish = async (values: any) => {
    if (!selectedRowKeys.length || totalMoney <= 0) return
    try {
      setLoading(true)
      const body = {
        orderNumberList: selectedRowKeys,
        totalMoney: totalMoney,
        invoiceContent: `${values.invoiceContent[0]}*${values.invoiceContent[1]}`,
        invoiceType: values.invoiceType,
        recipientName: values.recipientName,
        recipientTel: values.recipientTel,
        recipientAddress: values.recipientAddress,
      }
      const { data } = await companyInvoice(body)
      message.success(data)
      window.location.reload()
   } catch (error) {
      handleError(error)
    } finally {
      setLoading(false)
    }
  }

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
      title: '可开票金额',
      dataIndex: 'paymentAmount',
    },
    {
      title: '打款凭证',
      dataIndex: 'paymentVoucher',
      render: (text: any, record: any) => <>{text}</>,
    },
    {
      title: '审核状态',
      dataIndex: 'approvalStatus',
      render: (text: any, record: any) => <Badge status={text ? 'success' : 'processing'} text={text ? '审核通过' : '等待审核'}></Badge>,
    },
    {
      title: '审核时间',
      dataIndex: 'updateDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
    },
  ]


  const onBillChange = (value: any) => {
    console.log('onBillChange', value)
  }

  const onSelectChange = (selectedRowKeys: any, selectedRows: any) => {
    const totalMoney = selectedRows.map((item: any) => item.paymentAmount)
    const temp = totalMoney.length > 0 ? totalMoney.reduce((pre: any, cur: any) => pre + cur) : 0
    setSelectedRowKeys(selectedRowKeys)
    setTotalMoney(temp)
  }

  const rowSelection = {
    selectedRowKeys,
    onChange: onSelectChange,
  }

  return (
    <Card title="申请开票">
      <Table
        loading={loading}
        rowSelection={rowSelection}
        pagination={false}
        className={styles['bill-table']}
        size="small"
        bordered
        rowKey="orderNumber"
        columns={columns}
        dataSource={tableData}
      />
      <Divider />
      <Form 
        className={styles['bill-form']} 
        form={form} 
        {...layout} 
        onFinish={onFinish}>
        <Form.Item label="开票内容" name="invoiceContent">
          <Cascader options={billOptions} onChange={onBillChange} placeholder="请选择开票内容"></Cascader>
        </Form.Item>
        <Form.Item label="开票类型" name="invoiceType">
          <Select allowClear placeholder="请选择开票类型">
            {typeOptions.map(item => (
              <Select.Option key={item.value} value={item.value}>
                {item.label}
              </Select.Option>
            ))}
          </Select>
        </Form.Item>
        <Form.Item label="收件人姓名" name="recipientName">
          <Input placeholder="请输入收件人姓名" />
        </Form.Item>
        <Form.Item label="收件人手机号" name="recipientTel">
          <Input placeholder="请输入收件人手机号" />
        </Form.Item>
        <Form.Item label="收件人地址" name="recipientAddress"> 
          <Input placeholder="请输入收件人地址" />
        </Form.Item>
        <Form.Item label="开票金额总计" name="totalMoney">
          <span style={{ fontSize: 20, fontWeight: 500 }}>￥{totalMoney.toFixed(2)}</span>
        </Form.Item>
        <Form.Item style={{ marginLeft: 200 }}>
          <Button disabled={!totalMoney} type="primary" htmlType="submit">
            提交
          </Button>
        </Form.Item>
      </Form>
    </Card>
  )
}
