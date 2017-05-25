package com.ght.learn.ffs.web.tag;

import java.util.ArrayList;
import java.util.Set;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.springframework.core.convert.converter.Converter;

import com.ght.learn.ffs.entity.sys.Role;
import com.ght.learn.ffs.service.base.RoleQueryService;

import framework.core.utils.CollectionUtils;
import framework.core.utils.StringUtils;

public class RoleTag extends AbstractStringPrintJspTag {

    private static final long serialVersionUID = 9113516922512439821L;

    private String companyId;
    
    private String userId;
    
    private String roleId;

    @Override
    protected String getOutString() throws JspException {
	if (this.userId != null) {
	    Long userIdLong = (Long) ExpressionEvaluatorManager.evaluate("userId", this.userId, java.lang.Long.class, this, this.pageContext);
	    RoleQueryService roleQueryService = getBean(RoleQueryService.class);
	    Set<Long> roleIds = roleQueryService.queryRoleIdsByUserId(userIdLong);
	    if (CollectionUtils.isEmpty(roleIds)) {
		return null;
	    }
	    ArrayList<Role> roles = new ArrayList<Role>(roleIds.size());
	    for (Long roleId : roleIds) {
		Role role = roleQueryService.queryRoleByRoleId(roleId);
		if (role != null) {
		    roles.add(role);
		}
	    }
	    return StringUtils.collectionToString(roles, ", ", new Converter<Role, String>() {
		@Override
		public String convert(Role source) {
		    return source.getI18nName();
		}
	    });
	}
	
	if (this.companyId != null) {
		Long companyIdLong = (Long) ExpressionEvaluatorManager.evaluate("companyId", this.companyId, java.lang.Long.class, this, this.pageContext);
		RoleQueryService roleQueryService = getBean(RoleQueryService.class);
	    Set<Long> roleIds = roleQueryService.queryRoleIdsByCompanyId(companyIdLong);
	    if (CollectionUtils.isEmpty(roleIds)) {
		return null;
	    }
	    ArrayList<Role> roles = new ArrayList<Role>(roleIds.size());
	    for (Long roleId : roleIds) {
		Role role = roleQueryService.queryRoleByRoleId(roleId);
		if (role != null) {
		    roles.add(role);
		}
	    }
	    return StringUtils.collectionToString(roles, ", ", new Converter<Role, String>() {
		@Override
		public String convert(Role source) {
		    return source.getI18nName();
		}
	    });
	}
	if (this.roleId != null) {
	    Long roleIdLong = (Long) ExpressionEvaluatorManager.evaluate("roleId", this.roleId, java.lang.Long.class, this, this.pageContext);
	    Role role = getBean(RoleQueryService.class).queryRoleByRoleId(roleIdLong);
	    return role == null ? null : role.getI18nName();
	    
	}
	return null;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

}