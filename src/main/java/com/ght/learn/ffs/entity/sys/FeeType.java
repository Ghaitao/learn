package com.ght.learn.ffs.entity.sys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.common.I18nable;
import com.ght.learn.ffs.entity.AuditableEntity;

/**
 * 费用种类
 * 默认使用ADMIN的费用种类，各公司可以添加补充
 * @author MyYate
 */
@Entity
@Table(name = "S_FEE_TYPE", schema = FfsConstants.DB_SCHEMA_NAME)
public class FeeType extends AuditableEntity implements I18nable {

    private static final long serialVersionUID = -5347339316211893015L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;
    
    @Column(name = "NAME_CN", length=300)
    private String nameCn;
    
    @Column(name = "NAME_EN", length=300)
    private String nameEn;
    
    /**
     * 所属公司代码
     */
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
}