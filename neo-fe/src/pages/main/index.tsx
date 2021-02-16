import Loading from '@/components/Loading'
import HeaderRightMenu from '@/components/HeadeiRightMenu/headerRightMenu'
import React, { useEffect } from 'react'
import RoutesAuth from '../routes.auth'
import SideMenu from '@/components/Menu/menu'
import { Layout } from 'antd'
import { useObserver } from 'mobx-react'
import styles from './index.styl'

const { Header, Footer, Content } = Layout

const Main = () => {
  const [authenticating, setAuthenticating] = React.useState(true)

  useEffect(() => {
    setAuthenticating(false)
  }, [])

  return useObserver(() => (
    <Layout>
      <SideMenu />
      <Layout>
        <Header className={styles.header}>
          <HeaderRightMenu />
        </Header>
        <Content className={styles.content}>
          <div className="site-layout-background" style={{ padding: 24 }}>
            {authenticating ? <Loading /> : <RoutesAuth />}
          </div>
        </Content>
        <Footer style={{ textAlign: 'center' }}>NEO ©2020 Created by XXX</Footer>
      </Layout>
    </Layout>
  ))
}

export default Main
