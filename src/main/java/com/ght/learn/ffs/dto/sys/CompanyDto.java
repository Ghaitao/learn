package com.ght.learn.ffs.dto.sys;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ght.learn.ffs.entity.sys.Role;
import com.ght.learn.ffs.enums.LockStatus;

import framework.bean.dto.BaseDto;

public class CompanyDto extends BaseDto {

	private static final long serialVersionUID = -9160062780094198014L;

	private Long recId;

	/* 公司代码 */
	private String companyCode;

	/* 中文名 */
	private String nameCn;

	/* 英文名 */
	private String nameEn;

	/* 中英文名 */
	private String name;

	/* 城市代码 */
	private String cityCode;

	/* 机场代码 */
	private String airportCode;

	/* 版权信息 */
	private String copyRight;

	/* 客服电话 */
	private String customerServicePhone;

	/* 客服邮箱 */
	private String customerServiceEmail;
	
	private String address;
	private String postcode;
	private String contactorName;
	private String contactorPhone;
	private String contactorFax;
	private String contactorEmail;

	private MultipartFile faviconFile;

	private MultipartFile textLogoCnFile;

	private MultipartFile textLogoBigCnFile;

	private MultipartFile textLogoEnFile;

	private MultipartFile textLogoBigEnFile;
	
	private MultipartFile businessLogoFile;
	

	/* 开始时间 */
	private Date startDateTime;

	/* 到期时间 */
	private Date expiryDateTime;

	/* 创建人 */
	private Long creator;

	/* 创建时间 */
	private Date createDateTime;

	/* 修改人 */
	private Long modifier;

	/* 修改时间 */
	private Date modifyDateTime;

	/* 上传文件存储的目录 */
	private String uploadDirPath;

	/* 上传文件访问的二级域名名称 */
	private String uploadFileSldName;

	/* 状态 */
	private LockStatus status;

	private List<Role> roles;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public MultipartFile getFaviconFile() {
		return faviconFile;
	}

	public void setFaviconFile(MultipartFile faviconFile) {
		this.faviconFile = faviconFile;
	}

	public MultipartFile getTextLogoCnFile() {
		return textLogoCnFile;
	}

	public void setTextLogoCnFile(MultipartFile textLogoCnFile) {
		this.textLogoCnFile = textLogoCnFile;
	}

	public MultipartFile getTextLogoBigCnFile() {
		return textLogoBigCnFile;
	}

	public void setTextLogoBigCnFile(MultipartFile textLogoBigCnFile) {
		this.textLogoBigCnFile = textLogoBigCnFile;
	}

	public MultipartFile getTextLogoEnFile() {
		return textLogoEnFile;
	}

	public void setTextLogoEnFile(MultipartFile textLogoEnFile) {
		this.textLogoEnFile = textLogoEnFile;
	}

	public MultipartFile getTextLogoBigEnFile() {
		return textLogoBigEnFile;
	}

	public void setTextLogoBigEnFile(MultipartFile textLogoBigEnFile) {
		this.textLogoBigEnFile = textLogoBigEnFile;
	}

	public MultipartFile getBusinessLogoFile() {
		return businessLogoFile;
	}

	public void setBusinessLogoFile(MultipartFile businessLogoFile) {
		this.businessLogoFile = businessLogoFile;
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

	public LockStatus getStatus() {
		return status;
	}

	public void setStatus(LockStatus status) {
		this.status = status;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
	
}
