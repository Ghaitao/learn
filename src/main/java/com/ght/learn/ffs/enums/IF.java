package com.ght.learn.ffs.enums;

public enum IF {

    /**
     * 是
     */
    Y() {
	@Override
	public String getIcon() {
	    return "fa fa-check";
	}
    },
    /**
     * 否
     */
    N() {
	@Override
	public String getIcon() {
	    return "fa fa-close";
	}
    };
    
    public boolean isMe(IF value) {
	return this.equals(value);
    }
    
    public abstract String getIcon();
}