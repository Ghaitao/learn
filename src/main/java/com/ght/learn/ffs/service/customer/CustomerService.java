package com.ght.learn.ffs.service.customer;

import java.util.List;

import com.ght.learn.ffs.dto.customer.CustomerDto;
import com.ght.learn.ffs.dto.customer.CustomerQueryDto;
import com.ght.learn.ffs.entity.customer.Customer;
import com.ght.learn.ffs.entity.customer.CustomerContactor;
import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.enums.customer.CustomerType;
import com.ght.learn.ffs.service.FfsService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.pagination.OrderablePagination;
import framework.service.ServiceException;

public interface CustomerService extends FfsService{

	    Customer queryCustomerById(Long customerId);

	    Customer queryCustomerByCustomerCode(String companyCode, String customerCode);

	    /**
	     * 客户修改自己的信息
	     */
	    void doModifyCustomerByCustomer(CustomerDto customerDto, SessionUser operator);
	    
	    /**
	     * 创建客户
	     */
	    Long doCreateCustomerByCompany(CustomerDto customerDto, SessionUser operator) throws ServiceException;
	    void doModifyCustomerByCompany(CustomerDto customerDto, SessionUser operator) throws ServiceException;
	    
	    /**
	     * 客户提交申请
	     */
	    Long doApplyCustomer(CustomerDto customerDto, Company belongToCompany) throws ServiceException;

	    /**
	     * 公司人员查询客户的申请（客服和财务角色都使用这个方法）
	     */
	    List<Customer> queryCustomerApplys(CustomerQueryDto customerQueryDto, SessionUser operator);
	    
	    /**
	     * 公司人员查询客户
	     */
	    List<Customer> queryCustomersForCompany(CustomerQueryDto customerQueryDto, OrderablePagination op, SessionUser operator);

	    /**
	     * 查询客户的申请的联系人
	     */
	    List<CustomerContactor> queryCustomerContactorsByCustomerId(Long customerId, SessionUser operator);

	    /**
	     * 客服通过申请
	     */
	    void doPassCustomerApplyByCustomerService(Long customerId, String remark, SessionUser operator) throws ServiceException;

	    /**
	     * 财务通过申请
	     */
	    void doPassCustomerApplyByAccountant(Long customerId, String remark, SessionUser operator) throws ServiceException;

	    /**
	    * 客服修改客户信息
	    */
	    void doModifyCustomerApplyByCustomerService(Customer inputCustomer, SessionUser operator);
	    /**
	     * 财务修改客户信息
	     */
	    void doModifyCustomerApplyByAccountant(Customer inputCustomer, SessionUser operator);
	    
	    /**
	     * 删除客户
	     */
	    void doDeleteCustomer(Long customerId, SessionUser operator);
	    
	    /**
	     * 临时调整剩余信用额度
	     */
	    void doAdjustCustomerRemainingCreditLimit(Customer inputCustomer, SessionUser operator);
	    
	    /**
	     * 查询客户类型CustomerType为航空公司Airline类别的客户
	     */
	    List<Customer> queryCustomersOfAirline(SessionUser operator);
	    List<Customer> queryCustomersByCustomerTypes(CustomerType[] customerTypes, SessionUser operator);
}
