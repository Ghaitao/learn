package com.ght.learn.ffs.enums.task;

public enum NotificationType {
    /**
     * 客户申请
     */
    CustomerApply,
    /**
     * 订单批复
     */
    OrderRely,
    /**
     * 客户信用额度
     * 每个客户都有一个信用额度上限，当达到一个阈值时进行通知
     */
    CustomerCredit,
    /**
     * 公司使用期限
     * 每个公司都有一个使用期限，当达到一个日期阈值时进行通知
     */
    CompanyServiceLife;
    
    public boolean isMe(NotificationType notificationType) {
	return this.equals(notificationType);
    }
}