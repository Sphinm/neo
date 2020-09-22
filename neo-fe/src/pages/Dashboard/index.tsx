import React, { useEffect, useRef, useState } from 'react'
import { RoleStore } from '@/store/roleStore'
import { useObserver } from 'mobx-react'
import { Card, Button, Row, Col, Descriptions, Modal, Input, Form } from 'antd'
import history from '@/libs/history'
import echarts from 'echarts/lib/echarts'
import 'echarts/lib/chart/line'
import { options } from '@/setting/echartConfig'
import style from './index.styl'
import { AuthType } from '@/enums/role'
import { fetchUserInfo, insertUserInfo, updateUserInfo } from '@/apis/user'
import { handleError } from '@/libs/axios'

// const userInfo = [
//   { key: 'userName', filed: '对接人', value: '测试111' },
//   { key: 'bossMobile', filed: '对接手机号', value: '13651608916' },
//   { key: 'companyName', filed: '公司名称', value: '上海昂彻网络科技有限公司' },
//   { key: 'companyTax', filed: '公司税号', value: '91310112MA1GBT7K0C' },
//   { key: 'companyPhone', filed: '公司电话', value: '021-64500866' },
//   { key: 'rate', filed: '费率', value: '12%' },
//   { key: 'industry', filed: '所属行业', value: '人力资源服务' },
//   { key: 'companyAddress', filed: '公司地址', value: '上海市闵行区506室' },
//   { key: 'mailingName', filed: '收件人', value: '特殊222' },
//   { key: 'mailingMobile', filed: '收件人手机号', value: '13651608916' },
//   { key: 'mailingAddress', filed: '收件地址', value: '上海市闵行区剑川路951号5幢507室' },
//   { key: 'bankName', filed: '开户行', value: '上海银行江川路支行' },
//   { key: 'bankCardNo', filed: '银行账号', value: '620522002192085991' },
// ]

const textMessage = [
  '1. 我司只接受 6% 服务费专票',
  '2. 代理商同一手机号可同时开通代理商账户和用人单位账户',
  '3. 我司收到发票后 1-3 个工作日发放佣金',
]

type CanvasType = HTMLDivElement | HTMLCanvasElement

