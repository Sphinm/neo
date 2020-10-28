package com.example.neo.model;

import lombok.Data;

@Data
public class IEmployee {
    Integer id;
    String companyName;
    String userName;
    String idVerify;
    String userMobile;
    Boolean isSignUp;
}
