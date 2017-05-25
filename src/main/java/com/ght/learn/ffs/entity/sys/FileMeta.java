package com.ght.learn.ffs.entity.sys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.entity.CreatableEntity;
import com.ght.learn.ffs.enums.file.StorageMedium;
import com.ght.learn.ffs.tool.image.ImageUtil;

/**
 * 存储文件meta信息
 * 其他表里存储为“fileId”
 * @author MyYate
 */
@Entity
@Table(name = "S_FILE_META", schema = FfsConstants.DB_SCHEMA_NAME)
public class FileMeta extends CreatableEntity {

    private static final long serialVersionUID = -5710034824272295439L;

    @Id
    @Column(name = "FILE_META_ID", length=255)
    private String fileMetaId;
    
    /**
     * 原始文件名
     */
    @Column(name = "ORIG_FILE_NAME", length=1000)
    private String origFileName;
    
    /**
     * 文件后缀
     */
    @Column(name = "FILE_SUFFIX", length=10)
    private String fileSuffix;
    
    /**
     * 文件描述
     */
    @Column(name = "FILE_DESC", length=500)
    private String fileDesc;
    
    @Column(name = "CONTENT_TYPE", length=100)
    private String contentType;
    
    /**
     * 文件大小(字节)
     */
    @Column(name = "FILE_SIZE")
    private long fileSize = 0L;

    /**
     * 存储媒介
     */
    @Column(name = "STORAGE_MEDIUM", length=20)
    @Enumerated(EnumType.STRING)
    private StorageMedium storageMedium;
    
    /**
     * 文件ID
     * 本地存储：文件的相对路径
     * KV存储：Key
     * mongodb存储：文件_id
     */
    @Column(name = "FILE_ID", length=1000)
    private String fileId;
    
    /**
     * 上传文件存储的目录
     */
    @Column(name = "UPLOAD_DIR_PATH", length=300)
    private String uploadDirPath;
    
    /**
     * 上传文件访问的二级域名名称
     * SLD=Second-level domain
     * 如：http://upload.okforward.com中的upload，http://upload1.okforward.com中的upload1
     */
    @Column(name = "UPLOAD_FILE_SLD_NAME", length=100)
    private String uploadFileSldName;
    
    /**
     * 所属客户代码
     */
    @Column(name = "CORRELATED_CUSTOMER_CODE", length=20)
    private String correlatedCustomerCode;
    
    /**
     * 公司代码
     */
    @Column(name = "COMPANY_CODE", length=20)
    private String companyCode;
    
    @Override
    public Serializable getId() {
	return getFileMetaId();
    }
    
    public boolean isImage() {
	return ImageUtil.isImage(this.contentType);
    }

    public String getFileMetaId() {
        return fileMetaId;
    }

    public void setFileMetaId(String fileMetaId) {
        this.fileMetaId = fileMetaId;
    }

    public String getOrigFileName() {
        return origFileName;
    }

    public void setOrigFileName(String origFileName) {
        this.origFileName = origFileName;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public StorageMedium getStorageMedium() {
        return storageMedium;
    }

    public void setStorageMedium(StorageMedium storageMedium) {
        this.storageMedium = storageMedium;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getCorrelatedCustomerCode() {
        return correlatedCustomerCode;
    }

    public void setCorrelatedCustomerCode(String correlatedCustomerCode) {
        this.correlatedCustomerCode = correlatedCustomerCode;
    }

    public String getUploadDirPath() {
        return uploadDirPath;
    }

    public void setUploadDirPath(String uploadDirPath) {
        this.uploadDirPath = uploadDirPath;
    }

    public String getUploadFileSldName() {
        return uploadFileSldName;
    }

    public void setUploadFileSldName(String uploadFileSldName) {
        this.uploadFileSldName = uploadFileSldName;
    }
}