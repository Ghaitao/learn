package com.ght.learn.ffs.web.controller;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import com.ght.learn.ffs.common.EnumHelper;

import framework.bean.LabelValueBean;
import framework.web.springmvc.controller.BaseController;

public abstract class FfsController extends BaseController implements MessageSourceAware {
	
	private MessageSource messageSource;
    
    @Override
    public void setMessageSource(MessageSource messageSource) {
	this.messageSource = messageSource;
    }

    public <E extends Enum<E>> List<LabelValueBean<String>> createEnumLabelValueBeanList(Class<E> enumClass, boolean needPleaseSelect) {
        return EnumHelper.createLabelValueBeanList(enumClass, this.messageSource, needPleaseSelect);
    }
    
    protected MessageSource getMessageSource() {
        return messageSource;
    }
}