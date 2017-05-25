package com.ght.learn.ffs.service.customer.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ght.learn.ffs.dto.customer.CustomerDto;
import com.ght.learn.ffs.dto.customer.CustomerQueryDto;
import com.ght.learn.ffs.entity.customer.Customer;
import com.ght.learn.ffs.entity.customer.CustomerContactor;
import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.sys.Role;
import com.ght.learn.ffs.entity.sys.User;
import com.ght.learn.ffs.entity.sys.UserRole;
import com.ght.learn.ffs.enums.LockStatus;
import com.ght.learn.ffs.enums.customer.CustomerAuditStatus;
import com.ght.learn.ffs.enums.customer.CustomerSource;
import com.ght.learn.ffs.enums.customer.CustomerType;
import com.ght.learn.ffs.enums.task.TaskType;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.base.UserQueryService;
import com.ght.learn.ffs.service.customer.CustomerAuditService;
import com.ght.learn.ffs.service.customer.CustomerService;
import com.ght.learn.ffs.service.task.TaskService;
import com.ght.learn.ffs.tool.context.SessionUser;
import com.ght.learn.ffs.tool.file.MultipartFileOperation;
import com.ght.learn.ffs.tool.file.UploadFileItem;
import com.ght.learn.ffs.tool.image.ImageUtil;

import framework.core.pagination.OrderablePagination;
import framework.core.utils.CollectionUtils;
import framework.core.utils.ObjectUtils;
import framework.core.utils.StringUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;
import framework.service.ServiceException;

@Service("CustomerService")
public class CustomerServiceImpl extends FfsServiceImpl implements CustomerService{

	 @Resource(name = "localDiskMultipartFileOperation", type = MultipartFileOperation.class)
	    private MultipartFileOperation localDiskMultipartFileOperation;

	    /*@Resource(name = "StandardPasswordEncoder", type = PasswordEncoder.class)
	    private PasswordEncoder passwordEncoder;*/

	    @Resource(name = "CustomerAuditService", type = CustomerAuditService.class)
	    private CustomerAuditService customerAuditService;

	    @Resource(name = "UserQueryService", type = UserQueryService.class)
	    private UserQueryService userQueryService;

	    @Resource(name = "TaskService", type = TaskService.class)
	    private TaskService taskService;
	    @Resource(name = "CustomerService", type = CustomerService.class)
	    private CustomerService customerService;

	    // @Cacheable(value = "data", key =
	    // "'com.jinzhiyi.ffs.entity.customer.Customer@customerId='+#customerId")
	    @Override
	    public Customer queryCustomerById(Long customerId) {
			if (customerId == null) {
			    return null;
			}
			return getEntityManager().get(Customer.class, customerId);
	    }

