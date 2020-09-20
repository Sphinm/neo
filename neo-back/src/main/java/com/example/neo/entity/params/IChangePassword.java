package com.example.neo.entity.params;

import lombok.Data;

@Data
public class IChangePassword {
    private String oldPwd;
    private String newPwd;
}

