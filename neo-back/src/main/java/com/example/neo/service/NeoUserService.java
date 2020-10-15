package com.example.neo.service;

import com.example.neo.mybatis.model.NeoUser;

import java.util.List;

/**
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/9/29 16:26
 */
public interface NeoUserService {
    /**
     * 手机号/邮箱找到用户实体
     * @param userName
     * @return
     */
    NeoUser findUserByMobileOrEmail(String userName);

    /**
     * 根据用户名找到权限信息
     * @param userName
     * @return
     */
    List<String> findPermissions(String userName);
}
