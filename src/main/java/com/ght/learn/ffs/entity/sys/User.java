package com.ght.learn.ffs.entity.sys;

import java.io.Serializable;
import java.util.List;

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

import framework.core.enums.CommonEnums;
import framework.core.utils.StringUtils;

@Entity
@Table(name = "S_USER", schema = FfsConstants.DB_SCHEMA_NAME)
public class User extends AuditableEntity implements I18nable {

    private static final long serialVersionUID = 1987233241831449312L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length = 11)
    private Long recId;

    /**
     * 用户名(存储时都转换为小写字母)
     */
    @Column(name = "USER_CODE", length = 30)
    private String userCode;

    /**
     * 用户密码
     */
    @Column(name = "USER_PASSWORD", length = 700)
    private String userPassword;

    /**
     * 用户姓名（中文）
     */
    @Column(name = "USER_NAME_CN", length = 300)
    private String userNameCn;

    /**
     * 用户姓名（英文）
     */
    @Column(name = "USER_NAME_EN", length = 300)
    private String userNameEn;

    /**
     * 手机
     */
    @Column(name = "MOBILE_PHONE", length = 100)
    private String mobilePhone;

    /**
     * 电话
     */
    @Column(name = "TELEPHONE", length = 100)
    private String telephone;

    /**
     * Email
     */
    @Column(name = "EMAIL", length = 100)
    private String email;

    /**
     * QQ
     */
    @Column(name = "QQ", length = 100)
    private String qq;

    /**
     * 所属客户ID 一级代理的customerId为companyId
     */
    @Column(name = "CUSTOMER_ID", length = 11)
    private Long customerId;

    /**
     * 所属客户代码 一级代理的customerCode为companyCode
     */
    @Column(name = "CUSTOMER_CODE", length = 20)
    private String customerCode;

     @Column(name = "COMPANY_ID", length=11)
     private Long companyId;

    /**
     * 公司代码
     */
    @Column(name = "COMPANY_CODE", length = 20)
    private String companyCode;

    /**
     * 状态
     */
    @Column(name = "STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private LockStatus status;

    /***** 查询和保存使用 *****/
    @Transient
    private String userName;

    @Transient
    private String repeatUserPassword;

    @Transient
    private String newUserPassword;

    public String getNewUserPassword() {
	return newUserPassword;
    }

    public void setNewUserPassword(String newUserPassword) {
	this.newUserPassword = newUserPassword;
    }

    public String getRepeatUserPassword() {
	return repeatUserPassword;
    }

    public void setRepeatUserPassword(String repeatUserPassword) {
	this.repeatUserPassword = repeatUserPassword;
    }

    @Transient
    private List<Long> roleIds;

    @Override
    public Serializable getId() {
	return getRecId();
    }

    public boolean isLocked() {
	return LockStatus.Locked.isMe(getStatus());
    }

    /**
     * 显示用户名之用
     * 
     * @return
     */
    @Override
    public String getI18nName() {
	String name = I18nable.super.getI18nName();
	if (StringUtils.isEmpty(name)) {
	    name = getUserCode();
	}
	return name;
    }

    @Transient
    private CommonEnums.IF customer;

    public CommonEnums.IF getCustomer() {
	if (StringUtils.safeEquals(this.customerCode, this.companyCode)) {
	    return CommonEnums.IF.N;
	}
	return CommonEnums.IF.Y;
    }

    public void setCustomer(CommonEnums.IF customer) {
	this.customer = customer;
    }

    /*
     * public CommonEnums.IF isCustomer() {
     * if(StringUtils.safeEquals(this.customerCode, this.companyCode)) { return
     * CommonEnums.IF.Y; } return CommonEnums.IF.N; }
     */

    @Override
    public String getNameCn() {
	return getUserNameCn();
    }

    @Override
    public String getNameEn() {
	return getUserNameEn();
    }

    public Long getRecId() {
	return recId;
    }

    public void setRecId(Long recId) {
	this.recId = recId;
    }

    public String getUserCode() {
	return userCode;
    }

    public void setUserCode(String userCode) {
	this.userCode = userCode;
    }

    public String getUserPassword() {
	return userPassword;
    }

    public void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
    }

    public String getUserNameCn() {
	return userNameCn;
    }

    public void setUserNameCn(String userNameCn) {
	this.userNameCn = userNameCn;
    }

    public String getUserNameEn() {
	return userNameEn;
    }

    public void setUserNameEn(String userNameEn) {
	this.userNameEn = userNameEn;
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

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getQq() {
	return qq;
    }

    public void setQq(String qq) {
	this.qq = qq;
    }

    public String getCompanyCode() {
	return companyCode;
    }

    public void setCompanyCode(String companyCode) {
	this.companyCode = companyCode;
    }

    public LockStatus getStatus() {
	return status;
    }

    public void setStatus(LockStatus status) {
	this.status = status;
    }

    public String getCustomerCode() {
	return customerCode;
    }

    public void setCustomerCode(String customerCode) {
	this.customerCode = customerCode;
    }

    public Long getCustomerId() {
	return customerId;
    }

    public void setCustomerId(Long customerId) {
	this.customerId = customerId;
    }

    public Long getCompanyId() {
	return companyId;
    }

    public void setCompanyId(Long companyId) {
	this.companyId = companyId;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public List<Long> getRoleIds() {
	return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
	this.roleIds = roleIds;
    }
}
