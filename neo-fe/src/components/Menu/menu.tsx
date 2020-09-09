import history from '@/libs/history'
import React from 'react'
import { HomeOutlined, UsergroupAddOutlined, ShoppingOutlined } from '@ant-design/icons'
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
    <Sider
      style={{
        overflow: 'auto',
        height: '100vh',
        position: 'fixed',
        left: 0,
      }}
      collapsible
      collapsed={collapsed}
      onCollapse={handleCollapse}
    >
      <div className={styles['menu-header']}>
        <h1>NEO</h1>
      </div>
      <Menu
        theme="dark"
        defaultOpenKeys={[page]}
        selectedKeys={[`${page}${subPage ? `/${subPage}` : ''}${subPage2 ? `/${subPage2}` : ''}`]}
        mode="inline"
        onClick={handleSelect}
      >
        {RoleStore.currentRole?.role === AuthType.ADMIN && (
          <SubMenu
            key="merchant"
            title={
              <span>
                <HomeOutlined />
                <span>Dashboard</span>
              </span>
            }
          >
            <Menu.Item>Merchant List</Menu.Item>
            <Menu.Item key="merchants/product">Product List</Menu.Item>
          </SubMenu>
        )}
        {RoleStore.currentRole?.role === AuthType.ADMIN && (
          <SubMenu
            key="cms"
            title={
              <span>
                <HomeOutlined />
                <span>CMS</span>
              </span>
            }
          >
            <SubMenu key="homepage" title="Home Page">
              <Menu.Item key="cms/homepage/icon">Icon</Menu.Item>
              <Menu.Item key="cms/homepage/container">Container</Menu.Item>
            </SubMenu>
            <Menu.Item key="cms/store">Stores</Menu.Item>
          </SubMenu>
        )}
        {RoleStore.currentRole?.role === AuthType.ADMIN && (
          <SubMenu
            key="marketing"
            title={
              <span>
                <ShoppingOutlined />
                <span>Marketing</span>
              </span>
            }
          >
            <Menu.Item key="marketing/voucher/home">Voucher</Menu.Item>
            <SubMenu key="banners" title="Banners">
              <Menu.Item key="marketing/banners/ACTIVE">Actived Banner</Menu.Item>
              <Menu.Item key="marketing/banners/INACTIVE">Inactived Banner</Menu.Item>
            </SubMenu>
            <SubMenu key="popups" title="Popups">
              <Menu.Item key="marketing/popup/home/ACTIVE">Actived Popup</Menu.Item>
              <Menu.Item key="marketing/popup/home/INACTIVE">Inactived Popup</Menu.Item>
            </SubMenu>
            <Menu.Item key="marketing/push-notification/home">Push Notification</Menu.Item>
          </SubMenu>
        )}
        {RoleStore.currentRole?.role === AuthType.ADMIN && (
          <Menu.Item key="user-group">
            <span>
              <UsergroupAddOutlined />
              <span>User Group</span>
            </span>
          </Menu.Item>
        )}
        {RoleStore.currentRole?.role === AuthType.ADMIN && (
          <SubMenu
            key="users"
            title={
              <span>
                <UsergroupAddOutlined />
                <span>Admins</span>
              </span>
            }
          >
            <Menu.Item key="users/list">Admins List</Menu.Item>
            <Menu.Item key="users/roles">Admin Roles</Menu.Item>
          </SubMenu>
        )}
      </Menu>
    </Sider>
  ))
}

export default SideMenu
