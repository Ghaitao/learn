package com.ght.learn.ffs.entity.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ght.learn.ffs.common.FfsConstants;

import framework.dao.entity.BaseEntity;

@Entity
@Table(name = "C_CUSTOMER_CONTACTOR", schema = FfsConstants.DB_SCHEMA_NAME)
public class CustomerContactor extends BaseEntity {

    private static final long serialVersionUID = 7985196825594297916L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;
    
    @Column(name = "CUSTOMER_ID", length=11)
    private Long customerId;
    
    /**
     * 客户代码或简称
     */
    @Column(name = "CUSTOMER_CODE", length=20)
    private String customerCode;
    
    /**
     * 所属公司代码
     */
    @Column(name = "COMPANY_CODE", length=20)
    private String companyCode;
    
    @Column(name = "NAME", length=100)
    private String name;
    
    @Column(name = "POSITION", length=20)
    private String position;
    
    /**
     * 手机
     */
    @Column(name = "MOBILE_PHONE", length=100)
    private String mobilePhone;

    /**
     * 电话
     */
    @Column(name = "TELEPHONE", length=100)
    private String telephone;
    
    @Column(name = "FAX", length=100)
    private String fax;
    
    /**
     * Email
     */
    @Column(name = "EMAIL", length = 100)
    private String email;

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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}