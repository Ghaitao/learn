package com.ght.learn.ffs.service.sys;

import java.util.List;

import com.ght.learn.ffs.entity.sys.News;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.pagination.OrderablePagination;
import framework.service.CommonService;

public interface NewsService extends CommonService {

	/**
	 * 根据id获取新闻
	 * @param newsId
	 * @return
	 */
	News getNewsById(Long newsId);
	
	/**
	 * 按条件查询新闻
	 * @param dto
	 * @param op
	 * @return
	 */
	List<News> queryNewsShow(News dto, SessionUser sessionUser, OrderablePagination op);
	List<News> queryNews(News dto, SessionUser sessionUser, OrderablePagination op);
	
	/**
	 * 新增一条新闻
	 * @param dto
	 * @param sessionUser
	 * @return
	 */
	Long doCreateNews(News dto, SessionUser sessionUser);
	
	/**
	 * 修改一条新闻
	 * @param dto
	 * @param sessionUser
	 */
	void doUpdateNews(News dto, SessionUser sessionUser);
	
	/**
	 * 根据id删除新闻
	 * @param newsId
	 */
	void doDeleteNews(Long newsId);
	
	/**
	 * 置顶
	 * @param newsId
	 * @param sessionUser
	 */
	void doTopNews(Long newsId, SessionUser sessionUser);
	
	/**
	 * 不置顶
	 * @param newsId
	 * @param sessionUser
	 */
	void doUnTopNews(Long newsId, SessionUser sessionUser);
	
	/**
	 * 设置为重要
	 * @param newsId
	 * @param sessionUser
	 */
	void doImaportantNews(Long newsId, SessionUser sessionUser);
	
	/**
	 * 设置为正常
	 * @param newsId
	 * @param sessionUser
	 */
	void doNormalNews(Long newsId, SessionUser sessionUser);
}
