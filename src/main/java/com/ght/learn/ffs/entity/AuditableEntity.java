package com.ght.learn.ffs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class AuditableEntity extends CreatableEntity {

    private static final long serialVersionUID = 3524955574915991101L;

    /**
     * 修改人
     */
    @Column(name="MODIFIER")
    private Long modifier;
    
    /**
     * 修改时间
     */
    @Column(name="MODIFY_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDateTime;

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    public Date getModifyDateTime() {
        return modifyDateTime;
    }

    public void setModifyDateTime(Date modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }
}
