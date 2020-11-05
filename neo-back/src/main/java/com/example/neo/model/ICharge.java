package com.example.neo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/11/4 9:47
 */

public class ICharge{
    public double amount;

    public ICharge(double amount) {
        this.amount = amount;
    }

    public ICharge() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
