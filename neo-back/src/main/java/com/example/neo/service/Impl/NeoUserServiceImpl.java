package com.example.neo.service.Impl;

import com.example.neo.mybatis.mapper.NeoUserMapper;
import com.example.neo.mybatis.model.NeoUser;
import com.example.neo.mybatis.model.NeoUserExample;
import com.example.neo.service.NeoUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/9/29 16:41
 */
@Service
@Slf4j
public class NeoUserServiceImpl implements NeoUserService {
    @Autowired
    private NeoUserMapper neoUserMapper;
    @Override
    public NeoUser findUserByMobile(String mobile) {
        NeoUserExample neoUserExample = new NeoUserExample();
        neoUserExample.createCriteria().andMobileEqualTo(mobile);
        List<NeoUser> results = neoUserMapper.selectByExample(neoUserExample);
        if (results==null||results.size()==0){
            log.info("无对应用户，userName=%d",mobile);
            return null;
        }
        return results.get(0);
    }

    @Override
    public List<String> findPermissions(String userName) {
        return null;
    }
}