	    @Override
	    public Customer queryCustomerByCustomerCode(String companyCode, String customerCode) {
			if (StringUtils.isEmpty(customerCode) || StringUtils.isEmpty(companyCode)) {
			    return null;
			}
			SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Customer.class);
			sdc.add(SafeRestrictions.equal("customerCode", StringUtils.safeToUpperCase(StringUtils.safeToTrim(customerCode))));
			sdc.add(SafeRestrictions.equal("companyCode", companyCode));
			return getEntityManager().queryForUniqueObjectByCriteria(Customer.class, sdc);
		}

	    @Override
	    public void doModifyCustomerByCustomer(CustomerDto customerDto, SessionUser operator) {
			if (customerDto == null || customerDto.getCustomer() == null) {
			    return;
			}
			if (!operator.isCustomer()) {
			    return;
			}
			Customer dbCustomer = queryCustomerById(customerDto.getCustomer().getRecId());
			if (dbCustomer == null) {
			    return;
			}
			if (!StringUtils.safeEqualsIgnoreCase(dbCustomer.getCompanyCode(), operator.getCompanyCode()) 
				|| !StringUtils.safeEqualsIgnoreCase(dbCustomer.getCustomerCode(), operator.getCustomerCode())) {
			    return;
			}
			
			dbCustomer = fillCustomerFromCustomerDto(dbCustomer, customerDto);
			fillAuditableEntityOnUpdate(dbCustomer, operator);
			getEntityManager().update(dbCustomer);
			
			//保存三证合一
			if (customerDto.getLicenseImage() != null && ImageUtil.isImage(customerDto.getLicenseImage().getContentType())) {
			    if (StringUtils.hasText(dbCustomer.getLicenseFileMetaId())) {
				this.localDiskMultipartFileOperation.delete(dbCustomer.getLicenseFileMetaId(), operator);
			    }
			    dbCustomer.setLicenseFileMetaId(this.localDiskMultipartFileOperation.persist(new UploadFileItem(customerDto.getLicenseImage()), operator));
			}
			
			//先删除客户联系人
			getEntityManager().executeHql("delete from CustomerContactor cc where cc.customerId=?", new Object[] { dbCustomer.getRecId() });
			// 保存客户联系人
			List<CustomerContactor> customerContactorsDb = createCustomerContactorsFromCustomerDto(dbCustomer, customerDto);
			if (customerContactorsDb.size() > 0) {
			    getEntityManager().save(customerContactorsDb);
			}
	    }
	    
	    @Override
	    public Long doCreateCustomerByCompany(CustomerDto customerDto, SessionUser operator) throws ServiceException {
			Assert.notNull(customerDto);
			Assert.notNull(customerDto.getCustomer());
			
			customerDto.getCustomer().setCustomerCode(StringUtils.safeToUpperCase(StringUtils.safeToTrim(customerDto.getCustomer().getCustomerCode())));
			Customer dbCustomer = queryCustomerByCustomerCode(customerDto.getCustomer().getCustomerCode(), operator.getCompanyCode());
			// 验证客户代码是否存在
			if (dbCustomer != null) {
			    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.customer.apply.customerCodeExists",
				    new Object[] { customerDto.getCustomer().getCustomerCode() }, LocaleContextHolder.getLocale()));
			}
			
			dbCustomer = fillCustomerFromCustomerDto(null, customerDto);
			dbCustomer.setCustomerCode(customerDto.getCustomer().getCustomerCode());
			dbCustomer.setCompanyCode(operator.getCompanyCode());
			dbCustomer.setCustomerSource(CustomerSource.Manual);
			dbCustomer.setLockStatus(LockStatus.Normal);
			dbCustomer.setAuditStatus(CustomerAuditStatus.Finished);
			dbCustomer.setRemainingCreditLimit(BigDecimal.ZERO);
			dbCustomer.setCreditLimit(BigDecimal.ZERO);
	
			fillAuditableEntityOnCreate(dbCustomer, operator);
			getEntityManager().save(dbCustomer);
	
			// 保存客户联系人
			List<CustomerContactor> customerContactorsDb = createCustomerContactorsFromCustomerDto(dbCustomer, customerDto);
			if (customerContactorsDb.size() > 0) {
			    getEntityManager().save(customerContactorsDb);
			}
			
			return dbCustomer.getRecId();
	    }

	    @Override
	    public void doModifyCustomerByCompany(CustomerDto customerDto, SessionUser operator) throws ServiceException {
			if (customerDto == null || customerDto.getCustomer() == null) {
			    return;
			}
			Customer dbCustomer = queryCustomerById(customerDto.getCustomer().getRecId());
			if (dbCustomer == null) {
			    return;
			}
			if (!StringUtils.safeEqualsIgnoreCase(dbCustomer.getCompanyCode(), operator.getCompanyCode())) {
			    return;
			}
			if (!dbCustomer.isCustomerSourceManual()) {
			    return;
			}
			dbCustomer = fillCustomerFromCustomerDto(dbCustomer, customerDto);
			fillAuditableEntityOnUpdate(dbCustomer, operator);
			getEntityManager().update(dbCustomer);
			
			//先删除客户联系人
			getEntityManager().executeHql("delete from CustomerContactor cc where cc.customerId=?", new Object[] { dbCustomer.getRecId() });
			// 保存客户联系人
			List<CustomerContactor> customerContactorsDb = createCustomerContactorsFromCustomerDto(dbCustomer, customerDto);
			if (customerContactorsDb.size() > 0) {
			    getEntityManager().save(customerContactorsDb);
			}
	    }

	    @Override
	    public Long doApplyCustomer(CustomerDto customerDto, Company belongToCompany) throws ServiceException {
			Assert.notNull(customerDto);
			Assert.notNull(customerDto.getCustomer());
			Assert.hasText(customerDto.getCustomer().getCustomerCode());
			Assert.notNull(customerDto.getCustomer().getAcceptCustomerServiceId());
			Assert.notNull(customerDto.getAccount());
	
			// 保存客户信息
			customerDto.getCustomer().setCustomerCode(StringUtils.safeToUpperCase(StringUtils.safeToTrim(customerDto.getCustomer().getCustomerCode())));
			Customer dbCustomer = queryCustomerByCustomerCode(customerDto.getCustomer().getCustomerCode(), belongToCompany.getCompanyCode());
			// 验证客户代码是否存在
			if (dbCustomer != null) {
			    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.customer.apply.customerCodeExists",
				    new Object[] { customerDto.getCustomer().getCustomerCode() }, LocaleContextHolder.getLocale()));
			}
			String inputUserCode = StringUtils.safeToLowerCase(customerDto.getAccount().getUserCode());
			// 验证申请的用户代码是否存在
			User userDb = this.userQueryService.queryUserByUserCode(belongToCompany.getCompanyCode(), inputUserCode);
			if (userDb != null) {
			    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.customer.apply.userCodeExists", new Object[] { inputUserCode },
				    LocaleContextHolder.getLocale()));
			}
	
			dbCustomer = fillCustomerFromCustomerDto(null, customerDto);
			dbCustomer.setAcceptCustomerServiceId(customerDto.getCustomer().getAcceptCustomerServiceId());
			dbCustomer.setCompanyCode(belongToCompany.getCompanyCode());
			dbCustomer.setCustomerCode(customerDto.getCustomer().getCustomerCode());
			dbCustomer.setCustomerSource(CustomerSource.Apply);
			dbCustomer.setLockStatus(LockStatus.Locked);
			dbCustomer.setAuditStatus(CustomerAuditStatus.Submitted);
			dbCustomer.setRemainingCreditLimit(BigDecimal.ZERO);
			dbCustomer.setCreditLimit(BigDecimal.ZERO);
	
			if (customerDto.getLicenseImage() != null && ImageUtil.isImage(customerDto.getLicenseImage().getContentType())) {
			    SessionUser mockSessionUser = new SessionUser();
			    mockSessionUser.setCompanyCode(belongToCompany.getCompanyCode());
			    mockSessionUser.setCompanyId(belongToCompany.getRecId());
			    mockSessionUser.setCustomerCode(customerDto.getCustomer().getCustomerCode());
			    dbCustomer.setLicenseFileMetaId(this.localDiskMultipartFileOperation.persist(new UploadFileItem(customerDto.getLicenseImage()), mockSessionUser));
			}
	
			fillAuditableEntityOnCreate(dbCustomer, null);
			getEntityManager().save(dbCustomer);
	
			// 保存用户信息
			userDb = new User();
			userDb.setCompanyCode(belongToCompany.getCompanyCode());
			userDb.setCompanyId(belongToCompany.getRecId());
			userDb.setCustomerCode(dbCustomer.getCustomerCode());
			userDb.setCustomerId(dbCustomer.getRecId());
			userDb.setEmail(customerDto.getAccount().getEmail());
			userDb.setMobilePhone(customerDto.getAccount().getMobilePhone());
			userDb.setStatus(LockStatus.Normal);
			userDb.setTelephone(customerDto.getAccount().getTelephone());
			userDb.setUserNameCn(customerDto.getAccount().getUserNameCn());
			userDb.setUserNameEn(customerDto.getAccount().getUserNameEn());
			userDb.setUserCode(inputUserCode);
			userDb.setUserPassword(customerDto.getAccount().getUserPassword());
			fillAuditableEntityOnCreate(userDb, null);
			getEntityManager().save(userDb);
	
			// 给用户添加角色
			UserRole userRoleDb = new UserRole();
			userRoleDb.setCompanyCode(userDb.getCompanyCode());
			userRoleDb.setRoleId(Role.CUSTOMER);
			userRoleDb.setUserId(userDb.getRecId());
			getEntityManager().save(userRoleDb);
	
			// 保存客户联系人
			List<CustomerContactor> customerContactorsDb = createCustomerContactorsFromCustomerDto(dbCustomer, customerDto);
			if (customerContactorsDb.size() > 0) {
			    getEntityManager().save(customerContactorsDb);
			}
	
			// 触发申请任务，进去客户审核流程
			this.customerAuditService.doOnSubmitCustomerApply(dbCustomer.getRecId(), belongToCompany);
			return dbCustomer.getRecId();
	    }


	    private Customer fillCustomerFromCustomerDto(Customer dbCustomer, CustomerDto customerDto) {
			if (dbCustomer == null) {
			    dbCustomer = new Customer();
			}
			dbCustomer.setCustomerType(customerDto.getCustomer().getCustomerType());
			dbCustomer.setAddress(customerDto.getCustomer().getAddress());
			dbCustomer.setNameCn(customerDto.getCustomer().getNameCn());
			dbCustomer.setNameEn(customerDto.getCustomer().getNameEn());
			dbCustomer.setPostcode(customerDto.getCustomer().getPostcode());
			dbCustomer.setCity(customerDto.getCustomer().getCity());
			dbCustomer.setCountry(customerDto.getCustomer().getCountry());
			dbCustomer.setProvince(customerDto.getCustomer().getProvince());
			dbCustomer.setContactorEmail(customerDto.getCustomer().getContactorEmail());
			dbCustomer.setContactorFax(customerDto.getCustomer().getContactorFax());
			dbCustomer.setContactorName(customerDto.getCustomer().getContactorName());
			dbCustomer.setContactorPhone(customerDto.getCustomer().getContactorPhone());
			dbCustomer.setRemark(customerDto.getCustomer().getRemark());
			return dbCustomer;
	    }
	    
	    private List<CustomerContactor> createCustomerContactorsFromCustomerDto(Customer dbCustomer, CustomerDto customerDto) {
			// 保存客户联系人
			List<CustomerContactor> inputContactors = customerDto.getContactors();
			if (CollectionUtils.isEmpty(inputContactors)) {
			    return Collections.emptyList();
			}
			ArrayList<CustomerContactor> customerContactorsDb = new ArrayList<>(3);
			for (CustomerContactor inputContactor : inputContactors) {
			    // 筛选联系人
			    if (inputContactor != null && StringUtils.hasText(inputContactor.getName())) {
				CustomerContactor customerContactorDb = new CustomerContactor();
				customerContactorDb.setCompanyCode(dbCustomer.getCompanyCode());
				customerContactorDb.setCustomerCode(dbCustomer.getCustomerCode());
				customerContactorDb.setCustomerId(dbCustomer.getRecId());
				customerContactorDb.setFax(inputContactor.getFax());
				customerContactorDb.setMobilePhone(inputContactor.getMobilePhone());
				customerContactorDb.setName(inputContactor.getName());
				customerContactorDb.setPosition(inputContactor.getPosition());
				customerContactorDb.setEmail(inputContactor.getEmail());
				customerContactorDb.setTelephone(inputContactor.getTelephone());
				customerContactorsDb.add(customerContactorDb);
			    }
			}
			return customerContactorsDb;
	    }
	    
	    @Override
	    public List<Customer> queryCustomerApplys(CustomerQueryDto customerQueryDto, SessionUser operator) {
			List<Long> customerIds = this.taskService.queryMyCorrelatedIdsByTaskType(TaskType.CustomerApply, operator);
			if (CollectionUtils.isEmpty(customerIds)) {
			    return Collections.emptyList();
			}
	
			SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Customer.class);
			if (customerQueryDto != null) {
			    sdc.add(SafeRestrictions.likeAnyWhere("customerCode", StringUtils.safeToUpperCase(customerQueryDto.getCustomerCode())));
			    sdc.add(SafeRestrictions.or(SafeRestrictions.likeAnyWhere("nameCn", customerQueryDto.getName()), SafeRestrictions.likeAnyWhere("nameEn", customerQueryDto.getName())));
			}
			sdc.add(SafeRestrictions.equal("companyCode", operator.getCompanyCode()));
			sdc.add(SafeRestrictions.in("recId", customerIds));
			sdc.addOrder(Order.desc("createDateTime"));
			return this.getEntityManager().queryForListByCriteria(Customer.class, sdc);
	    }

	    @Override
	    public List<Customer> queryCustomersForCompany(CustomerQueryDto customerQueryDto, OrderablePagination op, SessionUser operator) {
			SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Customer.class);
			if (customerQueryDto != null) {
			    sdc.add(SafeRestrictions.likeAnyWhere("customerCode", StringUtils.safeToUpperCase(customerQueryDto.getCustomerCode())));
			    sdc.add(SafeRestrictions.or(SafeRestrictions.likeAnyWhere("nameCn", customerQueryDto.getName()), SafeRestrictions.likeAnyWhere("nameEn", customerQueryDto.getName())));
			    sdc.add(SafeRestrictions.equal("customerType", customerQueryDto.getCustomerType()));
			}
			sdc.add(SafeRestrictions.equal("companyCode", operator.getCompanyCode()));
			if (op != null && ObjectUtils.isNotEmpty(op.getSorters())) {
			    sdc.addSorters(op.getSorters());
			} else {
			    sdc.addOrder(Order.desc("createDateTime"));
			}
			return this.getEntityManager().queryForListByCriteria(Customer.class, sdc, op);
	    }

	    @Override
	    public List<CustomerContactor> queryCustomerContactorsByCustomerId(Long customerId, SessionUser operator) {
			if (customerId == null) {
			    return Collections.emptyList();
			}
			SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(CustomerContactor.class);
			sdc.add(SafeRestrictions.equal("companyCode", operator.getCompanyCode()));
			sdc.add(SafeRestrictions.equal("customerId", customerId));
			return getEntityManager().queryForListByCriteria(CustomerContactor.class, sdc);
	    }
	    
	    @Override
	    public void doPassCustomerApplyByCustomerService(Long customerId, String remark, SessionUser operator) throws ServiceException {
			if (customerId == null) {
			    return;
			}
			this.customerAuditService.doOnPassCustomerApplyByCustomerService(customerId, remark, operator);
	    }

	    @Override
	    public void doPassCustomerApplyByAccountant(Long customerId, String remark, SessionUser operator) throws ServiceException {
			if (customerId == null) {
			    return;
			}
			this.customerAuditService.doOnPassCustomerApplyByAccountant(customerId, remark, operator);
	    }
	    
	    @Override
	    public void doModifyCustomerApplyByCustomerService(Customer inputCustomer, SessionUser operator) {
			if (inputCustomer == null) {
			    return;
			}
			Customer customerDb = queryCustomerById(inputCustomer.getRecId());
			if (customerDb == null) {
			    return;
			}
	
			if (!StringUtils.safeEqualsIgnoreCase(customerDb.getCompanyCode(), operator.getCompanyCode())) {
			    return;
			}
			
			customerDb.setNameCn(inputCustomer.getNameCn());
			customerDb.setNameEn(inputCustomer.getNameEn());
			customerDb.setAddress(inputCustomer.getAddress());
			customerDb.setPostcode(inputCustomer.getPostcode());
			fillAuditableEntityOnUpdate(customerDb, operator);
			getEntityManager().update(customerDb);
	    }

	    @Override
	    public void doModifyCustomerApplyByAccountant(Customer inputCustomer, SessionUser operator) {
			if (inputCustomer == null || inputCustomer.getCreditLimit() == null) {
			    return;
			}
			Customer customerDb = queryCustomerById(inputCustomer.getRecId());
			if (customerDb == null) {
			    return;
			}
			
			if (!StringUtils.safeEqualsIgnoreCase(customerDb.getCompanyCode(), operator.getCompanyCode())) {
			    return;
			}
			
			customerDb.setAccountPeriod(inputCustomer.getAccountPeriod());
			
			BigDecimal dbCreditLimit = customerDb.getCreditLimit() == null ? BigDecimal.ZERO : customerDb.getCreditLimit();
			BigDecimal dbRemainingCreditLimit = customerDb.getRemainingCreditLimit() == null ? BigDecimal.ZERO : customerDb.getRemainingCreditLimit();
			
			customerDb.setRemainingCreditLimit(inputCustomer.getCreditLimit().subtract(dbCreditLimit).add(dbRemainingCreditLimit));
			customerDb.setCreditLimit(inputCustomer.getCreditLimit());
			fillAuditableEntityOnUpdate(customerDb, operator);
			getEntityManager().update(customerDb);
	    }

	    @Override
	    public void doDeleteCustomer(Long customerId, SessionUser operator) {
			if (ObjectUtils.isNull(customerId)) {
			    return;
			}
			
			Customer customer = queryCustomerById(customerId);
			if (ObjectUtils.isNull(customer)) {
			    return;
			}
			if (!StringUtils.safeEqualsIgnoreCase(customer.getCompanyCode(), operator.getCompanyCode())) {
			    return;
			}
			
			// 删除客户联系人
			getEntityManager().executeHql("delete from CustomerContactor cc where cc.customerId=?", new Object[] { customer.getRecId() });
			
			if (customer.isCustomerSourceApply()) {
			     List<Long> userIds = queryUserIdsOfThisCustomer(operator.getCompanyCode(), customer.getRecId());
			    if (CollectionUtils.isNotEmpty(userIds)) {
				// 删除用户角色
				getEntityManager().executeHql("delete from UserRole ur where ur.userId in {:userIds}", Collections.singletonMap("userIds", userIds));
			    }
			    //删除用户
			    getEntityManager().executeHql("delete from User u where u.customerId=? and u.companyCode=?", new Object[] {customer.getRecId(), operator.getCompanyCode()});
			    
			    this.customerAuditService.doOnDeleteCustomerApplyByCustomerService(customer.getRecId(), operator);
	
			    // 删除客户
			    getEntityManager().executeHql("delete from Customer c where c.companyCode=? and c.customerCode=? and c.lockStatus=?", new Object[] { operator.getCompanyCode(), customer.getCustomerCode(), LockStatus.Locked });
	
			    // 删除上传图片
			    if (StringUtils.hasText(customer.getLicenseFileMetaId())) {
				this.localDiskMultipartFileOperation.delete(customer.getLicenseFileMetaId(), operator);
			    }
			}
	    }
	    
	    private List<Long> queryUserIdsOfThisCustomer(String companyCode, Long customerId) {
			if (StringUtils.isEmpty(companyCode) || customerId == null) {
			    return Collections.emptyList();
			}
			SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(User.class);
			sdc.setProjection(Projections.property("recId"));
			sdc.add(SafeRestrictions.equal("companyCode", companyCode));
			sdc.add(SafeRestrictions.equal("customerId", customerId));
			return getEntityManager().queryForListByCriteria(Long.class, sdc);
	    }
	    
	    @Override
	    public void doAdjustCustomerRemainingCreditLimit(Customer inputCustomer, SessionUser operator) {
			if (ObjectUtils.isNull(inputCustomer) || ObjectUtils.isNull(inputCustomer.getRecId())) {
			    return;
			}
			if (inputCustomer.getTemporaryCreditLimit() == null) {
			    return;
			}
			Customer customerDb = queryCustomerById(inputCustomer.getRecId());
			if (ObjectUtils.isNull(customerDb)) {
			    return;
			}
	
			if (!StringUtils.safeEqualsIgnoreCase(customerDb.getCompanyCode(), operator.getCompanyCode())) {
			    return;
			}
			
			BigDecimal dbRemainingCreditLimit = customerDb.getRemainingCreditLimit() == null ? BigDecimal.ZERO : customerDb.getRemainingCreditLimit();
			customerDb.setRemainingCreditLimit(dbRemainingCreditLimit.add(inputCustomer.getTemporaryCreditLimit()));
			fillAuditableEntityOnUpdate(customerDb, operator);
			getEntityManager().update(customerDb);
	    }

	    @Override
	    public List<Customer> queryCustomersOfAirline(SessionUser operator) {
	    	return queryCustomersByCustomerTypes(new CustomerType[] {CustomerType.Airline}, operator);
	    }

	    @Override
	    public List<Customer> queryCustomersByCustomerTypes(CustomerType[] customerTypes, SessionUser operator) {
			if (ObjectUtils.isEmpty(customerTypes)) {
			    return Collections.emptyList();
			}
			SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Customer.class);
			sdc.add(SafeRestrictions.equal("companyCode", operator.getCompanyCode()));
			if (customerTypes.length == 1) {
			    sdc.add(SafeRestrictions.equal("customerType", customerTypes[0]));
			} else {
			    sdc.add(SafeRestrictions.in("customerType", customerTypes));
			}
			return getEntityManager().queryForListByCriteria(Customer.class, sdc);
	    }

}
