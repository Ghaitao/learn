package com.ght.learn.ffs.dto.customer;

import com.ght.learn.ffs.enums.customer.CustomerType;

import framework.bean.dto.BaseDto;

public class CustomerQueryDto extends BaseDto {

    private static final long serialVersionUID = -1217681991426612371L;

    /**
     * 客户代码或简称
     */
    private String customerCode;

    private String name;
    
    /**
     * 客户类型
     */
    private CustomerType customerType;

    public String getCustomerCode() {
	return customerCode;
    }

    public void setCustomerCode(String customerCode) {
	this.customerCode = customerCode;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
