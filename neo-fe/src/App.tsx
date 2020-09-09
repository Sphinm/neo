import React from 'react'
import { Router } from 'react-router-dom'
import history from '@/libs/history'
import RoutesEntry from '@/pages/router.entry'

class App extends React.Component {
  render() {
    return (
      <>
        <Router history={history}>
          <RoutesEntry />
        </Router>
      </>
    )
  }
}
export default App
