package com.example.neo.service.Impl;

import com.example.neo.mybatis.model.*;
import com.example.neo.mybatis.model.mapper.NeoFunctionsMapper;
import com.example.neo.mybatis.model.mapper.NeoRoleFunctionMapper;
import com.example.neo.mybatis.model.mapper.NeoUserMapper;
import com.example.neo.service.NeoFunctionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/10/14 13:26
 */
@Service
public class NeoFunctionsServiceImpl implements NeoFunctionsService {
    private static final Logger logger = LoggerFactory.getLogger(NeoFunctionsServiceImpl.class);

    @Autowired
    private NeoUserMapper neoUserMapper;
    @Autowired
    private NeoRoleFunctionMapper neoRoleFunctionMapper;
    @Autowired
    private NeoFunctionsMapper neoFunctionsMapper;

    @Override
    public List<NeoFunctions> getFunctionsByUserName(String userName) {
        //找到用户
        NeoUserExample neoUserExample = new NeoUserExample();
        NeoUserExample.Criteria c1= neoUserExample.createCriteria().andAccountEqualTo(userName);
        NeoUserExample.Criteria c2= neoUserExample.createCriteria().andEmailEqualTo(userName);
        neoUserExample.or(c2);
        List<NeoUser> neoUsers = neoUserMapper.selectByExample(neoUserExample);
        if (neoUsers==null||neoUsers.size()!=1){
            logger.info("用户不存在，或者数量超过一个,userName = %d",userName);
            //TODO：抛出异常
        }
        //根据用户role_id找到function_id
        NeoUser neoUser = neoUsers.get(0);
        NeoRoleFunctionExample neoRoleFunctionExample = new NeoRoleFunctionExample();
        neoRoleFunctionExample.createCriteria().andRoleIdEqualTo(neoUser.getRoleId());
        List<NeoRoleFunction> neoRoleFunctions = neoRoleFunctionMapper.selectByExample(neoRoleFunctionExample);

        return null;
    }
}
