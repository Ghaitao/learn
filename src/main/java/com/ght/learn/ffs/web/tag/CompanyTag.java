package com.ght.learn.ffs.web.tag;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.service.base.CompanyQueryService;

import framework.core.utils.StringUtils;

public class CompanyTag extends AbstractStringPrintJspTag {

    private static final long serialVersionUID = -367962375921207861L;
    
    private String companyCode;

    @Override
    protected String getOutString() throws JspException {
	if (StringUtils.isEmpty(this.companyCode)) {
	    return null;
	}
	String companyCodeString = (String) ExpressionEvaluatorManager.evaluate("companyCode", this.companyCode, java.lang.String.class, this, this.pageContext);
	Company company = getBean(CompanyQueryService.class).queryCompanyByCompanyCode(companyCodeString);
	return company == null ? null : company.getI18nName();
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

}