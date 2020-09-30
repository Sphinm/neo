package com.example.neo.model;

import lombok.Data;

import java.util.Date;

@Data
public class ICreateUser {
    // user
    String account;
    String userName;
    String mobile;
    String password;
    String email;
    Integer isLocked;
    Integer roleId;
    String roleName;
    String roleType;
    Integer relatedId;
    Integer creatorId;
    Integer updateId;
    Date createDate;
    Date updateDate;

    // companyInfo
    String companyName;
    String companyTax;
    String companyLocation;
    String companyBankName;
    String companyBankNumber;
    String companyIndustry;
    String companyRate;
    String companyFixedTel;
    String contactName;
    String contactTel;
    String recipientName;
    String recipientTel;
    String recipientAddress;
    String companyStatus;
    String companyType;
}
