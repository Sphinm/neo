package com.example.neo.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CompanyInfo {
    String id;
    /**
     * 公司名称
     */
    String company_name;
    /**
     * 公司税号
     */
    String company_tax;
    /**
     * 公司地址
     */
    String company_location;
    /**
     * 开户行名称
     */
    String company_bank_name;
    /**
     * 银行账户（基本户）
     */
    String company_bank_number;
    /**
     * 所属行业
     */
    String company_industry;
    /**
     * 税率
     */
    String company_rate;
    /**
     * 公司固定电话
     */
    String company_fixed_tel;
    /**
     * 联系人姓名
     */
    String contact_name;
    /**
     * 联系人电话
     */
    String contact_tel;
    /**
     * 收件人姓名
     */
    String recipient_name;
    /**
     * 收件人电话
     */
    String recipient_tel;
    /**
     * 收件人地址
     */
    String recipient_address;
    /**
     * 公司状态(0:待定，1：审核通过)
     */
    Integer company_status;
    /**
     * 公司类型（0：代理商，1：客户公司，2：管理员）
     */
    Integer company_type;
    /**
     * 公司名称
     */
    Integer creator_id;
    Integer update_id;
    Timestamp creator_date;
    Timestamp update_date;
}
