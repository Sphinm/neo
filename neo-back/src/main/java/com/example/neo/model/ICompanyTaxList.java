package com.example.neo.model;

import lombok.Data;

import java.util.Date;

@Data
public class ICompanyTaxList {
    int id;
    String number;
    int companyId;
    String companyName;
    Date createDate;
    Date month;
    boolean isDelete;
    String remark;
    String taxReceive;
}
