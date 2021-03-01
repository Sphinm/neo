package com.example.neo.model;

import lombok.Data;

import java.util.Date;

@Data
public class IUploadTaxInfo {
    Integer companyId;
    Date month;
    String remarks;
    String receipts;
}
