package com.example.neo.model;

import lombok.Data;

@Data
public class IChangePassword {
    private String oldPwd;
    private String newPwd;
}

