package com.ght.learn.ffs.enums.customer;

public enum CustomerAuditStatus {
    /**
     * 已提交
     */
    Submitted {
	@Override
	public String getCssClass() {
	    return "label label-info";
	}
	
    },
    /**
     * 处理中
     */
    Processing {
	@Override
	public String getCssClass() {
	    return "label label-primary";
	}
	
    },
    /**
     * 已完成
     */
    Finished {
	@Override
	public String getCssClass() {
	    return "label label-success";
	}
	
    };
    
    public boolean isMe(CustomerAuditStatus customerAuditStatus) {
	return this.equals(customerAuditStatus);
    }
    
    public abstract String getCssClass();
}