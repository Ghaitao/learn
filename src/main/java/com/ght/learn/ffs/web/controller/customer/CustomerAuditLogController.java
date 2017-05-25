package com.ght.learn.ffs.web.controller.customer;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ght.learn.ffs.service.customer.CustomerAuditService;
import com.ght.learn.ffs.tool.context.SessionUserHolder;

@Controller
@RequestMapping(value = "/customer")
public class CustomerAuditLogController {
    
    @Resource(name = "CustomerAuditService", type = CustomerAuditService.class)
    private CustomerAuditService customerAuditService;
    
    @RequestMapping(value = "/customerAuditLogs.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String customerAuditLogs(@RequestParam(value="customerId", required=false) Long customerId, Model model) throws Exception {
		model.addAttribute("customerAuditLogs", this.customerAuditService.queryCustomerAuditLogsByCustomerId(customerId, SessionUserHolder.getSessionUser()));
		return "customer/customerAuditLogs";
    }
    
}