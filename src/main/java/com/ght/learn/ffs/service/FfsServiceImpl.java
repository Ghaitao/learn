package com.ght.learn.ffs.service;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.AuditableEntity;
import com.ght.learn.ffs.entity.CreatableEntity;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.dao.orm.hibernate.HibernateOrmExecution;
import framework.service.CommonServiceImpl;

@Service("FfsService")
public class FfsServiceImpl extends CommonServiceImpl implements FfsService,MessageSourceAware{

	@Resource(name="MySQLHibernateEntityManager",type=HibernateOrmExecution.class)
	private HibernateOrmExecution entityManager;
	
	//定时周期执行指定任务
	@Resource(name = "scheduledExecutorService", type = ScheduledExecutorService.class)
    private ScheduledExecutorService scheduledExecutorService;
    
    private MessageSource messageSource;
    
	@Override
	protected HibernateOrmExecution getEntityManager() {
		// TODO Auto-generated method stub
		return this.entityManager;
	}
	@Override
    public void setMessageSource(MessageSource messageSource) {
	this.messageSource = messageSource;
    }

    protected ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    protected MessageSource getMessageSource() {
        return messageSource;
    }
    /**
     * 新增时,创建人,操作人添加
     * @param entity
     * @param operator
     */
    protected void fillCreatableEntityOnCreate(CreatableEntity entity, SessionUser operator) {
    	if (entity == null) {
    	    return;
    	}
    	if (entity.getCreateDateTime() == null) {
    	    Date currentTime = this.entityManager.getDbCurrentTimestamp();
    	    entity.setCreateDateTime(currentTime);
    	}
    	if (operator != null) {
    	    entity.setCreator(operator.getUserId());
    	}
    }
        
    protected void fillAuditableEntityOnCreate(AuditableEntity entity, SessionUser operator) {
    	if (entity == null) {
    	    return;
    	}
    	if (entity.getCreateDateTime() == null || entity.getModifyDateTime() == null) {
    	    Date currentTime = this.entityManager.getDbCurrentTimestamp();
    	    if (entity.getCreateDateTime() == null) {
    		entity.setCreateDateTime(currentTime);
    	    }
    	    if (entity.getModifyDateTime() == null) {
    		entity.setModifyDateTime(currentTime);
    	    }
    	}
    	if (operator != null) {
    	    entity.setCreator(operator.getUserId());
    	    entity.setModifier(operator.getUserId());
    	}
   }
        
   protected void fillAuditableEntityOnUpdate(AuditableEntity entity, SessionUser operator) {
    	if (entity == null) {
    	    return;
    	}
    	if (operator != null) {
    	    entity.setModifier(operator.getUserId());
    	}
    	Date currentTime = this.entityManager.getDbCurrentTimestamp();
    	entity.setModifyDateTime(currentTime);
   }

}
