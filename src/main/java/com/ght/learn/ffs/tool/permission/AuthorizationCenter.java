package com.ght.learn.ffs.tool.permission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.ght.learn.ffs.entity.customer.Customer;
import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.sys.Menu;
import com.ght.learn.ffs.entity.sys.User;
import com.ght.learn.ffs.enums.LockStatus;
import com.ght.learn.ffs.service.base.CompanyQueryService;
import com.ght.learn.ffs.service.base.MenuQueryService;
import com.ght.learn.ffs.service.base.RoleQueryService;
import com.ght.learn.ffs.service.base.UserQueryService;
import com.ght.learn.ffs.service.customer.CustomerService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.Constants;
import framework.core.utils.StringUtils;
import framework.service.ServiceException;

@Component("AuthorizationCenter")
public class AuthorizationCenter implements MessageSourceAware{

	private MessageSource messageSource;
	
    @Resource(name = "CompanyQueryService", type = CompanyQueryService.class)
    private CompanyQueryService companyQueryService;
    
    @Resource(name = "CustomerService", type = CustomerService.class)
    private CustomerService customerService;
    
    @Resource(name = "RoleQueryService", type = RoleQueryService.class)
    private RoleQueryService roleQueryService;
    
    @Resource(name = "UserQueryService", type = UserQueryService.class)
    private UserQueryService userQueryService;
    
    @Resource(name = "MenuQueryService", type = MenuQueryService.class)
    private MenuQueryService menuQueryService;
    
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
		
	}
	/**
     * 查询给定userId能看到的菜单
     * @param companyCode
     * @param userId
     * @return 父菜单，子菜单通过Menu.children获取
     */
	public List<Menu> queryVisibleMenusByUserId(Long userId){
		if(userId==null){
			return Collections.emptyList();
		}
		Set<Long> roleIds=this.roleQueryService.queryRoleIdsByUserId(userId);
		if(CollectionUtils.isEmpty(roleIds)){
			return Collections.emptyList();
		}
		HashSet<Long> allVisibleMenuIds = new HashSet<Long>();
		for(Long roleId:roleIds){
			List<Long> visibleMenuIds=this.menuQueryService.queryVisibleMenuIdsByRoleId(roleId);
			if (CollectionUtils.isNotEmpty(visibleMenuIds)) {
				allVisibleMenuIds.addAll(visibleMenuIds);
			}
		}
		if (allVisibleMenuIds.isEmpty()) {
		    return Collections.emptyList();
		}
		ArrayList<Menu> parents = new ArrayList<Menu>(allVisibleMenuIds.size() / 2);
		ArrayList<Menu> children = new ArrayList<Menu>(5);
		for (Long allVisibleMenuId : allVisibleMenuIds) {
		    Menu visibleMenu = this.menuQueryService.queryMenuByMenuId(allVisibleMenuId);
		    if (visibleMenu.isParent()) {
			parents.add(visibleMenu);
		    } else {
			children.add(visibleMenu);
		    }
		}
		if (parents.isEmpty() || children.isEmpty()) {
		    return Collections.emptyList();
		}
		Collections.sort(parents);
		for (Menu parent : parents) {
		    for (Menu child : children) {
			if (child.isMyParent(parent.getRecId())) {
			    parent.addChild(child);
			}
		    }
		    Collections.sort(parent.getChildren());
		}
		return parents;
	}
	public SessionUser login(String companyCode,String userCode,String password) throws ServiceException{
		if (StringUtils.isEmpty(userCode) || StringUtils.isEmpty(password)) {
		    throw new ServiceException(this.messageSource.getMessage("com.jinzhiyi.ffs.message.user.empty_usercode_or_password", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		}
		Company company = this.companyQueryService.queryCheckedCompanyByCompanyCode(companyCode);
		userCode = StringUtils.safeToLowerCase(StringUtils.safeToTrim(userCode));
		User user = this.userQueryService.queryUserByUserCode(company.getCompanyCode(), userCode);
		if (user == null) {
		    throw new ServiceException(this.messageSource.getMessage("com.jinzhiyi.ffs.message.user.invalid_usercode", new Object[] {userCode}, LocaleContextHolder.getLocale()));
		}
		if (LockStatus.Locked.equals(user.getStatus())) {
		    throw new ServiceException(this.messageSource.getMessage("com.jinzhiyi.ffs.message.user.locked_usercode", new Object[] {user.getUserCode()}, LocaleContextHolder.getLocale()));
		}
		//如果用户账户是属于company的customer，则验证客户的状态
		if (!StringUtils.safeEqualsIgnoreCase(user.getCompanyCode(), user.getCustomerCode())) {
		    Customer customer = this.customerService.queryCustomerByCustomerCode(user.getCompanyCode(), user.getCustomerCode());
		    if (customer == null) {
			throw new ServiceException(this.messageSource.getMessage("com.jinzhiyi.ffs.message.customer.invalid_customercode", new Object[] {user.getCustomerCode()}, LocaleContextHolder.getLocale()));
		    }
//		    if (!CustomerAuditStatus.Finished.isMe(customer.getAuditStatus())) {
//			throw new ServiceException(this.messageSource.getMessage("com.jinzhiyi.ffs.message.customer.auditingstatus", new Object[] {user.getCustomerCode()}, LocaleContextHolder.getLocale()));
//		    }
		    if (LockStatus.Locked.isMe(customer.getLockStatus())) {
			throw new ServiceException(this.messageSource.getMessage("com.jinzhiyi.ffs.message.customer.locked_customer", new Object[] {user.getCustomerCode()}, LocaleContextHolder.getLocale()));
		    }
		}


		password = StringUtils.safeToTrim(password);
		/*if (!this.passwordEncoder.matches(password, user.getUserPassword())) {
		    throw new ServiceException(this.messageSource.getMessage("com.jinzhiyi.ffs.message.user.invalid_password", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		}*/
		if(!password.equals(user.getUserPassword())){
			throw new ServiceException(this.messageSource.getMessage("com.jinzhiyi.ffs.message.user.invalid_password", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		}
		
		SessionUser sessionUser = new SessionUser();
		sessionUser.setCompanyId(user.getCompanyId());
		sessionUser.setCompanyCode(user.getCompanyCode());
		sessionUser.setCustomerCode(user.getCustomerCode());
		sessionUser.setUserCode(user.getUserCode());
		sessionUser.setUserId(user.getRecId());
		sessionUser.setUserNameCn(user.getUserNameCn());
		sessionUser.setUserNameEn(user.getUserNameEn());
		sessionUser.addRoleIds(this.roleQueryService.queryRoleIdsByUserId(user.getRecId()));
		return sessionUser;
	}

}
