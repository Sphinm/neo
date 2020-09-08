import React from 'react'
import { Layout } from 'antd'
// import AppHeader from './Header'
// import SideBar from './SideBar'
// import Content from './Content'
// import { RoleStore } from '@/store/roleStore'
import { useObserver } from 'mobx-react'
import { withRouter } from 'react-router-dom'
// import style from './index.styl'

const Index = () => {
  return useObserver(() => (
    <Layout>
      {/* {RoleStore.fetchUserCode ? (
        <>
          <SideBar />
          <Layout>
            <AppHeader />
            <Layout.Content className={style.content}>
              <Card bordered={false}>
                <Content />
              </Card>
            </Layout.Content>
            <Layout.Footer style={{ textAlign: 'center' }}>
              Centralized System ©2020 Created by Advance
              {process.env.NODE_ENV === 'production' && <div>{process.env.REACT_APP_TAG}</div>}
            </Layout.Footer>
          </Layout>
        </>
      ) : (
        <Spin tip="Loading..." />
      )} */}
    </Layout>
  ))
}
export default withRouter(Index)
