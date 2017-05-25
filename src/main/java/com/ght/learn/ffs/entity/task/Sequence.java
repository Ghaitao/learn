package com.ght.learn.ffs.entity.task;

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

import framework.dao.entity.BaseEntity;

/**
 * 流水号生成器
 * 每天从1开始生成编号
 * @author MyYate
 */
@Entity
@Table(name = "T_SEQUENCE", schema = FfsConstants.DB_SCHEMA_NAME)
public class Sequence extends BaseEntity {

    private static final long serialVersionUID = -8986426735204113296L;

    public enum SeqType {
	/**
	 * 订单编号
	 */
	OrderSerialNo,
	/**
	 * 进仓编号
	 */
	EntrySerialNo
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;
    
    /**
     * 流水号种类
     * 如：订单编号，进仓编号
     */
    @Column(name = "SEQ_TYPE", length=20)
    @Enumerated(EnumType.STRING)
    private Sequence.SeqType seqType;
    
    /**
     * 当前值
     * 初始为0
     */
    @Column(name = "CURRENT_VALUE", length=11)
    private Long currentValue;
    
    /**
     * 日期
     * 如：20170111
     */
    @Column(name = "FMT_DAY", length = 8)
    private String fmtDay;

    @Column(name = "COMPANY_CODE", length=20)
    private String companyCode;
    
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

    public Sequence.SeqType getSeqType() {
        return seqType;
    }

    public void setSeqType(Sequence.SeqType seqType) {
        this.seqType = seqType;
    }

    public Long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Long currentValue) {
        this.currentValue = currentValue;
    }

    public String getFmtDay() {
        return fmtDay;
    }

    public void setFmtDay(String fmtDay) {
        this.fmtDay = fmtDay;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}