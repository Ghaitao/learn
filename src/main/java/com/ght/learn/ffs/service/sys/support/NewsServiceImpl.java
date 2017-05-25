package com.ght.learn.ffs.service.sys.support;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.sys.News;
import com.ght.learn.ffs.enums.sys.NewsGrade;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.sys.NewsService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.enums.CommonEnums.IF;
import framework.core.pagination.OrderablePagination;
import framework.core.pagination.Sorter;
import framework.core.utils.ObjectUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;

@Component("NewsService")
public class NewsServiceImpl extends FfsServiceImpl implements NewsService {

	@Override
	public News getNewsById(Long newsId) {
		return this.getEntityManager().get(News.class, newsId);
	}

	@Override
	public List<News> queryNewsShow(News dto, SessionUser sessionUser, OrderablePagination op) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(News.class);
		if(dto != null) {
			sdc.add(SafeRestrictions.likeAnyWhere("title", dto.getTitle()));
		}
		sdc.add(SafeRestrictions.or(SafeRestrictions.equal("companyCode", sessionUser.getCompanyCode()),
			SafeRestrictions.equal("companyCode", Company.ADMIN_COMPANY_CODE)));
		if(op != null && !ObjectUtils.isEmpty(op.getSorters())) {
			sdc.addSorters(op.getSorters(), new Sorter[] {Sorter.desc("modifyDateTime") });
		}
		return this.getEntityManager().queryForListByCriteria(News.class, sdc, op);
	}
	
	@Override
	public List<News> queryNews(News dto, SessionUser sessionUser, OrderablePagination op) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(News.class);
		if(dto != null) {
			sdc.add(SafeRestrictions.likeAnyWhere("title", dto.getTitle()));
			sdc.add(SafeRestrictions.and(SafeRestrictions.between("startDateTime", dto.getStartDateTime(), dto.getExpiryDateTime()),
					SafeRestrictions.between("expiryDateTime", dto.getStartDateTime(), dto.getExpiryDateTime())));
		}
		sdc.add(SafeRestrictions.equal("companyCode", sessionUser.getCompanyCode()));
		if(op != null && !ObjectUtils.isEmpty(op.getSorters())) {
			sdc.addSorters(op.getSorters(), new Sorter[] {Sorter.desc("modifyDateTime") });
		}
		return this.getEntityManager().queryForListByCriteria(News.class, sdc, op);
	}


	@Override
	public Long doCreateNews(News dto, SessionUser sessionUser) {
		if(dto == null) {
			return null;
		}
		
		News entity = new News();
		entity.setTitle(dto.getTitle());
		entity.setSummary(dto.getSummary());
		entity.setContent(dto.getContent());
		entity.setGrade(dto.getGrade());
		entity.setTop(dto.getTop());
		entity.setStartDateTime(dto.getStartDateTime());
		entity.setExpiryDateTime(dto.getExpiryDateTime());
		entity.setCompanyCode(sessionUser.getCompanyCode());
		entity.setModifier(sessionUser.getUserId());
		entity.setModifyDateTime(this.getEntityManager().getDbCurrentTimestamp());
		Long newsId = (Long) this.getEntityManager().save(entity);
		return newsId;
	}

	@Override
	public void doUpdateNews(News dto, SessionUser sessionUser) {
		if (dto == null || dto.getRecId() == null) {
			return;
		}
		News entity = this.getNewsById(dto.getRecId());
		if (entity == null) {
			return;
		}
		
		entity.setTitle(dto.getTitle());
		entity.setSummary(dto.getSummary());
		entity.setContent(dto.getContent());
		entity.setGrade(dto.getGrade());
		entity.setTop(dto.getTop());
		entity.setStartDateTime(dto.getStartDateTime());
		entity.setExpiryDateTime(dto.getExpiryDateTime());
		entity.setCompanyCode(sessionUser.getCompanyCode());
		entity.setModifier(sessionUser.getUserId());
		entity.setModifyDateTime(this.getEntityManager().getDbCurrentTimestamp());
		this.getEntityManager().update(entity);
	}

	@Override
	public void doDeleteNews(Long newsId) {
		this.getEntityManager().delete(News.class, newsId);
	}

	@Override
	public void doTopNews(Long newsId, SessionUser sessionUser) {
		doUpdateTopStatus(newsId, IF.Y, sessionUser);
	}

	@Override
	public void doUnTopNews(Long newsId, SessionUser sessionUser) {
		doUpdateTopStatus(newsId, IF.N, sessionUser);
	}
	
	//修改置顶 
    protected void doUpdateTopStatus(Long newsId, IF status, SessionUser sessionUser) {
		News entity = this.getNewsById(newsId);
		if (entity == null) {
		    return;
		}
		// 置顶
		entity.setTop(status);
		// 操作信息
		entity.setModifier(sessionUser.getUserId());
		entity.setModifyDateTime(this.getEntityManager().getDbCurrentTimestamp());
    }
    
    @Override
	public void doImaportantNews(Long newsId, SessionUser sessionUser) {
		doUpdateImportantStatus(newsId, NewsGrade.Important, sessionUser);
	}

	@Override
	public void doNormalNews(Long newsId, SessionUser sessionUser) {
		doUpdateImportantStatus(newsId, NewsGrade.Normal, sessionUser);
		
	}

    //修改是否重要
    protected void doUpdateImportantStatus(Long newsId, NewsGrade status, SessionUser sessionUser) {
		News entity = this.getNewsById(newsId);
		if (entity == null) {
		    return;
		}
		// 置顶
		entity.setGrade(status);;
		// 操作信息
		entity.setModifier(sessionUser.getUserId());
		entity.setModifyDateTime(this.getEntityManager().getDbCurrentTimestamp());
    }

}
