package com.ght.learn.ffs.service.base.support;

import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.sys.Menu;
import com.ght.learn.ffs.entity.sys.RoleMenu;
import com.ght.learn.ffs.enums.Validity;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.base.MenuQueryService;

import framework.core.utils.CollectionUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;

@Service("MenuQueryService")
public class MenuQueryServiceImpl extends FfsServiceImpl implements MenuQueryService{

	@Override
	public List<Long> queryVisibleMenuIdsByRoleId(Long roleId) {
		if (roleId == null) {
		    return Collections.emptyList();
		}
		SafeDetachedCriteria sdcRoleMenu = SafeDetachedCriteria.forClass(RoleMenu.class);
		sdcRoleMenu.setProjection(Projections.distinct(Projections.property("menuId")));
		sdcRoleMenu.add(SafeRestrictions.equal("roleId", roleId));
		List<Long> menuIds = getEntityManager().queryForListByCriteria(Long.class, sdcRoleMenu);
		if (CollectionUtils.isEmpty(menuIds)) {
		    return Collections.emptyList();
		}
		return menuIds;
	}

	@Override
	public Menu queryMenuByMenuId(Long menuId) {
		if (menuId == null) {
		    return null;
		}
		SafeDetachedCriteria sdcMenu = SafeDetachedCriteria.forClass(Menu.class);
		sdcMenu.add(SafeRestrictions.equal("recId", menuId));
		sdcMenu.add(SafeRestrictions.equal("validity", Validity.Valid));
		return getEntityManager().queryForUniqueObjectByCriteria(Menu.class, sdcMenu);
	}

}
