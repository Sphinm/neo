package com.example.neo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    Integer id;
    String account;
    String userName;
    String mobile;
    String password;
    String email;
    /**
     * 是否锁定（0:未锁定，1:锁定）
     */
    Integer isLocked;
    /**
     * 角色id，关联neo_role表id
     */
    Integer roleId;
    /**
     * 关联id，关联neo_company表id
     */
    String relatedId;

    String creatorId;
    String updateId;
    Date createDate;
    Date updateDate;
}
