package com.example.neo.service.Impl;

import com.example.neo.entity.CompanyInfo;
import com.example.neo.entity.Role;
import com.example.neo.entity.User;
import com.example.neo.enums.UserTypeEnum;
import com.example.neo.mapper.UserInfoMapper;
import com.example.neo.mapper.UserMapper;
import com.example.neo.model.ICreateUser;
import com.example.neo.model.IGetUser;
import com.example.neo.mybatis.mapper.NeoRoleMapper;
import com.example.neo.mybatis.mapper.NeoUserMapper;
import com.example.neo.mybatis.model.NeoRole;
import com.example.neo.mybatis.model.NeoRoleExample;
import com.example.neo.mybatis.model.NeoUser;
import com.example.neo.mybatis.model.NeoUserExample;
import com.example.neo.service.UserService;
import com.example.neo.utils.ContextHolder;
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
    UserInfoMapper userInfoMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private NeoUserMapper neoUserMapper;
    @Autowired
    private NeoRoleMapper neoRoleMapper;

    public IGetUser findByMobile(String mobile) {
        IGetUser u = new IGetUser();
        NeoUserExample userExample = new NeoUserExample();
        userExample.createCriteria().andMobileEqualTo(mobile);
        List<NeoUser> users = neoUserMapper.selectByExample(userExample);
        if (users==null||users.size()!=1){
            return null;
        }
        NeoUser user = users.get(0);
        NeoRoleExample roleExample = new NeoRoleExample();
        roleExample.createCriteria().andIdEqualTo(users.get(0).getRoleId());
        List<NeoRole> roles = neoRoleMapper.selectByExample(roleExample);
        if (roles!=null&&roles.size()==1){
            u.setRoleName(roles.get(0).getRoleName());
            u.setRoleType(roles.get(0).getRoleType());
        }
        u.setUserName(user.getUsername());
        u.setEmail(user.getEmail());
        u.setMobile(user.getMobile());
        u.setIsLocked(user.getIsLocked()==true?1:0);
        return u;
    }

    /**
     * 根据 userId 获取用户信息
     *
     */
    @Override
    public CompanyInfo fetchUserInfo(String userId) {
        return userInfoMapper.fetchUserInfo(userId);
    }


    /**
     * 创建代理商和公司用户
     * 也可以创建公司和员工
     * @param user 用户信息
     * 创建用户的时候先判断用户有无公司信息，如果没有则不创建公司信息，同时无关联id
     */
    public void createUser(ICreateUser user, UserTypeEnum userType) {
        Date date = new Date();
        User userDto = new User();
        int insertId = -1;
        if (user.getCompanyName() == null || userType != UserTypeEnum.EMPLOYEE) {
            CompanyInfo companyInfo = new CompanyInfo();
            insertId = insertUserInfo(companyInfo, userType);
        }

        String userId = ContextHolder.getCurrentUserId();

        // 创建用户表
//        userDto.setAccount(user.getAccount());
//        userDto.setEmail(user.getEmail());
        userDto.setUserName(user.getUserName());
        userDto.setMobile(user.getMobile());
        userDto.setRoleId(userType.getId());
        userDto.setRelatedId(insertId > 0 ? insertId : null);
        userDto.setCreatorId(!StringUtils.isEmpty(userId) ? Integer.parseInt(userId) : null);
        userDto.setUpdateId(!StringUtils.isEmpty(userId) ? Integer.parseInt(userId) : null);
        userDto.setCreateDate(date);
        userDto.setUpdateDate(date);

        try {
            userMapper.createUser(userDto);
        } catch (RuntimeException e) {
            throw new RuntimeException("用户创建失败");
        }
    }

    /**
     * 创建公司用户信息
     *
     */
    @Override
    public int insertUserInfo(CompanyInfo companyInfo, UserTypeEnum userType) {
        Date date = new Date();
        CompanyInfo userDo = keepUserInfo(companyInfo);
        userDo.setCreatorId(companyInfo.getCreatorId());
        userDo.setCreateDate(date);
        userDo.setCompanyStatus(userType == UserTypeEnum.ADMIN ? 1 : 0);
        userDo.setCompanyType(userType.getId());
        log.info("insertUserInfo ===> {}", userDo);
       return userInfoMapper.insertUserInfo(userDo);
    }

    /**
     * 更新公司用户信息
     *
     */
    @Override
    public void updateUserInfo(CompanyInfo companyInfo) {
        CompanyInfo userDo = keepUserInfo(companyInfo);
        userDo.setId(companyInfo.getId());
        userDo.setCreatorId(companyInfo.getCreatorId());
        userDo.setCreateDate(companyInfo.getCreateDate());
        log.info("updateUserInfo ==========> {}", userDo);
        userInfoMapper.updateUserInfo(userDo);
    }

    private CompanyInfo keepUserInfo(CompanyInfo companyInfo) {
        Date date = new Date();
        CompanyInfo userDo = new CompanyInfo();
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
        userDo.setUpdateId(companyInfo.getUpdateId());
        userDo.setUpdateDate(date);
        return userDo;
    }
}
