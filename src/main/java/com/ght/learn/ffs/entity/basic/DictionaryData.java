package com.ght.learn.ffs.entity.basic;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.common.I18nable;

import framework.core.utils.StringUtils;
import framework.dao.entity.BaseEntity;

@Entity
@Table(name = "B_DICTIONARY_DATA", schema = FfsConstants.DB_SCHEMA_NAME)
public class DictionaryData extends BaseEntity implements I18nable {

    private static final long serialVersionUID = 1012010113820820294L;

    /**
     * 职位
     */
    public static final String PARENT_CODE_POSITION = "Position";
    /**
     * 包装种类
     */
    public static final String PARENT_CODE_PACKING_INFO = "PackingInfo";
    /**
     * 分泡比例
     */
    public static final String PARENT_CODE_BUBBLE_PROPORTION = "BubbleProportion";
    /**
     * 货损情况
     */
    public static final String PARENT_CODE_CARGO_DAMAGE = "CargoDamage";
    
    
    @Id
    @Column(name = "CODE", length=40)
    private String code;
    
    @Column(name = "PARENT_CODE", length=40)
    private String parentCode;
    
    @Column(name = "NAME_CN", length=40)
    private String nameCn;
    
    @Column(name = "NAME_EN", length=40)
    private String nameEn;
    
    @Column(name = "SORTER", length=3)
    private Integer sorter;
    
    @OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name="PARENT_CODE", referencedColumnName="CODE", nullable=true, insertable=false, updatable=false)
    private List<DictionaryData> subDicts;
    
    public List<DictionaryData> getSubDicts() {
		return subDicts;
	}

	public void setSubDicts(List<DictionaryData> subDicts) {
		this.subDicts = subDicts;
	}

	@Transient
    private String name;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public Serializable getId() {
	return this.parentCode + "#" + this.code;
    }

    @Override
    public String getI18nName() {
	String name = I18nable.super.getI18nName();
	if (StringUtils.isEmpty(name)) {
	    name = getCode();
	}
	return name;
    }
    
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSorter() {
        return sorter;
    }

    public void setSorter(Integer sorter) {
        this.sorter = sorter;
    }
}
