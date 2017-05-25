package com.ght.learn.ffs.entity.basic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.common.I18nable;
import com.ght.learn.ffs.enums.DI;

import framework.dao.entity.BaseEntity;
/**
 * 机场
 * @author ght
 *
 */
@Entity
@Table(name = "B_AIRPORT", schema = FfsConstants.DB_SCHEMA_NAME)
public class Airport extends BaseEntity implements I18nable {

    private static final long serialVersionUID = 9208164192550208916L;

    /**
     * 三字码
     */
    @Id
    @Column(name = "AIRPORT_CODE", length=5)
    private String airportCode;
    
    @Column(name = "NAME_CN", length=400)
    private String nameCn;
    
    @Column(name = "NAME_EN", length=400)
    private String nameEn;
    
    /**
     * 国内国外
     */
    @Column(name = "DI")
    @Enumerated(EnumType.STRING)
    private DI di;
    
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
	return getAirportCode();
    }
    
    /** Autocomplete use **/
    public String getDisplayKey() {
    	return getAirportCode() + "-" + getI18nName();
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public DI getDi() {
        return di;
    }

    public void setDi(DI di) {
        this.di = di;
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