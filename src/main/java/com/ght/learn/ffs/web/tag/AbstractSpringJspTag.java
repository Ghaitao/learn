package com.ght.learn.ffs.web.tag;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.tags.HtmlEscapingAwareTag;

public abstract class AbstractSpringJspTag extends HtmlEscapingAwareTag {

    private static final long serialVersionUID = 2688448232598891383L;


	
    
    protected <B> B getBean(Class<B> requiredType, String beanName) {
	return getApplicationContext().getBean(beanName, requiredType);
    }
    
    protected <B> B getBean(Class<B> requiredType) {
	return getApplicationContext().getBean(requiredType);
    }
    
    protected ApplicationContext getApplicationContext() {
	return WebApplicationContextUtils.getRequiredWebApplicationContext(this.pageContext.getSession().getServletContext());
    }
}
