package com.ght.learn.ffs.service.sys;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.tool.context.SessionUser;
import com.ght.learn.ffs.dto.sys.CompanyDto;

import framework.core.pagination.OrderablePagination;
import framework.service.CommonService;

public interface CompanyService extends CommonService {

	/**
	 * 通过recId查询一级代理公司
	 * @param companyCode
	 * @return
	 */
	Company getCompanyById(Long recId);
	
	/**
	 * 通过companyCode查询
	 * @param companyCode
	 * @return
	 */
	Company queryCompanyByCompanyCode(String companyCode);
	
	/**
	 * 按条件查询一级代理公司（分页）
	 * @param companyCode
	 * @param nameCn
	 * @return
	 */
	List<Company> queryCompanys(CompanyDto dto, OrderablePagination op);
    
    /**
     * 添加一级代理公司的基本信息
     * @param dto
     * @return
     */
    Long doCreateCompany(Company dto, SessionUser operator, MultipartFile faviconFile, MultipartFile textLogoBigCnFile, MultipartFile textLogoCnFile, 
			MultipartFile textLogoBigEnFile, MultipartFile textLogoEnFile, MultipartFile businessLogoFile); 
    
    /**
     * 修改一级代理公司的基本信息
     * @param dto
     */
    void doUpdateCompany(Company dto, SessionUser operator, MultipartFile faviconFile, MultipartFile textLogoBigCnFile, MultipartFile textLogoCnFile, 
			MultipartFile textLogoBigEnFile, MultipartFile textLogoEnFile,  MultipartFile businessLogoFile);
    
    void doUpdateCompanyByUserPerson(Company dto, SessionUser operator);
    /**
     * 通过recId删除一级代理公司
     * @param companyCode
     */
    void doDeleteCompany(Long recId);
    
    /**
     * 通过recId解锁一级代理公司
     * @param recId
     */
    void doNormalCompany(Long recId, SessionUser operator);
    
    /**
     * 通过recId锁定一级代理公司
     * @param recId
     */
    void doLockedCompany(Long recId, SessionUser operator);
	
    /**
     * 保存一级代理公司的角色
     * @param companyId
     * @param companyCode
     * @param roleIds
     */
    void doSaveCompanyRoles(Long companyId, String companyCode, List<Long> roleIds);
    
    /**
     * 判断是否存在相同的companyCode
     * @param companyCode
     * @return
     */
    boolean hasExistThisCompanyCode(String companyCode);
   
}
