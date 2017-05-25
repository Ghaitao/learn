package com.ght.learn.ffs.service.base.support;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.sys.News;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.base.NewsQueryService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.pagination.OrderablePagination;

@Component("NewsQueryService")
public class NewsQueryServiceImpl extends FfsServiceImpl implements NewsQueryService {

    @Override
    public List<News> queryRecentNews(SessionUser sessionUser) {
	if (sessionUser == null) {
	    return Collections.emptyList();
	}
	OrderablePagination pagination = new OrderablePagination(5, 0, 5);
	HashMap<String, Object> parameter = new HashMap<String, Object>();
	parameter.put("companyCode", sessionUser.getCompanyCode());
	parameter.put("adminCompanyCode", Company.ADMIN_COMPANY_CODE);
	return getEntityManager().queryForListWithPaginationBySqlMap(News.class, "com.ght.learn.ffs.entity.sys.news.queryNewsWithPagination", parameter, pagination);
    }

    @Override
    public List<News> queryNewsWithPagination(SessionUser sessionUser, OrderablePagination pagination) {
	if (sessionUser == null || pagination == null) {
	    return Collections.emptyList();
	}
	HashMap<String, Object> parameter = new HashMap<String, Object>();
	parameter.put("currentDateTime", getEntityManager().getDbCurrentTimestamp());
	parameter.put("companyCode", sessionUser.getCompanyCode());
	parameter.put("adminCompanyCode", Company.ADMIN_COMPANY_CODE);
	return getEntityManager().queryForListWithPaginationBySqlMap(News.class, "com.ght.learn.ffs.entity.sys.news.queryNewsWithPagination", parameter, pagination);
    }

    @Override
    public News queryNewsById(Long recId) {
	return getEntityManager().get(News.class, recId);
    }

}