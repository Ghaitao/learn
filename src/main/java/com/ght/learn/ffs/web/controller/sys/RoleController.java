package com.ght.learn.ffs.web.controller.sys;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ght.learn.ffs.dto.sys.RoleDto;
import com.ght.learn.ffs.entity.sys.Role;
import com.ght.learn.ffs.service.sys.RoleMenuService;
import com.ght.learn.ffs.service.sys.RoleService;
import com.ght.learn.ffs.web.controller.FfsController;

import framework.service.ServiceException;

@Controller
@RequestMapping("/sys")
public class RoleController extends FfsController{
	
	@Resource(name = "RoleService", type = RoleService.class)
	private RoleService roleService;
	
	@Resource(name = "RoleMenuService", type = RoleMenuService.class)
	private RoleMenuService roleMenuService;
	
	@RequestMapping(value = "/role/roleQueryNoResult.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String roleQueryNoResult(Model model) throws Exception {
		model.addAttribute("listRoles", null);
		return "/sys/role/roleQuery";
	}
	
	@RequestMapping(value = "/role/roleQuery.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String roleQuery(RoleDto dto, HttpServletRequest request , Model model) throws Exception {
		List<Role> list = this.queryWithPagination(request, op ->roleService.queryRole(dto, op));
		model.addAttribute("listRoles", list);
		model.addAttribute("role", dto);
		return "/sys/role/roleQuery";
	}
	
	@RequestMapping(value = "/role/preRoleModify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String preRoleModify(Long roleId, Model model) {
		model.addAttribute("menus", roleMenuService.queryRoleMenusByRoleId(roleId));
		model.addAttribute("role", roleService.getRoleById(roleId));
		return "/sys/role/roleModify";
	}
	
	@RequestMapping(value = "/role/roleModify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String roleModify(Role dto, Model model) {
		try {
			roleService.doUpdateRole(dto);
			return "redirect:/sys/role/preRoleModify.do?roleId=" + dto.getRecId();
		} catch (ServiceException e) {
			model.addAttribute("role", dto);
			model.addAttribute("errorMessage", e.getMessage());
			return "/sys/role/roleModify";
		}
	}
	
	@RequestMapping(value = "/role/preRoleCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String preRoleCreate() {
		return "/sys/role/roleCreate";
	}
	
	@RequestMapping(value = "/role/roleCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String roleCreate(Role dto) {
		Long roleId = roleService.doCreateRole(dto);
		return "redirect:/sys/role/preRoleModify.do?roleId=" + roleId;
	}
	
	@RequestMapping(value = "/role/roleDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String roleDelete(Long roleId) throws Exception {
		roleService.doDeleteRole(roleId);
		return "redirect:/sys/role/roleQuery.do";
	}
	@RequestMapping("/role/jsTreeDemo.do")
	public String JsTreeDemo(Long roleId, Model model){
		model.addAttribute("menus", roleMenuService.queryRoleMenusByRoleId(roleId));
		model.addAttribute("role", roleService.getRoleById(roleId));
		
		return "/sys/role/JsTreeDemo";
	}

}
