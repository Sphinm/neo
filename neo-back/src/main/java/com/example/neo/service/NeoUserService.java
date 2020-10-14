package com.example.neo.service;

import com.example.neo.mybatis.model.NeoUser;

import java.util.List;

/**
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/9/29 16:26
 */
public interface NeoUserService {
    /*
     * 手机号/邮箱找到用户实体
     */
    NeoUser findUserByMobileOrEmail(String userName);

    List<String> findPermissions(String userName);
}
