package com.ght.learn.ffs.enums.customer;

public enum CustomerAuditAction {
    /**
     * 提交
     */
    Submit {
	@Override
	public String getCssClass() {
	    return "label label-success";
	}
	
    },
    /**
     * 审核通过
     */
    Pass {
	@Override
	public String getCssClass() {
	    return "label label-success";
	}
	
    },
    /**
     * 审核拒绝
     */
    Reject {
	@Override
	public String getCssClass() {
	    return "label label-danger";
	}
	
    };
    
    public boolean isMe(CustomerAuditAction customerAuditAction) {
	return this.equals(customerAuditAction);
    }
    
    public abstract String getCssClass();
}