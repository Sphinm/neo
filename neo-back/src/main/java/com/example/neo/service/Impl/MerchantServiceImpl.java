package com.example.neo.service.Impl;

import com.example.neo.mybatis.mapper.NeoCompanyMapper;
import com.example.neo.mybatis.model.NeoCompany;
import com.example.neo.mybatis.model.NeoCompanyExample;
import com.example.neo.mybatis.model.NeoUser;
import com.example.neo.service.MerchantService;
import com.example.neo.utils.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    CommonService commonService;
    @Autowired
    MerchantService merchantService;
    @Autowired
    NeoCompanyMapper companyMapper;

    @Override
    public ResponseBean fetchRebate() {
        NeoUser user = commonService.fetchUserByMobile();
        log.info("{}",user);
        NeoCompanyExample example = new NeoCompanyExample();
        example.createCriteria().andIdEqualTo(user.getRelatedId());
        List<NeoCompany> company = companyMapper.selectByExample(example);
        Integer conpanyId = company.get(0).getId();
        return null;
    }
}
