package com.ght.learn.ffs.entity.sys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ght.learn.ffs.common.FfsConstants;

import framework.dao.entity.BaseEntity;

@Entity
@Table(name = "S_ROLE_MENU", schema = FfsConstants.DB_SCHEMA_NAME)
public class RoleMenu extends BaseEntity {

    private static final long serialVersionUID = -4034844853099899596L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;
    
    @Column(name = "ROLE_ID", length=11)
    private Long roleId;
    
    @Column(name = "MENU_ID", length=11)
    private Long menuId;
    
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}