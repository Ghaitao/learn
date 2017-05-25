package com.ght.learn.ffs.service.base;

import java.util.List;

import com.ght.learn.ffs.entity.basic.DictionaryData;
import com.ght.learn.ffs.entity.sys.FeeType;
import com.ght.learn.ffs.service.FfsService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.bean.LabelValueBean;

public interface BasicDataQueryService extends FfsService {
    List<DictionaryData> queryDictionaryDatas(String parentCode, SessionUser operator);
    List<LabelValueBean<String>> queryDictionaryDatasOfLabelValueBean(String parentCode, SessionUser operator);
    List<LabelValueBean<String>> queryDictionaryDatasOfLabelValueBean(String parentCode, SessionUser operator, boolean hasSelectOption);
    
    DictionaryData queryDictionaryData(String parentCode, String code, SessionUser operator);
    
    List<FeeType> queryFeeTypes(SessionUser operator);
}