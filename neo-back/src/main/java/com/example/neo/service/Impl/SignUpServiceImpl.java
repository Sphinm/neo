package com.example.neo.service.Impl;

import com.example.neo.enums.ResponseCodeEnum;
import com.example.neo.model.IChangeMobile;
import com.example.neo.mybatis.mapper.NeoEmployeeMapper;
import com.example.neo.mybatis.model.NeoCompany;
import com.example.neo.mybatis.model.NeoEmployee;
import com.example.neo.mybatis.model.NeoEmployeeExample;
import com.example.neo.service.SignUpService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private NeoEmployeeMapper employeeMapper;

    /**
     * 获取未签约的员工
     */
    @Override
    public ResponseBean fetchUnSignUpList() {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoEmployeeExample example = new NeoEmployeeExample();
        example.createCriteria().andCompanyIdEqualTo(company.getId()).andIsLockedEqualTo(false).andIsSignupEqualTo(false);
        return ResponseBean.success(employeeMapper.selectByExample(example));
    }

    /**
     * 获取签约的员工
     */
    @Override
    public ResponseBean fetchSignUpList() {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoEmployeeExample example = new NeoEmployeeExample();
        example.createCriteria().andCompanyIdEqualTo(company.getId()).andIsLockedEqualTo(false).andIsSignupEqualTo(true);
        return ResponseBean.success(employeeMapper.selectByExample(example));
    }

    /**
     * 根据身份证搜索
     *
     * @param id 身份证号
     */
    @Override
    public ResponseBean searchByIdCard(String id) {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoEmployeeExample example = new NeoEmployeeExample();
        example.createCriteria().andCompanyIdEqualTo(company.getId()).andIdVerifyEqualTo(id);
        List<NeoEmployee> list = employeeMapper.selectByExample(example);
        if (list.size() == 0) {
            return ResponseBean.fail(ResponseCodeEnum.NOT_FOUND);
        }
        return ResponseBean.success(list);
    }

    /**
     * 修改手机号
     */
    @Override
    public ResponseBean changeMobile(IChangeMobile mobile) {
        NeoCompany company = commonService.fetchCurrentCompany();
        NeoEmployeeExample example = new NeoEmployeeExample();
        example.createCriteria().andCompanyIdEqualTo(company.getId()).andTelEqualTo(mobile.getOldPhone());
        NeoEmployee newEmployee = new NeoEmployee();
        newEmployee.setTel(mobile.getNewPhone());
        employeeMapper.updateByExampleSelective(newEmployee, example);
        return ResponseBean.success();
    }
}
