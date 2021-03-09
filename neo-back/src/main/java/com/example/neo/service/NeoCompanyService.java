package com.example.neo.service;

import com.example.neo.model.ICharge;
import com.example.neo.model.IInvoice;
import com.example.neo.utils.ResponseBean;

public interface NeoCompanyService {
    /**
     * 获取所有公司
     * @return
     */
    ResponseBean fetchCompanyList();

    /**
     * 充值操作
     * @param icharge
     * @return
     */
    ResponseBean charge(ICharge icharge);

    /**
     * 获取当前用户充值页面公司财务信息
     * @return
     */
    ResponseBean getChargeInfo();

    /**
     * 充值列表
     * @return
     */
    ResponseBean getChargeList(int pageNum,int pageSize);

    /**
     * 申请发票
     */
    ResponseBean companyInvoice(IInvoice invoice);
}
