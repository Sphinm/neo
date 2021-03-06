package com.example.neo.model;

import lombok.Data;

import java.util.Date;

@Data
public class IIssue {
    String companyName;
    String orderNumber;
    String taskName;
    Double amount;
    Double rebate;
    Date createDate;
}
