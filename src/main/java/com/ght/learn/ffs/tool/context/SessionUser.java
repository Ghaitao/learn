package com.ght.learn.ffs.tool.context;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.ght.learn.ffs.common.I18nable;
import com.ght.learn.ffs.entity.sys.Role;

import framework.core.utils.StringUtils;

public class SessionUser implements Serializable, I18nable {
    
    private static final long serialVersionUID = -6168430741371611504L;

    private Long userId;
    
    private String userCode;
    
    private String userNameCn;

    private String userNameEn;
    
    /**
     * 所属客户代码
     */
    private String customerCode;
    
    private Long companyId;
    
    private String companyCode;
    
    private HashSet<Long> roleIds = new HashSet<Long>(5);
    
    public SessionUser addRoleIds(Collection<Long> roleIds) {
        if (roleIds != null) {
            this.roleIds.addAll(roleIds);
        }
        return this;
    }
    
    /**
     * 显示用户名之用
     * @return
     */
    @Override
    public String getI18nName() {
	String name = I18nable.super.getI18nName();
	if (StringUtils.isEmpty(name)) {
	    name = getUserCode();
	}
	return name;
    }
    
    @Override
    public String getNameCn() {
	return getUserNameCn();
    }

    @Override
    public String getNameEn() {
	return getUserNameEn();
    }
    
    public boolean isRoleJinZhiYiAdmin() {
	return hasThisRole(Role.JINZHIYI_ADMIN);
    }
    
    public boolean isRoleAdmin() {
	return hasThisRole(Role.ADMIN);
    }
    
    public boolean isRoleAccountant() {
	return hasThisRole(Role.ACCOUNTANT);
    }
    
    public boolean isRoleCustomerService() {
	return hasThisRole(Role.CUSTOMER_SERVICE);
    }

    public boolean isRoleCustomer() {
	return hasThisRole(Role.CUSTOMER);
    }
    
    public boolean isRoleAirRouter() {
	return hasThisRole(Role.AIR_ROUTER);
    }
    
    public boolean isRoleAirRouterManager() {
	return hasThisRole(Role.AIR_ROUTER_MANAGER);
    }
    
    public boolean isCustomer() {
	return !StringUtils.safeEquals(this.customerCode, this.companyCode);
    }
    
    public boolean isCompanyUser() {
	return StringUtils.safeEquals(this.customerCode, this.companyCode);
    }
    
    public boolean hasThisRole(Long roleId) {
	return this.roleIds.contains(roleId);
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Set<Long> getRoleIds() {
        return Collections.unmodifiableSet(this.roleIds);
    }

    public String getUserNameCn() {
        return userNameCn;
    }

    public void setUserNameCn(String userNameCn) {
        this.userNameCn = userNameCn;
    }

    public String getUserNameEn() {
        return userNameEn;
    }

    public void setUserNameEn(String userNameEn) {
        this.userNameEn = userNameEn;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     * use getCompanyCode()
     * @return
     */
    @Deprecated
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}