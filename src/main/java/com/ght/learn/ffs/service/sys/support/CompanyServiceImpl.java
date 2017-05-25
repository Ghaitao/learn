package com.ght.learn.ffs.service.sys.support;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ght.learn.ffs.dto.sys.CompanyDto;
import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.sys.CompanyRole;
import com.ght.learn.ffs.enums.LockStatus;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.sys.CompanyService;
import com.ght.learn.ffs.tool.context.SessionUser;
import com.ght.learn.ffs.tool.context.SessionUserHolder;
import com.ght.learn.ffs.tool.file.MultipartFileOperation;
import com.ght.learn.ffs.tool.file.UploadFileItem;

import framework.core.Constants;
import framework.core.pagination.OrderablePagination;
import framework.core.utils.ObjectUtils;
import framework.core.utils.StringUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;
import framework.service.ServiceException;

@Service("CompanyService")
public class CompanyServiceImpl extends FfsServiceImpl implements CompanyService {

	@Resource(name="localDiskMultipartFileOperation", type = MultipartFileOperation.class)
	private MultipartFileOperation localDiskMultipartFileOperation;
	
	@Override
	public Company getCompanyById(Long recId) {
		//通过recId获取一级代理公司
		Company entity = getEntityManager().get(Company.class, recId);
		return entity;
	}

	@Override
	public Company queryCompanyByCompanyCode(String companyCode) {
		if(StringUtils.isEmpty(companyCode)) {
			return null;
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Company.class);
		sdc.add(SafeRestrictions.equal("companyCode", companyCode));
		return this.getEntityManager().queryForUniqueObjectByCriteria(Company.class, sdc);
	}

