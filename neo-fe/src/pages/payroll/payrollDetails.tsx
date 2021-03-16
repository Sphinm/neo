import React, { useEffect, useState } from 'react'
import { Card, Table, Button, Form, Input, Row, Col, DatePicker, Badge } from 'antd'
import { downloadExcel } from '@/libs/download-excel'
import { fetchProvideDetail, searchProvideByIdCard } from '@/apis/compnay'
import moment from 'moment'
const { Search } = Input

export const PayrollDetails = () => {
  const [form] = Form.useForm()
  const [tableData, setTableData] = useState<any>([])
  const [loading, setLoading] = useState<boolean>(false)

  const columns = [
    {
      title: '流水号',
      dataIndex: 'bankSerialNumber',
    },
    {
      title: '订单号',
      dataIndex: 'orderNumber',
    },
    {
      title: '身份证',
      dataIndex: 'idNumber',
    },
    {
      title: '申请时间',
      dataIndex: 'createDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
    },
    {
      title: '收款人',
      dataIndex: 'name',
    },
    {
      title: '收款账号',
      dataIndex: 'bankNumber',
    },
    {
      title: '收款金额',
      dataIndex: 'amount',
    },
    {
      title: '发放时间',
      dataIndex: 'updateDate',
      render: (text: any, record: any) => <>{text ? moment(text).format('YYYY/MM/DD HH:mm:ss') : '-'}</>,
    },
    {
      title: '发放状态',
      dataIndex: 'status',
      render: (text: any, record: any) => <Badge status={text ? 'success' : 'processing'} text={text ? '发放完成' : '等待发放'}></Badge>,
    },
  ]

  useEffect(() => {
    fetchRecords()
  }, [])

  const fetchRecords =  async () => {
    try {
      setLoading(true)
      const { data } = await fetchProvideDetail()
      setTableData(data)
    } catch (error) {
    } finally {
      setLoading(false)
    }
  }

  const downLoadReport = async () => {
    downloadExcel(tableData)
  }

  const searchListByIdCard = async (value: string) => {
    try {
      const { data } = await searchProvideByIdCard(value)
      setTableData(data)
    } catch (error) {
    }
  }

  const changeDate = (dates: any, dateStrings: string) => {
    console.log(dates[0].valueOf(), dateStrings)
  } 

  return (
    <Card title="人员发放明细">
      <Form form={form}>
        <Row gutter={24}>
          <Col span={7}>
            <Form.Item name="idcard">
              <Search placeholder="身份证" enterButton="搜索" onSearch={value => searchListByIdCard(value)} />
            </Form.Item>
          </Col>
          <Col span={8}>
            <Form.Item name="time">
              <DatePicker.RangePicker allowClear onChange={changeDate as any} format="YYYY-MM-DD" placeholder={['开始时间', '结束时间']} />
            </Form.Item>
          </Col>
          <Button type="primary" onClick={downLoadReport}>
            批量导出发放明细
          </Button>
        </Row>
      </Form>

      <Table bordered loading={loading} rowKey="id" columns={columns as any} dataSource={tableData} />
    </Card>
  )
}