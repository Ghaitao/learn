package com.ght.learn.ffs.enums.sys;

public enum NewsGrade {
    Normal,
    Important;
    
    public boolean isMe(NewsGrade newsGrade) {
	return this.equals(newsGrade);
    }
}