package com.example.neo.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    Integer id;
    /**
     * TODO 暂时不需要
     * 用户名
     */
    String account;

    /**
     * 用户名
     */
    String userName;
    String mobile;
    String password;

    /**
     * 角色id，关联neo_role表id
     */
    Integer role_id;
    /**
     * 关联id，关联neo_company表id
     */
    Integer related_id;
    /**
     * 是否锁定（0:未锁定，1:锁定）
     */
    Integer is_locked;
    Integer creator_id;
    Integer update_id;
    Timestamp creator_date;
    Timestamp update_date;
}
