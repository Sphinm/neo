import React, { useEffect, useState } from 'react'
import { Card, Table, Input, Divider } from 'antd'
import {fetchMerchantAssignRecords, searchByCompanyAssign} from '@/apis/merchant'
import { handleError } from '@/libs/axios'

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
      title: '创建时间',
      dataIndex: 'age',
    },
    {
      title: '公司名称',
      dataIndex: 'address',
    },
    {
      title: '发放金额（成功）',
      dataIndex: 'action',
    },
    {
      title: '佣金',
      dataIndex: 'task',
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

  const searchByName = async (name: string) => {
    console.log(name)
    try {
      setLoading(true)
      const { data } = await searchByCompanyAssign(name)
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
        onSearch={value => searchByName(value)}
      />
      <Divider />
      <Table bordered loading={loading} rowKey="order_number" columns={columns as any} dataSource={tableData} />
    </Card>
  )
}
