package com.example.neo.service.Impl;

import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.ICreateUser;
import com.example.neo.model.IGetUser;
import com.example.neo.mybatis.mapper.NeoRoleMapper;
import com.example.neo.mybatis.mapper.NeoUserMapper;
import com.example.neo.mybatis.mapper.NoCompanyMapper;
import com.example.neo.mybatis.model.*;
import com.example.neo.service.UserService;
import com.example.neo.utils.ContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private NeoUserMapper neoUserMapper;
    @Autowired
    private NeoRoleMapper neoRoleMapper;
    @Autowired
    private NoCompanyMapper companyMapper;

    public IGetUser findByMobile(String mobile) {
        IGetUser u = new IGetUser();
        NeoUserExample userExample = new NeoUserExample();
        userExample.createCriteria().andMobileEqualTo(mobile);
        List<NeoUser> users = neoUserMapper.selectByExample(userExample);
        if (users == null || users.size() != 1) {
            return null;
        }
        NeoUser user = users.get(0);
        NoCompanyExample companyExample = new NoCompanyExample();
        companyExample.createCriteria().andIdEqualTo(user.getId());
        List<NoCompany> companyInfo = companyMapper.selectByExample(companyExample);
        if (companyInfo != null && companyInfo.size() == 1) {
            u.setUserInfo(companyInfo.get(0));
        }

        NeoRoleExample roleExample = new NeoRoleExample();
        roleExample.createCriteria().andIdEqualTo(user.getRoleId());
        List<NeoRole> roles = neoRoleMapper.selectByExample(roleExample);
        if (roles != null && roles.size() == 1) {
            u.setRoleName(roles.get(0).getRoleName());
            u.setRoleType(roles.get(0).getRoleType());
        }
        u.setUserName(user.getUsername());
        u.setEmail(user.getEmail());
        u.setMobile(user.getMobile());
        u.setIsLocked(user.getIsLocked() ? 1 : 0);
        return u;
    }

    /**
     * 创建用户的时候先判断用户有无公司信息，如果没有则不创建公司信息，同时无关联id
     *
     * 涉及到的表有 neo_user、neo_company、neo_company_relation 和 neo_employee 四张表
     * neo_user 表是所有角色都要的
     * neo_company 是非员工角色可以创建的，但是在创建用户的时候可以缺省（原则上一次填完，可修改）
     * neo_company_relation 记录非员工角色的关联关系（管理员 - 代理商，代理商 - 公司， 管理员 - 公司）
     * neo_employee 记录员工角色与公司的关联关系
     */
    public void createUser(ICreateUser user, UserTypeEnum userType) {
        Date date = new Date();
        NeoUserExample userDto = new NeoUserExample();
        int insertId = -1;
        if (user.getCompanyName() == null || userType != UserTypeEnum.EMPLOYEE) {
            NoCompanyExample companyInfo = new NoCompanyExample();
            insertId = insertUserInfo(companyInfo, userType);
        }

        String userId = ContextHolder.getCurrentUserId();

        // 创建用户表
//        userDto.setAccount(user.getAccount());
//        userDto.setEmail(user.getEmail());
//        userDto.setUserName(user.getUserName());
//        userDto.setMobile(user.getMobile());
//        userDto.setRoleId(userType.getId());
//        userDto.setRelatedId(insertId > 0 ? insertId : null);
//        userDto.setCreatorId(!StringUtils.isEmpty(userId) ? Integer.parseInt(userId) : null);
//        userDto.setUpdateId(!StringUtils.isEmpty(userId) ? Integer.parseInt(userId) : null);
//        userDto.setCreateDate(date);
//        userDto.setUpdateDate(date);
//
//        try {
//            userMapper.createUser(userDto);
//        } catch (RuntimeException e) {
//            throw new RuntimeException("用户创建失败");
//        }
    }

    /**
     * 创建公司用户信息
     */
    @Override
    public int insertUserInfo(NoCompanyExample companyInfo, UserTypeEnum userType) {
        Date date = new Date();
        NoCompanyExample userDo = keepUserInfo(companyInfo);
//        userDo.setCreatorId(companyInfo.getCreatorId());
//        userDo.setCreateDate(date);
//        userDo.setCompanyStatus(userType == UserTypeEnum.ADMIN ? 1 : 0);
//        userDo.setCompanyType(userType.getId());
//        log.info("insertUserInfo ===> {}", userDo);
//        return userInfoMapper.insertUserInfo(userDo);
        return 1;
    }

    /**
     * 更新公司用户信息
     */
    @Override
    public void updateUserInfo(NoCompanyExample companyInfo) {
        NoCompanyExample userDo = keepUserInfo(companyInfo);
//        userDo.setId(companyInfo.getId());
//        userDo.setCreatorId(companyInfo.getCreatorId());
//        userDo.setCreateDate(companyInfo.getCreateDate());
//        log.info("updateUserInfo ==========> {}", userDo);
//        userInfoMapper.updateUserInfo(userDo);
    }

    private NoCompanyExample keepUserInfo(NoCompanyExample companyInfo) {
        Date date = new Date();
        NoCompanyExample userDo = new NoCompanyExample();
//        userDo.setCompanyName(companyInfo.getCompanyName());
//        userDo.setCompanyTax(companyInfo.getCompanyTax());
//        userDo.setCompanyLocation(companyInfo.getCompanyLocation());
//        userDo.setCompanyBankName(companyInfo.getCompanyBankName());
//        userDo.setCompanyBankNumber(companyInfo.getCompanyBankNumber());
//        userDo.setCompanyIndustry(companyInfo.getCompanyIndustry());
//        userDo.setCompanyRate(companyInfo.getCompanyRate());
//        userDo.setCompanyFixedTel(companyInfo.getCompanyFixedTel());
//        userDo.setContactName(companyInfo.getContactName());
//        userDo.setContactTel(companyInfo.getContactTel());
//        userDo.setRecipientName(companyInfo.getRecipientName());
//        userDo.setRecipientTel(companyInfo.getRecipientTel());
//        userDo.setRecipientAddress(companyInfo.getRecipientAddress());
//        userDo.setCompanyStatus(companyInfo.getCompanyStatus());
//        userDo.setCompanyType(companyInfo.getCompanyType());
//        userDo.setUpdateId(companyInfo.getUpdateId());
//        userDo.setUpdateDate(date);
        return userDo;
    }
}
