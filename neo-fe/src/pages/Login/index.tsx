import React from 'react'
import { useHistory } from 'react-router-dom'
import { LockOutlined, UserOutlined } from '@ant-design/icons'
import { Button, Input, Form } from 'antd'
import { login } from '@/apis/auth'
import { handleError } from '@/libs/axios'
import styles from './index.styl'
import { RoleStore } from '@/store/roleStore'
import { AuthType } from '@/enums/role'

const Login = () => {
  const [form] = Form.useForm()
  const history = useHistory()
  const { validateFields } = form

  const [loading, setLoading] = React.useState(false)
  const handleSubmit = async () => {
    try {
      const values = await validateFields()
      const { mobile, password } = values
      setLoading(true)
      const { data } = await login({
        mobile,
        password,
      })
      if (data?.mobile) {
        RoleStore.currentRole = data
        data.role === AuthType.EMPLOYEE ? history.push('/main/report') : history.push('/main')
      }
    } catch (e) {
      handleError(e)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className={styles['login-container']}>
      <Form form={form} className={styles['login-form']} onFinish={handleSubmit}>
        <h1>NEO</h1>
        <Form.Item name="mobile" rules={[{ required: true, message: 'Please input your mobile!' }]}>
          <Input prefix={<UserOutlined style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Mobile" />
        </Form.Item>
        <Form.Item name="password" rules={[{ required: true, message: 'Please input your Password!' }]}>
          <Input
            prefix={<LockOutlined style={{ color: 'rgba(0,0,0,.25)' }} />}
            type="password"
            placeholder="Password"
          />
        </Form.Item>
        <Form.Item>
          <Button loading={loading} type="primary" htmlType="submit" className={styles['login-form-button']}>
            Log in
          </Button>
        </Form.Item>
      </Form>
    </div>
  )
}

export default Login
