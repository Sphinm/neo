import React, { useEffect, useState } from 'react'
import { Card, Table } from 'antd'
import { handleError } from '@/libs/axios'
import { fetchMerchantRebateRecords } from '@/apis/merchant'
import moment from 'moment'

export const FlexRebate = () => {
  const [loading, setLoading] = useState<boolean>(false)
  const [tableData, setTableData] = useState([])
  const columns = [
    {
      title: '订单号',
      dataIndex: 'orderNumber',
    },
    {
      title: '金额',
      dataIndex: 'rebateMoney',
    },
    {
      title: '公司名称',
      dataIndex: 'companyName',
    },
    {
      title: '充值金额',
      dataIndex: 'rechargeMoney',
    },
    {
      title: '费率',
      dataIndex: 'rate',
      render: (text: any, record: any) => <>{`${text}%`}</>,
    },
    {
      title: '创建时间',
      dataIndex: 'createDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
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
      <Table bordered loading={loading} rowKey="orderNumber" columns={columns} dataSource={tableData} />
    </Card>
  )
}
