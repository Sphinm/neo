package com.example.neo.service.Impl;

import com.example.neo.mybatis.mapper.NeoFunctionsMapper;
import com.example.neo.mybatis.mapper.NeoRoleFunctionMapper;
import com.example.neo.mybatis.mapper.NeoUserMapper;
import com.example.neo.mybatis.model.*;
import com.example.neo.service.NeoFunctionsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/10/14 13:26
 */
@Slf4j
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
    public List<NeoFunctions> getFunctionsByMobile(String mobile) {
        //找到用户
        NeoUserExample neoUserExample = new NeoUserExample();
        neoUserExample.createCriteria().andMobileEqualTo(mobile);
        List<NeoUser> neoUsers = neoUserMapper.selectByExample(neoUserExample);

        if (neoUsers == null || neoUsers.size() != 1) {
            logger.info("用户不存在，或者数量超过一个,userName = %d", mobile);
            throw new RuntimeException("用户异常");
        }
        //根据用户role_id找到function_id
        NeoUser neoUser = neoUsers.get(0);
        NeoRoleFunctionExample neoRoleFunctionExample = new NeoRoleFunctionExample();
        neoRoleFunctionExample.createCriteria().andRoleIdEqualTo(neoUser.getRoleId())
            .andIsLockedEqualTo(false);
        List<NeoRoleFunction> neoRoleFunctions = neoRoleFunctionMapper.selectByExample(neoRoleFunctionExample);
        List<Integer> functionIds = new ArrayList<>();
        for (NeoRoleFunction neoRoleFunction : neoRoleFunctions) {
            functionIds.add(neoRoleFunction.getFunctionId());
        }
        if (functionIds.size()==0){
            return null;
        }
        //根据functionid查询权限列表
        NeoFunctionsExample neoFunctionsExample = new NeoFunctionsExample();
        neoFunctionsExample.createCriteria().andIdIn(functionIds);
        return neoFunctionsMapper.selectByExample(neoFunctionsExample);

    }
}
