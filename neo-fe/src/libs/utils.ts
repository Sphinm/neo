import { message } from "antd"
import { RcFile } from "antd/lib/upload/interface"

// 图片类型
export enum ImageFormatEnum {
  JPG_PNG = 'JPG/PNG',
  PNG = 'PNG',
}


export  const isJpgOrPng = (imageFormat: ImageFormatEnum, file: RcFile) => {
  if (imageFormat === ImageFormatEnum.JPG_PNG) {
    return ['image/png', 'image/jpeg'].includes(file.type)
  } else if (imageFormat === ImageFormatEnum.PNG) {
    return ['image/png'].includes(file.type)
  }
  return false
}


export const beforeUpload = (file: RcFile, FileList: RcFile[]) => {
  const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png'
  if (!isJpgOrPng) {
    message.error('只能上传 JPG/PNG 图片!')
  }
  const isLt5M = file.size / 1024 / 1024 < 5
  if (!isLt5M) {
    message.error('文件大小不能超过 5M')
  }
  return isJpgOrPng && isLt5M
}
