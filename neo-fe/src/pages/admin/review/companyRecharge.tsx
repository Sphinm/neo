import React, { useEffect, useState } from 'react'
import { Card, Table, Button, Popconfirm, Badge } from 'antd'
import { handleError } from '@/libs/axios';
import { fetchReviewRecharge, reviewRecharge } from '@/apis/review';

export const CompanyRecharge = () => {
  const [tableData, setTableData] = useState([])
  
  useEffect(() => {
    fetchRechargeInfo()
  }, []);

  const fetchRechargeInfo = async() => {
    try {
      const { data } = await fetchReviewRecharge();
      setTableData(data)
    } catch (error) {
      handleError(error)
    }
  }

  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
    },
    {
      title: '订单号',
      dataIndex: 'orderNumber',
    },
    {
      title: '打款金额',
      dataIndex: 'paymentAmount',
    },
    {
      title: '充值金额',
      dataIndex: 'accountAmount',
    },
    {
      title: '打款凭证',
      dataIndex: 'paymentVoucher',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '审核状态',
      dataIndex: 'approvalStatus',
      render: (text: any, record: any) => <Badge status="processing" text="等待审核"></Badge>,
    },
    {
      title: '申请时间',
      dataIndex: 'createDate',
    },
    {
      title: '审核时间',
      dataIndex: 'updateDate',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '操作',
      render: (text: any, record: any) => {
        return (
          <Popconfirm title="通过该充值申请" onConfirm={() => checkRecharge(record.id)} okText="确认" cancelText="取消">
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
      await reviewRecharge(id);
      fetchRechargeInfo()
    } catch (error) {
      handleError(error)
    }
  }

  return (
    <Card title="公司充值记录">
      <Table bordered rowKey="id" columns={columns as any} dataSource={tableData} />
    </Card>
  )
}
