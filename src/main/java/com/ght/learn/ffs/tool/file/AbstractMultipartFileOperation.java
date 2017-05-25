package com.ght.learn.ffs.tool.file;

import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.sys.FileMeta;
import com.ght.learn.ffs.service.base.CompanyQueryService;
import com.ght.learn.ffs.service.base.FileMetaService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.utils.CollectionUtils;
import framework.core.utils.StringUtils;
import framework.exception.FrameworkRuntimeException;
import framework.monitor.error.ThrowableMonitor;

/**
 * 抽象文件上传操作
 * @author ght
 *
 */
public abstract class AbstractMultipartFileOperation implements MultipartFileOperation{

	//注入是在spring-files.xml中
	private CompanyQueryService companyQueryService; 
	
	
	private FileMetaService fileMetaService;
	
	private ThrowableMonitor throwableMonitor;
	@Override
	public String persist(UploadFileItem uploadFileItem, SessionUser sessionUser) {
		if (uploadFileItem == null || !uploadFileItem.isValidUploadFile() || sessionUser == null) {
		    return null;
		}
		Company company=this.companyQueryService.queryCompanyByCompanyCode(sessionUser.getCompanyCode());
		if(company==null){
			return null;
		}
		FileMeta fileMeta=new FileMeta();
		fileMeta.setFileMetaId(createFileMetaId());
		fileMeta.setCompanyCode(sessionUser.getCompanyCode());
		fileMeta.setCorrelatedCustomerCode(sessionUser.getCustomerCode());
		fileMeta.setFileDesc(uploadFileItem.getDescription());
		fileMeta.setFileSize(uploadFileItem.getMultipartFile().getSize());
		fileMeta.setFileSuffix(StringUtils.getFilenameExtension(uploadFileItem.getMultipartFile().getOriginalFilename()));
		fileMeta.setContentType(uploadFileItem.getMultipartFile().getContentType());
		fileMeta.setOrigFileName(uploadFileItem.getMultipartFile().getOriginalFilename());
		fileMeta.setUploadDirPath(company.getUploadDirPath());
		fileMeta.setUploadFileSldName(company.getUploadFileSldName());
		
		fileMeta.setCreator(sessionUser.getUserId());
		fileMeta.setCreateDateTime(this.fileMetaService.getCurrentTime());
		try {
		    persist(fileMeta, uploadFileItem.getMultipartFile(), sessionUser);
		} catch (Throwable e) {
		    this.throwableMonitor.monitor(e);
		    throw new FrameworkRuntimeException(e);
		}
		try {
		    this.fileMetaService.doStore(fileMeta);
		} catch (Throwable e) {
		    this.throwableMonitor.monitor(e);
		    throw new FrameworkRuntimeException(e);
		}
		return fileMeta.getFileMetaId();
	}
	//生成fileMetaId
	protected String createFileMetaId(){
		return UUID.randomUUID().toString();
	}
	protected abstract void persist(FileMeta fileMeta, MultipartFile multipartFile, SessionUser sessionUser) throws FrameworkRuntimeException;
	
	protected abstract boolean delete(FileMeta fileMeta, SessionUser sessionUser) throws FrameworkRuntimeException;
	
	@Override
	public void delete(String fileMetaId, SessionUser operator) throws FrameworkRuntimeException {
		if (StringUtils.isEmpty(fileMetaId) || operator == null) {
		    return;
		}
		FileMeta fileMeta = this.fileMetaService.queryFileMetaByFileMetaId(fileMetaId);
		if (fileMeta == null) {
		    return;
		}
		if (!StringUtils.safeEqualsIgnoreCase(fileMeta.getCompanyCode(), operator.getCompanyCode())) {
		    return;
		}
		this.fileMetaService.doDelete(fileMetaId);
		try {
		    delete(fileMeta, operator);
		} catch (Throwable e) {
		    this.throwableMonitor.monitor(e);
		}
		
	}
	@Override
    public void delete(List<String> fileMetaIds, SessionUser sessionUser) throws FrameworkRuntimeException {
		if (CollectionUtils.isEmpty(fileMetaIds) || sessionUser == null) {
		    return;
		}
		for (String fileMetaId : fileMetaIds) {
		    delete(fileMetaId, sessionUser);
		}
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public CompanyQueryService getCompanyQueryService() {
		return companyQueryService;
	}
	public void setCompanyQueryService(CompanyQueryService companyQueryService) {
		this.companyQueryService = companyQueryService;
	}
	public FileMetaService getFileMetaService() {
		return fileMetaService;
	}
	public void setFileMetaService(FileMetaService fileMetaService) {
		this.fileMetaService = fileMetaService;
	}
	public ThrowableMonitor getThrowableMonitor() {
		return throwableMonitor;
	}
	public void setThrowableMonitor(ThrowableMonitor throwableMonitor) {
		this.throwableMonitor = throwableMonitor;
	}

	
	
	
	
}
