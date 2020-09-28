package com.example.neo.service.Impl;

import com.example.neo.entity.CompanyInfo;
import com.example.neo.entity.Role;
import com.example.neo.entity.User;
import com.example.neo.enums.UserTypeEnum;
import com.example.neo.mapper.UserInfoMapper;
import com.example.neo.mapper.UserMapper;
import com.example.neo.model.ICreateUser;
import com.example.neo.model.IGetUser;
import com.example.neo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserMapper userMapper;

    public IGetUser findByUserId(String userId) {
        User user = userMapper.findByUserId(userId);
        Role role = userMapper.findRoleByUserId(user.getRoleId());
//        CompanyInfo companyInfo = new CompanyInfo();
//        log.info("info1111 => {}", companyInfo);
//        if (user.getRelatedId() > 0) {
//            companyInfo = userMapper.findCompanyInfo(user.getRelatedId());
//        } else {
//            companyInfo = {}
//        }
        IGetUser u = new IGetUser();
        u.setUserName(user.getUserName());
        u.setEmail(user.getEmail());
        u.setMobile(user.getMobile());
        u.setIsLocked(user.getIsLocked());
        u.setRoleName(role.getRoleName());
        u.setRoleType(role.getRoleType());
//        u.setUserInfo(companyInfo);
        log.info("111 => {}", u);
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
        log.debug("===============>");
        if (user.getCompanyName() == null || userType != UserTypeEnum.EMPLOYEE) {
            CompanyInfo companyInfo = new CompanyInfo();

            insertUserInfo(companyInfo, userType);
        }

        // 创建用户表
        userDto.setUserName(user.getUserName());
        userDto.setMobile(user.getMobile());
        userDto.setRoleId(userType.getId());
//        userDto.setRelatedId();
//        userDto.setCreatorId();
//        userDto.setUpdateId();
        userDto.setCreateDate(date);
        userDto.setUpdateDate(date);

        try {
            userMapper.createUser(userDto);
        } catch (RuntimeException e) {
            throw new RuntimeException("用户创建失败");
        }
    }

    /**
     * 添加用户信息
     *
     */
    @Override
    public int insertUserInfo(CompanyInfo companyInfo, UserTypeEnum userType) {
        Date date = new Date();
        CompanyInfo userDo = keepUserInfo(companyInfo);
        userDo.setCreatorId(companyInfo.getCreatorId());
        userDo.setCompanyStatus(userType == UserTypeEnum.ADMIN ? 1 : 0);
        userDo.setCompanyType(userType.getId());
        userDo.setCreateDate(date);
        log.info("userInfo ===> {}", userDo);
       return userInfoMapper.insertUserInfo(userDo);
    }

    /**
     * 更新用户信息
     *
     */
    @Override
    public void updateUserInfo(CompanyInfo companyInfo) {
        CompanyInfo userDo = keepUserInfo(companyInfo);
        userDo.setCreatorId(companyInfo.getCreatorId());
        userDo.setCreateDate(companyInfo.getCreateDate());
        log.info("==========> {}", userDo);
        userInfoMapper.updateUserInfo(userDo);
    }

    private CompanyInfo keepUserInfo(CompanyInfo companyInfo) {
        Date date = new Date();
        CompanyInfo userDo = new CompanyInfo();
        userDo.setId(companyInfo.getId());
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
