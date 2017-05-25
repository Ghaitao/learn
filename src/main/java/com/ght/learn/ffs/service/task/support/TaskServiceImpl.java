package com.ght.learn.ffs.service.task.support;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.task.Task;
import com.ght.learn.ffs.enums.task.TaskType;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.task.TaskService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.utils.CollectionUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;
import framework.service.ServiceException;

@Service("TaskService")
public class TaskServiceImpl extends FfsServiceImpl implements TaskService {

    @Override
    public List<Long> queryMyCorrelatedIdsByTaskType(TaskType taskType, SessionUser me) {
		Assert.notNull(taskType);
		Assert.notNull(me);
		Set<Long> roleIds = me.getRoleIds();
		if (CollectionUtils.isEmpty(roleIds)) {
		    return Collections.emptyList();
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Task.class);
		sdc.setProjection(Projections.property("correlatedId"));
		sdc.add(SafeRestrictions.equal("taskType", taskType));
		sdc.add(SafeRestrictions.equal("companyCode", me.getCompanyCode()));
		if (roleIds.size() == 1) {
		    sdc.add(SafeRestrictions.equal("handleRoleId", roleIds.iterator().next()));
		} else {
		    sdc.add(SafeRestrictions.in("handleRoleId", roleIds));
		}
		sdc.add(SafeRestrictions.or(SafeRestrictions.isNull("handleUserId"), SafeRestrictions.equal("handleUserId", me.getUserId())));
		return getEntityManager().queryForListByCriteria(Long.class, sdc);
    }

    @Override
    public Task queryMyTaskByTaskTypeCorrelatedId(TaskType taskType, Long correlatedId, SessionUser me) throws ServiceException {
		Assert.notNull(taskType);
		Assert.notNull(correlatedId);
		Assert.notNull(me);
		Set<Long> roleIds = me.getRoleIds();
		if (CollectionUtils.isEmpty(roleIds)) {
		    return null;
		}
		
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Task.class);
		sdc.add(SafeRestrictions.equal("taskType", taskType));
		sdc.add(SafeRestrictions.equal("correlatedId", correlatedId));
		sdc.add(SafeRestrictions.equal("companyCode", me.getCompanyCode()));
		if (roleIds.size() == 1) {
		    sdc.add(SafeRestrictions.equal("handleRoleId", roleIds.iterator().next()));
		} else {
		    sdc.add(SafeRestrictions.in("handleRoleId", roleIds));
		}
		sdc.add(SafeRestrictions.or(SafeRestrictions.isNull("handleUserId"), SafeRestrictions.equal("handleUserId", me.getUserId())));
		return getEntityManager().queryForUniqueObjectByCriteria(Task.class, sdc);
    }

    @Override
    public void doChangeMyTaskToOther(TaskType taskType, Long correlatedId, Long toUserId, Long toRoleId, SessionUser me) throws ServiceException {
		Assert.notNull(taskType);
		Assert.notNull(correlatedId);
		Assert.notNull(toRoleId);
		Assert.notNull(me);
		
		Task taskDb = queryMyTaskByTaskTypeCorrelatedId(taskType, correlatedId, me);
		Assert.notNull(taskDb, String.format("Can not find task by (taskType=%s, correlatedId=%s) of user (userId=%s, roleIds=%s)", 
			taskType, correlatedId, me.getUserId(), me.getRoleIds()));
		
		taskDb.setHandleRoleId(toRoleId);
		taskDb.setHandleUserId(toUserId);
		fillAuditableEntityOnUpdate(taskDb, me);
		getEntityManager().update(taskDb);
    }

    @Override
    public Long doCreateTask(Task task, Company belongToCompany) throws ServiceException {
		Assert.notNull(task);
		Assert.notNull(belongToCompany);
		
		Task taskDb = new Task();
		taskDb.setCorrelatedId(task.getCorrelatedId());
		taskDb.setTaskType(task.getTaskType());
		taskDb.setHandleRoleId(task.getHandleRoleId());
		taskDb.setHandleUserId(task.getHandleUserId());
		taskDb.setNameCn(task.getNameCn());
		taskDb.setNameEn(task.getNameEn());
		taskDb.setCompanyCode(belongToCompany.getCompanyCode());
		taskDb.setCreateDateTime(getEntityManager().getDbCurrentTimestamp());
		taskDb.setModifyDateTime(taskDb.getCreateDateTime());
		return (Long) getEntityManager().save(taskDb);
    }

    @Override
    public Long doCreateTask(Task task, SessionUser operator) throws ServiceException {
		Assert.notNull(task);
		
		Task taskDb = new Task();
		taskDb.setCorrelatedId(task.getCorrelatedId());
		taskDb.setTaskType(task.getTaskType());
		taskDb.setHandleRoleId(task.getHandleRoleId());
		taskDb.setHandleUserId(task.getHandleUserId());
		taskDb.setNameCn(task.getNameCn());
		taskDb.setNameEn(task.getNameEn());
		taskDb.setCompanyCode(operator.getCompanyCode());
		fillAuditableEntityOnCreate(taskDb, operator);
		return (Long) getEntityManager().save(taskDb);
    }

    @Override
    public void doDeleteTasks(TaskType taskType, Long correlatedId, SessionUser operator) throws ServiceException {
		if (taskType == null || correlatedId == null || operator == null) {
		    return;
		}
		getEntityManager().executeHql("delete from Task t where t.taskType=? and t.correlatedId=? and t.companyCode=?", new Object[] {taskType, correlatedId, operator.getCompanyCode()});
    }