	@Override
	public List<Company> queryCompanys(CompanyDto dto, OrderablePagination op) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Company.class);
		if(dto != null) {
			sdc.add(SafeRestrictions.equal("companyCode", StringUtils.safeToUpperCase(dto.getCompanyCode())));
			sdc.add(SafeRestrictions.or(SafeRestrictions.likeAnyWhere("nameCn", dto.getName()),
					SafeRestrictions.likeAnyWhere("nameEn", dto.getName())));
		}
		if(op != null && !ObjectUtils.isEmpty(op.getSorters())) {
			sdc.addSorters(op.getSorters());
		}
		return this.getEntityManager().queryForListByCriteria(Company.class, sdc, op);
	}

	@Override
	public Long doCreateCompany(Company dto, SessionUser operator, MultipartFile faviconFile,
			MultipartFile textLogoBigCnFile, MultipartFile textLogoCnFile, MultipartFile textLogoBigEnFile,
			MultipartFile textLogoEnFile, MultipartFile businessLogoFile) {
		if (dto == null || StringUtils.isEmpty(dto.getCompanyCode())) {
			// 判断是否为空
			throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.company.exception.service.company_code_empty", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		} else if (this.hasExistThisCompanyCode(dto.getCompanyCode())) {
			// 判断是否存在相同的companyCode
			throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.company.exception.service.company_code_exist", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		}
		Company entity = new Company();
		// 存储一级代理公司的基本信息
		entity.setCompanyCode(StringUtils.safeToUpperCase(dto.getCompanyCode()));
		entity.setNameCn(dto.getNameCn());
		entity.setNameEn(dto.getNameEn());
		entity.setCityCode(StringUtils.safeToUpperCase(dto.getCityCode()));
		entity.setAirportCode(StringUtils.safeToUpperCase(dto.getAirportCode()));
		entity.setCopyRight(dto.getCopyRight());
		entity.setCustomerServicePhone(dto.getCustomerServicePhone());
		entity.setCustomerServiceEmail(dto.getCustomerServiceEmail());
		entity.setUploadDirPath(dto.getUploadDirPath());
		entity.setUploadFileSldName(dto.getUploadFileSldName());
		
		entity.setAddress(dto.getAddress());
		entity.setPostcode(dto.getPostcode());
		entity.setContactorName(dto.getContactorName());
		entity.setContactorPhone(dto.getContactorPhone());
		entity.setContactorFax(dto.getContactorFax());
		entity.setContactorEmail(dto.getContactorEmail());
		
		SessionUser sessionUser = SessionUserHolder.getSessionUser();
		System.out.println(sessionUser.getI18nName()+"****"+sessionUser.getCompanyCode());
		entity.setFaviconFileMetaId(localDiskMultipartFileOperation.persist(new UploadFileItem(faviconFile), operator));
		entity.setTextLogoCnFileMetaId(localDiskMultipartFileOperation.persist(new UploadFileItem(textLogoCnFile), operator));
		entity.setTextLogoBigCnFileMetaId(localDiskMultipartFileOperation.persist(new UploadFileItem(textLogoBigCnFile), operator));
		entity.setTextLogoEnFileMetaId(localDiskMultipartFileOperation.persist(new UploadFileItem(textLogoEnFile), operator));
		entity.setTextLogoBigEnFileMetaId(localDiskMultipartFileOperation.persist(new UploadFileItem(textLogoBigEnFile), operator));
		entity.setBusinessLogoFileMetaId(localDiskMultipartFileOperation.persist(new UploadFileItem(businessLogoFile), operator));
		
		entity.setStartDateTime(dto.getStartDateTime());
		entity.setExpiryDateTime(dto.getExpiryDateTime());
		entity.setStatus(LockStatus.Normal);
		this.fillAuditableEntityOnCreate(entity, operator);
		
		Long companyId = (Long) this.getEntityManager().save(entity);
		
		//保存公司角色
		this.doSaveCompanyRoles(companyId, entity.getCompanyCode(), dto.getRoleIds());
		return companyId;

	}

	@Override
	public void doUpdateCompany(Company dto, SessionUser operator, MultipartFile faviconFile,
			MultipartFile textLogoBigCnFile, MultipartFile textLogoCnFile, MultipartFile textLogoBigEnFile,
			MultipartFile textLogoEnFile, MultipartFile businessLogoFile) {
		if (dto == null || dto.getRecId() == null) {
			return;
		}
		Company entity = this.getEntityManager().get(Company.class, dto.getRecId());
		if (entity == null) {
			return;
		}
		// 存储一级代理公司的基本信息
		entity.setNameCn(dto.getNameCn());
		entity.setNameEn(dto.getNameEn());
		entity.setCityCode(StringUtils.safeToUpperCase(dto.getCityCode()));
		entity.setAirportCode(StringUtils.safeToUpperCase(dto.getAirportCode()));
		entity.setCopyRight(dto.getCopyRight());
		entity.setCustomerServicePhone(dto.getCustomerServicePhone());
		entity.setCustomerServiceEmail(dto.getCustomerServiceEmail());
		entity.setUploadDirPath(dto.getUploadDirPath());
		entity.setUploadFileSldName(dto.getUploadFileSldName());
				
		entity.setAddress(dto.getAddress());
		entity.setPostcode(dto.getPostcode());
		entity.setContactorName(dto.getContactorName());
		entity.setContactorPhone(dto.getContactorPhone());
		entity.setContactorFax(dto.getContactorFax());
		entity.setContactorEmail(dto.getContactorEmail());
		SessionUser sessionUser = SessionUserHolder.getSessionUser();
		if (faviconFile != null && faviconFile.getSize() > 0) {
			localDiskMultipartFileOperation.delete(entity.getFaviconFileMetaId(), sessionUser);
			entity.setFaviconFileMetaId(localDiskMultipartFileOperation.persist(new UploadFileItem(faviconFile), sessionUser));
		}
		if (textLogoBigCnFile != null && textLogoBigCnFile.getSize() > 0) {
			localDiskMultipartFileOperation.delete(entity.getTextLogoBigCnFileMetaId(), sessionUser);
			entity.setTextLogoBigCnFileMetaId(localDiskMultipartFileOperation.persist(new UploadFileItem(textLogoBigCnFile), sessionUser));
		}
		if (textLogoCnFile != null && textLogoCnFile.getSize() > 0) {
			localDiskMultipartFileOperation.delete(entity.getTextLogoCnFileMetaId(), sessionUser);
			entity.setTextLogoCnFileMetaId(localDiskMultipartFileOperation.persist(new UploadFileItem(textLogoCnFile), sessionUser));
		}
		if (textLogoBigEnFile != null && textLogoBigEnFile.getSize() > 0) {
			localDiskMultipartFileOperation.delete(entity.getTextLogoBigEnFileMetaId(), sessionUser);
			entity.setTextLogoBigEnFileMetaId(localDiskMultipartFileOperation.persist(new UploadFileItem(textLogoBigEnFile), sessionUser));
		}
		if (textLogoEnFile != null && textLogoEnFile.getSize() > 0) {
			localDiskMultipartFileOperation.delete(entity.getTextLogoEnFileMetaId(), sessionUser);
			entity.setTextLogoEnFileMetaId(localDiskMultipartFileOperation.persist(new UploadFileItem(textLogoEnFile), sessionUser));
		}
		if (businessLogoFile != null && businessLogoFile.getSize() > 0) {
			localDiskMultipartFileOperation.delete(entity.getBusinessLogoFileMetaId(), sessionUser);
			entity.setBusinessLogoFileMetaId(localDiskMultipartFileOperation.persist(new UploadFileItem(businessLogoFile), sessionUser));
		}
		
		entity.setStartDateTime(dto.getStartDateTime());
		entity.setExpiryDateTime(dto.getExpiryDateTime());
		this.fillAuditableEntityOnUpdate(entity, operator);
		this.getEntityManager().update(entity);
		
		//保存公司角色
		this.doSaveCompanyRoles(dto.getRecId(), entity.getCompanyCode(), dto.getRoleIds());
		
	}

	@Override
	public void doUpdateCompanyByUserPerson(Company dto, SessionUser operator) {
		if (dto == null || dto.getRecId() == null) {
			return;
		}
		Company entity = this.getEntityManager().get(Company.class, dto.getRecId());
		if (entity == null) {
			return;
		}

		// 存储一级代理公司的基本信息
		entity.setNameCn(dto.getNameCn());
		entity.setNameEn(dto.getNameEn());
		entity.setCityCode(StringUtils.safeToUpperCase(dto.getCityCode()));
		entity.setAirportCode(StringUtils.safeToUpperCase(dto.getAirportCode()));
		entity.setCopyRight(dto.getCopyRight());
		entity.setCustomerServicePhone(dto.getCustomerServicePhone());
		entity.setCustomerServiceEmail(dto.getCustomerServiceEmail());
		
		entity.setAddress(dto.getAddress());
		entity.setPostcode(dto.getPostcode());
		entity.setContactorName(dto.getContactorName());
		entity.setContactorPhone(dto.getContactorPhone());
		entity.setContactorFax(dto.getContactorFax());
		entity.setContactorEmail(dto.getContactorEmail());
		this.fillAuditableEntityOnUpdate(entity, operator);
		this.getEntityManager().update(entity);
		
	}

	@Override
	public void doDeleteCompany(Long recId) {
		//删除User表
		this.getEntityManager().executeHql("delete User u where u.companyId = ?", new Object[] {recId});
		//删除CompanyRole表
		this.getEntityManager().executeHql("delete CompanyRole cr where cr.companyId = ?", new Object[] {recId});
		//删除Company表
		this.getEntityManager().delete(Company.class, recId);
		
	}

	@Override
	public void doNormalCompany(Long recId, SessionUser operator) {
		doUpdateLockStatus(recId, LockStatus.Normal, operator);
	}

	@Override
	public void doLockedCompany(Long recId, SessionUser operator) {
		doUpdateLockStatus(recId, LockStatus.Locked, operator);
	}
	//修改状态 
    protected void doUpdateLockStatus(Long recId, LockStatus lockStatus, SessionUser operator) {
		Company company = this.getCompanyById(recId);
		if (company == null) {
		    return;
		}
		// 状态
		company.setStatus(lockStatus);
		// 操作信息
		this.fillAuditableEntityOnUpdate(company, operator);
    }

	@Override
	public void doSaveCompanyRoles(Long companyId, String companyCode, List<Long> roleIds) {
		// 删除原有记录
    	getEntityManager().executeHql("delete CompanyRole cr where cr.companyId = ?", new Object[] {companyId});
    	if (roleIds != null && roleIds.size() > 0) {
	    	for (Long roleId : roleIds) {
	    		CompanyRole entity = new CompanyRole();
	    		entity.setRoleId(roleId);
	    		entity.setCompanyId(companyId);
	    		entity.setCompanyCode(companyCode);
	    		getEntityManager().save(entity);
	    	}
    	}
		
	}

	@Override
	public boolean hasExistThisCompanyCode(String companyCode) {
		if (StringUtils.isEmpty(companyCode)) {
			return false;
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Company.class);
		sdc.add(SafeRestrictions.equal("companyCode", StringUtils.safeToUpperCase(companyCode)));
		return getEntityManager().queryForListByCriteria(Company.class, sdc).size() > 0;
	}

}
