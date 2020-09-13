import history from '@/libs/history'
import React from 'react'
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
  const [collapsed, setCollapsed] = React.useState(false)
  const { page, subPage, subPage2 } = useParams<{ page: string; subPage?: string; subPage2?: string }>()

  const handleCollapse = React.useCallback((value: boolean) => {
    setCollapsed(value)
  }, [])

  const handleSelect = React.useCallback((param: any) => {
    history.push(`/main/${param.key}`)
  }, [])

  return useObserver(() => (
    <Sider collapsible collapsed={collapsed} onCollapse={handleCollapse}>
      <div className={styles['menu-header']}>
        <img src="https://oss.fengong8.com/flex-empl-app/fengong8/logo.svg" alt="" className={styles['menu-img']} />
        <h1>NEO</h1>
      </div>
      <Menu
        theme="dark"
        defaultOpenKeys={[page]}
        selectedKeys={[`${page}${subPage ? `/${subPage}` : ''}${subPage2 ? `/${subPage2}` : ''}`]}
        mode="inline"
        onClick={handleSelect}
      >
        <Menu.Item key="dashboard">
          <span>
            <HomeOutlined />
            <span>工作台</span>
          </span>
        </Menu.Item>
        {RoleStore.currentRole?.role === AuthType.ADMIN && (
          <Menu.Item key="admin/dataquery">
            <span>
              <ShopOutlined />
              <span>数据查询</span>
            </span>
          </Menu.Item>
        )}
        {RoleStore.currentRole?.role === AuthType.ADMIN && (
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
            <Menu.Item key="admin/company">公司客户档案</Menu.Item>
            <Menu.Item key="admin/employee">员工档案</Menu.Item>
          </SubMenu>
        )}
        {RoleStore.currentRole?.role === AuthType.ADMIN && (
          <SubMenu
            key="review"
            title={
              <span>
                <UsergroupAddOutlined />
                <span>待审核</span>
              </span>
            }
          >
            <Menu.Item key="admin/reviewbill">开票记录</Menu.Item>
            <Menu.Item key="admin/reviewpayroll">发放记录</Menu.Item>
            <Menu.Item key="admin/reviewrecharge">充值记录</Menu.Item>
            <Menu.Item key="admin/reviewuploadtax">完税凭证</Menu.Item>
          </SubMenu>
        )}
      </Menu>
    </Sider>
  ))
}

export default SideMenu
