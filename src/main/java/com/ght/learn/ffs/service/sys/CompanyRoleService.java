package com.ght.learn.ffs.service.sys;

import java.util.List;

import com.ght.learn.ffs.entity.sys.CompanyRole;

import framework.service.CommonService;

public interface CompanyRoleService extends CommonService {
	
	/**
	 * 根据companyId得到公司角色列表
	 * @param companyId
	 * @return 公司角色列表
	 */
	List<CompanyRole> queryCompanyRolesByCompanyId(Long companyId);
}
