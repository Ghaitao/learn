package com.ght.learn.ffs.service.sys.support;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ght.learn.ffs.dto.sys.RoleDto;
import com.ght.learn.ffs.entity.sys.Role;
import com.ght.learn.ffs.entity.sys.RoleMenu;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.sys.RoleService;

import framework.core.pagination.OrderablePagination;
import framework.core.utils.ObjectUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;

@Service("RoleService")
public class RoleServiceImpl extends FfsServiceImpl implements RoleService {

	@Override
	public Role getRoleById(Long recId) {
		Role entity = this.getEntityManager().get(Role.class, recId);
		return entity;
	}

	@Override
	public List<Role> queryRole(RoleDto dto, OrderablePagination op) {

		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Role.class);
		if(dto != null) {
			// 通过中文名或英文名查询角色信息
			sdc.add(SafeRestrictions.or(
					SafeRestrictions.likeAnyWhere("nameCn", dto.getName()),
					SafeRestrictions.likeAnyWhere("nameEn", dto.getName())
			));
		}
		if(op != null && !ObjectUtils.isEmpty(op.getSorters())) {
			sdc.addSorters(op.getSorters());
		}
		return getEntityManager().queryForListByCriteria(Role.class, sdc, op);
	}

	@Override
	public Long doCreateRole(Role dto) {
		if (dto == null) {
			return null;
		}

		Role entity = new Role();
		// 存储系统中的角色信息
		entity.setNameCn(dto.getNameCn());
		entity.setNameEn(dto.getNameEn());
		Long roleId = (Long) this.getEntityManager().save(entity);
		this.doSaveRoleMenus(roleId, dto.getMenuIds());
		return roleId;
	}

	@Override
	public void doUpdateRole(Role dto) {
		if (dto == null || dto.getRecId() == null) {
			return;
		}
		Role entity = this.getEntityManager().get(Role.class, dto.getRecId());
		if (entity == null) {
			return;
		}
		entity.setNameCn(dto.getNameCn());
		entity.setNameEn(dto.getNameEn());
		this.getEntityManager().update(entity);
		this.doSaveRoleMenus(entity.getRecId(), dto.getMenuIds());
	}

	@Override
	public void doDeleteRole(Long recId) {
		// 删除UserRole表
    	getEntityManager().executeHql("delete UserRole ur where ur.roleId = ?", new Object[] {recId});
		// 删除CompanyRole表
    	getEntityManager().executeHql("delete CompanyRole cr where cr.roleId = ?", new Object[] {recId});
    	// 删除RoleMenu表
    	getEntityManager().executeHql("delete RoleMenu rm where rm.roleId = ?", new Object[] {recId});
		// 删除Role表
		this.getEntityManager().delete(Role.class, recId);
	}

	@Override
	public List<Role> queryRoles() {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Role.class);
		return this.getEntityManager().queryForListByCriteria(Role.class, sdc);
	}
	
	private void doSaveRoleMenus(Long roleId, List<Long> menuIds) {
    	// 删除原有记录
    	getEntityManager().executeHql("delete RoleMenu rm where rm.roleId = ?", new Object[] {roleId});
    	if (menuIds != null && menuIds.size() > 0) {
	    	for (Long menuId : menuIds) {
	    		RoleMenu entity = new RoleMenu();
	    		entity.setMenuId(menuId);
	    		entity.setRoleId(roleId);
	    		getEntityManager().save(entity);
	    	}
    	}
    }
	
}
