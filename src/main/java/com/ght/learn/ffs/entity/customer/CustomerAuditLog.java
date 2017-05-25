package com.ght.learn.ffs.entity.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.entity.CreatableEntity;
import com.ght.learn.ffs.enums.customer.CustomerAuditAction;

@Entity
@Table(name = "C_CUSTOMER_AUDIT_LOG", schema = FfsConstants.DB_SCHEMA_NAME)
public class CustomerAuditLog extends CreatableEntity {

    private static final long serialVersionUID = 5915442307658545274L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;
    
    @Column(name = "CUSTOMER_ID", length=11)
    private Long customerId;
    
    /**
     * 审计动作
     */
    @Column(name = "AUDIT_ACTION", length=20)
    @Enumerated(EnumType.STRING)
    private CustomerAuditAction auditAction;
    
    @Column(name = "REMARK", length=1000)
    private String remark;
    
    /**
     * 所属公司代码
     */
    @Column(name = "COMPANY_CODE", length=20)
    private String companyCode;
    
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public CustomerAuditAction getAuditAction() {
        return auditAction;
    }

    public void setAuditAction(CustomerAuditAction auditAction) {
        this.auditAction = auditAction;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}