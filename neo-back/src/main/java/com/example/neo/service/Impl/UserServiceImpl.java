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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 获取用户信息
     */
    public IGetUser fetchUserInfo() {
        IGetUser u = new IGetUser();
        NeoUser user = commonService.fetchUserByMobile();
        NoCompanyExample companyExample = new NoCompanyExample();
        // company 主键 id 等于 user 表的 related_id
        companyExample.createCriteria().andIdEqualTo(user.getRelatedId());
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

        // 当前用户是 admin
        if (neoUser.getRoleId() == 1) {
            switch (userType) {
                // 创建 merchant 用户，涉及 neo_user、neo_company
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
        NeoUser userInfo = user.getUserInfo();
        NoCompany companyInfo = user.getCompanyInfo();
        NeoUser userDto = new NeoUser();
        Date date = new Date();

        int companyId = -1;
        // 公司名称不为空或者创建的用户类型不为员工则创建公司信息
        if (user.getCompanyInfo().getCompanyName() == null || userType != UserTypeEnum.EMPLOYEE) {
            insertUserInfo(companyInfo, userType);
            NoCompanyExample example = new NoCompanyExample();
            example.createCriteria().andContactTelEqualTo(companyInfo.getContactTel());
            List<NoCompany> companyList = companyMapper.selectByExample(example);
            if (companyList != null && companyList.size() == 1) {
                companyId = companyList.get(0).getId();
            }
        }

        // 创建用户表
        userDto.setAccount(userInfo.getMobile()); // account 暂时不用
        userDto.setEmail(userInfo.getEmail());
        userDto.setUsername(userInfo.getUsername());
        userDto.setMobile(userInfo.getMobile());
        userDto.setPassword(passwordEncoder.encode(userInfo.getPassword() != null ? userInfo.getPassword() : "123456"));
        userDto.setIsLocked(userType != null);
        userDto.setRoleId(userType.getId());
        userDto.setRelatedId(companyId);
        userDto.setCreatorId(userId);
        userDto.setUpdateId(userId);
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
    public void insertUserInfo(NoCompany companyInfo, UserTypeEnum userType) {
        Date date = new Date();
        NeoUser neoUser = commonService.fetchUserByMobile();
        NoCompany company = commonUserInfo(companyInfo, neoUser);
        company.setCreatorId(neoUser.getId());
        company.setCreateDate(date);
        // 如果是 roleId 为 1 则是管理员创建， status 为 1，表示正常，其他则需要审核
        company.setCompanyStatus(neoUser.getRoleId() == 1);
        // companyType 为2 是代理商、3是公司
        company.setCompanyType(userType.getId() == 3);
        companyMapper.insert(company);
    }

    /**
     * 更新公司用户信息
     */
    @Override
    public void updateUserInfo(NoCompany companyInfo) {
        NeoUser user = commonService.fetchUserByMobile();
        updateCommonUser(companyInfo, user, "user");
    }

    private void updateCommonUser(NoCompany companyInfo, NeoUser user, String from) {
        NoCompany userDo = commonUserInfo(companyInfo, user);
        userDo.setId(from.equals("user") ? user.getId() : companyInfo.getId());
        userDo.setCreatorId(companyInfo.getCreatorId());
        userDo.setCreateDate(companyInfo.getCreateDate());
        companyMapper.updateByPrimaryKeySelective(userDo);
    }

    /**
     * 插入公共的信息
     */
    private NoCompany commonUserInfo(NoCompany companyInfo, NeoUser user) {
        Date date = new Date();
        NoCompany company = new NoCompany();
        company.setCompanyName(companyInfo.getCompanyName());
        company.setCompanyTax(companyInfo.getCompanyTax());
        company.setCompanyLocation(companyInfo.getCompanyLocation());
        company.setCompanyBankName(companyInfo.getCompanyBankName());
        company.setCompanyBankNumber(companyInfo.getCompanyBankNumber());
        company.setCompanyIndustry(companyInfo.getCompanyIndustry());
        company.setCompanyRate(companyInfo.getCompanyRate());
        company.setCompanyFixedTel(companyInfo.getCompanyFixedTel());
        company.setContactName(companyInfo.getContactName());
        company.setContactTel(companyInfo.getContactTel());
        company.setRecipientName(companyInfo.getRecipientName());
        company.setRecipientTel(companyInfo.getRecipientTel());
        company.setRecipientAddress(companyInfo.getRecipientAddress());
        company.setCompanyStatus(companyInfo.getCompanyStatus());
        company.setCompanyType(companyInfo.getCompanyType());
        company.setUpdateId(user.getId());
        company.setUpdateDate(date);
        return company;
    }

    /**
     * 查询 merchant 信息
     * @param userType 代理商
     */
    @Override
    public List<ICreateUser> fetchMerchantInfo(UserTypeEnum userType) {
        NeoUserExample userExample = new NeoUserExample();
        NoCompanyExample example = new NoCompanyExample();
        userExample.createCriteria().andRoleIdEqualTo(userType.getId());
        // merchant 为 0 所以找个和 merchant 无关的
        example.createCriteria().andCompanyTypeEqualTo(userType == UserTypeEnum.COMPANY);
        List<NeoUser> userList = neoUserMapper.selectByExample(userExample);
        List<NoCompany> companyList = companyMapper.selectByExample(example);

        List<ICreateUser> list = new ArrayList<>();
        for (NoCompany company : companyList) {
            for (NeoUser user : userList) {
                if (user.getRelatedId().equals(company.getId()) && user.getIsLocked()) {
                    ICreateUser createUser = new ICreateUser();
                    createUser.setUserInfo(user);
                    createUser.setCompanyInfo(company);
                    list.add(createUser);
                }
            }
        }
        return list;
    }

    @Override
    public void updateMerchantInfo(ICreateUser user, UserTypeEnum userType) {
        NeoUser userInfo = user.getUserInfo();
        NoCompany companyInfo = user.getCompanyInfo();
        updateCommonUser(companyInfo, userInfo, "company");
        updateUser(userInfo);
    }

    private void updateUser(NeoUser user) {
        Date date = new Date();
        NeoUser userDto = new NeoUser();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setMobile(user.getMobile());
        userDto.setIsLocked(user.getIsLocked());
        userDto.setRoleId(user.getRoleId());
        userDto.setRelatedId(user.getRelatedId());
        userDto.setCreatorId(user.getCreatorId());
        userDto.setUpdateId(user.getUpdateId());
        userDto.setCreateDate(user.getCreateDate());
        userDto.setUpdateDate(date);
        if (user.getPassword() != null && user.getPassword().length() > 0) {
            userDto.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        neoUserMapper.updateByPrimaryKeySelective(userDto);
    }

    @Override
    public void deleteMerchantInfo(int id) {
        NeoUserExample userExample = new NeoUserExample();
        userExample.createCriteria().andRelatedIdEqualTo(id);
        List<NeoUser> userList = neoUserMapper.selectByExample(userExample);
        if (userList != null && userList.size() == 1) {
            userList.get(0).setIsLocked(userList.size() > 1);
            neoUserMapper.updateByExample(userList.get(0), userExample);
        }
    }
}
