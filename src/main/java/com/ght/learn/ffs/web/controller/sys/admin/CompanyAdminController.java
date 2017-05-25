package com.ght.learn.ffs.web.controller.sys.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ght.learn.ffs.dto.sys.CompanyDto;
import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.sys.CompanyRole;
import com.ght.learn.ffs.entity.sys.Role;
import com.ght.learn.ffs.service.sys.CompanyRoleService;
import com.ght.learn.ffs.service.sys.CompanyService;
import com.ght.learn.ffs.service.sys.RoleService;
import com.ght.learn.ffs.tool.context.SessionUserHolder;
import com.ght.learn.ffs.web.controller.FfsController;

import framework.service.ServiceException;

@Controller
@RequestMapping("/sys/admin")
public class CompanyAdminController extends FfsController{
	
	@Resource(name = "CompanyService", type = CompanyService.class)
	private CompanyService companyService;
	
	@Resource(name = "CompanyRoleService", type = CompanyRoleService.class)
	private CompanyRoleService companyRoleService;
	
	@Resource(name = "RoleService", type = RoleService.class)
	private RoleService roleService;
	
	@RequestMapping(value = "/company/companyQueryNoResult.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String companyQueryNoResult(Model model) throws Exception {
		model.addAttribute("listCompany", null);
		return "/sys/admin/company/companyQuery";
	}
	
	@RequestMapping(value = "/company/companyQuery.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String companyQuery(CompanyDto dto, HttpServletRequest request , Model model) throws Exception {
		List<Company> list = this.queryWithPagination(request, op -> companyService.queryCompanys(dto, op));
		model.addAttribute("listCompany", list);
		model.addAttribute("company", dto);
		return "/sys/admin/company/companyQuery";
	}
	@RequestMapping(value = "/company/companyDetail.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String companyDetail(Long recId, Model model) {
		Company company = companyService.getCompanyById(recId);
		model.addAttribute("company", company);
		return "/sys/admin/company/companyDetail";
	}
	
	@RequestMapping(value = "/company/preCompanyCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String preCompanyCreate(Model model) {
		System.out.println(SessionUserHolder.getSessionUser().getCompanyCode()+"******"+SessionUserHolder.getSessionUser().getI18nName());
		List<Role> roles = roleService.queryRoles();
		model.addAttribute("roles", roles);
		return "/sys/admin/company/companyCreate";
	}
	@RequestMapping(value = "/company/companyCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String companyCreate(@RequestParam(value="faviconFile") MultipartFile faviconFile, @RequestParam(value="textLogoBigCnFile") MultipartFile textLogoBigCnFile, 
			@RequestParam(value="textLogoCnFile") MultipartFile textLogoCnFile, @RequestParam(value="textLogoBigEnFile") MultipartFile textLogoBigEnFile, 
			@RequestParam(value="textLogoEnFile") MultipartFile textLogoEnFile,  MultipartFile businessLogoFile, Company dto, Model model) {
		try { 
			Long companyId = companyService.doCreateCompany(dto, SessionUserHolder.getSessionUser(), faviconFile, textLogoBigCnFile, textLogoCnFile, textLogoBigEnFile, textLogoEnFile, businessLogoFile);
			return "redirect:/sys/admin/company/companyDetail.do?recId=" + companyId;
		} catch (ServiceException e) {
			List<Role> roles = roleService.queryRoles();
			model.addAttribute("company", dto);
			model.addAttribute("roles", roles);
		    model.addAttribute("errorMessage", e.getMessage());
			return "/sys/admin/company/companyCreate";
		}
	}
	
	@RequestMapping(value = "/company/preCompanyModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String preCompanyModify(Long recId, Model model) {
		Company company = companyService.getCompanyById(recId);
		List<Role> roles = roleService.queryRoles();
		List<CompanyRole> companyRoles = companyRoleService.queryCompanyRolesByCompanyId(company.getRecId());
		model.addAttribute("roles", roles);
		model.addAttribute("companyRoles", companyRoles);
		model.addAttribute("company", company);
		return "/sys/admin/company/companyModify";
	}
	/**
	 * 修改公司信息
	 * @param faviconFile
	 * @param textLogoBigCnFile
	 * @param textLogoCnFile
	 * @param textLogoBigEnFile
	 * @param textLogoEnFile
	 * @param businessLogoFile
	 * @param company
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/company/companyModify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String companyModify(@RequestParam(value="faviconFile") MultipartFile faviconFile, @RequestParam(value="textLogoBigCnFile") MultipartFile textLogoBigCnFile, 
			@RequestParam(value="textLogoCnFile") MultipartFile textLogoCnFile, @RequestParam(value="textLogoBigEnFile") MultipartFile textLogoBigEnFile, 
			@RequestParam(value="textLogoEnFile") MultipartFile textLogoEnFile,  MultipartFile businessLogoFile, Company company, Model model) {
		try {
			companyService.doUpdateCompany(company, SessionUserHolder.getSessionUser(), faviconFile, textLogoBigCnFile, textLogoCnFile, textLogoBigEnFile, textLogoEnFile, businessLogoFile);
			return "redirect:/sys/admin/company/companyDetail.do?recId=" + company.getRecId();
		} catch (ServiceException e) {
			model.addAttribute("company", company);
		    model.addAttribute("errorMessage", e.getMessage());
			return "/sys/admin/company/companyModify";
		}
	}
	/**
	 * 锁定
	 * @param recId
	 * @return
	 */
	@RequestMapping(value = "/company/companyLocked.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String companyLocked(Long recId) {
		companyService.doLockedCompany(recId, SessionUserHolder.getSessionUser());
		return "redirect:/sys/admin/company/companyDetail.do?recId="+recId;
	}
	/**
	 * 解锁
	 * @param recId
	 * @return
	 */
	@RequestMapping(value = "/company/companyNormal.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String normal(Long recId) {
		companyService.doNormalCompany(recId, SessionUserHolder.getSessionUser());
		return "redirect:/sys/admin/company/companyDetail.do?recId="+recId;
	}

}
