package com.ght.learn.ffs.web.controller.customer;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ght.learn.ffs.common.LabelValueBeanGenerator;
import com.ght.learn.ffs.dto.customer.CustomerAirlineStockDto;
import com.ght.learn.ffs.entity.customer.Customer;
import com.ght.learn.ffs.entity.customer.CustomerAirlineStockDetail;
import com.ght.learn.ffs.entity.customer.CustomerAirlineStockSegment;
import com.ght.learn.ffs.service.customer.CustomerAirlineStockService;
import com.ght.learn.ffs.service.customer.CustomerService;
import com.ght.learn.ffs.tool.context.SessionUserHolder;
import com.ght.learn.ffs.web.controller.FfsController;

import framework.service.ServiceException;
import framework.web.response.StructuredResponse;

@Controller
@RequestMapping(value = "/customer")
public class CustomerAirlineStockController extends FfsController {

    @Resource(name = "CustomerService", type = CustomerService.class)
    private CustomerService customerService;
    
    @Resource(name = "CustomerAirlineStockService", type = CustomerAirlineStockService.class)
    private CustomerAirlineStockService customerAirlineStockService;

    private void initModelData(Model model) {
		List<Customer> airlines = this.customerService.queryCustomersOfAirline(SessionUserHolder.getSessionUser());
		model.addAttribute("airlines", LabelValueBeanGenerator.convertFromBeanList(airlines, getMessageSource(), new LabelValueBeanGenerator.LabelValue<Customer>() {
			    @Override
			    public String getLabel(Customer t) {
				return t.getCustomerCode() + " / " + t.getI18nName();
			    }
	
			    @Override
			    public String getValue(Customer t) {
				return t.getCustomerCode();
			    }
			}));
    }
    
    /**
     * 查询号段
     */
    @RequestMapping(value = "/queryCustomerAirlineStockSegments.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String queryCustomerAirlineStockSegments(@ModelAttribute(name="customerAirlineStockDto") CustomerAirlineStockDto customerAirlineStockDto, Model model) throws Exception {
		initModelData(model);
		model.addAttribute("customerAirlineStockDto", customerAirlineStockDto);
		return "customer/stock/queryCustomerAirlineStockSegments";
    }
    @RequestMapping(value = "/doQueryCustomerAirlineStockSegments.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String doQueryCustomerAirlineStockSegments(@ModelAttribute(name="customerAirlineStockDto") CustomerAirlineStockDto customerAirlineStockDto, Model model, HttpServletRequest request) throws Exception {
		List<CustomerAirlineStockSegment> customerAirlineStockSegments = queryWithPagination(50, request, 
			op -> this.customerAirlineStockService.queryCustomerAirlineStockSegments(customerAirlineStockDto, op, SessionUserHolder.getSessionUser()));
		model.addAttribute("customerAirlineStockSegments", customerAirlineStockSegments);
		return queryCustomerAirlineStockSegments(customerAirlineStockDto, model);
    }
    
