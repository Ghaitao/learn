package com.ght.learn.ffs.entity.sys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ght.learn.ffs.common.FfsConstants;

import framework.dao.entity.BaseEntity;

/**
 * 这家公司能使用哪些角色
 */
@Entity
@Table(name = "S_COMPANY_ROLE", schema = FfsConstants.DB_SCHEMA_NAME)
public class CompanyRole extends BaseEntity {

    private static final long serialVersionUID = 7309874592825977347L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;
    
    @Column(name = "ROLE_ID", length=11)
    private Long roleId;
    
    @Column(name = "COMPANY_ID", length=11)
    private Long companyId;
    
    /**
     * 公司代码
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}