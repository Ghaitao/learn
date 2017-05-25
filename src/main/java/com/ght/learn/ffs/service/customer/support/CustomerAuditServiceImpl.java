package com.ght.learn.ffs.service.customer.support;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ght.learn.ffs.common.EnumHelper;
import com.ght.learn.ffs.entity.customer.Customer;
import com.ght.learn.ffs.entity.customer.CustomerAuditLog;
import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.sys.Role;
import com.ght.learn.ffs.entity.task.Notification;
import com.ght.learn.ffs.entity.task.Task;
import com.ght.learn.ffs.enums.LockStatus;
import com.ght.learn.ffs.enums.customer.CustomerAuditAction;
import com.ght.learn.ffs.enums.customer.CustomerAuditStatus;
import com.ght.learn.ffs.enums.task.NotificationType;
import com.ght.learn.ffs.enums.task.TaskType;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.customer.CustomerAuditService;
import com.ght.learn.ffs.service.task.NotificationService;
import com.ght.learn.ffs.service.task.TaskService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;
import framework.service.ServiceException;

/**
 * 客户申请审核流程
 * @author MyYate
 */
@Service("CustomerAuditService")
public class CustomerAuditServiceImpl extends FfsServiceImpl implements CustomerAuditService {

    @Resource(name = "TaskService", type = TaskService.class)
    private TaskService taskService;
    
    @Resource(name = "NotificationService", type = NotificationService.class)
    private NotificationService notificationService;
    