    /**
     * 删除号段
     */
    @RequestMapping(value = "/deleteCustomerAirlineStockSegment.do", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody StructuredResponse<String> deleteCustomerAirlineStockSegment(@RequestParam(value = "airlineStockSegmentId", required = false) Long airlineStockSegmentId) throws Exception {
		try {
		    this.customerAirlineStockService.doDeleteCustomerAirlineStockSegment(airlineStockSegmentId, SessionUserHolder.getSessionUser());
		    return StructuredResponse.success(String.class, "");
		} catch (ServiceException e) {
		    return StructuredResponse.error(String.class, e.getMessage());
		}
    }
    
    /**
     * 查询票证明细
     */
    @RequestMapping(value = "/queryCustomerAirlineStockDetails.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String queryCustomerAirlineStockDetails(@ModelAttribute(name="customerAirlineStockDto") CustomerAirlineStockDto customerAirlineStockDto, Model model) throws Exception {
		initModelData(model);
		model.addAttribute("customerAirlineStockDto", customerAirlineStockDto);
		return "customer/stock/queryCustomerAirlineStockDetails";
    }
    @RequestMapping(value = "/doQueryCustomerAirlineStockDetails.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String doQueryCustomerAirlineStockDetails(@ModelAttribute(name="customerAirlineStockDto") CustomerAirlineStockDto customerAirlineStockDto, Model model, HttpServletRequest request) throws Exception {
		List<CustomerAirlineStockDetail> customerAirlineStockDetails = queryWithPagination(request, 
			op -> this.customerAirlineStockService.queryCustomerAirlineStockDetails(customerAirlineStockDto, op, SessionUserHolder.getSessionUser()));
		model.addAttribute("customerAirlineStockDetails", customerAirlineStockDetails);
		return queryCustomerAirlineStockDetails(customerAirlineStockDto, model);
    }
    
    @RequestMapping(value = "/createCustomerAirlineStockSegment.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String createCustomerAirlineStockSegment(@ModelAttribute(name="customerAirlineStockSegment") CustomerAirlineStockSegment inputCustomerAirlineStockSegment, Model model) throws Exception {
		initModelData(model);
		return "customer/stock/createCustomerAirlineStockSegment";
    }
    @RequestMapping(value = "/doCreateCustomerAirlineStockSegment.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String doCreateCustomerAirlineStockSegment(@ModelAttribute(name="customerAirlineStockSegment") CustomerAirlineStockSegment inputCustomerAirlineStockSegment, Model model) throws Exception {
		initModelData(model);
		try {
		    this.customerAirlineStockService.doCreateCustomerAirlineStockSegment(inputCustomerAirlineStockSegment, SessionUserHolder.getSessionUser());
		    return "redirect:/customer/doQueryCustomerAirlineStockDetails.do?customerCode=" + inputCustomerAirlineStockSegment.getCustomerCode();
		} catch (ServiceException e) {
		    model.addAttribute("errorMessage", e.getMessage());
		    return createCustomerAirlineStockSegment(inputCustomerAirlineStockSegment, model);
		}
    }
    
    @RequestMapping(value = "/getNextAwbNo.do", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody StructuredResponse<String> getNextAwbNo(@RequestParam(value = "customerCode", required = false) String customerCode) throws Exception {
		try {
		    CustomerAirlineStockDetail customerAirlineStockDetail = this.customerAirlineStockService.doGetNextAwbNo(customerCode, SessionUserHolder.getSessionUser());
		    return StructuredResponse.success(String.class, customerAirlineStockDetail.getAwbFullNo());
		} catch (ServiceException e) {
		    return StructuredResponse.error(String.class, e.getMessage());
		}
    }
    
    @RequestMapping(value = "/setAsAvailableCustomerAirlineStockDetail.do", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody StructuredResponse<String> setAsAvailableCustomerAirlineStockDetail(@RequestParam(value = "customerAirlineStockDetailRecId", required = false) Long customerAirlineStockDetailRecId) throws Exception {
		try {
		    this.customerAirlineStockService.doSetAsAvailableCustomerAirlineStockDetail(customerAirlineStockDetailRecId, SessionUserHolder.getSessionUser());
		    return StructuredResponse.success(String.class, "");
		} catch (ServiceException e) {
		    return StructuredResponse.error(String.class, e.getMessage());
		}
    }
    
    @RequestMapping(value = "/setAsUnavailableCustomerAirlineStockDetail.do", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody StructuredResponse<String> setAsUnavailableCustomerAirlineStockDetail(@RequestParam(value = "customerAirlineStockDetailRecId", required = false) Long customerAirlineStockDetailRecId) throws Exception {
		try {
		    this.customerAirlineStockService.doSetAsUnavailableCustomerAirlineStockDetail(customerAirlineStockDetailRecId, SessionUserHolder.getSessionUser());
		    return StructuredResponse.success(String.class, "");
		} catch (ServiceException e) {
		    return StructuredResponse.error(String.class, e.getMessage());
		}
    }
}