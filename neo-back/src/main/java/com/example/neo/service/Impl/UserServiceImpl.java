package com.example.neo.service.Impl;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.enums.UserTypeEnum;
import com.example.neo.model.*;
import com.example.neo.mybatis.mapper.*;
import com.example.neo.mybatis.model.*;
import com.example.neo.service.UserService;
import com.example.neo.utils.ResponseBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    private NeoCompanyMapper companyMapper;
    @Autowired
    private NeoEmployeeMapper employeeMapper;
    @Autowired
    private NeoCompanyRelationMapper relationMapper;
    @Autowired
    private NeoFinanceMapper financeMapper;
    @Autowired
    private NeoWithdrawMapper withdrawMapper;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 获取用户信息
     */
    public ResponseBean fetchUserInfo() {
       try {
           IGetUser u = new IGetUser();
           NeoUser user = commonService.fetchUserByMobile();
           NeoCompanyExample companyExample = new NeoCompanyExample();
           // company 主键 id 等于 user 表的 related_id
           companyExample.createCriteria().andIdEqualTo(user.getRelatedId());
           List<NeoCompany> companyInfo = companyMapper.selectByExample(companyExample);
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
           return ResponseBean.success(u);
       } catch (Exception e) {
           return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
       }
    }

    /**
     * 创建用户的时候先判断用户有无公司信息，如果没有则不创建公司信息，同时无关联id
     * 涉及到的表有 neo_user、neo_company、neo_company_relation 和 neo_employee 四张表
     * neo_user 表是所有角色都要的
     * neo_company 是非员工角色可以创建的，但是在创建用户的时候可以缺省（原则上一次填完，可修改）
     * neo_company_relation 记录非员工角色的关联关系（管理员 - 代理商，代理商 - 公司， 管理员 - 公司）
     * neo_employee 记录员工角色与公司的关联关系
     */
    public ResponseBean createUser(ICreateUser user, UserTypeEnum userType) {
        NeoUser neoUser = commonService.fetchUserByMobile();
        // 获取当前用户 id
        int userId = neoUser.getId();

        // 当前用户是 admin, 则无需创建对应的关系表
        if (neoUser.getRoleId() == 1) {
            createInfo(user, userType, userId);
        }
        // 当前用户是 merchant, 创建 company 用户，涉及 neo_user、neo_company 和 neo_company_relation 表
        if (neoUser.getRoleId() == 2 && userType == UserTypeEnum.COMPANY) {
           int companyId = createInfo(user, userType, userId);
           createCompanyRelated(companyId, neoUser);
        }

        // 当前用户是 company 且需要创建 employee 的情况，涉及 neo_user 和 neo_employee 表
        if (neoUser.getRoleId() == 3 && userType == UserTypeEnum.EMPLOYEE) {
            createInfo(user, userType, userId);
            createEmployee(neoUser);
        }
        return ResponseBean.success();
    }

    /**
     * 创建员工信息
     * 貌似不能走公共接口创建员工信息
     */
    private void createEmployee(NeoUser neoUser) {

    }

    /**
     * 插入代理商公司关系表
     * @param companyId
     * @param neoUser
     */
    private void createCompanyRelated(int companyId, NeoUser neoUser) {
        Date date = new Date();
        NeoCompanyRelation relation = new NeoCompanyRelation();

        relation.setAgentId(neoUser.getRelatedId());
        relation.setCompanyId(companyId);
        relation.setIsChecked(false);
        relation.setIsDeleted(false);
        relation.setCreatorId(neoUser.getId());
        relation.setCreateDate(date);
        relation.setUpdateId(neoUser.getId());
        relation.setUpdateDate(date);
        relationMapper.insert(relation);
    }

    private int createInfo(ICreateUser user, UserTypeEnum userType, Integer userId) {
        NeoUser userInfo = user.getUserInfo();
        NeoCompany companyInfo = user.getCompanyInfo();
        NeoUser userDto = new NeoUser();
        Date date = new Date();

        int companyId = -1;
        // 公司名称不为空或者创建的用户类型不为员工则创建公司信息
        if (user.getCompanyInfo().getCompanyName() != null && userType != UserTypeEnum.EMPLOYEE) {
            insertUserInfo(companyInfo, userType);
            NeoCompanyExample example = new NeoCompanyExample();
            // 通过手机号做唯一区分，比如同一个手机号只能有一个代理商或者公司，但是允许一个手机号即是代理商又是公司
            example.createCriteria().andContactTelEqualTo(companyInfo.getContactTel());
            List<NeoCompany> companyList = companyMapper.selectByExample(example);

            if (companyList.size() != 1) {
                throw new RuntimeException("用户手机号已存在");
            }
            companyId = companyList.get(0).getId();
        }

        // 创建用户表
        userDto.setAccount(userInfo.getMobile());
        userDto.setEmail(userInfo.getEmail());
        userDto.setUsername(userInfo.getUsername());
        userDto.setMobile(userInfo.getMobile());
        userDto.setRelatedId(companyId);
        userDto.setIsLocked(userType != null);
        userDto.setRoleId(userType.getId());
        userDto.setCreatorId(userId);
        userDto.setUpdateId(userId);
        userDto.setCreateDate(date);
        userDto.setUpdateDate(date);
        userDto.setPassword(passwordEncoder.encode(userInfo.getPassword() != null ? userInfo.getPassword() : "123456"));

        try {
            neoUserMapper.insert(userDto);
        } catch (RuntimeException e) {
            throw new RuntimeException("用户创建失败");
        }
        return companyId;
    }

    /**
     * 创建公司用户信息
     */
    @Override
    public ResponseBean insertUserInfo(NeoCompany companyInfo, UserTypeEnum userType) {
        try {
            Date date = new Date();
            NeoUser neoUser = commonService.fetchUserByMobile();
            NeoCompany company = commonUserInfo(companyInfo, neoUser);
            company.setCreatorId(neoUser.getId());
            company.setCreateDate(date);
            // 如果是 roleId 为 1 则是管理员创建， status 为 1，表示正常，其他则需要审核
            company.setCompanyStatus(neoUser.getRoleId() == 1);
            // companyType 为2 是代理商、3是公司
            company.setCompanyType(userType.getId() == 3);
            companyMapper.insert(company);
            return ResponseBean.success();
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.CREATE_USER_FAILED);
        }
    }

    /**
     * 更新公司用户信息
     */
    @Override
    public ResponseBean updateUserInfo(NeoCompany companyInfo) {
        NeoUser user = commonService.fetchUserByMobile();
        return updateCommonUser(companyInfo, user, "user");
    }

    private ResponseBean updateCommonUser(NeoCompany companyInfo, NeoUser user, String from) {
        try {
            NeoCompany userDo = commonUserInfo(companyInfo, user);
            userDo.setId(from.equals("user") ? user.getId() : companyInfo.getId());
            userDo.setCreatorId(companyInfo.getCreatorId());
            userDo.setCreateDate(companyInfo.getCreateDate());
            companyMapper.updateByPrimaryKeySelective(userDo);
            NeoCompanyExample neoCompanyExample = new NeoCompanyExample();
            neoCompanyExample.createCriteria().andIdEqualTo(user.getId());
            List<NeoCompany> results = companyMapper.selectByExample(neoCompanyExample);
            return ResponseBean.success(results.get(0));
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.CREATE_USER_FAILED);
        }
    }

    /**
     * 插入公共的信息
     */
    private NeoCompany commonUserInfo(NeoCompany companyInfo, NeoUser user) {
        Date date = new Date();
        NeoCompany company = new NeoCompany();
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
     * @param userType 代理商/公司
     */
    @Override
    public ResponseBean fetchMerchantInfo(UserTypeEnum userType) {
        try {
            NeoCompanyExample example = new NeoCompanyExample();
            // userType 为 merchant，需要为 false，为 company 则为 true
            example.createCriteria().andCompanyTypeEqualTo(userType == UserTypeEnum.COMPANY);
            List<NeoCompany> companyList = companyMapper.selectByExample(example);

            List<ICreateUser> list = new ArrayList<>();
            for (NeoCompany company : companyList) {
                NeoUserExample userExample = new NeoUserExample();
                userExample.createCriteria().andRelatedIdEqualTo(company.getId());
                List<NeoUser> userList = neoUserMapper.selectByExample(userExample);
                if (userList == null || userList.size() == 0) continue;
                NeoUser user = userList.get(0);
                // 用户关联 id 和公司 id 相等且未锁定
                if (user.getIsLocked()) {
                    ICreateUser createUser = new ICreateUser();
                    createUser.setUserInfo(user);
                    createUser.setCompanyInfo(company);
                    list.add(createUser);
                }
            }
            return ResponseBean.success(list);
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean updateMerchantInfo(ICreateUser user, UserTypeEnum userType) {
        NeoUser userInfo = user.getUserInfo();
        NeoCompany companyInfo = user.getCompanyInfo();
        updateCommonUser(companyInfo, userInfo, "company");
        return updateUser(userInfo);
    }

    private ResponseBean updateUser(NeoUser user) {
        try {
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
            return ResponseBean.success();
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean deleteMerchantInfo(int id) {
        try {
            NeoUserExample userExample = new NeoUserExample();
            userExample.createCriteria().andRelatedIdEqualTo(id);
            List<NeoUser> userList = neoUserMapper.selectByExample(userExample);
            if (userList != null && userList.size() == 1) {
                userList.get(0).setIsLocked(false);
                neoUserMapper.updateByExample(userList.get(0), userExample);
            }
            return ResponseBean.success();
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    @Override
    public ResponseBean fetchEmployee(int pageNum, int pageSize) {
        try {
            NeoEmployeeExample example = new NeoEmployeeExample();
            example.createCriteria().andIsLockedEqualTo(false);
            PageHelper.startPage(pageNum, pageSize);
            List<NeoEmployee> employeeList = employeeMapper.selectByExample(example);
            PageInfo<NeoEmployee> pageInfo = new PageInfo<>(employeeList);
            PageInfo<IEmployee> newPageInfo = new PageInfo<>();
            BeanUtils.copyProperties(pageInfo,newPageInfo);
            List<NeoEmployee> pageInfoList = pageInfo.getList();
            List<IEmployee> list = new ArrayList<>();
            if (pageInfoList==null||pageInfoList.size()==0) {
                return ResponseBean.success(newPageInfo);
            }
            for (NeoEmployee item : pageInfoList) {
                IEmployee employee = new IEmployee();
                BeanUtils.copyProperties(item, employee);
                employee.setId(item.getId());
                NeoCompany companyInfo = companyMapper.selectByPrimaryKey(item.getCompanyId());
                employee.setCompanyName(companyInfo == null ? "" : companyInfo.getCompanyName());
                employee.setIdVerify(item.getIdVerify());
                employee.setIsSignUp(item.getIsSignup());
                employee.setUserName(item.getName());
                employee.setUserMobile(item.getTel());
                list.add(employee);
            }
            newPageInfo.setList(list);
            return ResponseBean.success(newPageInfo);
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    /**
     * delete user
     */
    @Override
    public ResponseBean deleteEmployee(int employeeId) {
        try {
            NeoEmployee employee = employeeMapper.selectByPrimaryKey(employeeId);
            employee.setIsLocked(true);
            employeeMapper.updateByPrimaryKeySelective(employee);
            return ResponseBean.success();
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    /**
     * 根据代理商 id 查询旗下的公司
     * @param merchantId: 代理商id
     * @return ResponseBean
     */
    @Override
    public ResponseBean fetchCompanyListByMerchantId(int merchantId) {
        try {
            // 查询公司的金额信息
            NeoCompanyRelationExample relationExample = new NeoCompanyRelationExample();
            relationExample.createCriteria().andAgentIdEqualTo(merchantId);
            List<NeoCompanyRelation> relationList = relationMapper.selectByExample(relationExample);
            List<IDataQueryCompany> queryList = new ArrayList<>();

            for (NeoCompanyRelation relation : relationList) {
                NeoFinanceExample financeExample = new NeoFinanceExample();
                financeExample.createCriteria().andCompanyIdEqualTo(relation.getCompanyId());
                List<NeoFinance> financeList = financeMapper.selectByExample(financeExample);
                for (NeoFinance item : financeList) {
                    IDataQueryCompany dataOne = new IDataQueryCompany();
                    dataOne.setId(item.getId());
                    dataOne.setBalance(item.getBalance());
                    dataOne.setTotalIssued(item.getTotalIssued());
                    dataOne.setTotalRecharge(item.getTotalRecharge());
                    NeoCompany queryCompany = companyMapper.selectByPrimaryKey(item.getCompanyId());
                    dataOne.setCompanyName(queryCompany.getCompanyName());
                    queryList.add(dataOne);
                }
                log.info("2222 {}", queryList);
            }
            return ResponseBean.success(queryList);
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }
    }

    /**
     *
     * @return ResponseBean
     */
    @Override
    public ResponseBean fetchDataQuery() {
        try {
            NeoCompanyExample companyExample = new NeoCompanyExample();
            // companyType 为 false 是代理商
            companyExample.createCriteria().andCompanyTypeEqualTo(false);
            List<NeoCompany> agentList = companyMapper.selectByExample(companyExample);
            List<IDataQuery> list = new ArrayList<>();
            for (NeoCompany agent : agentList) {
                IDataQuery data = new IDataQuery();
                // 查询当前代理商的余额信息
                NeoFinanceExample financeOne = new NeoFinanceExample();
                financeOne.createCriteria().andCompanyIdEqualTo(agent.getId());
                List<NeoFinance> financeListOne = financeMapper.selectByExample(financeOne);
                // 查询当前代理商的提现信息
                NeoWithdrawExample withdrawExample = new NeoWithdrawExample();
                withdrawExample.createCriteria().andUserIdEqualTo(agent.getId());
                List<NeoWithdraw> withdrawList = withdrawMapper.selectByExample(withdrawExample);
                int totalWithdraw = 0;
                // 汇总当前代理商的提现金额
                for (NeoWithdraw withdraw : withdrawList) {
                    totalWithdraw += withdraw.getAmount();
                }
                data.setId(agent.getId());
                data.setMerchantName(agent.getCompanyName());
                data.setTotalAmount(totalWithdraw);
                // 根据余额记录取最新的一条记录
                data.setBalance(financeListOne.size() >= 1 ? financeListOne.get(0).getBalance(): 0);
                list.add(data);
            }
            return ResponseBean.success(list);
        } catch (Exception e) {
            return ResponseBean.fail(ResponseCodeEnum.SERVER_ERROR);
        }

    }
}
