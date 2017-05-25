package com.ght.learn.ffs.service.base;

import java.util.List;
import java.util.Set;

import com.ght.learn.ffs.entity.sys.Role;
import com.ght.learn.ffs.service.FfsService;

public interface RoleQueryService extends FfsService{
	Role queryRoleByRoleId(Long roleId);
	//跟君用户id查询角色的id
	Set<Long> queryRoleIdsByUserId(Long userId);

	Set<Long> queryRoleIdsByCompanyId(Long companyId);
	/**
     * 查询给定companyCode里角色为roleId的所有人Id
     * @param companyCode
     * @param roleId
     * @return
     */
	List<Long> queryUserIdsByRoleId(String companyCode, Long roleId);
}
