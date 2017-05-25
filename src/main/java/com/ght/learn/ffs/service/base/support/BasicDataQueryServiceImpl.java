package com.ght.learn.ffs.service.base.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.basic.DictionaryData;
import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.sys.FeeType;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.base.BasicDataQueryService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.bean.LabelValueBean;
import framework.core.Constants;
import framework.core.utils.CollectionUtils;
import framework.core.utils.StringUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;

@Service("BasicDataQueryService")
public class BasicDataQueryServiceImpl extends FfsServiceImpl implements BasicDataQueryService {

    //@Cacheable(value="data", key="'com.jinzhiyi.ffs.entity.basic.DictionaryData@parentCode='+#parentCode")
    @Override
    public List<DictionaryData> queryDictionaryDatas(String parentCode, SessionUser operator) {
		if (StringUtils.isEmpty(parentCode)) {
		    return Collections.emptyList();
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(DictionaryData.class);
		sdc.add(SafeRestrictions.equal("parentCode", parentCode));
		sdc.addOrder(Order.asc("sorter"));
		return getEntityManager().queryForListByCriteria(DictionaryData.class, sdc);
    }

    @Override
    public List<LabelValueBean<String>> queryDictionaryDatasOfLabelValueBean(String parentCode, SessionUser operator) {
    	return queryDictionaryDatasOfLabelValueBean(parentCode, operator, true);
    }

    @Override
    public List<LabelValueBean<String>> queryDictionaryDatasOfLabelValueBean(String parentCode, SessionUser operator, boolean hasSelectOption) {
		ArrayList<LabelValueBean<String>> result = new ArrayList<>();
		if (hasSelectOption) {
		    LabelValueBean<String> lb = new LabelValueBean<String>();
		    lb.setValue("");
		    lb.setLabel(getMessageSource().getMessage("com.jinzhiyi.ffs.message.option.select", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		    result.add(lb);
		}
		
		List<DictionaryData> dictionaryDatas = queryDictionaryDatas(parentCode, operator);
		if (CollectionUtils.isEmpty(dictionaryDatas)) {
		    return result;
		}
		
		for (DictionaryData dictionaryData : dictionaryDatas) {
		    LabelValueBean<String> lb = new LabelValueBean<String>();
		    lb.setValue(dictionaryData.getCode());
		    lb.setLabel(dictionaryData.getI18nName());
		    result.add(lb);
		}
		return result;
    }

    @Override
    public DictionaryData queryDictionaryData(String parentCode, String code, SessionUser operator) {
		if (StringUtils.isEmpty(parentCode) || StringUtils.isEmpty(code)) {
		    return null;
		}
		
		List<DictionaryData> dictionaryDatas = queryDictionaryDatas(parentCode, operator);
		if (CollectionUtils.isEmpty(dictionaryDatas)) {
		    return null;
		}
		for (DictionaryData dictionaryData : dictionaryDatas) {
		    if (StringUtils.safeEquals(code, dictionaryData.getCode())) {
			return dictionaryData;
		    }
		}
		return null;
    }

  //  @Cacheable(value="data", key="'com.jinzhiyi.ffs.entity.sys.FeeType@companyCode='+#operator.companyCode")
    @Override
    public List<FeeType> queryFeeTypes(SessionUser operator) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(FeeType.class);
		sdc.add(SafeRestrictions.or(SafeRestrictions.equal("companyCode", operator.getCompanyCode()), SafeRestrictions.equal("companyCode", Company.ADMIN_COMPANY_CODE)));
		return getEntityManager().queryForListByCriteria(FeeType.class, sdc);
    }
}
