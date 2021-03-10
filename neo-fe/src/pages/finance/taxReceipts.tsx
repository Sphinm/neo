import React, { useEffect, useState } from 'react'
import { Card, Table } from 'antd'
import { handleError } from '@/libs/axios'
import { fetchCompanyRecepits } from '@/apis/compnay'
import moment from 'moment'

export const TaxReceipts = () => {
  const [tableData, setTableData] = useState<any>([])
  const [loading, setLoading] = useState<boolean>(false)

  useEffect(() => {
    fetchRecords()
  }, [])

  const fetchRecords =  async () => {
    try {
      setLoading(true)
      const { data } = await fetchCompanyRecepits()
      setTableData(data)
    } catch (error) {
      handleError(error)
    } finally {
      setLoading(false)
    }
  }

  const columns = [
    {
      title: '编号',
      dataIndex: 'number',
    },
    {
      title: '创建时间',
      dataIndex: 'createDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
    },
    {
      title: '公司名称',
      dataIndex: 'companyName',
    },
    {
      title: '完税凭证',
      dataIndex: 'taxReceive',
      // render: (text: any, record: any) => <></>,
    },
    {
      title: '月份',
      dataIndex: 'month',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM')}</>,
    },
    {
      title: '备注信息',
      dataIndex: 'remark',
    },
  ]

  return (
    <Card title="完税凭证">
      <Table loading={loading} bordered rowKey="orderNumber" columns={columns} dataSource={tableData} />
    </Card>
  )
}
