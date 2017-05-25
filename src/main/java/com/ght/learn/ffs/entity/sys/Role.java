package com.ght.learn.ffs.entity.sys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.common.I18nable;

import framework.core.utils.StringUtils;
import framework.dao.entity.BaseEntity;

@Entity
@Table(name = "S_ROLE", schema = FfsConstants.DB_SCHEMA_NAME)
public class Role extends BaseEntity implements I18nable {

    private static final long serialVersionUID = 5514202459336235706L;
    
    /**
     * 金之翼管理员
     */
    public static final Long JINZHIYI_ADMIN = Long.valueOf(100);
    
    
    
    /**
     * 管理员
     */
    public static final Long ADMIN = Long.valueOf(1000);
    
    /**
     * 客户
     */
    public static final Long CUSTOMER = Long.valueOf(2000);
    
    /**
     * 客服
     */
    public static final Long CUSTOMER_SERVICE = Long.valueOf(2010);
    
    /**
     * 航线
     */
    public static final Long AIR_ROUTER = Long.valueOf(2020);
    
    /**
     * 航线经理
     */
    public static final Long AIR_ROUTER_MANAGER = Long.valueOf(2030);
    
    /**
     * 财务
     */
    public static final Long ACCOUNTANT = Long.valueOf(2040);
    
    /**
     * 仓库管理员
     */
    public static final Long WAREHOUSE_KEEPER = Long.valueOf(2050);
    
//    /**
//     * 管理员
//     */
//    public static final Role ADMIN = new Role(Long.valueOf(1000), "管理员", "Administrator");
//    
//    /**
//     * 监控员
//     */
//    public static final Role MONITOR = new Role(Long.valueOf(2000), "客服", "Customer");
//    
//    /**
//     * 客服
//     */
//    public static final Role CUSTOMER_SERVICE = new Role(Long.valueOf(2010), "客服", "Customer Service");
//    
//    /**
//     * 航线
//     */
//    public static final Role AIR_ROUTER = new Role(Long.valueOf(2020), "航线", "Air Router");
//    
//    /**
//     * 航线经理
//     */
//    public static final Role AIR_ROUTER_MANAGER = new Role(Long.valueOf(2030), "航线经理", "Air Router Manager");
//    
//    /**
//     * 财务
//     */
//    public static final Role ACCOUNTANT = new Role(Long.valueOf(2040), "财务", "Accountant");
//    
//    /**
//     * 仓库管理员
//     */
//    public static final Role WAREHOUSE_KEEPER = new Role(Long.valueOf(2050), "仓库管理员", "Warehouse Keeper");
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;
    
    @Column(name = "NAME_CN", length=40)
    private String nameCn;
    
    @Column(name = "NAME_EN", length=40)
    private String nameEn;

    @Transient
    private List<Long> menuIds;
    
    public Role(Long recId, String nameCn, String nameEn) {
	super();
	this.recId = recId;
	this.nameCn = nameCn;
	this.nameEn = nameEn;
    }

    public Role() {
	super();
    }

    @Override
    public String getI18nName() {
	String name = I18nable.super.getI18nName();
	if (StringUtils.isEmpty(name)) {
	    name = String.valueOf(this.recId);
	}
	return name;
    }
    
    public boolean isThisRole(Long roleId) {
	if (roleId == null) {
	    return false;
	}
	return roleId.equals(this.recId);
    }
    
    @Override
    public Serializable getId() {
	return getRecId();
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }
    
    public List<Long> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Long> menuIds) {
		this.menuIds = menuIds;
	}

}