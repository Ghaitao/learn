package com.ght.learn.ffs.service.customer;

import java.util.List;

import com.ght.learn.ffs.entity.customer.CustomerAuditLog;
import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.service.FfsService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.service.ServiceException;

public interface CustomerAuditService extends FfsService {
    
    List<CustomerAuditLog> queryCustomerAuditLogsByCustomerId(Long customerId, SessionUser sessionUser);
    
    /**
     * 提交客户申请
     */
    void doOnSubmitCustomerApply(Long customerId, Company belongToCompany) throws ServiceException;

    /**
     * 通过审核
     */
    void doOnPassCustomerApplyByCustomerService(Long customerId, String remark, SessionUser operator) throws ServiceException;
    void doOnDeleteCustomerApplyByCustomerService(Long customerId, SessionUser operator) throws ServiceException;
    void doOnPassCustomerApplyByAccountant(Long customerId, String remark, SessionUser operator) throws ServiceException;
    
    /**
     * 拒绝审核
     */
    void doOnRejectCustomerApplyByAccountant(Long customerId, String remark, SessionUser operator) throws ServiceException;
    
//    /**
//     * 通过审核
//     */
//    void doOnPassCustomerApply(Long customerId, SessionUser operator) throws ServiceException;
//    
//    /**
//     * 拒绝审核
//     */
//    void doOnRejectCustomerApply(Long customerId, String remark, SessionUser operator) throws ServiceException;
}