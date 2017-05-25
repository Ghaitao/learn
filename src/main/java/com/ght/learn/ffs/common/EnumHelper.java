package com.ght.learn.ffs.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import framework.bean.LabelValueBean;
import framework.core.Constants;
import framework.core.utils.CollectionUtils;
import framework.core.utils.ObjectUtils;

public abstract class EnumHelper {
    
	public static String createEnumResourceKey(String enumDeclaringClassName, String enumName) {
		return enumDeclaringClassName + Constants.DOT + enumName;
	    }
	    
	    public static String createEnumResourceKey(Enum<?> e) {
		if (e == null) {
		    return null;
		}
		return createEnumResourceKey(e.getDeclaringClass().getName(), e.name());
	    }
	    
	    public static String getEnumResourceMessage(String enumDeclaringClassName, String enumName, MessageSource messageSource) {
	    	return getEnumResourceMessageWithDefaultMessage(createEnumResourceKey(enumDeclaringClassName, enumName), enumName, messageSource);
	    }
	    
	    public static String getEnumResourceMessage(Enum<?> e, MessageSource messageSource) {
		return getEnumResourceMessage(e, messageSource, LocaleContextHolder.getLocale());
	    }
	    
	    public static String getEnumResourceMessage(Enum<?> e, MessageSource messageSource, Locale language) {
		if (e == null) {
		    return null;
		}
		return getEnumResourceMessageWithDefaultMessage(createEnumResourceKey(e), e.name(), messageSource, language);
	    }
	    
	    public static String getEnumResourceMessage(String enumResourceKey, MessageSource messageSource) {
		return getEnumResourceMessageWithDefaultMessage(enumResourceKey, null, messageSource, LocaleContextHolder.getLocale());
	    }
	    
	    public static String getEnumResourceMessageWithDefaultMessage(String enumResourceKey, String defaultMessage, MessageSource messageSource) {
		return getEnumResourceMessageWithDefaultMessage(enumResourceKey, defaultMessage, messageSource, LocaleContextHolder.getLocale());
	    }
	    
	    public static String getEnumResourceMessageWithDefaultMessage(String enumResourceKey, String defaultMessage, MessageSource messageSource, Locale language) {
		if (messageSource == null) {
		    return defaultMessage;
		}
		return messageSource.getMessage(enumResourceKey, Constants.EMPTY_OBJECT_ARRAY, defaultMessage, language);
	    }
	    
	    public static List<LabelValueBean<String>> createLabelValueBeanList(Enum<?>[] enums, MessageSource messageSource, boolean needPleaseSelect) {
		if (ObjectUtils.isEmpty(enums)) {
		    return createLabelValueBeanList(Collections.emptyList(), messageSource, needPleaseSelect);
		} else {
		    return createLabelValueBeanList(Arrays.asList(enums), messageSource, needPleaseSelect);
		}
	    }
	    
	    public static List<LabelValueBean<String>> createLabelValueBeanList(List<? extends Enum<?>> enumList, MessageSource messageSource, boolean needPleaseSelect) {
		ArrayList<LabelValueBean<String>> lbList = new ArrayList<LabelValueBean<String>>();
		if (needPleaseSelect) {
		    lbList.add(new LabelValueBean<String>(messageSource.getMessage(FfsConstants.MESSAGE_CODE_SELECT_OPTION, Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()), ""));
		}
		if (CollectionUtils.isEmpty(enumList)) {
		    return lbList;
		}
		for (Enum<?> e : enumList) {
	            LabelValueBean<String> lb = new LabelValueBean<String>();
	            lb.setLabel(getEnumResourceMessage(e, messageSource));
	            lb.setValue(e.name());
	            lbList.add(lb);
		}
		return lbList;
	    }
	    
	    public static <E extends Enum<E>> List<LabelValueBean<String>> createLabelValueBeanList(Class<E> enumClass, MessageSource messageSource) {
		return createLabelValueBeanList(enumClass, messageSource, false);
	    }
	    
	    public static <E extends Enum<E>> List<LabelValueBean<String>> createLabelValueBeanList(Class<E> enumClass, MessageSource messageSource, boolean needPleaseSelect) {
		ArrayList<LabelValueBean<String>> enumList = new ArrayList<LabelValueBean<String>>();
		if (needPleaseSelect) {
		    enumList.add(new LabelValueBean<String>(messageSource.getMessage(FfsConstants.MESSAGE_CODE_SELECT_OPTION, Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()), ""));
		}
		if (enumClass == null) {
		    return enumList;
		}
		EnumSet<E> enumSet = EnumSet.allOf(enumClass);
	        for (E e : enumSet) {
	            LabelValueBean<String> lb = new LabelValueBean<String>();
	            lb.setLabel(getEnumResourceMessage(e, messageSource));
	            lb.setValue(e.name());
	            enumList.add(lb);
		}
	        return enumList;
	    }
}