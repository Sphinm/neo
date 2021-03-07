import React, { useEffect, useState } from 'react'
import { Card, Table, Badge } from 'antd'
import { handleError } from '@/libs/axios'
import { fetchMerchantwithdrawRecords } from '@/apis/merchant'
import moment from 'moment'

export const WithdrawRecords = () => {
  const [loading, setLoading] = useState(false)
  const [tableData, setTableData] = useState<any>([])

  useEffect(() => {
    fetchInitData()
  }, [])

  const fetchInitData = async () => {
    try {
      setLoading(true)
      const { data } = await fetchMerchantwithdrawRecords()
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
      title: '金额',
      dataIndex: 'amount',
    },
    {
      title: '审核状态',
      dataIndex: 'status',
      render: (text: any, record: any) => <Badge status={text ? 'success' : 'processing'} text={text ? '审核通过' : '等待审核'}></Badge>,
    },
    {
      title: '审核时间',
      dataIndex: 'reviewDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
    },
    {
      title: '申请时间',
      dataIndex: 'createDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
    },
  ]


  return (
    <Card title="提现记录">
      <Table loading={loading} bordered rowKey="orderNumber" columns={columns as any} dataSource={tableData} />
    </Card>
  )
}
