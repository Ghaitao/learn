package com.ght.learn.ffs.web.controller.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ght.learn.ffs.entity.customer.Customer;
import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.sys.User;
import com.ght.learn.ffs.service.customer.CustomerService;
import com.ght.learn.ffs.service.sys.CompanyService;
import com.ght.learn.ffs.service.sys.UserService;
import com.ght.learn.ffs.tool.context.SessionUserHolder;
import com.ght.learn.ffs.web.controller.FfsController;

import framework.service.ServiceException;

@Controller
@RequestMapping(value = "/sys")
public class PersonController extends FfsController{

	@Resource(name = "UserService", type = UserService.class)
	private UserService userService;
	
	@Resource(name = "CompanyService", type = CompanyService.class)
	private CompanyService companyService;
	
	@Resource(name = "CustomerService", type = CustomerService.class)
	private CustomerService customerService;
	
	@RequestMapping(value = "/userPerson/userPersonDetail.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userPersonDetail(Long recId, Model model) throws Exception {
		User user = userService.getUserById(recId);
//		Company company = companyService.getCompanyById(SessionUserHolder.getSessionUser().getCompanyId());
//		Customer customer = customerService.queryCustomerById(SessionUserHolder.getSessionUser().getCustomerId());
		Company company = companyService.queryCompanyByCompanyCode(SessionUserHolder.getSessionUser().getCompanyCode());
		Customer customer = customerService.queryCustomerByCustomerCode(SessionUserHolder.getSessionUser().getCompanyCode(), SessionUserHolder.getSessionUser().getCustomerCode());
		model.addAttribute("user", user);
		model.addAttribute("company", company);
		model.addAttribute("customer", customer);
		return "/sys/userPerson/userPersonDetail";
	}
	@RequestMapping(value = "/userPerson/preUserPersonModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String preUserPersonModify(Long recId, Model model) throws Exception {
		try {
			User user = userService.getUserById(recId);
			model.addAttribute("user", user);
		} catch (ServiceException e) {
			// 存在serviceException时， 返回修改页面
		    model.addAttribute("errorMessage", e.getMessage());
		}
		return "sys/userPerson/userPersonModify";
	}
	
	@RequestMapping(value = "/userPerson/userPersonModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userPersonModify(User dto, Model model) throws Exception {
		try {
			userService.doUpdateUser(dto, SessionUserHolder.getSessionUser());
			//保存成功 返回详细页面
			return "redirect:/sys/userPerson/userPersonDetail.do?recId=" + dto.getRecId();
		} catch (ServiceException e) {
			// 存在serviceException时， 返回修改页面
			model.addAttribute("user", dto);
		    model.addAttribute("errorMessage", e.getMessage());
			return "sys/userPerson/userPersonModify";
		}
	}
	
	@RequestMapping(value = "/userPerson/prePasswordModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String prePasswordModify(Long recId, Model model) throws Exception {
		try {
			User user = userService.getUserById(recId);
			model.addAttribute("user", user);
		} catch (ServiceException e) {
			// 存在serviceException时， 返回修改页面
		    model.addAttribute("errorMessage", e.getMessage());
		}
		return "sys/userPerson/passwordModify";
	}
	
	@RequestMapping(value = "/userPerson/passwordModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String passwordModify(User dto, Model model) throws Exception {
		try {
			userService.doUpdateUserByPassword(dto, SessionUserHolder.getSessionUser());
			//修改密码成功 返回详细页面
			return "redirect:/sys/userPerson/userPersonDetail.do?recId=" + dto.getRecId();
		} catch (ServiceException e) {
			// 存在serviceException时， 返回修改页面
		    model.addAttribute("errorMessage", e.getMessage());
			return "sys/userPerson/passwordModify";
		}
	}
	
	@RequestMapping(value = "/userPerson/preResetPasswordModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String preResetPasswordModify(Long recId, String flag, Model model) throws Exception {
		try {
			User user = userService.getUserById(recId);
			model.addAttribute("user", user);
			model.addAttribute("flag", flag);
		} catch (ServiceException e) {
			// 存在serviceException时， 返回修改页面
		    model.addAttribute("errorMessage", e.getMessage());
		}
		return "sys/userPerson/resetPasswordModify";
	}
	
	@RequestMapping(value = "/userPerson/resetPasswordModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String resetPasswordModify(User dto, String flag, Model model) throws Exception {
		try {
			userService.doUpdateUserByPasswordReset(dto, SessionUserHolder.getSessionUser());
			//重置密码成功 返回详细页面
			if(flag.equals("admin")) {
				return "redirect:/sys/admin/user/userDetail.do?recId=" + dto.getRecId();
			}
			return "redirect:/sys/admin/userCompany/userCompanyDetail.do?recId=" + dto.getRecId();
		} catch (ServiceException e) {
			// 存在serviceException时， 返回修改页面
		    model.addAttribute("errorMessage", e.getMessage());
			return "sys/userPerson/passwordModify";
		}
	}
	
	@RequestMapping(value = "/userPerson/companyByUserPersonModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String companyByUserPersonModify(Company company, Model model) {
		try {
			companyService.doUpdateCompanyByUserPerson(company, SessionUserHolder.getSessionUser());
			return "redirect:/sys/userPerson/userPersonDetail.do?recId=" + SessionUserHolder.getSessionUser().getUserId();
		} catch (ServiceException e) {
			model.addAttribute("company", company);
		    model.addAttribute("errorMessage", e.getMessage());
		    return "sys/userPerson/companyByUserPersonModify";
		}
	}
	
	@RequestMapping(value = "/userPerson/preCompanyByUserPersonModify.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String preCompanyByUserPersonModify(Long recId, Model model) {
		Company company = companyService.getCompanyById(recId);
		model.addAttribute("company", company);
		return "sys/userPerson/companyByUserPersonModify";
	}
	
	
	
}
