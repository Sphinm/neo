package com.example.neo.security;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.springframework.security.core.GrantedAuthority;

import java.util.Map;

/**
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/10/9 13:34
 */
public class UserGrantedAuthority implements GrantedAuthority {

    private Map<String, Object> authoritys = Maps.newHashMap();

    public UserGrantedAuthority(String name, Object value){
        authoritys.put(name,value);
    }

    @Override
    public String getAuthority() {
        return JSON.toJSONString(authoritys);
    }
}
