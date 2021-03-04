import React, { useEffect, useState } from 'react'
import { Card, Table } from 'antd'
import { handleError } from '@/libs/axios'
import { fetchMerchantRebateRecords } from '@/apis/merchant'

export const FlexRebate = () => {
  const [loading, setLoading] = useState<boolean>(false)
  const [tableData, setTableData] = useState([])
  const columns = [
    {
      title: '订单号',
      dataIndex: 'name',
    },
    {
      title: '金额',
      dataIndex: 'age',
    },
    {
      title: '公司名称',
      dataIndex: 'address',
    },
    {
      title: '充值金额',
      dataIndex: 'action',
    },
    {
      title: '费率',
      dataIndex: 'task',
    },
    {
      title: '创建时间',
      dataIndex: 'task',
    },
  ]

  useEffect(() => {
    fetchRebateRecords()
  }, [])

  const fetchRebateRecords = async () => {
    try {
      setLoading(true)
      const { data } = await fetchMerchantRebateRecords()
      setTableData(data)
     } catch (error) {
      handleError(error)
    } finally {
      setLoading(false)
    }
  }


  return (
    <Card title="返佣记录">
      <Table bordered loading={loading} rowKey="name" columns={columns as any} dataSource={tableData} />
    </Card>
  )
}
