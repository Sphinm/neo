package com.example.neo.service.Impl;

import com.example.neo.mybatis.model.NeoUser;
import com.example.neo.mybatis.model.NeoUserExample;
import com.example.neo.mybatis.model.mapper.NeoUserMapper;
import com.example.neo.service.NeoUserService;
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
public class NeoUserServiceImpl implements NeoUserService {
    private static final Logger logger = LoggerFactory.getLogger(NeoUserServiceImpl.class);
    @Autowired
    private NeoUserMapper neoUserMapper;
    @Override
    public NeoUser findUserByMobileOrEmail(String userName) {
        NeoUserExample neoUserExample = new NeoUserExample();
        NeoUserExample.Criteria c1= neoUserExample.createCriteria().andAccountEqualTo(userName);
        NeoUserExample.Criteria c2= neoUserExample.createCriteria().andEmailEqualTo(userName);
        neoUserExample.or(c2);
        List<NeoUser> results = neoUserMapper.selectByExample(neoUserExample);
        if (results==null||results.size()==0){
            logger.info("无对应用户，userName=%d",userName);
            return null;
        }
        return results.get(0);
    }

    @Override
    public List<String> findPermissions(String userName) {
        return null;
    }
}