    private Customer queryCustomerById(Long customerId, String companyCode) {
		Assert.notNull(customerId);
		Assert.hasText(companyCode);
		
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Customer.class);
		sdc.add(SafeRestrictions.equal("recId", customerId));
		sdc.add(SafeRestrictions.equal("companyCode", companyCode));
		Customer customer = getEntityManager().queryForUniqueObjectByCriteria(Customer.class, sdc);
		if (customer == null) {
		    throw new IllegalArgumentException(String.format("Can not find customer by customerId=%s", customerId));
		}
		return customer;
    }
    
    @Override
    public List<CustomerAuditLog> queryCustomerAuditLogsByCustomerId(Long customerId, SessionUser sessionUser) {
		if (customerId == null || sessionUser == null) {
		    return Collections.emptyList();
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(CustomerAuditLog.class);
		sdc.add(SafeRestrictions.equal("customerId", customerId));
		//sdc.add(SafeRestrictions.equal("companyCode", sessionUser.getCompanyCode()));
		sdc.addOrder(Order.desc("createDateTime"));
		return getEntityManager().queryForListByCriteria(CustomerAuditLog.class, sdc);
    }

    @Override
    public void doOnDeleteCustomerApplyByCustomerService(Long customerId, SessionUser operator) throws ServiceException {
		Customer customer = queryCustomerById(customerId, operator.getCompanyCode());
		//删除生成任务
		this.taskService.doDeleteTasks(TaskType.CustomerApply, customer.getRecId(), operator);
		//删除客户审计记录
		getEntityManager().executeHql("delete from CustomerAuditLog ca where ca.customerId=?", new Object[] {customer.getRecId()});
		//删除通知
		getEntityManager().executeHql("delete from Notification n where n.notificationType=? and n.receiverUserId=? and n.correlatedId=?", 
			new Object[] {NotificationType.CustomerApply, customer.getAcceptCustomerServiceId(), customer.getRecId()});
    }

    @Override
    public void doOnSubmitCustomerApply(Long customerId, Company belongToCompany) throws ServiceException {
		Customer customer = queryCustomerById(customerId, belongToCompany.getCompanyCode());
		
		Task taskDb = new Task();
		taskDb.setCorrelatedId(customer.getRecId());
		taskDb.setTaskType(TaskType.CustomerApply);
		taskDb.setHandleRoleId(Role.CUSTOMER_SERVICE);
		taskDb.setHandleUserId(customer.getAcceptCustomerServiceId());
		taskDb.setNameCn(EnumHelper.getEnumResourceMessage(TaskType.CustomerApply, getMessageSource(), Locale.CHINESE));
		taskDb.setNameEn(EnumHelper.getEnumResourceMessage(TaskType.CustomerApply, getMessageSource(), Locale.ENGLISH));
		this.taskService.doCreateTask(taskDb, belongToCompany);
		
		CustomerAuditLog customerAuditLog = new CustomerAuditLog();
		customerAuditLog.setAuditAction(CustomerAuditAction.Submit);
		customerAuditLog.setCompanyCode(customer.getCompanyCode());
		customerAuditLog.setCreateDateTime(getEntityManager().getDbCurrentTimestamp());
		customerAuditLog.setCreator(null);
		customerAuditLog.setCustomerId(customer.getRecId());
		customerAuditLog.setRemark(null);
		getEntityManager().save(customerAuditLog);
		
		getScheduledExecutorService().submit(new Runnable() {
		    @Override
		    public void run() {
			Notification inputNotification = new Notification();
				inputNotification.setAction(CustomerAuditAction.Submit.name());
				inputNotification.setReceiverUserId(customer.getAcceptCustomerServiceId());
				inputNotification.setNotificationType(NotificationType.CustomerApply);
				inputNotification.setCorrelatedId(customer.getRecId());
				inputNotification.setCorrelatedCode(customer.getCustomerCode());
				inputNotification.setNameCn(customer.getNameCn());
				inputNotification.setNameEn(customer.getNameEn());
				inputNotification.setCreator(null);
				notificationService.doSubmitNotificationToUser(inputNotification);
		    }
		});
    }

    @Override
    public void doOnPassCustomerApplyByCustomerService(Long customerId, String remark, SessionUser operator) throws ServiceException {
		Customer customer = queryCustomerById(customerId, operator.getCompanyCode());
		
		getEntityManager().executeHql("update Customer c set c.auditStatus=? where c.recId=?", new Object[] {CustomerAuditStatus.Processing, customer.getRecId()});
		
		this.taskService.doChangeMyTaskToOther(TaskType.CustomerApply, customer.getRecId(), null, Role.ACCOUNTANT, operator);
		
		CustomerAuditLog customerAuditLog = new CustomerAuditLog();
		customerAuditLog.setAuditAction(CustomerAuditAction.Pass);
		customerAuditLog.setCompanyCode(customer.getCompanyCode());
		customerAuditLog.setCreateDateTime(getEntityManager().getDbCurrentTimestamp());
		customerAuditLog.setCreator(operator.getUserId());
		customerAuditLog.setCustomerId(customer.getRecId());
		customerAuditLog.setRemark(remark);
		getEntityManager().save(customerAuditLog);
		
		getScheduledExecutorService().submit(new Runnable() {
		    @Override
		    public void run() {
			Notification inputNotification = new Notification();
			inputNotification.setAction(CustomerAuditAction.Pass.name());
			inputNotification.setNotificationType(NotificationType.CustomerApply);
			inputNotification.setCorrelatedId(customer.getRecId());
			inputNotification.setCorrelatedCode(customer.getCustomerCode());
			inputNotification.setNameCn(customer.getNameCn());
			inputNotification.setNameEn(customer.getNameEn());
			inputNotification.setCreator(operator.getUserId());
			notificationService.doSubmitNotificationToRole(inputNotification, Role.ACCOUNTANT, operator.getCompanyCode());
		    }
		});
    }

    @Override
    public void doOnPassCustomerApplyByAccountant(Long customerId, String remark, SessionUser operator) throws ServiceException {
	
		Customer customer = queryCustomerById(customerId, operator.getCompanyCode());
		
		getEntityManager().executeHql("update Customer c set c.lockStatus=?, c.auditStatus=? where c.recId=?", new Object[] {LockStatus.Normal, CustomerAuditStatus.Finished, customer.getRecId()});
		
		this.taskService.doDeleteTasks(TaskType.CustomerApply, customer.getRecId(), operator);
		
		CustomerAuditLog customerAuditLog = new CustomerAuditLog();
		customerAuditLog.setAuditAction(CustomerAuditAction.Pass);
		customerAuditLog.setCompanyCode(customer.getCompanyCode());
		customerAuditLog.setCreateDateTime(getEntityManager().getDbCurrentTimestamp());
		customerAuditLog.setCreator(operator.getUserId());
		customerAuditLog.setCustomerId(customer.getRecId());
		customerAuditLog.setRemark(remark);
		getEntityManager().save(customerAuditLog);
		
		getScheduledExecutorService().submit(new Runnable() {
		    @Override
		    public void run() {
				Notification inputNotification = new Notification();
				inputNotification.setAction(CustomerAuditAction.Pass.name());
				inputNotification.setReceiverUserId(customer.getAcceptCustomerServiceId());
				inputNotification.setNotificationType(NotificationType.CustomerApply);
				inputNotification.setCorrelatedId(customer.getRecId());
				inputNotification.setCorrelatedCode(customer.getCustomerCode());
				inputNotification.setNameCn(customer.getNameCn());
				inputNotification.setNameEn(customer.getNameEn());
				inputNotification.setCreator(operator.getUserId());
				notificationService.doSubmitNotificationToUser(inputNotification);
		    }
		});
    }

    @Override
    public void doOnRejectCustomerApplyByAccountant(Long customerId, String remark, SessionUser operator) throws ServiceException {
		Customer customer = queryCustomerById(customerId, operator.getCompanyCode());
		
		this.taskService.doChangeMyTaskToOther(TaskType.CustomerApply, customer.getRecId(), customer.getAcceptCustomerServiceId(), Role.CUSTOMER_SERVICE, operator);
		
		CustomerAuditLog customerAuditLog = new CustomerAuditLog();
		customerAuditLog.setAuditAction(CustomerAuditAction.Reject);
		customerAuditLog.setCompanyCode(customer.getCompanyCode());
		customerAuditLog.setCreateDateTime(getEntityManager().getDbCurrentTimestamp());
		customerAuditLog.setCreator(operator.getUserId());
		customerAuditLog.setCustomerId(customer.getRecId());
		customerAuditLog.setRemark(remark);
		getEntityManager().save(customerAuditLog);
		
		getScheduledExecutorService().submit(new Runnable() {
		    @Override
		    public void run() {
				Notification inputNotification = new Notification();
				inputNotification.setAction(CustomerAuditAction.Reject.name());
				inputNotification.setReceiverUserId(customer.getAcceptCustomerServiceId());
				inputNotification.setNotificationType(NotificationType.CustomerApply);
				inputNotification.setCorrelatedId(customer.getRecId());
				inputNotification.setCorrelatedCode(customer.getCustomerCode());
				inputNotification.setNameCn(customer.getNameCn());
				inputNotification.setNameEn(customer.getNameEn());
				inputNotification.setCreator(operator.getUserId());
				notificationService.doSubmitNotificationToUser(inputNotification);
		    }
		});
    }


//    @Override
//    public void doOnSubmitCustomerApply(Long customerId) throws ServiceException {
//	Customer customer = getEntityManager().get(Customer.class, customerId);
//	if (customer == null) {
//	    throw new IllegalArgumentException(String.format("Can not find customer by customerId=%s", customerId));
//	}
//	
//	this.taskService.doSubmitTask(TaskType.CustomerApply, customer.getRecId(), null, CollectionUtils.asMap(new String[] {"customer"}, new Object[] {customer}));
//	
//	CustomerAuditLog customerAuditLog = new CustomerAuditLog();
//	customerAuditLog.setAuditAction(CustomerAuditAction.Submit);
//	customerAuditLog.setCompanyCode(customer.getCompanyCode());
//	customerAuditLog.setCreateDateTime(getEntityManager().getDbCurrentTimestamp());
//	customerAuditLog.setCreator(null);
//	customerAuditLog.setCustomerId(customer.getRecId());
//	customerAuditLog.setRemark(null);
//	getEntityManager().save(customerAuditLog);
//    }
//
//    @Override
//    public void doOnPassCustomerApply(Long customerId, SessionUser operator) throws ServiceException {
//	doOnActionCustomerApply(CustomerAuditAction.Pass, customerId, null, operator);
//    }
//
//    @Override
//    public void doOnRejectCustomerApply(Long customerId, String remark, SessionUser operator) throws ServiceException {
//	doOnActionCustomerApply(CustomerAuditAction.Reject, customerId, remark, operator);
//    }
//    
//    private void doOnActionCustomerApply(CustomerAuditAction action, Long customerId, String remark, SessionUser operator) throws ServiceException {
//	Customer customer = getEntityManager().get(Customer.class, customerId);
//	if (customer == null) {
//	    throw new IllegalArgumentException(String.format("Can not find customer by customerId=%s", customerId));
//	}
//	
//	boolean finished = this.taskService.doActionTask(action.name(), TaskType.CustomerApply, customer.getRecId(), operator, CollectionUtils.asMap(new String[] {"customer"}, new Object[] {customer}));
//	if (finished) {
//	    getEntityManager().executeHql("update Customer c set c.status=? where c.recId", new Object[] {LockStatus.Normal, customerId});
//	}
//	
//	CustomerAuditLog customerAuditLog = new CustomerAuditLog();
//	customerAuditLog.setAuditAction(action);
//	customerAuditLog.setCompanyCode(customer.getCompanyCode());
//	customerAuditLog.setCreateDateTime(getEntityManager().getDbCurrentTimestamp());
//	customerAuditLog.setCreator(operator.getUserId());
//	customerAuditLog.setCustomerId(customer.getRecId());
//	customerAuditLog.setRemark(remark);
//	getEntityManager().save(customerAuditLog);
//    }
}