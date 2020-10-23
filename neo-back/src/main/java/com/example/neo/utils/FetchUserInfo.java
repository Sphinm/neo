package com.example.neo.utils;

import com.example.neo.mybatis.mapper.NeoUserMapper;
import com.example.neo.mybatis.model.NeoUser;
import com.example.neo.mybatis.model.NeoUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class FetchUserInfo {
    @Autowired
    NeoUserMapper neoUserMapper;

    /**
     * 根据手机号获取用户信息
     *
     * @return user
     */
    public NeoUser fetchUserByMobile() {
        String mobile = SecurityContextHolder.getContext().getAuthentication().getName();
        NeoUserExample userExample = new NeoUserExample();
        userExample.createCriteria().andMobileEqualTo(mobile);
        List<NeoUser> users = neoUserMapper.selectByExample(userExample);
        if (users == null || users.size() != 1) {
            return null;
        }
        return users.get(0);
    }

}
