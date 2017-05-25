package com.ght.learn.ffs.enums.customer;

/**
 * 客户来源
 * @author MyYate
 */
public enum CustomerSource {

    /**
     * 申请流程
     */
    Apply,
    /**
     * 手动创建
     */
    Manual;
    
    public boolean isMe(CustomerSource customerSource) {
	return this.equals(customerSource);
    }
}