package com.ght.learn.ffs.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ght.learn.ffs.entity.sys.Menu;
import com.ght.learn.ffs.service.base.NewsQueryService;
import com.ght.learn.ffs.tool.context.CompanyHolder;
import com.ght.learn.ffs.tool.context.SessionUser;
import com.ght.learn.ffs.tool.context.SessionUserHolder;
import com.ght.learn.ffs.tool.permission.AuthorizationCenter;
import com.ght.learn.ffs.web.filter.SessionUserFilter;

import framework.service.ServiceException;

@Controller
public class AuthorizationController extends FfsController{
	@Resource(name = "AuthorizationCenter", type = AuthorizationCenter.class)
	private AuthorizationCenter authorizationCenter;
	
	@Resource(name = "NewsQueryService", type = NewsQueryService.class)
	private NewsQueryService newsQueryService;
	
	@RequestMapping(value="/login.do",method={RequestMethod.POST,RequestMethod.GET})
	public String login(@RequestParam(value="userCode", required=false) String userCode, 
		    @RequestParam(value="userPassword", required=false) String userPassword,HttpServletRequest request,HttpServletResponse response, Model model){
		HttpSession session=request.getSession();
		if(session!=null&&session.getAttribute(SessionUserFilter.USER_SESSION_ID)!=null){
			return "redirect:/console.do";
		}
		if(StringUtils.isEmpty(userCode)&&StringUtils.isEmpty(userPassword)){
			return "login";
		}
		
		SessionUser sessionUser=null;
		try {
		    sessionUser = this.authorizationCenter.login(CompanyHolder.getCompanyCode(), userCode, userPassword);
		} catch (ServiceException e) {
		    model.addAttribute("userCode", userCode);
		    model.addAttribute("errorMessage", e.getMessage());
		    return "login";
		}
		session=request.getSession(true);
		session.setAttribute(SessionUserFilter.USER_SESSION_ID, sessionUser);
		List<Menu> menus = this.authorizationCenter.queryVisibleMenusByUserId(sessionUser.getUserId());
		session.setAttribute(SessionUserFilter.MENU_SESSION_ID, menus);
		return "redirect:/console.do";
	}
	 @RequestMapping(value = "/logout.do", method = { RequestMethod.POST, RequestMethod.GET })
	 public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session != null) {
		    try {
			session.invalidate();
		    } catch (IllegalStateException e) {
			
		    }
		}
		return "redirect:/login.do";
	}
	@RequestMapping(value = "/console.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String console(Model model) throws Exception {
		model.addAttribute("recentNews", this.newsQueryService.queryRecentNews(SessionUserHolder.getSessionUser()));
		 //model.addAttribute("recentNews", null);
		return "console";
	}
}
