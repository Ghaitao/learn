package com.ght.learn.ffs.service.sys;

import java.util.List;

import com.ght.learn.ffs.entity.sys.UserRole;

import framework.service.CommonService;

public interface UserRoleService extends CommonService {

	/**
	 * 根据userId得到公司角色列表
	 * @param userId
	 * @return 公司角色列表
	 */
	List<UserRole> queryUserRolesByUserId(Long userId);
}
