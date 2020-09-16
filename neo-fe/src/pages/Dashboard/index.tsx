import React, { useEffect, useRef, useState } from 'react'
import { RoleStore } from '@/store/roleStore'
import { useObserver } from 'mobx-react'
import { Card, Button, Row, Col, Descriptions, Modal, Input, Form } from 'antd'
import history from '@/libs/history'
import echarts from 'echarts/lib/echarts'
import 'echarts/lib/chart/line'
import { options } from '@/setting/echartConfig'
import style from './index.styl'

const userInfo = [
  { key: 'userName', filed: '对接人', value: '测试111' },
  { key: 'bossMobile', filed: '对接手机号', value: '13651608916' },
  { key: 'companyName', filed: '公司名称', value: '上海昂彻网络科技有限公司' },
  { key: 'companyTax', filed: '公司税号', value: '91310112MA1GBT7K0C' },
  { key: 'companyPhone', filed: '公司电话', value: '021-64500866' },
  { key: 'rate', filed: '费率', value: '12%' },
  { key: 'industry', filed: '所属行业', value: '人力资源服务' },
  { key: 'companyAddress', filed: '公司地址', value: '上海市闵行区506室' },
  { key: 'mailingName', filed: '收件人', value: '特殊222' },
  { key: 'mailingMobile', filed: '收件人手机号', value: '13651608916' },
  { key: 'mailingAddress', filed: '收件地址', value: '上海市闵行区剑川路951号5幢507室' },
  { key: 'bankName', filed: '开户行', value: '上海银行江川路支行' },
  { key: 'bankCardNo', filed: '银行账号', value: '620522002192085991' },
]

type CanvasType = HTMLDivElement | HTMLCanvasElement

// 员工无法看到该页面
const Dashboard = () => {
  const [form] = Form.useForm()
  const chartRef = useRef<CanvasType>(null)
  const [visible, setVisible] = useState(false)
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
    renderChart()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  useEffect(() => {
    RoleStore.fetchCurrentRole()
  }, [])

  const goFinance = () => {
    history.push('/main/finance/recharge')
  }

  const goFinanceList = () => {
    history.push('/main/finance/recharge-records')
  }

  const handleOk = () => {
    setVisible(false)
  }

  return useObserver(() => (
    <>
      {RoleStore.currentRole?.role !== 'ADMIN' && (
        <Card>
          <div className={style['money-title']}>账户可用余额</div>
          <div className={style['money']}>￥100.24</div>
          <div className={style['button-group']}>
            <Button type="primary" onClick={goFinance}>
              立即充值
            </Button>
            <Button type="default" className={style['button-right']} onClick={goFinanceList}>
              查看明细
            </Button>
          </div>
        </Card>
      )}
      <div className={style['card-wrapper']}>
        <Row gutter={24}>
          <Col span={RoleStore.currentRole?.role !== 'ADMIN' ? 18 : 24}>
            <Card>
              {RoleStore.currentRole?.role === 'ADMIN' && (
                <Button className={style['edit']} type="primary" onClick={() => setVisible(true)}>
                  编辑
                </Button>
              )}
              <Descriptions title="基本信息">
                {userInfo.map(item => {
                  return (
                    <Descriptions.Item key={item.key} label={item.filed}>
                      <span className={style['item-title']}>{item.value}</span>
                    </Descriptions.Item>
                  )
                })}
              </Descriptions>
              {/* Echart */}
              <div className={style['canvas-title']}>最近一个月发放情况</div>
              <div style={{ width: '100%', height: '300px' }} ref={chartRef as any} />
            </Card>
          </Col>
          {RoleStore.currentRole?.role !== 'ADMIN' && (
            <Col span={6}>
              <Card title="公告">
                由于银行对公转账的相关限制，费用发放在工作日内：周一 至 周五 09:00 ~
                16:00之间进行，超过该时间顺延到第二个工作日办理，如需按时发放请提前做好相关工作准备。
              </Card>
              <Card style={{ margin: '20px 0' }}>
                <div className={style['money-title']}>最近一个月发放金额</div>
                <div className={style['money']}>￥100.24 元</div>
              </Card>
              <Card>
                <div className={style['money-title']}>最近一个月发放人数</div>
                <div className={style['money']}>￥100 人</div>
              </Card>
            </Col>
          )}
        </Row>
      </div>
      <Modal
        getContainer={false}
        title="编辑用户信息"
        visible={visible}
        onOk={handleOk}
        onCancel={() => setVisible(false)}
        footer={[
          <Button key="back" onClick={() => setVisible(false)}>
            取消
          </Button>,
          <Button key="submit" type="primary" onClick={handleOk}>
            提交
          </Button>,
        ]}
      >
        <Form form={form} initialValues={userInfo}>
          <Form.Item label="对接人" name="username">
            <Input placeholder="请输入手机号"></Input>
          </Form.Item>
          <Form.Item label="对接手机号" name="username">
            <Input placeholder="请输入手机号"></Input>
          </Form.Item>
          <Form.Item label="公司名称" name="username">
            <Input placeholder="请输入手机号"></Input>
          </Form.Item>
          <Form.Item label="公司税号" name="username">
            <Input placeholder="请输入手机号"></Input>
          </Form.Item>
          <Form.Item label="对接人" name="username">
            <Input placeholder="请输入手机号"></Input>
          </Form.Item>
          <Form.Item label="对接人" name="username">
            <Input placeholder="请输入手机号"></Input>
          </Form.Item>
          <Form.Item label="对接人" name="username">
            <Input placeholder="请输入手机号"></Input>
          </Form.Item>
          <Form.Item label="对接人" name="username">
            <Input placeholder="请输入手机号"></Input>
          </Form.Item>
          <Form.Item label="对接人" name="username">
            <Input placeholder="请输入手机号"></Input>
          </Form.Item>
          <Form.Item label="对接人" name="username">
            <Input placeholder="请输入手机号"></Input>
          </Form.Item>
        </Form>
      </Modal>
    </>
  ))
}

export default Dashboard
