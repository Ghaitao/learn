package com.ght.learn.ffs.service.sys.support;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.sys.CompanyRole;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.sys.CompanyRoleService;

import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;

@Service("CompanyRoleService")
public class CompanyRoleServiceImpl extends FfsServiceImpl implements CompanyRoleService {

	@Override
	public List<CompanyRole> queryCompanyRolesByCompanyId(Long companyId) {
		if (companyId == null) {
			return null;
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(CompanyRole.class);
		sdc.add(SafeRestrictions.equal("companyId", companyId));
		List<CompanyRole> result = getEntityManager().queryForListByCriteria(CompanyRole.class, sdc);
		return result;
	}

}
