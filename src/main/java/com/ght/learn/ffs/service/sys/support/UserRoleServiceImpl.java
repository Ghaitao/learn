package com.ght.learn.ffs.service.sys.support;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.sys.UserRole;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.sys.UserRoleService;

import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;

@Service("UserRoleService")
public class UserRoleServiceImpl extends FfsServiceImpl implements UserRoleService {

	@Override
	public List<UserRole> queryUserRolesByUserId(Long userId) {
		if (userId == null) {
			return null;
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(UserRole.class);
		sdc.add(SafeRestrictions.equal("userId", userId));
		List<UserRole> result = getEntityManager().queryForListByCriteria(UserRole.class, sdc);
		return result;
	}

}
