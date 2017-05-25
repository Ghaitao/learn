package com.ght.learn.ffs.service.sys.support;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ght.learn.ffs.entity.sys.RoleMenu;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.sys.RoleMenuService;

import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;

@Component("RoleMenuService")
public class RoleMenuServiceImpl extends FfsServiceImpl implements RoleMenuService {

	@Override
	public List<RoleMenu> queryRoleMenusByRoleId(Long roleId) {
		if (roleId == null) {
			return null;
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(RoleMenu.class);
		sdc.add(SafeRestrictions.equal("roleId", roleId));
		List<RoleMenu> result = getEntityManager().queryForListByCriteria(RoleMenu.class, sdc);
		return result;
	}

}
