package com.ght.learn.ffs.web.tag;

import javax.servlet.jsp.JspException;

import framework.core.utils.StringUtils;

public abstract class AbstractStringPrintJspTag extends AbstractSpringJspTag {

    private static final long serialVersionUID = 6756475090366942563L;

    @Override
    protected int doStartTagInternal() throws Exception {
	this.pageContext.getOut().print(htmlEscape(StringUtils.replaceNullString(getOutString())));
	return SKIP_BODY;
    }

    protected abstract String getOutString() throws JspException;
}