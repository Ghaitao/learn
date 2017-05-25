package com.ght.learn.ffs.web.controller.sys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ght.learn.ffs.entity.sys.Menu;
import com.ght.learn.ffs.service.sys.MenuService;

import framework.bean.mapping.JsonMapper;
import framework.core.utils.CollectionUtils;
import framework.service.ServiceException;

@Controller
@RequestMapping("/sys/menu/")
public class MenuController {
	
	@Resource(name="MenuService",type=MenuService.class)
	private MenuService menuService;
	
	@Resource(name = "Jackson2JsonMapper")
	private JsonMapper jsonMapper;
	
	
	@RequestMapping(value = "/menuQueryNoResult.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String menuQueryNoResult(Model model) throws Exception {
		model.addAttribute("listMenus", null);
		return "/sys/menu/menuQuery";
	}
	
	@RequestMapping(value = "/menuQuery.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String menuQuery(Menu dto, HttpServletRequest request , Model model) throws Exception {
		List<Menu> list = menuService.queryMenusWithoutPagination(dto);
		
		ArrayList<Menu> parents = new ArrayList<Menu>();
		ArrayList<Menu> children = new ArrayList<Menu>();
		for (Menu menu : list) {
		    if (menu.isParent()) {
		    	parents.add(menu);
		    } else {
		    	children.add(menu);
		    }
		}
		ArrayList<Menu> otherChildren = new ArrayList<Menu>();
		otherChildren.addAll(children);
		Collections.sort(parents);
		for (Menu parent : parents) {
		    for (Menu child : otherChildren) {
				if (child.isMyParent(parent.getRecId())) {
				    parent.addChild(child);
				    children.remove(child);
				}
		    }
		    Collections.sort(parent.getChildren());
		}
		
		// 如果查询中只查了子节点 需要如此显示
		if (CollectionUtils.isNotEmpty(children)) {
			for (Menu child : children) {
				parents.add(child);
		    }
		}
		
		model.addAttribute("listMenus", parents);
		model.addAttribute("menu", dto);
		return "/sys/menu/menuQuery";
	}
	@RequestMapping(value = "/menuQueryAll.do", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<Menu> menuQueryAll() throws Exception {
		List<Menu> list = menuService.queryAllMenusWithValid();
		return list;
	}
	
	@RequestMapping(value = "/preMenuCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String preMenuCreate(Long parentId, Model model) {
		Menu dto = new Menu();
		dto.setParentId(parentId);
		model.addAttribute("menu", dto);
		return "/sys/menu/menuCreate";
	}
	
	@RequestMapping(value = "/menuCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String menuCreate(Menu dto, Model model) {
		try {
			Long menuId = menuService.doCreateMenu(dto);
			return "redirect:/sys/menu/preMenuModify.do?menuId=" + menuId;
		} catch (ServiceException e) {
			model.addAttribute("menu", dto);
			model.addAttribute("errorMessage", e.getMessage());
			return "/sys/menu/menuCreate";
		}
	}
	@RequestMapping(value = "/preMenuModify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String preMenuModify(Long menuId, Model model) {
		model.addAttribute("menu", menuService.getMenuById(menuId));
		return "/sys/menu/menuModify";
	}
	
	@RequestMapping(value = "/menuModify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String menuModify(Menu dto, Model model) {
		try {
			menuService.doUpdateMenu(dto);
			return "redirect:/sys/menu/preMenuModify.do?menuId=" + dto.getRecId();
		} catch (ServiceException e) {
			model.addAttribute("menu", dto);
			model.addAttribute("errorMessage", e.getMessage());
			return "/sys/menu/menuModify";
		}
	}
	
	@RequestMapping(value = "/menuDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String menuDelete(Long menuId) {
		menuService.doDeleteMenu(menuId);
		return "redirect:/sys/menu/menuQuery.do";
	}
	
	@RequestMapping(value = "/menuValid.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String menuValid(Long menuId) {
		menuService.doValidMenu(menuId);
		return "redirect:/sys/menu/preMenuModify.do?menuId=" + menuId;
	}
	
	@RequestMapping(value = "/menuInValid.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String menuInValid(Long menuId) {
		menuService.doInvalidMenu(menuId);
		return "redirect:/sys/menu/preMenuModify.do?menuId=" + menuId;
	}
}
