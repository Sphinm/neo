import React, { useEffect, useRef, useState } from 'react'
import { RoleStore } from '@/store/roleStore'
import { useObserver } from 'mobx-react'
import { Card, Button, Row, Col, Descriptions, Modal, Input, Form, Spin, Divider, message } from 'antd'
import history from '@/libs/history'
import echarts from 'echarts/lib/echarts'
import 'echarts/lib/chart/line'
import { options } from '@/setting/echartConfig'
import style from './index.styl'
import { AuthType } from '@/enums/role'
import { insertUserInfo, updateUserInfo } from '@/apis/user'
import { handleError } from '@/libs/axios'
import { fetchMerchantBalance, fetchMerchantRebate } from '@/apis/merchant'

const textMessage = [
  '1. 我司只接受 6% 服务费专票',
  '2. 代理商同一手机号不可同时开通代理商账户和用人单位账户',
  '3. 我司收到发票后 1-3 个工作日发放佣金',
]

const TIPS = `由于银行对公转账的相关限制，费用发放在工作日内：周一 至 周五 09:00 ~
16:00之间进行，超过该时间顺延到第二个工作日办理，如需按时发放请提前做好相关工作准备。`

type CanvasType = HTMLDivElement | HTMLCanvasElement

// 员工无法看到该页面
const Dashboard = () => {
  const [form] = Form.useForm()
  const chartRef = useRef<CanvasType>(null)
  const [visible, setVisible] = useState(false)
  const [userInfo, setUserInfo] = useState<any>({})
  const [isLoaded, setLoaded] = useState<boolean>(false)
  // 代理商返佣
  const [rebateList, setRebateList] = useState<any[]>([])
  const [rebateMoney, setRebateMoney] = useState(0)
  const [balance, setBalance] = useState(0)
  // 公司发放
  const [issuesList, setIssuesList] = useState<any[]>([])
  const [issuesMoney, setIssuesMoney] = useState(0)
  // 图表
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
    RoleStore.currentRole?.roleType !== AuthType.ADMIN && renderChart()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  // 代理商初始化请求
  useEffect(() => {
    if (RoleStore.currentRole?.roleType === AuthType.MERCHANT) {
      fetchRebateByMonth(AuthType.MERCHANT)
      fetchBalanceMoney()
    }
  }, [])

  // 公司初始化请求
  useEffect(() => {
    if (RoleStore.currentRole?.roleType === AuthType.COMPANY) {
      fetchRebateByMonth(AuthType.COMPANY)
      fetchBalanceMoney()
    }
  }, [])

  useEffect(() => {
    setLoaded(true)
    const userInfo = RoleStore.currentRole?.userInfo
    setUserInfo(userInfo ? userInfo : {})
    setLoaded(false)
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [RoleStore.currentRole?.roleType])

  const goFinance = () => {
    if (RoleStore.currentRole?.roleType === AuthType.MERCHANT) {
      history.push('/main/finance/withdraw')
    } else {
      history.push('/main/finance/recharge')
    }
  }


  const fetchRebateByMonth = async (type: AuthType) => {
    try {
      const { data } = await fetchMerchantRebate()
      if (data.length) {
        if (type === AuthType.MERCHANT) {
          setRebateList(data)
          setRebateMoney(data.reduce((a: any, b: any) => a.amount + b.rebate))
        } else {
          setIssuesList(data)
          setIssuesMoney(data.reduce((a: any, b: any) => a.amount + b.amount))
        }
      }
    } catch (error) {
      handleError(error)
    }
  } 

  const fetchBalanceMoney = async () => {
    try {
      const { data } = await fetchMerchantBalance()
      setBalance(data)
    } catch (error) {
      handleError(error)
    }
  }

  const goFinanceList = () => {
    if (RoleStore.currentRole?.roleType === AuthType.MERCHANT) {
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
        const { data } = await updateUserInfo({ ...userInfo, ...values })
        setUserInfo(data)
        message.success('信息修改成功')
      } else {
        // insert
        const { data } = await insertUserInfo(values, AuthType.ADMIN)
        setUserInfo(data)
        message.success('创建信息成功')
      }
    } catch (error) {
      handleError(error)
    }
    setVisible(false)
  }

  const openEditDialog = () => {
    form.setFieldsValue({ ...userInfo })
    setVisible(true)
  }

  return useObserver(() => (
    <Spin spinning={isLoaded && RoleStore.currentRole?.roleType}>
      {RoleStore.currentRole?.roleType !== 'ADMIN' && (
        <Card className={style['dash-header']}>
          <div className={style['money-title']}>账户可用余额</div>
          <div className={style['money']}>￥{balance}</div>
          <div className={style['button-group']}>
            <Button type="primary" onClick={goFinance}>
              {RoleStore.currentRole?.roleType === AuthType.MERCHANT ? '申请提现' : '立即充值'}
            </Button>
            <Button type="default" className={style['button-right']} onClick={goFinanceList}>
              查看明细
            </Button>
          </div>
          <div className={style['right-text']}>
            {RoleStore.currentRole?.roleType === AuthType.MERCHANT &&
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
          <Col span={RoleStore.currentRole?.roleType !== 'ADMIN' ? 18 : 24}>
            <Card>
              {RoleStore.currentRole?.roleType === 'ADMIN' && (
                <Button className={style['edit']} type="primary" onClick={openEditDialog}>
                  编辑用户信息
                </Button>
              )}
              <Descriptions title="基本信息">
                {Object.keys(userInfo).length > 0 && userInfo.contactName && (
                  <>
                    <Descriptions.Item label="对接人">
                      <span className={style['item-title']}>{userInfo?.contactName}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="对接手机号">
                      <span className={style['item-title']}>{userInfo?.contactTel}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="公司名称">
                      <span className={style['item-title']}>{userInfo?.companyName}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="公司税号">
                      <span className={style['item-title']}>{userInfo?.companyTax}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="公司固话">
                      <span className={style['item-title']}>{userInfo?.companyFixedTel}</span>
                    </Descriptions.Item>
                    {RoleStore.currentRole?.roleType !== 'ADMIN' && (<Descriptions.Item label="费率">
                      <span className={style['item-title']}>{userInfo?.companyRate ?? 0}%</span>
                    </Descriptions.Item>)}
                    <Descriptions.Item label="所属行业">
                      <span className={style['item-title']}>{userInfo?.companyIndustry}</span>
                    </Descriptions.Item>
                    <Descriptions.Item label="公司地址">
                      <span className={style['item-title']}>{userInfo?.companyLocation}</span>
                    </Descriptions.Item>
                  </>
                )}
              </Descriptions>
              <Divider />
              <Descriptions>
                <Descriptions.Item label="收件人">
                  <span className={style['item-title']}>{userInfo?.recipientName}</span>
                </Descriptions.Item>
                <Descriptions.Item label="收件人手机号">
                  <span className={style['item-title']}>{userInfo?.recipientTel}</span>
                </Descriptions.Item>
                <Descriptions.Item label="收件地址">
                  <span className={style['item-title']}>{userInfo?.recipientAddress}</span>
                </Descriptions.Item>
                <Descriptions.Item label="开户行">
                  <span className={style['item-title']}>{userInfo.companyBankName}</span>
                </Descriptions.Item>
                <Descriptions.Item label="银行账号">
                  <span className={style['item-title']}>{userInfo?.companyBankNumber}</span>
                </Descriptions.Item>
              </Descriptions>
              {/* Echart */}
              {RoleStore.currentRole?.roleType !== AuthType.ADMIN && RoleStore.currentRole?.roleType !== AuthType.EMPLOYEE && (
                <>
                  <div className={style['canvas-title']}>
                    {RoleStore.currentRole?.roleType === AuthType.COMPANY ? '最近一个月发放情况' : '最近一个月返佣情况'}
                  </div>
                  <div style={{ width: '100%', height: '300px' }} ref={chartRef as any} />
                </>
              )}
            </Card>
          </Col>
          {RoleStore.currentRole?.roleType !== 'ADMIN' && (
            <Col span={6}>
              {RoleStore.currentRole?.roleType === AuthType.COMPANY && (
                <Card title="公告">{TIPS}</Card>
              )}
              <Card style={{ margin: '20px 0' }}>
                <div className={style['money-title']}>
                  {RoleStore.currentRole?.roleType === AuthType.COMPANY ? '最近一个月发放金额' : '最近一个月返佣金额'}
                </div>
                <div className={style['money']}>￥{RoleStore.currentRole?.roleType === AuthType.COMPANY ? issuesMoney : rebateMoney} 元</div>
              </Card>
              <Card>
                <div className={style['money-title']}>
                  {RoleStore.currentRole?.roleType === AuthType.COMPANY ? '最近一个月发放次数' : '最近一个月返佣次数'}
                </div>
                <div className={style['money']}>{RoleStore.currentRole?.roleType === AuthType.COMPANY ? issuesList.length : rebateList.length} 人</div>
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
          <Row gutter={24}>
            <Col span={8}>
              <Form.Item label="对接人" name="contactName" rules={[{ required: true, message: '请输入对接人' }]}>
                <Input placeholder="请输入对接人"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="对接人手机号" name="contactTel" rules={[{ required: true, message: '请输入对接人手机号' }]}>
                <Input placeholder="请输入对接人手机号"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="公司名称" name="companyName" rules={[{ required: true, message: '请输入公司名称' }]}>
                <Input placeholder="请输入公司名称"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="公司税号" name="companyTax" rules={[{ required: true, message: '请输入公司税号' }]}>
                <Input placeholder="请输入公司税号"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="公司固话"
                name="companyFixedTel"
                rules={[{ required: true, message: '请输入公司固定电话' }]}
              >
                <Input placeholder="请输入公司固定电话"></Input>
              </Form.Item>
            </Col>
            
            <Col span={8}>
            {RoleStore.currentRole?.roleType !== 'ADMIN' && (<Form.Item label="费率" name="companyRate" rules={[{ required: true, message: '请输入费率' }]}>
                <Input placeholder="请输入费率"></Input>
              </Form.Item>)}
              
            </Col>
            <Col span={8}>
              <Form.Item label="所属行业" name="companyIndustry" rules={[{ required: true, message: '请输入所属行业' }]}>
                <Input placeholder="请输入所属行业"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="公司地址" name="companyLocation" rules={[{ required: true, message: '请输入公司地址' }]}>
                <Input placeholder="请输入公司地址"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="收件人" name="recipientName" rules={[{ required: true, message: '请输入收件人姓名' }]}>
                <Input placeholder="请输入收件人姓名"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="收件人手机号"
                name="recipientTel"
                rules={[{ required: true, message: '请输入收件人手机号' }]}
              >
                <Input placeholder="收件人手机号"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="开户行" name="companyBankName" rules={[{ required: true, message: '请输入开户行' }]}>
                <Input placeholder="请输入开户行"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="银行账号" name="companyBankNumber" rules={[{ required: true, message: '请输入银行账号' }]}>
                <Input placeholder="请输入银行账号"></Input>
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item label="收件地址" name="recipientAddress" rules={[{ required: true, message: '请输入收件人地址' }]}>
                <Input placeholder="请输入收件人地址"></Input>
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Modal>
    </Spin>
  ))
}

export default Dashboard
