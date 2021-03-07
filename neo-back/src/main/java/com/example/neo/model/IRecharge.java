package com.example.neo.model;

import lombok.Data;

import java.util.Date;

@Data
public class IRecharge {
    String orderNumber;
    String companyName;
    /**
     * 金额 = 充值金额 * （客户费率 - 代理商费率）
     */
    Double rebateMoney;
    Double rechargeMoney;
    Float rate;
    Date createDate;
}
