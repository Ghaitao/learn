package com.ght.learn.ffs.service.sys;

import java.util.List;

import com.ght.learn.ffs.entity.sys.RoleMenu;

import framework.service.CommonService;

public interface RoleMenuService extends CommonService {

	/**
	 * 根据roleId得到角色菜单列表
	 * @param roleId
	 * @return 角色菜单列表
	 */
	List<RoleMenu> queryRoleMenusByRoleId(Long roleId);
}
