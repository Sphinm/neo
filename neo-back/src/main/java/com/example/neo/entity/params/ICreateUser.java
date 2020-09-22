package com.example.neo.entity.params;

import com.example.neo.enums.UserStatusEnum;
import com.example.neo.enums.UserTypeEnum;
import lombok.Data;

@Data
public class ICreateUser {
    private long userId;
    private UserTypeEnum role;
    private UserStatusEnum status;

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
}
