import React, { useEffect, useState } from 'react'
import { Card, Table, Button, Popconfirm, Badge } from 'antd'
import { fetchReviewCompany, reviewCompany } from '@/apis/review';
import { handleError } from '@/libs/axios';

export const MerchantAdd = () => {
  const [tableData, setTableData] = useState([])

  useEffect(() => {
    fetchCompanyInfo()
  }, []);

  const fetchCompanyInfo = async() => {
    try {
      const { data } = await fetchReviewCompany();
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
      title: '公司名称',
      dataIndex: 'companyName',
    },
    {
      title: '公司税号',
      dataIndex: 'companyTax',
    },
    {
      title: '公司地址',
      dataIndex: 'companyAddress',
    },
    {
      title: '联系人',
      dataIndex: 'contactName',
    },
    {
      title: '联系电话',
      dataIndex: 'contactTel',
    },
    {
      title: '开户行',
      dataIndex: 'bankName',
    },
    {
      title: '银行账号',
      dataIndex: 'bankCode',
    },
    {
      title: '费率',
      dataIndex: 'rate',
    },
    {
      title: '审核状态',
      dataIndex: 'isChecked',
      render: (text: any, record: any) => <div>{ record.isChecked ? <Badge status="success" text="审核通过"></Badge> : <Badge status="processing" text="等待审核"></Badge> }</div>,
    },
    {
      title: '审核时间',
      dataIndex: 'checkTime',
      render: (text: any, record: any) => <div>{record.isChecked ? record.checkTime : "-"}</div>,
    },
    {
      title: '操作',
      fixed: 'right',
      render: (text: any, record: any) => {
        return (
          <Popconfirm
            title="通过该新增用户申请"
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
    console.log('checkRecharge', id)
    try {
      await reviewCompany(id);
      fetchCompanyInfo()
    } catch (error) {
      handleError(error)
    }
  }

  return (
    <Card title="代理商新增客户审核">
      <Table scroll={{ x: 1500 }} bordered rowKey="id" columns={columns as any} dataSource={tableData} />
    </Card>
  )
}
