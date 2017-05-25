package com.ght.learn.ffs.entity.sys;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.common.I18nable;
import com.ght.learn.ffs.entity.AuditableEntity;
import com.ght.learn.ffs.enums.LockStatus;

import framework.core.utils.StringUtils;

/**
 * 平台使用的公司清单
 */
@Entity
@Table(name = "S_COMPANY", schema = FfsConstants.DB_SCHEMA_NAME)
public class Company extends AuditableEntity implements I18nable {

    private static final long serialVersionUID = -5049732754778182670L;

    public static final String ADMIN_COMPANY_CODE = "ADMIN";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length = 11)
    private Long recId;

    /**
     * 公司代码(几位大写字母或数字)
     */
    @Column(name = "COMPANY_CODE", length = 20)
    private String companyCode;

    /**
     * 中文全称
     */
    @Column(name = "NAME_CN", length = 300)
    private String nameCn;

    /**
     * 英文全称
     */
    @Column(name = "NAME_EN", length = 300)
    private String nameEn;

    @Column(name = "ADDRESS", length=1000)
    private String address;
    
    @Column(name = "POSTCODE", length=20)
    private String postcode;
    
    @Column(name = "CONTACTOR_NAME", length=30)
    private String contactorName;
    
    @Column(name = "CONTACTOR_PHONE", length=50)
    private String contactorPhone;
    
    @Column(name = "CONTACTOR_FAX", length=50)
    private String contactorFax;
    
    @Column(name = "CONTACTOR_EMAIL", length=50)
    private String contactorEmail;
    
    @Column(name = "CITY_CODE", length = 100)
    private String cityCode;

    @Column(name = "AIRPORT_CODE", length = 100)
    private String airportCode;

    @Column(name = "COPY_RIGHT", length = 500)
    private String copyRight;

    /**
     * 客服电话
     */
    @Column(name = "CUSTOMER_SERVICE_PHONE", length = 500)
    private String customerServicePhone;

    /**
     * 客服邮箱
     */
    @Column(name = "CUSTOMER_SERVICE_EMAIL", length = 500)
    private String customerServiceEmail;

    /**
     * shortcut icon
     */
    @Column(name = "FAVICON_FILE_META_ID", length = 300)
    private String faviconFileMetaId;

    /**
     * 中文文字LOGO
     */
    @Column(name = "TEXT_LOGO_CN_FILE_META_ID", length = 300)
    private String textLogoCnFileMetaId;

    /**
     * 中文文字LOGO
     */
    @Column(name = "TEXT_LOGO_BIG_CN_FILE_META_ID", length = 300)
    private String textLogoBigCnFileMetaId;

    /**
     * 英文文字LOGO
     */
    @Column(name = "TEXT_LOGO_EN_FILE_META_ID", length = 300)
    private String textLogoEnFileMetaId;

    /**
     * 英文文字LOGO
     */
    @Column(name = "TEXT_LOGO_BIG_EN_FILE_META_ID", length = 300)
    private String textLogoBigEnFileMetaId;

    /**
     * 业务LOGO
     */
    @Column(name = "BUSINESS_LOGO_FILE_META_ID", length = 300)
    private String businessLogoFileMetaId;
    
    /**
     * 开始使用时间 年月日 时分秒
     */
    @Column(name = "START_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;

    /**
     * 使用到期时间 年月日 时分秒
     */
    @Column(name = "EXPIRY_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDateTime;

    /**
     * 上传文件存储的目录
     */
    @Column(name = "UPLOAD_DIR_PATH", length = 300)
    private String uploadDirPath;

    /**
     * 上传文件访问的二级域名名称 SLD=Second-level domain
     * 如：http://upload.okforward.com中的upload，http://upload1.okforward.
     * com中的upload1
     */
    @Column(name = "UPLOAD_FILE_SLD_NAME", length = 100)
    private String uploadFileSldName;

    /**
     * 状态
     */
    @Column(name = "STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private LockStatus status;

    @Override
    public Serializable getId() {
	return getRecId();
    }

    @Transient
    private List<Long> roleIds;

    public List<Long> getRoleIds() {
	return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
	this.roleIds = roleIds;
    }

    public boolean isAdminCompany() {
	return StringUtils.safeEqualsIgnoreCase(ADMIN_COMPANY_CODE, this.companyCode);
    }

    /**
     * 显示公司名之用
     * 
     * @return
     */
    @Override
    public String getI18nName() {
	String name = I18nable.super.getI18nName();
	if (StringUtils.isEmpty(name)) {
	    name = getCompanyCode();
	}
	return name;
    }

    public String getTextLogo() {
	String logo = null;
	if (I18nable.super.isEnglish()) {
	    logo = getTextLogoEnFileMetaId();
	} else {
	    logo = getTextLogoCnFileMetaId();
	}
	return logo;
    }

    public String getTextLogoBig() {
	String logo = null;
	if (I18nable.super.isEnglish()) {
	    logo = getTextLogoBigEnFileMetaId();
	} else {
	    logo = getTextLogoBigCnFileMetaId();
	}
	return logo;
    }

    public boolean isLocked() {
	return LockStatus.Locked.isMe(getStatus());
    }

    public Long getRecId() {
	return recId;
    }

    public void setRecId(Long recId) {
	this.recId = recId;
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

    public String getFaviconFileMetaId() {
	return faviconFileMetaId;
    }

    public void setFaviconFileMetaId(String faviconFileMetaId) {
	this.faviconFileMetaId = faviconFileMetaId;
    }

    public String getTextLogoCnFileMetaId() {
	return textLogoCnFileMetaId;
    }

    public void setTextLogoCnFileMetaId(String textLogoCnFileMetaId) {
	this.textLogoCnFileMetaId = textLogoCnFileMetaId;
    }

    public String getTextLogoBigCnFileMetaId() {
	return textLogoBigCnFileMetaId;
    }

    public void setTextLogoBigCnFileMetaId(String textLogoBigCnFileMetaId) {
	this.textLogoBigCnFileMetaId = textLogoBigCnFileMetaId;
    }

    public String getTextLogoEnFileMetaId() {
	return textLogoEnFileMetaId;
    }

    public void setTextLogoEnFileMetaId(String textLogoEnFileMetaId) {
	this.textLogoEnFileMetaId = textLogoEnFileMetaId;
    }

    public String getTextLogoBigEnFileMetaId() {
	return textLogoBigEnFileMetaId;
    }

    public void setTextLogoBigEnFileMetaId(String textLogoBigEnFileMetaId) {
	this.textLogoBigEnFileMetaId = textLogoBigEnFileMetaId;
    }

    public LockStatus getStatus() {
	return status;
    }

    public void setStatus(LockStatus status) {
	this.status = status;
    }

    public Date getStartDateTime() {
	return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
	this.startDateTime = startDateTime;
    }

    public Date getExpiryDateTime() {
	return expiryDateTime;
    }

    public void setExpiryDateTime(Date expiryDateTime) {
	this.expiryDateTime = expiryDateTime;
    }

    public String getCopyRight() {
	return copyRight;
    }

    public void setCopyRight(String copyRight) {
	this.copyRight = copyRight;
    }

    public String getCustomerServicePhone() {
	return customerServicePhone;
    }

    public void setCustomerServicePhone(String customerServicePhone) {
	this.customerServicePhone = customerServicePhone;
    }

    public String getCustomerServiceEmail() {
	return customerServiceEmail;
    }

    public void setCustomerServiceEmail(String customerServiceEmail) {
	this.customerServiceEmail = customerServiceEmail;
    }

    public String getCityCode() {
	return cityCode;
    }

    public void setCityCode(String cityCode) {
	this.cityCode = cityCode;
    }

    public String getAirportCode() {
	return airportCode;
    }

    public void setAirportCode(String airportCode) {
	this.airportCode = airportCode;
    }

    public String getUploadDirPath() {
	return uploadDirPath;
    }

    public void setUploadDirPath(String uploadDirPath) {
	this.uploadDirPath = uploadDirPath;
    }

    public String getUploadFileSldName() {
	return uploadFileSldName;
    }

    public void setUploadFileSldName(String uploadFileSldName) {
	this.uploadFileSldName = uploadFileSldName;
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

    public String getBusinessLogoFileMetaId() {
        return businessLogoFileMetaId;
    }

    public void setBusinessLogoFileMetaId(String businessLogoFileMetaId) {
        this.businessLogoFileMetaId = businessLogoFileMetaId;
    }
}