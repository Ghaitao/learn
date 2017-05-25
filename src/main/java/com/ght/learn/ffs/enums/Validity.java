package com.ght.learn.ffs.enums;

public enum Validity {
    Valid,
    Invalid;
    
    public boolean isMe(Validity validity) {
	return this.equals(validity);
    }
}