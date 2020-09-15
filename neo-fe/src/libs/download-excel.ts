import * as XLSX from 'xlsx'
import { WritingOptions } from 'xlsx'
import moment from 'moment'

/**
 * 下载 excel
 * @param data 需要下载的表格数据
 * @param colunm 摘出需要的字段 默认下载全部
 * @param name 导出文件的名称前缀
 *
 */
export const downloadExcel = (data: any[], colunm?: any, name: string = '文件') => {
  const json = data.map(item => {
    return Object.keys(item).reduce((pre: any, cur: any) => {
      const preKey = colunm?.cur || cur
      pre[preKey] = item[cur]
      return pre
    }, {})
  })
  const sheet = XLSX.utils.json_to_sheet(json)

  openDownloadDialog(sheet2blob(sheet), `${name}_${moment().format('YYYY-MM-DD')}.csv`)
}

export const openDownloadDialog = (url: any, saveName?: string) => {
  let event
  if (typeof url === 'object' && url instanceof Blob) {
    url = URL.createObjectURL(url) // 创建 blob 地址
  }
  const aLink = document.createElement('a')
  aLink.href = url
  // 注意模拟 a 标签 download 仅限于同源域名 非同源的图片资源会打开
  aLink.download = saveName || url
  if (window.MouseEvent) {
    event = new MouseEvent('click')
  } else {
    event = document.createEvent('MouseEvents')
    event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null)
  }
  aLink.dispatchEvent(event)
}

const sheet2blob = (sheet: any, sheetName?: any) => {
  sheetName = sheetName || 'sheet1'
  const workbook = {
    SheetNames: [sheetName],
    Sheets: {} as any,
  }
  workbook.Sheets[sheetName] = sheet // 生成excel的配置项

  const wopts: WritingOptions = {
    bookType: 'csv',
    bookSST: false,
    type: 'binary',
  }
  const wbout = XLSX.write(workbook, wopts)
  const blob = new Blob([s2ab(wbout)], {
    type: 'application/octet-stream',
  })

  return blob
}

/**
 *  字符串转ArrayBuffer
 */
const s2ab = (s: string) => {
  const buf = new ArrayBuffer(s.length)
  const view = new Uint8Array(buf)
  for (let i = 0; i !== s.length; ++i) view[i] = s.charCodeAt(i) & 0xff
  return buf
}
