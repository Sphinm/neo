import React, { useEffect, useState } from 'react'
import { Table } from 'antd'
import { fetchAllData } from '@/apis/user';
import { handleError } from '@/libs/axios';

export const DataQuery = () => {
  const [tableData, setTableData] = useState<any>([])

  useEffect(() => {
    fetchAgentData()
  }, []);

 
  const fetchAgentData = async() => {
    try {
      const { data } = await fetchAllData();
      setTableData(data)
    } catch (error) {
      handleError(error)
    }
  }

  const expandedRowRender = (data: any) => {
    const columns = [
      { title: '公司ID', dataIndex: 'id' },
      { title: '公司名称', dataIndex: 'companyName' },
      { title: '充值金额', dataIndex: 'totalRecharge' },
      { title: '发放金额', dataIndex: 'totalIssued' },
      { title: '剩余金额', dataIndex: 'balance' },
    ]
    
    return <Table rowKey="id" columns={columns} dataSource={data.companyInfo || []} pagination={false} />
  }

  const columns = [
    { title: 'ID', dataIndex: 'id' },
    { title: '代理商名称', dataIndex: 'merchantName' },
    { title: '账户余额', dataIndex: 'balance' },
    { title: '已提现金额', dataIndex: 'totalAmount' },
  ]

  return <Table rowKey="id" columns={columns} expandable={{ expandedRowRender }} dataSource={tableData} />
}
