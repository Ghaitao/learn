package com.ght.learn.ffs.web.controller.customer;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ght.learn.ffs.common.EnumHelper;
import com.ght.learn.ffs.dto.customer.CustomerQueryDto;
import com.ght.learn.ffs.enums.customer.CustomerType;
import com.ght.learn.ffs.service.customer.CustomerService;
import com.ght.learn.ffs.tool.context.SessionUserHolder;
import com.ght.learn.ffs.web.controller.FfsController;

import framework.web.pagination.AjaxableBootstrap3WebPagination;

@Controller
@RequestMapping(value="/customer")
public class CustomerProviderController extends FfsController{

	@Resource(name = "CustomerService", type = CustomerService.class)
	private CustomerService service;

    @RequestMapping(value = "/customer/customerShowDialog.do", method = { RequestMethod.GET, RequestMethod.POST })
    public String customerShowDialog(@ModelAttribute("queryDto") CustomerQueryDto queryDto, HttpServletRequest request, Model model) throws Exception {
		List<?> result = super.queryWithPagination(AjaxableBootstrap3WebPagination.class, 10, request,
			op -> this.service.queryCustomersForCompany(queryDto, op, SessionUserHolder.getSessionUser()));
		model.addAttribute("result", result);
		model.addAttribute("customerTypeList", EnumHelper.createLabelValueBeanList(CustomerType.class, getMessageSource(), true));
		return "/customer/customerShowDialog";
    }

    @RequestMapping(value = "/customer/customerAutocomplete.do", method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody List<?> customerAutocomplete(String customerCode) throws Exception {
		CustomerQueryDto queryDto = new CustomerQueryDto();
		queryDto.setCustomerCode(customerCode);
		List<?> result = this.service.queryCustomersForCompany(queryDto, null, SessionUserHolder.getSessionUser());
		return result;
    }
	
}
