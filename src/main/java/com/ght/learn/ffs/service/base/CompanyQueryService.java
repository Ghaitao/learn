package com.ght.learn.ffs.service.base;

import java.util.List;

import com.ght.learn.ffs.entity.sys.Company;

import framework.service.CommonService;
import framework.service.ServiceException;

public interface CompanyQueryService extends CommonService{

	Company queryCompanyByCompanyCode(String companyCode);
    Company queryCheckedCompanyByCompanyCode(String companyCode) throws ServiceException;
    
    List<String> queryValidCompanyCodes();
}
