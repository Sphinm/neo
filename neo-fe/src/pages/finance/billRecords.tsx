import React, { useEffect, useState } from 'react'
import { Card, Table, Badge } from 'antd'
import { handleError } from '@/libs/axios'
import { fetchCompanyInvoiceList } from '@/apis/compnay'
import moment from 'moment'

export const BillRecords = () => {
  const [tableData, setTableData] = useState<any>([])
  const [loading, setLoading] = useState<boolean>(false)

  useEffect(() => {
    fetchRecords()
  }, [])

  const fetchRecords =  async () => {
    try {
      setLoading(true)
      const { data } = await fetchCompanyInvoiceList()
      setTableData(data)
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
      title: '开票金额',
      dataIndex: 'invoiceAmount',
    },
    {
      title: '发票类目',
      dataIndex: 'invoiceContent',
    },
    {
      title: '发票类型',
      dataIndex: 'invoiceType',
      render: (text: any, record: any) => <>{text ? '增值税专用发票' : '增值税普通发票'}</>,
    },
    {
      title: '发票抬头',
      dataIndex: 'invoiceCompany',
    },
    {
      title: '状态',
      dataIndex: 'status',
      render: (text: any, record: any) => <Badge status={text ? 'success' : 'processing'} text={text ? '已寄出' : '等待审核'}></Badge>,
    },
    {
      title: '审核时间',
      dataIndex: 'updateDate',
      render: (text: any, record: any) => <>{text ? moment(text).format('YYYY/MM/DD HH:mm:ss') : '-'}</>,
    },
  ]

  return (
    <Card title="开票记录">
      <Table loading={loading} bordered rowKey="orderNumber" columns={columns} dataSource={tableData} />
    </Card>
  )
}
