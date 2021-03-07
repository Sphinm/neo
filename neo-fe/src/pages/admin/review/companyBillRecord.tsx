import React, { useEffect, useState } from 'react'
import { Card, Table, Button, Popconfirm, Badge } from 'antd'
import { handleError } from '@/libs/axios';
import { fetchReviewInvoice, reviewInvoice } from '@/apis/review';
import moment from 'moment';

export const CompanyBillRecord = () => {
  const [tableData, setTableData] = useState([])
  
  useEffect(() => {
    fetchBillInfo()
  }, []);

  const fetchBillInfo = async() => {
    try {
      const { data } = await fetchReviewInvoice();
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
      title: '开票金额',
      dataIndex: 'invoiceAmount',
    },
    {
      title: '发票内容',
      dataIndex: 'invoiceContent',
    },
    {
      title: '发票类型',
      dataIndex: 'invoiceType',
    },
    {
      title: '发票抬头',
      dataIndex: 'invoiceCompany',
    },
    {
      title: '审核状态',
      dataIndex: 'status',
      render: (text: any, record: any) => <Badge status="processing" text="等待审核"></Badge>,
    },
    {
      title: '审核时间',
      dataIndex: 'updateDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
    },
    {
      title: '申请时间',
      dataIndex: 'createDate',
      render: (text: any, record: any) => <>{moment(text).format('YYYY/MM/DD HH:mm:ss')}</>,
    },
    {
      title: '操作',
      fixed: 'right',
      render: (text: any, record: any) => {
        return (
          <Popconfirm title="通过该开票申请" onConfirm={() => checkRecharge(record.id)} okText="确认" cancelText="取消">
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
      await reviewInvoice(id);
      fetchBillInfo()
    } catch (error) {
      handleError(error)
    }
  }

  return (
    <Card title="公司开票记录">
      <Table bordered rowKey="id" columns={columns as any} dataSource={tableData} />
    </Card>
  )
}
