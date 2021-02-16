import React from 'react'
import { Spin } from 'antd'
import style from './index.styl'

const Loading = () => {
  return (
    <div className={style['loading_container']}>
      <Spin size="large" />
      <span>Loading...</span>
    </div>
  )
}

export default Loading
