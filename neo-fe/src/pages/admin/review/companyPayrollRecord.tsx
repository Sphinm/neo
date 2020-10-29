import React, { useEffect, useState } from 'react'
import { Card, Table, Button, Popconfirm, Badge } from 'antd'
import { handleError } from '@/libs/axios';
import { fetchReviewProvide, reviewProvide } from '@/apis/review';

export const CompanyPayrollRecord = () => {
  const [tableData, setTableData] = useState([])
  
  useEffect(() => {
    fetchBillInfo()
  }, []);

  const fetchBillInfo = async() => {
    try {
      const { data } = await fetchReviewProvide();
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
      title: '发放金额',
      dataIndex: 'amount',
    },
    // {
    //   title: '总笔数',
    //   dataIndex: 'address',
    // },
    {
      title: '审核状态',
      dataIndex: 'provideStatus',
      render: (text: any, record: any) => <Badge status="processing" text="等待审核"></Badge>,
    },
    {
      title: '审核时间',
      dataIndex: 'updateDate',
      render: (text: any, record: any) => <div>等待发放</div>,
    },
    {
      title: '申请时间',
      dataIndex: 'createDate',
    },
    {
      title: '操作',
      key: 'task',
      render: (text: any, record: any) => {
        return (
          <Popconfirm
            title="通过该笔发放申请"
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
      await reviewProvide(id);
      fetchBillInfo()
    } catch (error) {
      handleError(error)
    }
  }

  return (
    <Card title="公司发放记录">
      <Table bordered rowKey="id" columns={columns as any} dataSource={tableData} />
    </Card>
  )
}
