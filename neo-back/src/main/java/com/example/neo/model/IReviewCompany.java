package com.example.neo.model;

import lombok.Data;

import java.util.Date;

@Data
public class IReviewCompany {
    Integer id;
    String companyName;
    String companyTax;
    String companyAddress;
    String contactName;
    String contactTel;
    String bankName;
    String bankCode;
    Float rate;
    Boolean isChecked;
    Date checkTime;
}
