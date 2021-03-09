import React, { useEffect, useState } from 'react'
import { Card, Table, Button, Badge } from 'antd'
import moment from 'moment'
import { fetchCompanyChargeList } from '@/apis/compnay'
import { handleError } from '@/libs/axios'

export const RechargeRecords = () => {
  const [loading, setLoading] = useState<boolean>(false)
  const [tableData, setTableData] = useState([])
  
  const columns = [
    {
      title: '订单号',
      dataIndex: 'orderNumber',
    },
    
    {
      title: '打款金额',
      dataIndex: 'paymentAmount',
    },
    {
      title: '充值金额',
      dataIndex: 'accountAmount',
    },
    {
      title: '打款凭证',
      dataIndex: 'paymentVoucher',
    },
    {
      title: '审核状态',
      dataIndex: 'approvalStatus',
      render: (text: any, record: any) => <Badge status={text ? 'success' : 'processing'} text={text ? '审核通过' : '等待审核'}></Badge>,
    },
    {
      title: '充值时间',
      dataIndex: 'createDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
    },
    {
      title: '操作',
      align: 'center',
      render: (text: any, record: any) => {
        return (
          <Button
            type="link"
          >
            <a rel="noopener noreferrer" href={record.paymentVoucher} target="_blank">下载回单</a>
          </Button>
        )
      },
    },
  ]

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

  return (
    <Card title="充值记录">
      <Table loading={loading} bordered rowKey="orderNumber" columns={columns as any} dataSource={tableData} />
    </Card>
  )
}
