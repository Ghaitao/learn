package com.ght.learn.ffs.service.sys;

import java.util.List;

import com.ght.learn.ffs.dto.sys.RoleDto;
import com.ght.learn.ffs.entity.sys.Role;

import framework.core.pagination.OrderablePagination;
import framework.service.CommonService;

public interface RoleService extends CommonService {

	/**
	 * 通过recId获取系统角色
	 * @param recId
	 * @return
	 */
	Role getRoleById(Long recId);
	
	/**
	 * 通过名称查询系统角色
	 * @param roleName
	 * @return
	 */
	List<Role> queryRole(RoleDto dto, OrderablePagination op);
	
	/**
	 * 添加系统角色信息
	 * @param dto
	 * @return
	 */
	Long doCreateRole(Role dto);
	
	/**
	 * 修改系统角色信息
	 * @param dto
	 */
	void doUpdateRole(Role dto);
	
	/**
	 * 通过recId删除角色
	 * @param roleId
	 */
	void doDeleteRole(Long recId);

	/**
	 * 获取系统所拥有的的全部角色
	 * @param companyCode
	 * @return
	 */
	List<Role> queryRoles();
	
}
