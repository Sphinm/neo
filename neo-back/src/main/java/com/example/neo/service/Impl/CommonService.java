package com.example.neo.service.Impl;

import com.example.neo.mybatis.mapper.NeoCompanyMapper;
import com.example.neo.mybatis.mapper.NeoUserMapper;
import com.example.neo.mybatis.model.NeoCompany;
import com.example.neo.mybatis.model.NeoCompanyExample;
import com.example.neo.mybatis.model.NeoUser;
import com.example.neo.mybatis.model.NeoUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonService {
    @Autowired
    private NeoUserMapper neoUserMapper;
    @Autowired
    private NeoCompanyMapper companyMapper;


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

    /**
     * 根据 userId 获取用户信息
     *
     * @return user
     */
    public NeoCompany fetchCompanyByUserId(Integer id) {
        NeoUser users = neoUserMapper.selectByPrimaryKey(id);
        return companyMapper.selectByPrimaryKey(users.getRelatedId());
    }

    /**
     * 获取当前用户关联公司信息
     * @return
     */
    public NeoCompany fetchCurrentCompany(){
        NeoUser user = fetchUserByMobile();
        NeoCompanyExample companyExample = new NeoCompanyExample();
        companyExample.createCriteria().andIdEqualTo(user.getRelatedId());
        List<NeoCompany> companies = companyMapper.selectByExample(companyExample);
        if (companies==null||companies.size()==0){
            return null;
        }
        return companies.get(0);
    }

    /**
     * 根据 companyId 查询 companyName
     */
    public String fetchCompanyNameById(int id) {
        NeoCompany company = companyMapper.selectByPrimaryKey(id);
        if (company == null){
            return "";
        }
        return company.getCompanyName();
    }

}
