package com.ght.learn.ffs.entity.sys;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.enums.sys.NewsGrade;

import framework.core.enums.CommonEnums;
import framework.core.enums.CommonEnums.IF;
import framework.dao.entity.BaseEntity;

@Entity
@Table(name = "S_NEWS", schema = FfsConstants.DB_SCHEMA_NAME)
public class News extends BaseEntity {

    private static final long serialVersionUID = -8929488045952291239L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;
    
    /**
     * 标题
     */
    @Column(name = "TITLE", length=300)
    private String title;
    
    /**
     * 摘要
     */
    @Column(name = "SUMMARY", length=1000)
    private String summary;
    
    /**
     * 内容
     * 字段类型在MySQL里建成text
     */
    @Column(name = "CONTENT")
    private String content;
    
    /**
     * 新闻等级
     */
    @Column(name = "GRADE", length=20)
    @Enumerated(EnumType.STRING)
    private NewsGrade grade;
    
    /**
     * 是否置顶
     */
    @Column(name = "TOP", length=20)
    @Enumerated(EnumType.STRING)
    private CommonEnums.IF top;
    
    /**
     * 开始使用时间
     * 年月日 时分秒
     */
    @Column(name="START_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDateTime;
    
    /**
     * 使用到期时间
     * 年月日 时分秒
     */
    @Column(name="EXPIRY_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDateTime;
    
    /**
     * 发布新闻的公司代码
     */
    @Column(name = "COMPANY_CODE", length=20)
    private String companyCode;
    
    /**
     * 修改人
     */
    @Column(name="MODIFIER")
    private Long modifier;
    
    /**
     * 修改时间
     */
    @Column(name="MODIFY_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDateTime;

	@Override
    public Serializable getId() {
	return getRecId();
    }

    public boolean isTopped() {
	return IF.isY(this.top);
    }
    
    public boolean isImportantGrade() {
	return NewsGrade.Important.isMe(this.grade);
    }
    
    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewsGrade getGrade() {
        return grade;
    }

    public void setGrade(NewsGrade grade) {
        this.grade = grade;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(Date expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public CommonEnums.IF getTop() {
        return top;
    }

    public void setTop(CommonEnums.IF top) {
        this.top = top;
    }

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    public Date getModifyDateTime() {
        return modifyDateTime;
    }

    public void setModifyDateTime(Date modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }
}