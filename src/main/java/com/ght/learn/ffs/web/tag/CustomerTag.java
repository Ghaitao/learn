package com.ght.learn.ffs.web.tag;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ght.learn.ffs.entity.customer.Customer;
import com.ght.learn.ffs.service.customer.CustomerService;
import com.ght.learn.ffs.tool.context.SessionUserHolder;

import framework.core.utils.StringUtils;

public class CustomerTag extends AbstractStringPrintJspTag {

    private static final long serialVersionUID = -6035958939712879948L;
    
    private String customerId;

    private String customerCode;
    
    @Override
    protected String getOutString() throws JspException {
	if (StringUtils.isEmpty(this.customerId) && StringUtils.isEmpty(this.customerCode)) {
	    return null;
	}
	Customer customer = null;
	if (StringUtils.hasText(this.customerId)) {
	    Long customerIdLong = (Long) ExpressionEvaluatorManager.evaluate("customerId", this.customerId, java.lang.Long.class, this, this.pageContext);
	    customer = getBean(CustomerService.class).queryCustomerById(customerIdLong);
	} else if (StringUtils.hasText(this.customerCode)) {
	    String customerCodeString = (String) ExpressionEvaluatorManager.evaluate("customerCode", this.customerCode, java.lang.String.class, this, this.pageContext);
	    customer = getBean(CustomerService.class).queryCustomerByCustomerCode(SessionUserHolder.getSessionUser().getCompanyCode(), customerCodeString);
	}
	return customer == null ? getNullShow() : customer.getI18nName();
    }

    private String getNullShow() {
	if (StringUtils.hasText(customerId)) {
	    return this.customerId;
	}
	if (StringUtils.hasText(customerCode)) {
	    return this.customerCode;
	}
	return null;
    }
    
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}