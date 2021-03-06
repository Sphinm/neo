import React, { useEffect, useState } from 'react'
import { Card, Table, Input, Divider } from 'antd'
import {fetchMerchantAssignRecords, searchByCompanyAssign} from '@/apis/merchant'
import { handleError } from '@/libs/axios'
import moment from 'moment'

const { Search } = Input


export const FlexPayroll = () => {
  const [loading, setLoading] = useState<boolean>(false)
  const [tableData, setTableData] = useState([])
  const columns = [
    {
      title: '订单号',
      dataIndex: 'orderNumber',
    },
    {
      title: '公司名称',
      dataIndex: 'companyName',
    },
    {
      title: '发放金额（成功）',
      dataIndex: 'amount',
    },
    {
      title: '佣金',
      dataIndex: 'rebate',
    },
    {
      title: '创建时间',
      dataIndex: 'createDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
    },
  ]

  useEffect(() => {
    fetchAssignRecords()
  }, [])

  const fetchAssignRecords = async () => {
    try {
      setLoading(true)
      const { data } = await fetchMerchantAssignRecords()
      setTableData(data)
     } catch (error) {
      handleError(error)
    } finally {
      setLoading(false)
    }
  }

  const searchByNumber = async (id: string) => {
    try {
      setLoading(true)
      const { data } = await searchByCompanyAssign(id)
      setTableData(data)
     } catch (error) {
      handleError(error)
    } finally {
      setLoading(false)
    }
  }

  return (
    <Card title="发放记录">
      <Search
        style={{ width: 300 }}
        placeholder="请输入公司名称"
        enterButton="搜索"
        onSearch={value => searchByNumber(value)}
      />
      <Divider />
      <Table bordered loading={loading} rowKey="order_number" columns={columns as any} dataSource={tableData} />
    </Card>
  )
}
