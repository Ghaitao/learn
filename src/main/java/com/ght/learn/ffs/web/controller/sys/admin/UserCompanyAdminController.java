package com.ght.learn.ffs.web.controller.sys.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.sys.CompanyRole;
import com.ght.learn.ffs.entity.sys.User;
import com.ght.learn.ffs.entity.sys.UserRole;
import com.ght.learn.ffs.service.sys.CompanyRoleService;
import com.ght.learn.ffs.service.sys.CompanyService;
import com.ght.learn.ffs.service.sys.UserRoleService;
import com.ght.learn.ffs.service.sys.UserService;
import com.ght.learn.ffs.tool.context.SessionUserHolder;
import com.ght.learn.ffs.web.controller.FfsController;

import framework.core.Constants;
import framework.service.ServiceException;

@Controller
@RequestMapping(value = "/sys/admin")
public class UserCompanyAdminController extends FfsController{

	@Resource(name = "UserService", type = UserService.class)
	private UserService service;
	
	@Resource(name = "CompanyService", type = CompanyService.class)
	private CompanyService companyService;
	
	@Resource(name = "CompanyRoleService", type = CompanyRoleService.class)
	private CompanyRoleService companyRoleService;

	@Resource(name = "UserRoleService", type = UserRoleService.class)
	private UserRoleService userRoleService;
	
	@RequestMapping(value="/userCompany/userCompanyQuery.do",method={RequestMethod.GET,RequestMethod.POST})
	public String userQuery(@ModelAttribute("queryDto") User queryDto,HttpServletRequest request,Model model) throws Exception{
		//System.out.println("是否显示::::"+EnumHelper.getEnumResourceMessage("com.ght.learn.ffs.enums.LockStatus", "Normal", this.getMessageSource()));
		List<?> result=queryWithPagination(request, op -> service.queryUsers(queryDto, op));
		Company company=companyService.getCompanyById(queryDto.getCompanyId());
		model.addAttribute("company", company);
		model.addAttribute("result", result);
		return "sys/admin/userCompany/userCompanyQuery";
	}
	
	@RequestMapping(value = "/userCompany/userCompanyDetail.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userDetail(Long recId, Model model){
		User dto = service.getUserById(recId);
		model.addAttribute("dto", dto);
		return "sys/admin/userCompany/userCompanyDetail";
	}
	
	@RequestMapping(value = "/userCompany/preUserCompanyCreate.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String preUserCreate(User dto, Model model) throws Exception {
		if (dto == null || dto.getCompanyId() == null) {
		    model.addAttribute("errorMessage", getMessageSource().getMessage("com.jinzhiyi.ffs.message.user.exception.service.company_code_empty", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		    return "sys/admin/userCompany/userCompanyCreate"; 
		}
		List<CompanyRole> roles = companyRoleService.queryCompanyRolesByCompanyId(dto.getCompanyId());
		model.addAttribute("user", dto);
		model.addAttribute("roles", roles);
		return "sys/admin/userCompany/userCompanyCreate";
	}
	@RequestMapping(value = "/userCompany/userCompanyCreate.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userCreate(User dto, Model model) throws Exception {
		try {
			Long userId = service.doCreateUser(dto, SessionUserHolder.getSessionUser());
			//保存成功 返回详细页面
			return "redirect:/sys/admin/userCompany/userCompanyDetail.do?recId=" + userId;
		} catch (ServiceException e) {
			// 存在serviceException时， 返回修改页面
			List<CompanyRole> roles = companyRoleService.queryCompanyRolesByCompanyId(dto.getCompanyId());
			model.addAttribute("user", dto);
			model.addAttribute("roles", roles);
		    model.addAttribute("errorMessage", e.getMessage());
			return "sys/admin/userCompany/userCompanyCreate";
		}
	}
	@RequestMapping(value = "/userCompany/preUserCompanyModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String preUserModify(Long recId, Model model) throws Exception {
		try {
			User user = service.getUserById(recId);
			List<CompanyRole> roles = companyRoleService.queryCompanyRolesByCompanyId(user.getCompanyId());
			List<UserRole> userRoles = userRoleService.queryUserRolesByUserId(recId);
			model.addAttribute("user", user);
			model.addAttribute("roles", roles);
			model.addAttribute("userRoles", userRoles);
		} catch (ServiceException e) {
			// 存在serviceException时， 返回修改页面
		    model.addAttribute("errorMessage", e.getMessage());
		}
		return "sys/admin/userCompany/userCompanyModify";
	}
	@RequestMapping(value = "/userCompany/userCompanyModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userModify(User dto, Model model) throws Exception {
		try {
			service.doUpdateUser(dto, SessionUserHolder.getSessionUser());
			//保存成功 返回详细页面
			return "redirect:/sys/admin/userCompany/userCompanyDetail.do?recId=" + dto.getRecId();
		} catch (ServiceException e) {
			// 存在serviceException时， 返回修改页面
			List<CompanyRole> roles = companyRoleService.queryCompanyRolesByCompanyId(dto.getCompanyId());
			model.addAttribute("user", dto);
			model.addAttribute("roles", roles);
		    model.addAttribute("errorMessage", e.getMessage());
			return "sys/admin/userCompany/userCompanyModify";
		}
	}
	
	@RequestMapping(value = "/userCompany/userCompanyDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String userCompanyDelete(Long recId) {
		service.doDeleteUser(recId);;
		return "redirect:/sys/admin/userCompany/userCompanyQuery.do";
	}
	
	@RequestMapping(value = "/userCompany/userCompanyNormal.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userNormal(Long recId) throws Exception {
		service.doNormalUser(recId, SessionUserHolder.getSessionUser());
		return "redirect:/sys/admin/userCompany/userCompanyDetail.do?recId=" + recId;
	}

	@RequestMapping(value = "/userCompany/userCompanyLocked.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userLocked(Long recId) throws Exception {
		service.doLockedUser(recId, SessionUserHolder.getSessionUser());
		return "redirect:/sys/admin/userCompany/userCompanyDetail.do?recId=" + recId;
	}
	
	
}
