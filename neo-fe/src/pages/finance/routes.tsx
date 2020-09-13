import React from 'react'
import { Redirect, Route, Switch } from 'react-router'

import { Recharge } from './recharge'
import { RechargeRecords } from './rechargeRecords'
import { ApplyBill } from './applyBill'
import { BillRecords } from './billRecords'
import { TaxReceipts } from './taxReceipts'
import { Withdraw } from './withdraw'
import { WithdrawRecords } from './withdrawRecords'

const Finance = () => {
  return (
    <Switch>
      <Route exact path="/main/finance/recharge" component={Recharge} />
      <Route exact path="/main/finance/recharge-records" component={RechargeRecords} />
      <Route exact path="/main/finance/approve" component={ApplyBill} />
      <Route exact path="/main/finance/approve-records" component={BillRecords} />
      <Route exact path="/main/finance/receipts" component={TaxReceipts} />
      <Route exact path="/main/finance/withdraw" component={Withdraw} />
      <Route exact path="/main/finance/withdraw-records" component={WithdrawRecords} />
      <Route render={() => <Redirect to="/main/dashbord" />} />
    </Switch>
  )
}

export default Finance
