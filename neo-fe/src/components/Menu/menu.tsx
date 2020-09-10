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
        <Menu.Item key="merchant">
          <span>
            <HomeOutlined />
            <span>Dashboard</span>
          </span>
        </Menu.Item>
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
            {/* <SubMenu key="banners" title="Banners">
              <Menu.Item key="marketing/banners/ACTIVE">Actived Banner</Menu.Item>
              <Menu.Item key="marketing/banners/INACTIVE">Inactived Banner</Menu.Item>
            </SubMenu>
            <SubMenu key="popups" title="Popups">
              <Menu.Item key="marketing/popup/home/ACTIVE">Actived Popup</Menu.Item>
              <Menu.Item key="marketing/popup/home/INACTIVE">Inactived Popup</Menu.Item>
            </SubMenu> */}
            <Menu.Item key="marketing/push-notification/home">Push Notification</Menu.Item>
          </SubMenu>
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
