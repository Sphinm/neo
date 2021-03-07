import React, { useEffect, useState } from 'react'
import { Card, Table, Button, Popconfirm, Badge } from 'antd'
import { handleError } from '@/libs/axios';
import { fetchReviewWithdraw, reviewWithdraw } from '@/apis/review';
import moment from 'moment';

export const MerchantWithdraw = () => {
  const [tableData, setTableData] = useState([])
  
  useEffect(() => {
    fetchBillInfo()
  }, []);

  const fetchBillInfo = async() => {
    try {
      const { data } = await fetchReviewWithdraw();
      setTableData(data)
    } catch (error) {
      handleError(error)
    }
  }

  const columns = [
    {
      title: '订单号',
      dataIndex: 'orderNumber',
    },
    {
      title: '提现金额',
      dataIndex: 'amount',
    },
    {
      title: '审核状态',
      dataIndex: 'status',
      render: (text: any, record: any) => <Badge status="processing" text="等待审核"></Badge>,
    },
    {
      title: '审核时间',
      dataIndex: 'reviewDate',
      render: (text: any, record: any) => <>{record.status ? moment(record.reviewDate).format('YYYY/MM/DD HH:mm:ss') : "-"}</>,
    },
    {
      title: '申请时间',
      dataIndex: 'createDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
    },
    {
      title: '操作',
      render: (text: any, record: any) => {
        return (
          <Popconfirm
            title="通过该笔提现申请前请确保已给该代理商打款"
            onConfirm={() => checkRecharge(record.id)}
            okText="确认"
            cancelText="取消"
          >
            <Button disabled={record.status === 'SUCCESS'} type="primary">
              审核
            </Button>
          </Popconfirm>
        )
      },
    },
  ]

  const checkRecharge = async(id: string) => {
    try {
      await reviewWithdraw(id);
      fetchBillInfo()
    } catch (error) {
      handleError(error)
    }
  }

  return (
    <Card title="代理商提现申请">
      <Table bordered rowKey="id" columns={columns as any} dataSource={tableData} />
    </Card>
  )
}
