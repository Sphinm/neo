package com.example.neo.service.Impl;

import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.ICreateUser;
import com.example.neo.model.IGetUser;
import com.example.neo.mybatis.mapper.NeoRoleMapper;
import com.example.neo.mybatis.mapper.NeoUserMapper;
import com.example.neo.mybatis.mapper.NoCompanyMapper;
import com.example.neo.mybatis.model.*;
import com.example.neo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Autowired
    private CommonService commonService;

    /**
     * 获取用户信息
     */
    public IGetUser fetchUserInfo() {
        IGetUser u = new IGetUser();
        NeoUser user = commonService.fetchUserByMobile();
        NoCompanyExample companyExample = new NoCompanyExample();
        companyExample.createCriteria().andCreatorIdEqualTo(user.getId());
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
     * 涉及到的表有 neo_user、neo_company、neo_company_relation 和 neo_employee 四张表
     * neo_user 表是所有角色都要的
     * neo_company 是非员工角色可以创建的，但是在创建用户的时候可以缺省（原则上一次填完，可修改）
     * neo_company_relation 记录非员工角色的关联关系（管理员 - 代理商，代理商 - 公司， 管理员 - 公司）
     * neo_employee 记录员工角色与公司的关联关系
     */
    public void createUser(ICreateUser user, UserTypeEnum userType) {
        NeoUser neoUser = commonService.fetchUserByMobile();
        int userId = neoUser.getId();
        log.info("neoUser =====>>>", neoUser);

        // 当前用户是 admin
        if (neoUser.getRoleId() == 1) {
            switch (userType) {
                // 创建 merchant 用户，涉及 neo_user、neo_company 和 neo_company_relation 表
                case MERCHANT:
                    createInfo(user, userType, userId);
                    break;
                // 创建 company 用户，涉及 neo_user、neo_company 和 neo_company_relation 表
                case COMPANY:
                    break;
            }
        }
        // 当前用户是 merchant, 创建 company 用户，涉及 neo_user、neo_company 和 neo_company_relation 表
        if (neoUser.getRoleId() == 2 && userType == UserTypeEnum.COMPANY) {

        }

        // 当前用户是 company 且需要创建 employee 的情况，涉及 neo_user 和 neo_employee 表
        if (neoUser.getRoleId() == 3 && userType == UserTypeEnum.MERCHANT) {

        }
    }

    private void createInfo(ICreateUser user, UserTypeEnum userType, Integer userId) {
        NeoUser userDto = new NeoUser();
        Date date = new Date();
        // 公司名称不为空或者创建的用户类型不为员工则创建公司信息
        int insertId = -1;
        if (user.getCompanyName() == null || userType != UserTypeEnum.EMPLOYEE) {
            NoCompany userDo = new NoCompany();
            userDo.setCompanyName(user.getCompanyName());
            userDo.setCompanyTax(user.getCompanyTax());
            userDo.setCompanyLocation(user.getCompanyLocation());
            userDo.setCompanyBankName(user.getCompanyBankName());
            userDo.setCompanyBankNumber(user.getCompanyBankNumber());
            userDo.setCompanyIndustry(user.getCompanyIndustry());
            userDo.setCompanyRate(user.getCompanyRate());
            userDo.setCompanyFixedTel(user.getCompanyFixedTel());
            userDo.setContactName(user.getContactName());
            userDo.setContactTel(user.getContactTel());
            userDo.setRecipientName(user.getRecipientName());
            userDo.setRecipientTel(user.getRecipientTel());
            userDo.setRecipientAddress(user.getRecipientAddress());
            userDo.setCompanyStatus(user.getCompanyStatus());
            userDo.setCompanyType(user.getCompanyType());
            userDo.setUpdateId(userId);
            userDo.setUpdateDate(date);
            insertId = insertUserInfo(userDo, userType);
        }

        // 创建用户表
//        userDto.setAccount(user.getMobile()); // account 暂时不用
//        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUserName());
        userDto.setMobile(user.getMobile());
        userDto.setRoleId(userType.getId());
        userDto.setRelatedId(insertId > 0 ? insertId : null);
        userDto.setCreatorId(!StringUtils.isEmpty(userId) ? userId : null);
        userDto.setUpdateId(!StringUtils.isEmpty(userId) ? userId : null);
        userDto.setCreateDate(date);
        userDto.setUpdateDate(date);

        try {
            neoUserMapper.insert(userDto);
        } catch (RuntimeException e) {
            throw new RuntimeException("用户创建失败");
        }
    }

    /**
     * 创建公司用户信息
     */
    @Override
    public int insertUserInfo(NoCompany companyInfo, UserTypeEnum userType) {
        Date date = new Date();
        NeoUser neoUser = commonService.fetchUserByMobile();
        NoCompany company = commonUserInfo(companyInfo, neoUser);
        company.setCreatorId(neoUser.getId());
        company.setCreateDate(date);
        company.setCompanyStatus(userType == UserTypeEnum.ADMIN);
        company.setCompanyType(userType.getId() != 0);
        log.info("insertUserInfo ===> {}", company);
        return companyMapper.insert(company);
    }

    /**
     * 更新公司用户信息
     */
    @Override
    public void updateUserInfo(NoCompany companyInfo) {
        NeoUser neoUser = commonService.fetchUserByMobile();
        NoCompanyExample example = new NoCompanyExample();
        NoCompany userDo = commonUserInfo(companyInfo, neoUser);
        userDo.setId(neoUser.getId());
        userDo.setCreatorId(companyInfo.getCreatorId());
        userDo.setCreateDate(companyInfo.getCreateDate());
        log.info("updateUserInfo ==========> {}", userDo);
        companyMapper.updateByExample(userDo, example);
    }

    /**
     * 插入公共的信息
     */
    private NoCompany commonUserInfo(NoCompany companyInfo, NeoUser user) {
        Date date = new Date();
        NoCompany userDo = new NoCompany();
        userDo.setCompanyName(companyInfo.getCompanyName());
        userDo.setCompanyTax(companyInfo.getCompanyTax());
        userDo.setCompanyLocation(companyInfo.getCompanyLocation());
        userDo.setCompanyBankName(companyInfo.getCompanyBankName());
        userDo.setCompanyBankNumber(companyInfo.getCompanyBankNumber());
        userDo.setCompanyIndustry(companyInfo.getCompanyIndustry());
        userDo.setCompanyRate(companyInfo.getCompanyRate());
        userDo.setCompanyFixedTel(companyInfo.getCompanyFixedTel());
        userDo.setContactName(companyInfo.getContactName());
        userDo.setContactTel(companyInfo.getContactTel());
        userDo.setRecipientName(companyInfo.getRecipientName());
        userDo.setRecipientTel(companyInfo.getRecipientTel());
        userDo.setRecipientAddress(companyInfo.getRecipientAddress());
        userDo.setCompanyStatus(companyInfo.getCompanyStatus());
        userDo.setCompanyType(companyInfo.getCompanyType());
        userDo.setUpdateId(user.getId());
        userDo.setUpdateDate(date);
        return userDo;
    }
}
