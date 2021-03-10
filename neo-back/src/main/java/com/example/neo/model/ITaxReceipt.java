package com.example.neo.model;

import lombok.Data;

import java.util.Date;

@Data
public class ITaxReceipt {
    String number;
    Date createDate;
    String companyName;
    String taxReceive;
    Date month;
    String remark;
}
