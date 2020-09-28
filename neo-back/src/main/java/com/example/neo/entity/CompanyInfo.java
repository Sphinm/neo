package com.example.neo.entity;

import lombok.Data;

import java.util.Date;


@Data
public class CompanyInfo {
    String id;
    /**
     * 公司名称
     */
    String companyName;
    /**
     * 公司税号
     */
    String companyTax;
    /**
     * 公司地址
     */
    String companyLocation;
    /**
     * 开户行名称
     */
    String companyBankName;
    /**
     * 银行账户（基本户）
     */
    String companyBankNumber;
    /**
     * 所属行业
     */
    String companyIndustry;
    /**
     * 税率
     */
    String companyRate;
    /**
     * 公司固定电话
     */
    String companyFixedTel;
    /**
     * 联系人姓名
     */
    String contactName;
    /**
     * 联系人电话
     */
    String contactTel;
    /**
     * 收件人姓名
     */
    String recipientName;
    /**
     * 收件人电话
     */
    String recipientTel;
    /**
     * 收件人地址
     */
    String recipientAddress;
    /**
     * 公司状态(0:待定，1：审核通过)
     */
    Integer companyStatus;
    /**
     * 公司类型（0：管理员，1：代理商，2：客户公司）
     */
    Integer companyType;
    /**
     * 公司名称
     */
    Integer creatorId;
    Integer updateId;
    Date createDate;
    Date updateDate;
}
