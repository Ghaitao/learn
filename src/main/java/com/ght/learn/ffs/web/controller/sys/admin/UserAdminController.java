package com.ght.learn.ffs.web.controller.sys.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ght.learn.ffs.entity.sys.CompanyRole;
import com.ght.learn.ffs.entity.sys.User;
import com.ght.learn.ffs.entity.sys.UserRole;
import com.ght.learn.ffs.service.sys.CompanyRoleService;
import com.ght.learn.ffs.service.sys.UserRoleService;
import com.ght.learn.ffs.service.sys.UserService;
import com.ght.learn.ffs.tool.context.SessionUserHolder;
import com.ght.learn.ffs.web.controller.FfsController;

import framework.service.ServiceException;

@Controller
@RequestMapping(value = "/sys/admin")
public class UserAdminController extends FfsController {
	
	@Resource(name = "UserService", type = UserService.class)
	private UserService userService;
	
	@Resource(name = "CompanyRoleService", type = CompanyRoleService.class)
	private CompanyRoleService companyRoleService;
	
	@Resource(name = "UserRoleService", type = UserRoleService.class)
	private UserRoleService userRoleService;
	
	@RequestMapping(value = "/user/userQueryNoResult.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userQueryNoResult(Model model) throws Exception {
		model.addAttribute("result", null);
		return "sys/admin/user/userQuery";
	}
	@RequestMapping(value = "/user/userQuery.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userQuery(@ModelAttribute("queryDto") User queryDto, HttpServletRequest request, Model model)
			throws Exception {
		List<?> result = queryWithPagination(request, op -> userService.queryUsers(queryDto, op));
		model.addAttribute("result", result);
		return "sys/admin/user/userQuery";
	}
	@RequestMapping(value = "/user/userDetail.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userDetail(Long recId, Model model) throws Exception {
		User dto = userService.getUserById(recId);
		model.addAttribute("dto", dto);
		return "sys/admin/user/userDetail";
	}
	
	@RequestMapping(value = "/user/preUserModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String preUserModify(Long recId, Model model) throws Exception {
		try {
			User user = userService.getUserById(recId);
			List<CompanyRole> roles = companyRoleService.queryCompanyRolesByCompanyId(user.getCompanyId());
			List<UserRole> userRoles = userRoleService.queryUserRolesByUserId(recId);
			model.addAttribute("user", user);
			model.addAttribute("roles", roles);
			model.addAttribute("userRoles", userRoles);
		} catch (ServiceException e) {
			// 存在serviceException时， 返回修改页面
		    model.addAttribute("errorMessage", e.getMessage());
		}
		return "sys/admin/user/userModify";
	}
	
	@RequestMapping(value = "/user/userModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userModify(User dto, Model model) throws Exception {
		try {
			userService.doUpdateUser(dto, SessionUserHolder.getSessionUser());
			//保存成功 返回详细页面
			return "redirect:/sys/admin/user/userDetail.do?recId=" + dto.getRecId();
		} catch (ServiceException e) {
			// 存在serviceException时， 返回修改页面
			model.addAttribute("user", dto);
		    model.addAttribute("errorMessage", e.getMessage());
			return "sys/admin/user/userModify";
		}
	}

	@RequestMapping(value = "/user/userDelete.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userDelete(Long recId, Model model) throws Exception {
		userService.doDeleteUser(recId);
		return "redirect:/sys/admin/user/userQuery.do";
	}

	@RequestMapping(value = "/user/userNormal.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userNormal(Long recId) throws Exception {
		userService.doNormalUser(recId, SessionUserHolder.getSessionUser());
		return "redirect:/sys/admin/user/userDetail.do?recId=" + recId;
	}

	@RequestMapping(value = "/user/userLocked.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userLocked(Long recId) throws Exception {
		userService.doLockedUser(recId, SessionUserHolder.getSessionUser());
		return "redirect:/sys/admin/user/userDetail.do?recId=" + recId;
	}
}
