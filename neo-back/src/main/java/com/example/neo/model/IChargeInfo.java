package com.example.neo.model;

import lombok.Data;

/**
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/11/5 15:01
 */
@Data
public class IChargeInfo {
    //订单号
    String orderNumber;
    //公司名
    String companyName;
    //打款金额
    Double paymentAmount;
    //费率
    float rate;
    //充值金额
    Double accountAmount;
    //打款凭证
    String paymentVoucher;
    //开票状态
    Boolean invoicingStatus;
    //审核状态
    Boolean approvalStatus;

}
