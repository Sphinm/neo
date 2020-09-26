package com.example.neo.entity;

import lombok.Data;

@Data
public class Role {
    Integer id;
    String roleName;
    String roleType;
    String isLocked;
}
