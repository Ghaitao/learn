package com.ght.learn.ffs.enums.customer;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户类型
 * @author MyYate
 */
public enum CustomerType {

    /**
     * 同行托货人
     */
    PeerCompany() {
	@Override
	public boolean isNeedApply() {
	    return true;
	}
    },
    /**
     * 发货人
     */
    Shipper() {
	@Override
	public boolean isNeedApply() {
	    return true;
	}
    },
    /**
     * 委托单位
     */
    Consignor() {
	@Override
	public boolean isNeedApply() {
	    return false;
	}
    },
    /**
     * 收货人
     */
    Consignee() {
	@Override
	public boolean isNeedApply() {
	    return false;
	}
    },
    /**
     * 一级代理
     */
    TierOneAgent() {
	@Override
	public boolean isNeedApply() {
	    return false;
	}
    },
    /**
     * 航空公司
     */
    Airline() {
	@Override
	public boolean isNeedApply() {
	    return false;
	}
    };
    
    /**
     * 是否需要申请流程
     * @return
     */
    public abstract boolean isNeedApply();
    
    public static List<CustomerType> getNeedApplyCustomerTypes() {
	ArrayList<CustomerType> customerTypes = new ArrayList<>(8);
	for (CustomerType customerType : CustomerType.values()) {
	    if (customerType.isNeedApply()) {
		customerTypes.add(customerType);
	    }
	}
	return customerTypes;
    }
}