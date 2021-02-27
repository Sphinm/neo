import React, { useEffect, useState } from 'react'
import { Table } from 'antd'
import { fetchAllData, fetchCompanyByMerchantId } from '@/apis/user';
import { handleError } from '@/libs/axios';

export const DataQuery = () => {
  const [tableData, setTableData] = useState<any>([])
  const [companyData, setCompanyData] = useState<any>([])
  const [loading, setLoading] = useState(false)

  useEffect(() => {
    fetchAgentData()
  }, [])

 
  const fetchAgentData = async() => {
    try {
      setLoading(true)
      const { data } = await fetchAllData();
      setTableData(data)
    } catch (error) {
      handleError(error)
    } finally {
      setLoading(false)
    }
  }

  const fetchCompanyByMerchant = async(id: string) => {
    try {
      setLoading(true)
      const { data } = await fetchCompanyByMerchantId(id);
      setCompanyData(data)
    } catch (error) {
      handleError(error)
    } finally {
      setLoading(false)
    }
  }

  const expandedRowRender = () => {
    const columns = [
      { title: '公司ID', dataIndex: 'id' },
      { title: '公司名称', dataIndex: 'companyName' },
      { title: '充值金额', dataIndex: 'totalRecharge' },
      { title: '发放金额', dataIndex: 'totalIssued' },
      { title: '剩余金额', dataIndex: 'balance' },
    ]
    return <Table rowKey="id" columns={columns} dataSource={companyData} pagination={false} />
  }

  const columns = [
    { title: 'ID', dataIndex: 'id' },
    { title: '代理商名称', dataIndex: 'merchantName' },
    { title: '账户余额', dataIndex: 'balance' },
    { title: '已提现金额', dataIndex: 'totalAmount' },
  ]

  const onExpand = (expanded: boolean, record: any) => {
    console.log(11, expanded, record)
    if (expanded) {
      fetchCompanyByMerchant(record.id)
    } else {
      setCompanyData([])
    }
  }

  return <Table loading={loading} rowKey="id" columns={columns} expandable={{ expandedRowRender, onExpand}} dataSource={tableData} />
}
