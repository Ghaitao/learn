package com.ght.learn.ffs.enums.customer;

/**
 * 信用额度
 * @author MyYate
 *
 */
public enum CreditLimit {

    CL_0_5 () {
	@Override
	public int getMaxCreditLimit() {
	    return 50000;
	}
    },
    CL_5_10 () {
	@Override
	public int getMaxCreditLimit() {
	    return 100000;
	}
    },
    CL_10_20 () {
	@Override
	public int getMaxCreditLimit() {
	    return 200000;
	}
    },
    CL_20_100 () {
	@Override
	public int getMaxCreditLimit() {
	    return 100000;
	}
    };
    
    public abstract int getMaxCreditLimit();
}