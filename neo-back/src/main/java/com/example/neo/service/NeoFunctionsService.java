package com.example.neo.service;

import com.example.neo.mybatis.model.NeoFunctions;

import java.util.List;

/**
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/10/14 13:27
 */
public interface NeoFunctionsService {
    //根据用户名获取权限信息
    List<NeoFunctions> getFunctionsByMobile(String userName);
}
