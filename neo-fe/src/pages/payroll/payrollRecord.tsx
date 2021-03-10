import React, { useEffect, useState } from 'react'
import { Card, Table } from 'antd'
import { handleError } from '@/libs/axios'

export const PayrollRecord = () => {
  const [tableData, setTableData] = useState<any>([])
  const [loading, setLoading] = useState<boolean>(false)

  useEffect(() => {
    fetchRecords()
  }, [])

  const fetchRecords =  async () => {
    try {
      setLoading(true)
      setTableData([])
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
      dataIndex: 'age',
    },
    {
      title: '发放金额',
      dataIndex: 'address',
    },
    {
      title: '订单状态',
      dataIndex: 'action',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '任务',
      dataIndex: 'task',
      render: (text: any, record: any) => <div>绑定任务</div>,
    },
  ]

  return (
    <Card title="发放记录">
      <Table loading={loading} bordered rowKey="orderNumber" columns={columns} dataSource={tableData} />
    </Card>
  )
}
