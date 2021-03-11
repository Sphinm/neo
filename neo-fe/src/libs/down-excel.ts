import ExportJsonExcel from 'js-export-excel'
import moment from 'moment'

/**
 * 下载 excel
 * @param data 需要下载的表格数据
 * @param colunm 中文的字段
 * @param name 导出文件的名称前缀
 *
 */
export const downloadExcel = (data: any[], colunm: any, name: string = '') => {
  let table = [] 
  const json = data.map(item => {
    return Object.keys(item).reduce((pre: any, cur: any) => {
      const preKey = colunm?.cur || cur
      pre[preKey] = item[cur]
      return pre
    }, {})
  })

}
