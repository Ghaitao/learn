package com.ght.learn.ffs.entity.customer;

import java.io.Serializable;
import java.math.BigDecimal;

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
import com.ght.learn.ffs.entity.AuditableEntity;
import com.ght.learn.ffs.enums.LockStatus;
import com.ght.learn.ffs.enums.customer.AccountPeriod;
import com.ght.learn.ffs.enums.customer.CustomerAuditStatus;
import com.ght.learn.ffs.enums.customer.CustomerSource;
import com.ght.learn.ffs.enums.customer.CustomerType;

import framework.core.utils.StringUtils;

@Entity
@Table(name = "C_CUSTOMER", schema = FfsConstants.DB_SCHEMA_NAME)
public class Customer extends AuditableEntity implements I18nable {

    private static final long serialVersionUID = 4332452444222058387L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;
    
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
    
    /**
     * 客户类型
     */
    @Column(name = "CUSTOMER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;
    
    @Column(name = "NAME_CN", length=300)
    private String nameCn;
    
    @Column(name = "NAME_EN", length=300)
    private String nameEn;
    
    @Column(name = "ADDRESS", length=1000)
    private String address;
    
    @Column(name = "POSTCODE", length=20)
    private String postcode;
    
    @Column(name = "CITY", length=30)
    private String city;
    
    /**
     * 省或州
     */
    @Column(name = "PROVINCE", length=30)
    private String province;
    
    @Column(name = "COUNTRY", length=30)
    private String country;
    
    @Column(name = "CONTACTOR_NAME", length=30)
    private String contactorName;
    
    @Column(name = "CONTACTOR_PHONE", length=50)
    private String contactorPhone;
    
    @Column(name = "CONTACTOR_FAX", length=50)
    private String contactorFax;
    
    @Column(name = "CONTACTOR_EMAIL", length=50)
    private String contactorEmail;
    
    /**
     * 三证合一图片
     */
    @Column(name = "LICENSE_FILE_META_ID", length=300)
    private String licenseFileMetaId;
    
    @Column(name = "REMARK", length=1000)
    private String remark;

    /**
     * 受理客服ID
     * User.recId
     */
    @Column(name="ACCEPT_CUSTOMER_SERVICE_ID", length=11)
    private Long acceptCustomerServiceId;
    
    /**
     * 审计状态
     */
    @Column(name = "AUDIT_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private CustomerAuditStatus auditStatus;
    
    /**
     * 账期
     */
    @Column(name = "ACCOUNT_PERIOD", length = 20)
    @Enumerated(EnumType.STRING)
    private AccountPeriod accountPeriod;
    
    /**
     * 信用额度
     */
    @Column(name = "CREDIT_LIMIT", scale=1, length=11)
    private BigDecimal creditLimit;
    
    /**
     * 剩余信用额度
     */
    @Column(name = "REMAINING_CREDIT_LIMIT", scale=1, length=11)
    private BigDecimal remainingCreditLimit;
    
    /**
     * 状态
     * 审计完成通过后改为Normal
     */
    @Column(name = "LOCK_STATUS", length=20)
    @Enumerated(EnumType.STRING)
    private LockStatus lockStatus;
    
    /**
     * 客户来源
     */
    @Column(name = "CUSTOMER_SOURCE", length=20)
    @Enumerated(EnumType.STRING)
    private CustomerSource customerSource;
    
    /**
     * 临时信用额度
     */
    @Transient
    private BigDecimal temporaryCreditLimit;
    
    @Override
    public Serializable getId() {
	return getRecId();
    }

    @Override
    public String getI18nName() {
	String name = I18nable.super.getI18nName();
	if (StringUtils.isEmpty(name)) {
	    name = getCustomerCode();
	}
	return name;
    }

    public boolean isCustomerSourceApply() {
	return CustomerSource.Apply.isMe(this.customerSource);
    }
    
    public boolean isCustomerSourceManual() {
	return CustomerSource.Manual.isMe(this.customerSource);
    }
    
    public boolean isLockStatusNormal() {
	return LockStatus.Normal.isMe(this.lockStatus);
    }
    
    public boolean isLockStatusLocked() {
	return LockStatus.Locked.isMe(this.lockStatus);
    }
    
    public boolean isAuditStatusProcessing() {
	return CustomerAuditStatus.Processing.isMe(this.auditStatus);
    }
    
    public boolean isAuditStatusSubmitted() {
	return CustomerAuditStatus.Submitted.isMe(this.auditStatus);
    }
    
    public boolean isAuditStatusFinished() {
	return CustomerAuditStatus.Finished.isMe(this.auditStatus);
    }
    
    /** Autocomplete use **/
    public String getDisplayKey() {
    	return getCustomerCode() + "-" + getI18nName();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLicenseFileMetaId() {
        return licenseFileMetaId;
    }

    public void setLicenseFileMetaId(String licenseFileMetaId) {
        this.licenseFileMetaId = licenseFileMetaId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getAcceptCustomerServiceId() {
        return acceptCustomerServiceId;
    }

    public void setAcceptCustomerServiceId(Long acceptCustomerServiceId) {
        this.acceptCustomerServiceId = acceptCustomerServiceId;
    }

    public LockStatus getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(LockStatus lockStatus) {
        this.lockStatus = lockStatus;
    }

    public CustomerAuditStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(CustomerAuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getTemporaryCreditLimit() {
        return temporaryCreditLimit;
    }

    public void setTemporaryCreditLimit(BigDecimal temporaryCreditLimit) {
        this.temporaryCreditLimit = temporaryCreditLimit;
    }

    public AccountPeriod getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(AccountPeriod accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    public BigDecimal getRemainingCreditLimit() {
        return remainingCreditLimit;
    }

    public void setRemainingCreditLimit(BigDecimal remainingCreditLimit) {
        this.remainingCreditLimit = remainingCreditLimit;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public CustomerSource getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(CustomerSource customerSource) {
        this.customerSource = customerSource;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactorName() {
        return contactorName;
    }

    public void setContactorName(String contactorName) {
        this.contactorName = contactorName;
    }

    public String getContactorPhone() {
        return contactorPhone;
    }

    public void setContactorPhone(String contactorPhone) {
        this.contactorPhone = contactorPhone;
    }

    public String getContactorFax() {
        return contactorFax;
    }

    public void setContactorFax(String contactorFax) {
        this.contactorFax = contactorFax;
    }

    public String getContactorEmail() {
        return contactorEmail;
    }

    public void setContactorEmail(String contactorEmail) {
        this.contactorEmail = contactorEmail;
    }
}