// 员工无法看到该页面
const Dashboard = () => {
  const [form] = Form.useForm()
  const chartRef = useRef<CanvasType>(null)
  const [visible, setVisible] = useState(false)
  const [userInfo, setUserInfo] = useState<any>({})
  let chartInstance: echarts.ECharts | null = null

  const renderChart = () => {
    const renderedInstance = echarts.getInstanceByDom(chartRef?.current as CanvasType)
    if (renderedInstance) {
      chartInstance = renderedInstance
    } else {
      chartInstance = echarts.init(chartRef?.current as CanvasType)
    }
    chartInstance.setOption(options as echarts.EChartOption)
  }

  useEffect(() => {
    RoleStore.currentRole?.role !== 'ADMIN' && renderChart()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  useEffect(() => {
    RoleStore.fetchCurrentRole()
    fetchUserDetailInfo()
  }, [])

  const fetchUserDetailInfo = async () => {
    try {
      const { data } = await fetchUserInfo()
      console.log(22, data)
      setUserInfo(data ? data : {})
    } catch (error) {
      handleError(error)
    }
  }

  const goFinance = () => {
    if (RoleStore.currentRole?.role === AuthType.MERCHANT) {
      history.push('/main/finance/withdraw')
    } else {
      history.push('/main/finance/recharge')
    }
  }

  const goFinanceList = () => {
    if (RoleStore.currentRole?.role === AuthType.MERCHANT) {
      history.push('/main/flexible/rebates')
    } else {
      history.push('/main/finance/recharge-records')
    }
  }

  const handleOk = async () => {
    const values = await form.validateFields()
    try {
      if (Object.keys(userInfo).length) {
        // update
        const { data } = await updateUserInfo(values)
        console.log(112, data)
      } else {
        // insert
        await insertUserInfo(values)
      }
    } catch (error) {
      handleError(error)
    }
    setVisible(false)
  }

  return useObserver(() => (
    <>
      {RoleStore.currentRole?.role !== 'ADMIN' && (
        <Card className={style['dash-header']}>
          <div className={style['money-title']}>账户可用余额</div>
          <div className={style['money']}>￥100.24</div>
          <div className={style['button-group']}>
            <Button type="primary" onClick={goFinance}>
              {RoleStore.currentRole?.role === AuthType.MERCHANT ? '申请提现' : '立即充值'}
            </Button>
            <Button type="default" className={style['button-right']} onClick={goFinanceList}>
              查看明细
            </Button>
          </div>
          <div className={style['right-text']}>
            {RoleStore.currentRole?.role === AuthType.MERCHANT &&
              textMessage.map(item => {
                return (
                  <div key={item} className={style['item-text']}>
                    {item}
                  </div>
                )
              })}
          </div>
        </Card>
      )}
      <div className={style['card-wrapper']}>
        <Row gutter={24}>
          <Col span={RoleStore.currentRole?.role !== 'ADMIN' ? 18 : 24}>
            <Card>
              {RoleStore.currentRole?.role === 'ADMIN' && (
                <Button className={style['edit']} type="primary" onClick={() => setVisible(true)}>
                  编辑用户信息
                </Button>
              )}
              <Descriptions title="基本信息">
                {Object.keys(userInfo).length > 0 && (
                  <>
                    <Descriptions.Item label="对接人">
                      <span className={style['item-title']}>{userInfo?.userName}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="对接手机号">
                      <span className={style['item-title']}>{userInfo?.companyMobile}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="公司名称">
                      <span className={style['item-title']}>{userInfo?.company}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="公司税号">
                      <span className={style['item-title']}>{userInfo?.taxNumber}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="公司固话">
                      <span className={style['item-title']}>{userInfo?.fixedTelephone}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="费率">
                      <span className={style['item-title']}>{userInfo?.rate ?? 0}%</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="所属行业">
                      <span className={style['item-title']}>{userInfo?.industry}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="公司地址">
                      <span className={style['item-title']}>{userInfo?.companyAddress}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="收件人">
                      <span className={style['item-title']}>{userInfo?.receiverName}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="收件人手机号">
                      <span className={style['item-title']}>{userInfo?.receiverMobile}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="收件地址">
                      <span className={style['item-title']}>{userInfo?.receiverAddress}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="开户行">
                      <span className={style['item-title']}>{userInfo.bank}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="银行账号">
                      <span className={style['item-title']}>{userInfo?.bankAccount}</span>
                    </Descriptions.Item>
                  </>
                )}
              </Descriptions>
              {/* Echart */}
              {RoleStore.currentRole?.role !== 'ADMIN' && (
                <>
                  <div className={style['canvas-title']}>
                    {RoleStore.currentRole?.role === AuthType.COMPANY ? '最近一个月发放情况' : '最近一个月返佣情况'}
                  </div>
                  <div style={{ width: '100%', height: '300px' }} ref={chartRef as any} />
                </>
              )}
            </Card>
          </Col>
          {RoleStore.currentRole?.role !== 'ADMIN' && (
            <Col span={6}>
              {RoleStore.currentRole?.role === AuthType.COMPANY && (
                <Card title="公告">
                  由于银行对公转账的相关限制，费用发放在工作日内：周一 至 周五 09:00 ~
                  16:00之间进行，超过该时间顺延到第二个工作日办理，如需按时发放请提前做好相关工作准备。
                </Card>
              )}
              <Card style={{ margin: '20px 0' }}>
                <div className={style['money-title']}>
                  {RoleStore.currentRole?.role === AuthType.COMPANY ? '最近一个月发放金额' : '最近一个月返佣金额'}
                </div>
                <div className={style['money']}>￥100.24 元</div>
              </Card>
              <Card>
                <div className={style['money-title']}>
                  {RoleStore.currentRole?.role === AuthType.COMPANY ? '最近一个月发放人数' : '最近一个月返佣人数'}
                </div>
                <div className={style['money']}>￥100 人</div>
              </Card>
            </Col>
          )}
        </Row>
      </div>
      <Modal
        getContainer={false}
        title={Object.keys(userInfo).length === 0 ? '创建用户信息' : '编辑用户信息'}
        visible={visible}
        onOk={handleOk}
        onCancel={() => setVisible(false)}
        footer={[
          <Button key="back" onClick={() => setVisible(false)}>
            取消
          </Button>,
          <Button key="submit" htmlType="submit" type="primary" onClick={handleOk}>
            提交
          </Button>,
        ]}
      >
        <Form form={form}>
          <Form.Item label="对接人" name="userName" rules={[{ required: true, message: '请输入对接人' }]}>
            <Input placeholder="请输入对接人"></Input>
          </Form.Item>
          <Form.Item
            label="对接人手机号"
            name="companyMobile"
            rules={[{ required: true, message: '请输入对接人手机号' }]}
          >
            <Input placeholder="请输入对接人手机号"></Input>
          </Form.Item>
          <Form.Item label="公司名称" name="company" rules={[{ required: true, message: '请输入公司名称' }]}>
            <Input placeholder="请输入公司名称"></Input>
          </Form.Item>
          <Form.Item label="公司税号" name="taxNumber" rules={[{ required: true, message: '请输入公司税号' }]}>
            <Input placeholder="请输入公司税号"></Input>
          </Form.Item>
          <Form.Item label="公司固话" name="fixedTelephone" rules={[{ required: true, message: '请输入公司固定电话' }]}>
            <Input placeholder="请输入公司固定电话"></Input>
          </Form.Item>
          <Form.Item label="费率" name="rate" rules={[{ required: true, message: '请输入费率' }]}>
            <Input placeholder="请输入费率"></Input>
          </Form.Item>
          <Form.Item label="所属行业" name="industry" rules={[{ required: true, message: '请输入所属行业' }]}>
            <Input placeholder="请输入所属行业"></Input>
          </Form.Item>
          <Form.Item label="公司地址" name="companyAddress" rules={[{ required: true, message: '请输入公司地址' }]}>
            <Input placeholder="请输入公司地址"></Input>
          </Form.Item>
          <Form.Item label="收件人" name="receiverName" rules={[{ required: true, message: '请输入收件人手机号' }]}>
            <Input placeholder="请输入收件人手机号"></Input>
          </Form.Item>
          <Form.Item
            label="收件人手机号"
            name="receiverMobile"
            rules={[{ required: true, message: '请输入收件人手机号' }]}
          >
            <Input placeholder="收件人手机号"></Input>
          </Form.Item>
          <Form.Item label="开户行" name="bank" rules={[{ required: true, message: '请输入开户行' }]}>
            <Input placeholder="请输入开户行"></Input>
          </Form.Item>
          <Form.Item label="银行账号" name="bankAccount" rules={[{ required: true, message: '请输入银行账号' }]}>
            <Input placeholder="请输入银行账号"></Input>
          </Form.Item>
          <Form.Item label="收件地址" name="receiverAddress" rules={[{ required: true, message: '请输入收件人地址' }]}>
            <Input placeholder="请输入收件人地址"></Input>
          </Form.Item>
        </Form>
      </Modal>
    </>
  ))
}

export default Dashboard
