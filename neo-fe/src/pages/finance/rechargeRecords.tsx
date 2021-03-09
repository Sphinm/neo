import React, { useEffect, useState } from 'react'
import { Card, Table, Button } from 'antd'
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
      title: '收款人',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: '充值金额',
      key: 'task',
      render: (text: any, record: any) => <div>绑定任务</div>,
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
            onClick={() => {
              handleSelectRow(record)
            }}
          >
            下载回单
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

  const handleSelectRow = (record: any) => {
    console.log('handleSelectRow', record)
    window.location.href = record.paymentVoucher
  }

  return (
    <Card title="充值记录">
      <Table loading={loading} bordered rowKey="orderNumber" columns={columns as any} dataSource={tableData} />
    </Card>
  )
}
