import React, { useState } from 'react'
import { Menu, Dropdown, Avatar, Modal, Button, Input, Form, notification } from 'antd'
import { UserOutlined, DownOutlined } from '@ant-design/icons'
import { RoleStore } from '@/store/roleStore'
import { changePwd, logout } from '@/apis/auth'
import style from './index.styl'
import { useObserver } from 'mobx-react'
import { handleError } from '@/libs/axios'

const HeaderRightMenu = () => {
  const [form] = Form.useForm()
  const [visible, setVisible] = useState(false)

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
      <Menu.Item onClick={() => setVisible(true)}>更改密码</Menu.Item>
      <Menu.Item onClick={logoutBoss}>退出</Menu.Item>
    </Menu>
  )

  const handleOk = async () => {
    const values = await form.validateFields()
    console.log(11, values)
    if (values.oldPwd === values.newPwd) {
      notification.error({
        message: '新老密码不能相同',
      })
      return
    }
    if (values.newPwd !== values.checkPwd) {
      notification.error({
        message: '确认密码错误，请检查重试',
      })
      return
    }
    try {
      const params = {
        oldPwd: values.oldPwd,
        newPwd: values.newPwd,
      }
      await changePwd(params)
      form.resetFields()
      setVisible(false)
      notification.success({
        message: '更改密码成功',
      })
    } catch (e) {
      handleError(e)
    }
  }

  const cancelText = () => {
    form.resetFields()
    setVisible(false)
  }

  return useObserver(() => (
    <div className={style.wraper}>
      <Dropdown overlay={menu} trigger={['click']}>
        <p className={style['ant-dropdown-link']} onClick={e => e.preventDefault()}>
          <Avatar icon={<UserOutlined />} />
          <span>{RoleStore.currentRole?.userName || '未登录'}</span>
          <DownOutlined />
        </p>
      </Dropdown>
      <Modal
        title="修改密码"
        visible={visible}
        onOk={handleOk}
        onCancel={() => setVisible(false)}
        footer={[
          <Button key="back" onClick={cancelText}>
            取消
          </Button>,
          <Button key="submit" type="primary" onClick={handleOk}>
            提交
          </Button>,
        ]}
      >
        <Form form={form}>
          <Form.Item label="初始密码" name="oldPwd">
            <Input placeholder="请输入初始密码"></Input>
          </Form.Item>
          <Form.Item label="新的密码" name="newPwd">
            <Input type="password" placeholder="请输入新的密码"></Input>
          </Form.Item>
          <Form.Item label="确认密码" name="checkPwd">
            <Input type="password" placeholder="再次输入新密码"></Input>
          </Form.Item>
        </Form>
      </Modal>
    </div>
  ))
}

export default HeaderRightMenu
