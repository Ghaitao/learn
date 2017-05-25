package com.ght.learn.ffs.entity.task;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.common.I18nable;
import com.ght.learn.ffs.entity.CreatableEntity;
import com.ght.learn.ffs.enums.IF;
import com.ght.learn.ffs.enums.task.NotificationType;

@Entity
@Table(name = "T_NOTIFICATION", schema = FfsConstants.DB_SCHEMA_NAME)
public class Notification extends CreatableEntity implements I18nable {

    private static final long serialVersionUID = -5618123780940250878L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;
    
    /**
     * 通知类型
     */
    @Column(name = "NOTIFICATION_TYPE", length=50)
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    
    @Column(name = "ACTION", length=20)
    private String action;
    
    @Column(name = "NAME_CN", length=300)
    private String nameCn;
    
    @Column(name = "NAME_EN", length=300)
    private String nameEn;
    
    /**
     * 关联的主键ID
     * 如：客户Id，订单Id
     */
    @Column(name = "CORRELATED_ID", length=11)
    private Long correlatedId;
    
    /**
     * 关联的主键CODE
     * 如：客户代码，订单号
     */
    @Column(name = "CORRELATED_CODE", length=100)
    private String correlatedCode;
    
    /**
     * 接受者用户ID 
     */
    @Column(name = "RECEIVER_USER_ID", length = 11)
    private Long receiverUserId;
    
    /**
     * 是否已读
     */
    @Column(name = "HAS_READ", length=5)
    @Enumerated(EnumType.STRING)
    private IF hasRead;
    
    /**
     * 当前时间与通知创建时间相差秒数
     */
    @Transient
    private Long differSeconds;
    
    @Override
    public Serializable getId() {
	return getRecId();
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public Long getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(Long receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getDifferSeconds() {
        return differSeconds;
    }

    public void setDifferSeconds(Long differSeconds) {
        this.differSeconds = differSeconds;
    }

    public IF getHasRead() {
        return hasRead;
    }

    public void setHasRead(IF hasRead) {
        this.hasRead = hasRead;
    }

    public Long getCorrelatedId() {
        return correlatedId;
    }

    public void setCorrelatedId(Long correlatedId) {
        this.correlatedId = correlatedId;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getCorrelatedCode() {
        return correlatedCode;
    }

    public void setCorrelatedCode(String correlatedCode) {
        this.correlatedCode = correlatedCode;
    }
}