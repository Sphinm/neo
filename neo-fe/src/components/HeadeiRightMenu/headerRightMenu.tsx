import React from 'react'
import { Menu, Dropdown, Avatar } from 'antd'
import { UserOutlined, DownOutlined } from '@ant-design/icons'
import { RoleStore } from '@/store/roleStore'
import { logout } from '@/apis/auth'
import style from './index.styl'
import { useObserver } from 'mobx-react'
import { handleError } from '@/libs/axios'

const HeaderRightMenu = () => {
  const logoutBoss = async () => {
    try {
      await logout()
    } catch (e) {
      handleError(e)
    } finally {
      window.location.href = '/login'
    }
  }
  const menu = (
    <Menu>
      {/* <Menu.Item onClick={changeLanguage}>Change Language</Menu.Item> */}
      <Menu.Item onClick={logoutBoss}>退出登录</Menu.Item>
    </Menu>
  )
  return useObserver(() => (
    <div className={style.wraper}>
      <Dropdown overlay={menu} trigger={['click']}>
        <p className={style['ant-dropdown-link']} onClick={e => e.preventDefault()}>
          <Avatar icon={<UserOutlined />} />
          <span>{RoleStore.currentRole?.userName || 'UNLOGIN'}</span>
          <DownOutlined />
        </p>
      </Dropdown>
    </div>
  ))
}

export default HeaderRightMenu
