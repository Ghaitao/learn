package com.ght.learn.ffs.service.base.support;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.sys.User;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.base.RoleQueryService;
import com.ght.learn.ffs.service.base.UserQueryService;

import framework.core.utils.CollectionUtils;
import framework.core.utils.StringUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;
/**
 * 缓存没加
 * @author ght
 *
 */
@Service("UserQueryService")
public class UserQueryServiceImpl extends FfsServiceImpl implements UserQueryService{

	
	@Resource(name = "RoleQueryService", type = RoleQueryService.class)
    private RoleQueryService roleQueryService;
	
	//@Cacheable(value="data", key="'com.jinzhiyi.ffs.entity.sys.User@companyCode='+#companyCode+'&userCode='+#userCode")
	@Override
	public User queryUserByUserCode(String companyCode, String userCode) {
		if(StringUtils.isEmpty(userCode)&&StringUtils.isEmpty(companyCode)){
			return null;
		}
		SafeDetachedCriteria sdc=SafeDetachedCriteria.forClass(User.class);
		sdc.add(SafeRestrictions.equal("companyCode", StringUtils.safeToUpperCase(companyCode)));
		sdc.add(SafeRestrictions.equal("userCode", StringUtils.safeToLowerCase(userCode)));
		return getEntityManager().queryForUniqueObjectByCriteria(User.class, sdc);
	}

	@Override
	public User queryUserByUserId(Long userId) {
		
		return getEntityManager().get(User.class, userId);
	}

	@Override
	public List<User> queryUsersByCompanyCodeCustomerCode(String companyCode, String customerCode) {
		if (StringUtils.isEmpty(companyCode) || StringUtils.isEmpty(customerCode)) {
		    return Collections.emptyList();
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(User.class);
		sdc.add(SafeRestrictions.equal("companyCode", companyCode));
		sdc.add(SafeRestrictions.equal("customerCode", customerCode));
		return getEntityManager().queryForListByCriteria(User.class, sdc);
	}

	@Override
	public List<User> queryUsersByCompanyCodeRoleId(String companyCode, Long roleId) {
		List<Long> userIds = this.roleQueryService.queryUserIdsByRoleId(companyCode, roleId);
		if (CollectionUtils.isEmpty(userIds)) {
		    return Collections.emptyList();
		}
		ArrayList<User> users = new ArrayList<User>(userIds.size());
		for (Long userId : userIds) {
		    users.add(queryUserByUserId(userId));
		}
		return users;
	}

}
