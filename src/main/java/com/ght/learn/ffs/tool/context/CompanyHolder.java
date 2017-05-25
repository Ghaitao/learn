package com.ght.learn.ffs.tool.context;

import com.ght.learn.ffs.entity.sys.Company;

public abstract class CompanyHolder {

    private static ThreadLocal<Company> COMPANY_CONTEXT = new ThreadLocal<Company>();

    public static String getCompanyCode() {
	return getCompany().getCompanyCode();
    }
    
    public static Company getCompany() {
	return COMPANY_CONTEXT.get();
    }

    public static void setCompany(Company company) {
	if (company != null) {
	    COMPANY_CONTEXT.set(company);
	}
    }

    public static void removeCompany() {
	COMPANY_CONTEXT.remove();
    }
}