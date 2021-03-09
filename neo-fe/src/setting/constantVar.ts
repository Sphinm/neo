/** 任务列表 */
export const taskOptions = ['软件开发协作团队', '测试团队']

export const billOptions = [
  {
    value: '研发技术服务',
    label: '研发技术服务',
    children: [
      {
        value: '技术咨询服务',
        label: '技术咨询服务',
      },
    ],
  },
  {
    value: '信息技术服务',
    label: '信息技术服务',
    children: [
      {
        value: '信息服务费',
        label: '信息服务费',
      },
      {
        value: '软件开发服务',
        label: '软件开发服务',
      },
      {
        value: '软件咨询服务',
        label: '软件咨询服务',
      },
      {
        value: '技术维护服务',
        label: '技术维护服务',
      },
      {
        value: '电路设计服务',
        label: '电路设计服务',
      },
      {
        value: '电路测试服务',
        label: '电路测试服务',
      },
      {
        value: '相关电路技术支持服务服务',
        label: '相关电路技术支持服务服务',
      },
    ],
  },
]

export const typeOptions = [{
  value: 0,
  label: '增值税普通发票',
}, {
  value: 1,
  label: '增值税专用发票'
}]
