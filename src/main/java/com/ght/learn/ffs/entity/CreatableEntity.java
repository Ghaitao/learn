package com.ght.learn.ffs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import framework.dao.entity.BaseEntity;

@MappedSuperclass
public abstract class CreatableEntity extends BaseEntity {

    private static final long serialVersionUID = -1505491184759570417L;

    /**
     * 创建人
     */
    @Column(name="CREATOR", insertable=true, updatable=false)
    private Long creator;
    
    /**
     * 创建时间
     */
    @Column(name="CREATE_DATE_TIME", insertable=true, updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDateTime;
    
    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}
