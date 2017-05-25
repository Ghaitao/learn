package com.ght.learn.ffs.web.tag;

import javax.servlet.jsp.JspException;

import com.ght.learn.ffs.common.EnumHelper;

public class EnumTag extends AbstractStringPrintJspTag{

	private static final long serialVersionUID = 6718972511403598645L;
    
    private String declaringClassName;
    
    private String name;
	@Override
	protected String getOutString() throws JspException {
		//System.out.println("这是啥:::"+EnumHelper.getEnumResourceMessage(this.declaringClassName, this.name, getApplicationContext()));
		
		return EnumHelper.getEnumResourceMessage(this.declaringClassName, this.name, getApplicationContext());
		//return getApplicationContext().getMessage(this.declaringClassName+ Constants.DOT+this.name, Constants.EMPTY_OBJECT_ARRAY, null,LocaleContextHolder.getLocale());
	}
	public void setDeclaringClassName(String declaringClassName) {
        this.declaringClassName = declaringClassName;
    }

    public void setName(String name) {
        this.name = name;
    }

}
