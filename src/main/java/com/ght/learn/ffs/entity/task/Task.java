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
import com.ght.learn.ffs.common.I18nable;
import com.ght.learn.ffs.entity.AuditableEntity;
import com.ght.learn.ffs.enums.task.TaskType;

/**
 * 任务表
 * 客户申请或者订单批复产生的任务
 * @author MyYate
 */
@Entity
@Table(name = "T_TASK", schema = FfsConstants.DB_SCHEMA_NAME)
public class Task extends AuditableEntity implements I18nable {

    private static final long serialVersionUID = 8473058072754160420L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_ID", length=11)
    private Long recId;
    
    /**
     * 流程类型
     */
    @Column(name = "TASK_TYPE", length=20)
    @Enumerated(EnumType.STRING)
    private TaskType taskType;
    
    /**
     * 关联的主键ID
     * 如：客户Id，订单Id
     */
    @Column(name = "CORRELATED_ID", length=11)
    private Long correlatedId;
    
    /**
     * 任务名称中文
     */
    @Column(name = "NAME_CN", length=300)
    private String nameCn;
    
    /**
     * 任务名称英文
     */
    @Column(name = "NAME_EN", length=300)
    private String nameEn;
    
    /**
     * 处理者ID 
     * 如果handleUserId不为空，则只有用户handleUserId能查到这条数据
     * 如果handleUserId为空，看handleRoleId
     */
    @Column(name = "HANDLE_USER_ID", length = 11)
    private Long handleUserId;
    
    /**
     * 处理者角色ID
     * 如果handleRoleId不为空，则只有用户角色为nextHanlderRoleId能查到这条数据
     */
    @Column(name = "HANDLE_ROLE_ID", length = 11)
    private Long handleRoleId;
    
    /**
     * 所属公司代码
     */
    @Column(name = "COMPANY_CODE", length=20)
    private String companyCode;
    
//    /**
//     * 当前任务所在的节点
//     */
//    @Column(name = "TASK_NODE_ID", length=11)
//    private Long taskNodeId;

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

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Long getCorrelatedId() {
        return correlatedId;
    }

    public void setCorrelatedId(Long correlatedId) {
        this.correlatedId = correlatedId;
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

    public Long getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(Long handleUserId) {
        this.handleUserId = handleUserId;
    }

    public Long getHandleRoleId() {
        return handleRoleId;
    }

    public void setHandleRoleId(Long handleRoleId) {
        this.handleRoleId = handleRoleId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}