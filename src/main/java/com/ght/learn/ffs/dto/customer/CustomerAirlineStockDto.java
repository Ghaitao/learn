package com.ght.learn.ffs.dto.customer;

import framework.bean.dto.BaseDto;

public class CustomerAirlineStockDto extends BaseDto {

    private static final long serialVersionUID = -5487273061631148214L;

    //查询部分
    private String customerCode;
    
    /**
     * 运单前缀
     */
    private String stkPrefix;
    
    /**
     * 起止号或结束号
     */
    private String startNoOrEndNo;
    
    /**
     * 主单号
     * 如99912312311或999-12312311
     */
    private String awbFullNo;
    
    private Long airlineStockSegmentId;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAwbFullNo() {
        return awbFullNo;
    }

    public void setAwbFullNo(String awbFullNo) {
        this.awbFullNo = awbFullNo;
    }

    public Long getAirlineStockSegmentId() {
        return airlineStockSegmentId;
    }

    public void setAirlineStockSegmentId(Long airlineStockSegmentId) {
        this.airlineStockSegmentId = airlineStockSegmentId;
    }

    public String getStkPrefix() {
        return stkPrefix;
    }

    public void setStkPrefix(String stkPrefix) {
        this.stkPrefix = stkPrefix;
    }

    public String getStartNoOrEndNo() {
        return startNoOrEndNo;
    }

    public void setStartNoOrEndNo(String startNoOrEndNo) {
        this.startNoOrEndNo = startNoOrEndNo;
    }
}