//    private void doDeleteTask(Long taskId) {
//	if (taskId == null) {
//	    return;
//	}
//	getEntityManager().executeHql("delete from Task t where t.recId=?", new Object[] {taskId});
//    }
    
//    @Override
//    public Long doSubmitTask(TaskType taskType, Long correlatedId, SessionUser operator, Map<String, Object> variables) throws ServiceException {
//	Assert.notNull(taskType);
//	Assert.notNull(correlatedId);
//	
//	//doDeleteTask(taskType, correlatedId);
//	TaskNode taskNode = this.processDefinitionProvider.queryFirstTaskNode(taskType, operator.getCompanyCode(), variables);
//	
//	Task task = new Task();
//	task.setCorrelatedId(correlatedId);
//	task.setTaskNodeId(NumberUtils.toLong(taskNode.getId()));
//	task.setTaskType(taskType);
//	task.setHandleRoleId(NumberUtils.toLong(taskNode.getAssignment().getRoleId()));
//	task.setHandleUserId(NumberUtils.toLong(taskNode.getAssignment().getUserId()));
//	task.setNameCn(taskNode.getNameCn());
//	task.setNameEn(taskNode.getNameEn());
//	if (operator != null) {
//	    task.setModifier(operator.getUserId());
//	    task.setCreator(operator.getUserId());
//	}
//	task.setCreateDateTime(getEntityManager().getDbCurrentTimestamp());
//	task.setModifyDateTime(task.getCreateDateTime());
//	return (Long) getEntityManager().save(task);
//    }
//
//    @Override
//    public boolean doActionTask(String action, TaskType taskType, Long correlatedId, SessionUser operator, Map<String, Object> variables) throws ServiceException {
//	Assert.notNull(action);
//	Assert.notNull(taskType);
//	Assert.notNull(correlatedId);
//	Assert.notNull(operator);
//	
//	Task task = queryTask(taskType, correlatedId, operator);
//	if (task == null) {
//	    throw new ServiceException(String.format("Can not find task by taskType=%s and correlatedId=%s or this task is finished", taskType, correlatedId));
//	}
//	
//	TaskNode taskNode = this.processDefinitionProvider.queryTaskNode(task.getTaskNodeId().toString(), taskType, operator.getCompanyCode(), variables);
//	Transition transition = taskNode.retrieveTransition(action);
//	if (transition == null) {
//	    return false;
//	}
//	if (transition.isToEnd()) {
//	    doDeleteTasks(taskType, correlatedId);
//	    return true;
//	}
//
////	List<String> toList = transition.getToList();
////	for (String to : toList) {
////	    TaskNode nextTaskNode = this.processDefinitionProvider.queryTaskNode(to, taskType, operator.getCompanyCode(), variables);
////	}
////	task.setTaskNodeId(NumberUtils.toLong(nextTaskNode.getId()));
////	task.setHandleRoleId(NumberUtils.toLong(nextTaskNode.getAssignment().getRoleId()));
////	task.setHandleUserId(NumberUtils.toLong(nextTaskNode.getAssignment().getUserId()));
////	task.setNameCn(nextTaskNode.getNameCn());
////	task.setNameEn(nextTaskNode.getNameEn());
////	task.setModifier(operator.getUserId());
////	task.setModifyDateTime(getEntityManager().getDbCurrentTimestamp());
////	getEntityManager().update(task);
//	return false;
//    }
}
