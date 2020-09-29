package com.example.neo.model;

import lombok.Data;

@Data
public class ICreateUser {
    // user
    String userName;
    String mobile;
    String email;
    Integer isLocked;
    String roleName;
    String roleType;
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
