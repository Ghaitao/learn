package com.ght.learn.ffs.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.CollectionUtils;

import framework.bean.LabelValueBean;
import framework.core.Constants;

public abstract class LabelValueBeanGenerator {

    public static <T> List<LabelValueBean<String>> convertFromBeanList(List<T> beanList, MessageSource messageSource, LabelValue<T> labelValue) {
	return convertFromBeanList(beanList, true, messageSource, labelValue);
    }
    
    public static <T> List<LabelValueBean<String>> convertFromBeanList(List<T> beanList, 
	    boolean hasSelectOption,
	    MessageSource messageSource,
	    LabelValue<T> labelValue) {
	ArrayList<LabelValueBean<String>> list = new ArrayList<>(); 
	if (hasSelectOption) {
	    LabelValueBean<String> lb = new LabelValueBean<String>();
	    lb.setValue("");
	    lb.setLabel(messageSource.getMessage(FfsConstants.MESSAGE_CODE_SELECT_OPTION, Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
	    list.add(lb);
	}
	if (CollectionUtils.isEmpty(beanList)) {
	    return list;
	}
	for (T t : beanList) {
	    list.add(new LabelValueBean<String>(labelValue.getLabel(t), labelValue.getValue(t)));
	}
	return list;
    }
    
    public static interface LabelValue<T> {
	
	String getLabel(T t);
	
	String getValue(T t);
    }
}