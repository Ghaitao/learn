package com.ght.learn.ffs.entity.basic;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.common.I18nable;

import framework.dao.entity.BaseEntity;

/**
 * 航空公司
 */
@Entity
@Table(name = "B_AIRLINE", schema = FfsConstants.DB_SCHEMA_NAME)
public class Airline extends BaseEntity implements I18nable {
    
    private static final long serialVersionUID = -7369729705889117356L;

    /**
     * 承运人二字码
     */
    @Id
    @Column(name = "AIRLINE_CODE", length=5)
    private String airlineCode;
    
    /**
     * 承运人运单前缀
     */
    @Column(name = "STOCK_PREFIX", length=5)
    private String stockPrefix;
    
    @Column(name = "NAME_CN", length=400)
    private String nameCn;
    
    @Column(name = "NAME_EN", length=400)
    private String nameEn;

    /***** 查询和保存使用 *****/
    @Transient
    private String name;

    @Override
    public String getNameCn() {
	return this.nameCn;
    }

    @Override
    public String getNameEn() {
	return this.nameEn;
    }

    @Override
    public Serializable getId() {
	return getAirlineCode();
    }
    
    /** Autocomplete use **/
    public String getDisplayKey() {
    	return getAirlineCode() + "-" + getI18nName();
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getStockPrefix() {
        return stockPrefix;
    }

    public void setStockPrefix(String stockPrefix) {
        this.stockPrefix = stockPrefix;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}