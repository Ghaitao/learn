package com.ght.learn.ffs.entity.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.common.I18nable;
import com.ght.learn.ffs.enums.Validity;

import framework.core.utils.StringUtils;
import framework.dao.entity.BaseEntity;

@Entity
@Table(name = "S_MENU", schema = FfsConstants.DB_SCHEMA_NAME)
public class Menu extends BaseEntity implements Comparable<Menu>, I18nable {

    private static final long serialVersionUID = -4012024268862111832L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;
    
    @Column(name = "NAME_CN", length=40)
    private String nameCn;
    
    @Column(name = "NAME_EN", length=40)
    private String nameEn;
    
    @Column(name = "REMARK", length=40)
    private String remark;
    
    @Column(name = "URL", length=1500)
    private String url;
    
    /**
     * 排序
     */
    @Column(name="SORT", length=3)
    private Integer sort;
    
    /**
     * 菜单图标，如：'fa fa-odnoklassniki'
     */
    @Column(name = "ICON", length=40)
    private String icon;
    
    @Column(name = "PARENT_ID", length=11)
    private Long parentId;
    
    @Column(name = "VALIDITY", length=10)
    @Enumerated(EnumType.STRING)
    private Validity validity;
    
    @Transient
    private String name;
    
    @Transient
    private List<Menu> children = new ArrayList<Menu>(5);
    
    @Override
    public Serializable getId() {
	return getRecId();
    }

    @Override
    public int compareTo(Menu menu) {
	if (menu == null) {
	    return -1;
	}
	if (menu.getSort() == null) {
	    return -1;
	}
	if (this.getSort() == null) {
	    return 1;
	}
	int minus =  this.getSort().intValue() - menu.getSort().intValue();
	if (minus != 0) {
	    return minus;
	}
	if (menu.getRecId().longValue() > this.getRecId().longValue()) {
	    return 1;
	}
	if (menu.getRecId().longValue() < this.getRecId().longValue()) {
	    return -1;
	}
	return 0;
    }

    public boolean isvalid() {
    	return Validity.Valid.isMe(getValidity());
    }
    
    public boolean isParent() {
	return this.parentId == null;
    }
    
    public boolean isMyParent(Long parentId) {
	if (parentId == null) {
	    return false;
	}
	return parentId.equals(this.parentId);
    }
    
    public boolean isMyUrl(String requestUri) {
	return StringUtils.safeContains(this.url, requestUri);
    }
    
    public List<Menu> getChildren() {
        return children;
    }

    public Menu addChild(Menu child) {
	if (child == null) {
	    return this;
	}
	this.children.add(child);
	return this;
    }
    
    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Validity getValidity() {
        return validity;
    }

    public void setValidity(Validity validity) {
        this.validity = validity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}