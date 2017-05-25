package com.ght.learn.ffs.entity.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.entity.CreatableEntity;

/**
 * 号段
 * @author MyYate
 */
@Entity
@Table(name = "C_CUSTOMER_AIRLINE_STK_SEG", schema = FfsConstants.DB_SCHEMA_NAME)
public class CustomerAirlineStockSegment extends CreatableEntity {

    private static final long serialVersionUID = -848701783332314247L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;

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
     * 运单开始编号（7位数字，不包括第8位模7结果）
     */
    @Column(name = "START_NO", length=7)
    private String startNo;

    /**
     * 运单结束编号（7位数字，不包括第8位模7结果）
     */
    @Column(name = "END_NO", length=7)
    private String endNo;

    /**
     * 数量
     */
    @Column(name = "COUNT", length=5)
    private Long count;

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

    public String getStkPrefix() {
        return stkPrefix;
    }

    public void setStkPrefix(String stkPrefix) {
        this.stkPrefix = stkPrefix;
    }

    public String getStartNo() {
        return startNo;
    }

    public void setStartNo(String startNo) {
        this.startNo = startNo;
    }

    public String getEndNo() {
        return endNo;
    }

    public void setEndNo(String endNo) {
        this.endNo = endNo;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
