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
