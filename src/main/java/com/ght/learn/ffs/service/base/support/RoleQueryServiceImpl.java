package com.ght.learn.ffs.service.base.support;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.sys.Role;
import com.ght.learn.ffs.entity.sys.UserRole;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.base.RoleQueryService;

import framework.core.utils.CollectionUtils;
import framework.core.utils.StringUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;

@Service("RoleQueryService")
public class RoleQueryServiceImpl extends FfsServiceImpl implements RoleQueryService{

	@Override
	public Role queryRoleByRoleId(Long roleId) {
		return getEntityManager().get(Role.class, roleId);
	}

	@Override
	public Set<Long> queryRoleIdsByUserId(Long userId) {
		if(userId==null){
			return null;
		}
		SafeDetachedCriteria sdc=SafeDetachedCriteria.forClass(UserRole.class);
		sdc.setProjection(Projections.distinct(Projections.property("roleId")));
		sdc.add(SafeRestrictions.equal("userId", userId));
		List<Long> roleIds=getEntityManager().queryForListByCriteria(Long.class, sdc);
		if (CollectionUtils.isEmpty(roleIds)) {
		    return Collections.emptySet();
		}
		return new HashSet<Long>(roleIds);
	}

	@Override
	public Set<Long> queryRoleIdsByCompanyId(Long companyId) {
		if(companyId==null){
			return null;
		}
		SafeDetachedCriteria sdc=SafeDetachedCriteria.forClass(UserRole.class);
		sdc.setProjection(Projections.distinct(Projections.property("roleId")));
		sdc.add(SafeRestrictions.equal("companyId", companyId));
		List<Long> roleIds=getEntityManager().queryForListByCriteria(Long.class, sdc);
		if (CollectionUtils.isEmpty(roleIds)) {
		    return Collections.emptySet();
		}
		return new HashSet<Long>(roleIds);
	}

	/**
     * 查询给定companyCode里角色为roleId的所有人Id
     * @param companyCode
     * @param roleId
     * @return
     */
	@Override
	public List<Long> queryUserIdsByRoleId(String companyCode, Long roleId) {
		if (StringUtils.isEmpty(companyCode) || roleId == null) {
		    return Collections.emptyList();
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(UserRole.class);
		sdc.setProjection(Projections.distinct(Projections.property("userId")));
		sdc.add(SafeRestrictions.equal("roleId", roleId));
		sdc.add(SafeRestrictions.equal("companyCode", companyCode));
		List<Long> userIds = getEntityManager().queryForListByCriteria(Long.class, sdc);
		return userIds;
	}

}
