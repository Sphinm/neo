package com.example.neo.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class IInvoice {
    ArrayList<String> orderNumberList;
    Double totalMoney;
    String invoiceContent;
    Boolean invoiceType;
    String recipientName;
    String recipientTel;
    String recipientAddress;
}
