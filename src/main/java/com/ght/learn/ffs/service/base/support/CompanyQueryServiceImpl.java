package com.ght.learn.ffs.service.base.support;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Projections;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.enums.LockStatus;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.base.CompanyQueryService;

import framework.core.utils.DateUtils;
import framework.core.utils.StringUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;
import framework.service.ServiceException;

@Component("CompanyQueryService")
public class CompanyQueryServiceImpl extends FfsServiceImpl implements CompanyQueryService{

	 private MessageSource messageSource;
	    
	 @Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	@Override
	public Company queryCompanyByCompanyCode(String companyCode) {
		if (StringUtils.isEmpty(companyCode)) {
		    return null;
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Company.class);
		sdc.add(SafeRestrictions.equal("companyCode", companyCode));
		return getEntityManager().queryForUniqueObjectByCriteria(Company.class, sdc);
	}

	@Override
	public Company queryCheckedCompanyByCompanyCode(String companyCode) throws ServiceException {
		Company company = queryCompanyByCompanyCode(companyCode);
		if (company == null) {
		    throw new ServiceException(this.messageSource.getMessage("com.jinzhiyi.ffs.message.company.invalid_companycode", new Object[] {companyCode}, LocaleContextHolder.getLocale()));
		}
		if (LockStatus.Locked.equals(company.getStatus())) {
		    throw new ServiceException(this.messageSource.getMessage("com.jinzhiyi.ffs.message.company.locked_company", new Object[] {company.getCompanyCode()}, LocaleContextHolder.getLocale()));
		}

		if (company.getStartDateTime() == null && company.getExpiryDateTime() == null) {
		    return company;
		}
		
		Date current = getEntityManager().getDbCurrentTimestamp();
		if (company.getStartDateTime() != null) {
		    if (current.before(company.getStartDateTime())) {
			throw new ServiceException(this.messageSource.getMessage("com.jinzhiyi.ffs.message.company.not_reach_start_date", 
				new Object[] {company.getCompanyCode(), DateUtils.getDateFormat("yyyy-MM-dd HH:mm:ss", LocaleContextHolder.getLocale()).format(company.getStartDateTime())}, 
				LocaleContextHolder.getLocale()));
		    }
		}
		
		if (company.getExpiryDateTime() != null) {
		    if (current.after(company.getExpiryDateTime())) {
			throw new ServiceException(this.messageSource.getMessage("com.jinzhiyi.ffs.message.company.exceed_deadline", 
				new Object[] {company.getCompanyCode(), DateUtils.getDateFormat("yyyy-MM-dd HH:mm:ss", LocaleContextHolder.getLocale()).format(company.getExpiryDateTime())}, 
				LocaleContextHolder.getLocale()));
		    }
		}
		return company;
	}

	@Override
	public List<String> queryValidCompanyCodes() {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Company.class);
		sdc.setProjection(Projections.property("companyCode"));
		sdc.add(SafeRestrictions.notEqual("companyCode", Company.ADMIN_COMPANY_CODE));
		sdc.add(SafeRestrictions.equal("status", LockStatus.Normal));
		return getEntityManager().queryForListByCriteria(String.class, sdc);
	}

}
