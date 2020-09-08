import { Suspense } from 'react'
import React from 'react'
import Loading from '@/components/Loading'

const createLazy = (factory: () => Promise<{ default: React.ComponentType<unknown> }>) => {
  const Component = React.lazy(factory)
  const LazyLoad = () => {
    return (
      <Suspense fallback={<Loading />}>
        <Component />
      </Suspense>
    )
  }
  return LazyLoad
}

export default createLazy
