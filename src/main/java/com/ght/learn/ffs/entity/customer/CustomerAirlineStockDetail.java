package com.ght.learn.ffs.entity.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.common.FwbUtil;
import com.ght.learn.ffs.entity.CreatableEntity;
import com.ght.learn.ffs.enums.IF;


/**
 * 运单号
 * @author MyYate
 */
@Entity
@Table(name = "C_CUSTOMER_AIRLINE_STK_DETAIL", schema = FfsConstants.DB_SCHEMA_NAME)
public class CustomerAirlineStockDetail extends CreatableEntity {

    private static final long serialVersionUID = 2576471017822519576L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;

    @Column(name = "AIRLINE_STOCK_SEGMENT_ID", length=11)
    private Long airlineStockSegmentId;
    
    /**
     * 客户代码或简称
     */
    @Column(name = "CUSTOMER_CODE", length=20)
    private String customerCode;
    
    /**
     * 所属公司代码
     */
    @Column(name = "COMPANY_CODE", length=20)
    private String companyCode;
    
    /**
     * 运单前缀
     */
    @Column(name = "STK_PREFIX", length=3)
    private String stkPrefix;

    /**
     * 运单编号
     */
    @Column(name = "STK_NO", length = 8)
    private String stkNo;

    /**
     * 是否可用：Y 可用/ N 不可用
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "CAN_USE", length=5)
    private IF canUse;

    @Override
    public Serializable getId() {
	return getRecId();
    }
    
    public boolean isHasUsed() {
	return IF.N.isMe(this.canUse);
    }
    
    public String getAwbFullNo() {
	return FwbUtil.formatAwbno(this.stkPrefix, this.stkNo);
    }
    
    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getStkPrefix() {
        return stkPrefix;
    }

    public void setStkPrefix(String stkPrefix) {
        this.stkPrefix = stkPrefix;
    }

    public String getStkNo() {
        return stkNo;
    }

    public void setStkNo(String stkNo) {
        this.stkNo = stkNo;
    }

    public IF getCanUse() {
        return canUse;
    }

    public void setCanUse(IF canUse) {
        this.canUse = canUse;
    }

    public Long getAirlineStockSegmentId() {
        return airlineStockSegmentId;
    }

    public void setAirlineStockSegmentId(Long airlineStockSegmentId) {
        this.airlineStockSegmentId = airlineStockSegmentId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
