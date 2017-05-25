package com.ght.learn.ffs.service.customer;

import java.util.List;

import com.ght.learn.ffs.dto.customer.CustomerAirlineStockDto;
import com.ght.learn.ffs.entity.customer.CustomerAirlineStockDetail;
import com.ght.learn.ffs.entity.customer.CustomerAirlineStockSegment;
import com.ght.learn.ffs.service.FfsService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.pagination.OrderablePagination;
import framework.service.ServiceException;

public interface CustomerAirlineStockService extends FfsService {

    List<CustomerAirlineStockSegment> queryCustomerAirlineStockSegments(CustomerAirlineStockDto customerAirlineStockDto, OrderablePagination op, SessionUser operator);
    void doDeleteCustomerAirlineStockSegment(Long airlineStockSegmentId, SessionUser operator);
    
    List<CustomerAirlineStockDetail> queryCustomerAirlineStockDetails(CustomerAirlineStockDto customerAirlineStockDto, OrderablePagination op, SessionUser operator);
    
    /**
     * 创建号段并生成明细
     */
    Long doCreateCustomerAirlineStockSegment(CustomerAirlineStockSegment inputCustomerAirlineStockSegment, SessionUser operator) throws ServiceException;
    
    /**
     * 获取下一个可用的主单号
     */
    CustomerAirlineStockDetail doGetNextAwbNo(String customerCode, SessionUser operator) throws ServiceException;
    void doUsedAwbNo(String customerCode, String awbFullNo, SessionUser operator) throws ServiceException;
    
    void doSetAsAvailableCustomerAirlineStockDetail(Long customerAirlineStockDetailRecId, SessionUser operator) throws ServiceException;
    void doSetAsUnavailableCustomerAirlineStockDetail(Long customerAirlineStockDetailRecId, SessionUser operator) throws ServiceException;
}