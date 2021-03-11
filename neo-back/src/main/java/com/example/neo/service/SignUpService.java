package com.example.neo.service;

import com.example.neo.model.IChangeMobile;
import com.example.neo.utils.ResponseBean;

public interface SignUpService {

    /**
     * 获取未签约的员工
     * @return
     */
    ResponseBean fetchUnSignUpList();

    /**
     * 获取签约的员工
     * @return
     */
    ResponseBean fetchSignUpList();

    /**
     * 根据身份证搜索
     */
    ResponseBean searchByIdCard(String id);

    /**
     * 修改手机号
     */
    ResponseBean changeMobile(IChangeMobile mobile);
}
