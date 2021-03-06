import history from '@/libs/history'
import React, { useState, useCallback } from 'react'
import { HomeOutlined, ShopOutlined, UsergroupAddOutlined, ShoppingOutlined } from '@ant-design/icons'
import { Layout, Menu } from 'antd'
import { useParams } from 'react-router-dom'
import { useObserver } from 'mobx-react'
import { RoleStore } from '@/store/roleStore'
import { AuthType } from '@/enums/role'
import styles from './index.styl'

const { Sider } = Layout
const { SubMenu } = Menu

const SideMenu = () => {
  const [collapsed, setCollapsed] = useState(false)
  const { page, subPage, subPage2 } = useParams<{ page: string; subPage?: string; subPage2?: string }>()

  const handleCollapse = useCallback((value: boolean) => {
    setCollapsed(value)
  }, [])

  const handleSelect = useCallback((param: any) => {
    history.push(`/main/${param.key}`)
  }, [])

  return useObserver(() => (
    <Sider collapsible collapsed={collapsed} onCollapse={handleCollapse}>
      <div className={styles['menu-header']}>
        <img src="https://www.nicepng.com/png/detail/266-2660352_neo-logo-neo.png" alt="" className={styles['menu-img']} />
        <h1>NEO</h1>
      </div>
      <Menu
        theme="dark"
        defaultOpenKeys={[page]}
        selectedKeys={[`${page}${subPage ? `/${subPage}` : ''}${subPage2 ? `/${subPage2}` : ''}`]}
        mode="inline"
        onClick={handleSelect}
      >
        {RoleStore.currentRole?.roleType !== AuthType.EMPLOYEE && (
          <Menu.Item key="dashboard">
            <span>
              <HomeOutlined />
              <span>工作台</span>
            </span>
          </Menu.Item>
        )}
        {/* 管理员配置 */}
        {RoleStore.currentRole?.roleType === AuthType.ADMIN && (
          <Menu.Item key="dataquery">
            <span>
              <ShopOutlined />
              <span>数据查询</span>
            </span>
          </Menu.Item>
        )}
        {RoleStore.currentRole?.roleType === AuthType.ADMIN && (
          <SubMenu
            key="admin"
            title={
              <span>
                <ShoppingOutlined />
                <span>用户信息</span>
              </span>
            }
          >
            <Menu.Item key="admin/merchant">代理商档案</Menu.Item>
            <Menu.Item key="admin/company">公司档案</Menu.Item>
            <Menu.Item key="admin/employee">员工档案</Menu.Item>
          </SubMenu>
        )}
        {RoleStore.currentRole?.roleType === AuthType.ADMIN && (
          <SubMenu
            key="review"
            title={
              <span>
                <UsergroupAddOutlined />
                <span>待审核</span>
              </span>
            }
          >
            <Menu.Item key="admin/reviewrecharge">公司充值审核</Menu.Item>
            <Menu.Item key="admin/reviewbill">公司开票记录</Menu.Item>
            <Menu.Item key="admin/reviewpayroll">公司发放记录</Menu.Item>
            <Menu.Item key="admin/reviewadd">代理商新增客户审核</Menu.Item>
            <Menu.Item key="admin/withdraw">代理商提现审核</Menu.Item>
            <Menu.Item key="admin/reviewuploadtax">上传完税凭证</Menu.Item>
          </SubMenu>
        )}

        {/* 代理商配置 */}
        {/* {RoleStore.currentRole?.roleType === AuthType.MERCHANT && (
          <SubMenu
            key="sole"
            title={
              <span>
                <ShoppingOutlined />
                <span>个独管理</span>
              </span>
            }
          >
            <Menu.Item key="sole/add">新增客户</Menu.Item>
            <Menu.Item key="sole/records">客户列表</Menu.Item>
          </SubMenu>
        )} */}
        {RoleStore.currentRole?.roleType === AuthType.MERCHANT && (
          <SubMenu
            key="flexible"
            title={
              <span>
                <ShoppingOutlined />
                <span>灵活用工</span>
              </span>
            }
          >
            {/* <Menu.Item key="flexible/add">新增客户</Menu.Item> */}
            <Menu.Item key="flexible/list">客户列表</Menu.Item>
            <Menu.Item key="flexible/payroll">发放记录</Menu.Item>
            <Menu.Item key="flexible/rebates">返佣记录</Menu.Item>
          </SubMenu>
        )}
        {RoleStore.currentRole?.roleType === AuthType.MERCHANT && (
          <SubMenu
            key="finance"
            title={
              <span>
                <ShoppingOutlined />
                <span>财务中心</span>
              </span>
            }
          >
            <Menu.Item key="finance/withdraw">提现</Menu.Item>
            <Menu.Item key="finance/withdraw-records">提现记录</Menu.Item>
          </SubMenu>
        )}

        {/* 公司配置 */}
        {RoleStore.currentRole?.roleType === AuthType.COMPANY && (
          <SubMenu
            key="payroll"
            title={
              <span>
                <ShoppingOutlined />
                <span>发放中心</span>
              </span>
            }
          >
            <Menu.Item key="payroll/list">申请发放</Menu.Item>
            {/* <Menu.Item key="payroll/approve">申请发放</Menu.Item> */}
            {/* <Menu.Item key="payroll/records">发放记录</Menu.Item> */}
            <Menu.Item key="payroll/details">人员发放明细</Menu.Item>
          </SubMenu>
        )}
        {RoleStore.currentRole?.roleType === AuthType.COMPANY && (
          <SubMenu
            key="finance"
            title={
              <span>
                <ShoppingOutlined />
                <span>财务中心</span>
              </span>
            }
          >
            <Menu.Item key="finance/recharge">充值</Menu.Item>
            <Menu.Item key="finance/recharge-records">充值记录</Menu.Item>
            <Menu.Item key="finance/approve">申请开票</Menu.Item>
            <Menu.Item key="finance/approve-records">开票记录</Menu.Item>
            <Menu.Item key="finance/receipts">完税凭证</Menu.Item>
          </SubMenu>
        )}
        {RoleStore.currentRole?.roleType === AuthType.COMPANY && (
          <SubMenu
            key="sign"
            title={
              <span>
                <ShoppingOutlined />
                <span>签约中心</span>
              </span>
            }
          >
            <Menu.Item key="sign/unsigned">待签约人员</Menu.Item>
            <Menu.Item key="sign/signed">已签约人员</Menu.Item>
          </SubMenu>
        )}
        {/* 员工配置 */}
        {RoleStore.currentRole?.roleType === AuthType.EMPLOYEE && (
          <Menu.Item key="report">
            <span>
              <HomeOutlined />
              <span>员工协议</span>
            </span>
          </Menu.Item>
        )}
      </Menu>
    </Sider>
  ))
}

export default SideMenu
