import React, { useEffect, useState } from 'react'
import { Card, Table, Button, Popconfirm } from 'antd'
import { deleteEmployee, fetchAllEmployee } from '@/apis/user';
import { handleError } from '@/libs/axios';

export const Employee = () => {
  const [tableData, setTableData] = useState([])

  useEffect(() => {
    fetchAllUser()
  }, []);

  const fetchAllUser = async(pageNum = 1) => {
    try {
      const body = {
        pageNum: pageNum,
        pageSize: 20
      }
      const { data } = await fetchAllEmployee(body)
      console.log(data)
      setTableData(data.list)
    } catch (error) {
      handleError(error)
    }
  }

  const columns = [
    {
      title: '用户ID',
      dataIndex: 'id',
    },
    {
      title: '姓名',
      dataIndex: 'userName',
    },
    {
      title: '身份证号',
      dataIndex: 'idVerify',
    },
    {
      title: '手机号',
      dataIndex: 'userMobile',
    },
    {
      title: '关联公司',
      dataIndex: 'companyName',
    },
    {
      title: '是否签约',
      dataIndex: 'isSignUp',
      render: (text: any, record: any) => <div>{record.isSignUp ? '已签约' : '未签约'}</div>,
    },
    {
      title: '操作',
      render: (text: any, record: any) => {
        return (
          <Popconfirm
            title="将该员工从列表中移除"
            onConfirm={() => deleteUser(record.id)}
            okText="确认"
            cancelText="取消"
          >
            <Button type="primary">删除</Button>
          </Popconfirm>
        )
      },
    },
  ]

  const deleteUser = async(id: string) => {
    try {
      await deleteEmployee(id)
      fetchAllUser()
    } catch (error) {
      handleError(error)
    }
  }

  const changePage = (page: number) => {
    fetchAllUser(page)
  }

  return (
    <Card title="员工档案">
      <Table bordered rowKey="id" columns={columns as any} dataSource={tableData} pagination={{
            showQuickJumper: true,
            pageSize: 20,
            onChange: (page) => changePage(page),
          }} />
    </Card>
  )
}
