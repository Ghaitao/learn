package com.ght.learn.ffs.enums;

public enum LockStatus {
    Normal() {

	@Override
	public String getIcon() {
	    return "icon-lock";
	}

	@Override
	public String getShowIcon() {
	    return "icon-lock-open";
	}
	
    }, 
    Locked {

	@Override
	public String getIcon() {
	    return "icon-lock-open";
	}
	
	@Override
	public String getShowIcon() {
	    return "icon-lock";
	}
    };
    
    public boolean isMe(LockStatus lockStatus) {
	return this.equals(lockStatus);
    }
    
    public abstract String getIcon();
    public abstract String getShowIcon();
}