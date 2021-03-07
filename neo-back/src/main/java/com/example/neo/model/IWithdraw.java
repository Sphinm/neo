package com.example.neo.model;

import lombok.Data;

@Data
public class IWithdraw {
    /**
     * 申请提现金额
     */
    Double withdrawMoney;
    /**
     * 快递公司
     */
    String deliveryCompany;
    /**
     * 快递单号
     */
    Integer deliveryNumber;
}
