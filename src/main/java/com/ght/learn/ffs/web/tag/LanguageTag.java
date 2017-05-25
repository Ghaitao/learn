package com.ght.learn.ffs.web.tag;

import java.util.HashSet;
import java.util.Locale;

import javax.servlet.jsp.JspException;

import org.springframework.context.i18n.LocaleContextHolder;

public class LanguageTag extends AbstractStringPrintJspTag {

	private static final long serialVersionUID = 837211358558490184L;

	@SuppressWarnings("serial")
	private static final HashSet<Locale> cnSet = new HashSet<Locale>() {{
		add(Locale.CHINESE); 
		add(Locale.SIMPLIFIED_CHINESE); 
		add(Locale.TRADITIONAL_CHINESE); 
	}};
	
	@SuppressWarnings("serial")
	private static final HashSet<Locale> enSet = new HashSet<Locale>() {{
		add(Locale.ENGLISH); 
		add(Locale.US); 
		add(Locale.UK); 
		add(Locale.CANADA); 
	}};
	
	private String langName;

	private boolean localeFlag = true;
	
	@Override
	protected String getOutString() throws JspException {
		Locale local = localeFlag == true ? LocaleContextHolder.getLocale() : new Locale(langName);
		if (local == null  || cnSet.contains(local) ) {
			return "zh-CN";
		} else if (enSet.contains(local) ) {
			return "en";
		}
		return local.getLanguage();
	}
	
	public void setLocaleFlag(boolean localeFlag) {
		this.localeFlag = localeFlag;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

}