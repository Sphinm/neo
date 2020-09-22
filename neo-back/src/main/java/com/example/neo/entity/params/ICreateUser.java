package com.example.neo.entity.params;

import lombok.Data;

@Data
public class ICreateUser {
    private String userId;
    private String role;
    private String status;

    private String userName;
    private String mobile;
    private String password;
    private String company;
    private String taxNumber;
    private String companyMobile;
    private String companyAddress;
    private String fixedTelephone;
    private String rate;
    private String industry;
    private String bank;
    private String bankAccount;
    private String receiverName;
    private String receiverMobile;
    private String receiverAddress;

    public void advancedUserDto(ICreateUser user) {
        employeeUserDto(user);
        this.company = user.getCompany();
        this.taxNumber = user.getTaxNumber();
        this.companyMobile = user.getCompanyMobile();
        this.companyAddress = user.getCompanyAddress();
        this.fixedTelephone = user.getFixedTelephone();
        this.rate = user.getRate();
        this.industry = user.getIndustry();
        this.bank = user.getBank();
        this.bankAccount = user.getBankAccount();
        this.receiverName = user.getReceiverName();
        this.receiverMobile = user.getReceiverMobile();
        this.receiverAddress = user.getReceiverAddress();
    }

    public void employeeUserDto(ICreateUser user) {
        this.userId = user.getUserId();
        this.role = user.getRole();
        this.status = user.getStatus();
        this.userName = user.getUserName();
        this.mobile = user.getMobile();
        this.password = user.getPassword();
    }
}
