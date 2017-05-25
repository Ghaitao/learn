package com.ght.learn.ffs.common;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import framework.core.utils.StringUtils;

public interface I18nable {
    
    /**
     * 取得显示的名称
     * @return
     */
    default String getI18nName() {
	String name = null;
	if (I18nable.this.isEnglish()) {
	    name = getNameEn();
	} else {
	    name = getNameCn();
	}
	return name;
    }
    
    default boolean isEnglish() {
	return StringUtils.safeEqualsIgnoreCase(Locale.ENGLISH.getLanguage(), getCurrentLocale().getLanguage());
    }
    
    default Locale getCurrentLocale() {
	return LocaleContextHolder.getLocale();
    }
    
    String getNameCn();
    String getNameEn();
}