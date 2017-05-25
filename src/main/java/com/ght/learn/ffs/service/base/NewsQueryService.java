package com.ght.learn.ffs.service.base;

import java.util.List;

import com.ght.learn.ffs.entity.sys.News;
import com.ght.learn.ffs.service.FfsService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.pagination.OrderablePagination;

public interface NewsQueryService extends FfsService {
    List<News> queryRecentNews(SessionUser sessionUser);
    List<News> queryNewsWithPagination(SessionUser sessionUser, OrderablePagination pagination);
    
    News queryNewsById(Long recId);
}