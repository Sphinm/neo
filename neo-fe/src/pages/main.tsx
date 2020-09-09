import Loading from '@/components/Loading'
import MainBreadCrumb from '@/components/breadcrumb'
import React from 'react'
import RoutesAuth from './routes.auth'
import SideMenu from '@/components/Menu/menu'
import { Layout } from 'antd'
import { useObserver } from 'mobx-react'

const { Header, Footer, Content } = Layout

const Main = () => {
  const [authenticating, setAuthenticating] = React.useState(true)

  React.useEffect(() => {
    setAuthenticating(false)
  }, [])

  return useObserver(() => (
    <Layout>
      <SideMenu />
      <Layout className="site-layout" style={{ marginLeft: 200 }}>
        <Header style={{ paddingLeft: 20, background: '#fff' }}>
          <MainBreadCrumb />
        </Header>
        <Content style={{ margin: '24px 16px 0', overflow: 'initial' }}>
          <div className="site-layout-background" style={{ padding: 24 }}>
            {authenticating ? <Loading /> : <RoutesAuth />}
          </div>
        </Content>
        <Footer style={{ textAlign: 'center' }}>Boss ©2020 Created by XXX</Footer>
      </Layout>
    </Layout>
  ))
}

export default Main
