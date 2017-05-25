package com.ght.learn.ffs.web.controller.customer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ght.learn.ffs.common.EnumHelper;
import com.ght.learn.ffs.common.LabelValueBeanGenerator;
import com.ght.learn.ffs.dto.customer.CustomerDto;
import com.ght.learn.ffs.dto.customer.CustomerQueryDto;
import com.ght.learn.ffs.entity.customer.Customer;
import com.ght.learn.ffs.entity.customer.CustomerContactor;
import com.ght.learn.ffs.entity.sys.Role;
import com.ght.learn.ffs.entity.sys.User;
import com.ght.learn.ffs.enums.customer.AccountPeriod;
import com.ght.learn.ffs.enums.customer.CustomerType;
import com.ght.learn.ffs.service.base.BasicDataQueryService;
import com.ght.learn.ffs.service.base.UserQueryService;
import com.ght.learn.ffs.service.customer.CustomerService;
import com.ght.learn.ffs.tool.context.CompanyHolder;
import com.ght.learn.ffs.tool.context.SessionUserHolder;
import com.ght.learn.ffs.web.controller.FfsController;
import com.ght.learn.ffs.web.controller.code.CodeController;

import framework.core.Constants;
import framework.core.utils.CollectionUtils;
import framework.core.utils.StringUtils;
import framework.service.ServiceException;


@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends FfsController {
	@Resource(name = "CustomerService", type = CustomerService.class)
    private CustomerService customerService;

    @Resource(name = "UserQueryService", type = UserQueryService.class)
    private UserQueryService userQueryService;
    
    @Resource(name = "BasicDataQueryService", type = BasicDataQueryService.class)
    private BasicDataQueryService basicDataQueryService;

    static final int INIT_CUSTOMER_CONTACTOR_SIZE = 3;
    
    /**
     * 创建客户
     */
    @RequestMapping(value = "/createCustomer.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String createCustomer(Model model) throws Exception {
		initCreateCustomerModelData(model);
		if (!model.containsAttribute("customerDto")) {
		    CustomerDto customerDto = new CustomerDto();
		    customerDto.setContactors(fillEmptyCustomerContactors(null));
		    model.addAttribute("customerDto", customerDto);
		}
		return "customer/createCustomer";
    }
    @RequestMapping(value = "/doCreateCustomerByCompany.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String doCreateCustomerByCompany(CustomerDto customerDto, Model model) throws Exception {
		try {
		    Long customerId = this.customerService.doCreateCustomerByCompany(customerDto, SessionUserHolder.getSessionUser());
		    return "redirect:/customer/customerDetail.do?customerId=" + customerId;
		} catch (ServiceException e) {
		    model.addAttribute("errorMessage", e.getMessage());
		    model.addAttribute("customerDto", customerDto);
		    return apply(model);
		}
    }
    /**
     * 公司人员修改自己客户信息
     */
    @RequestMapping(value = "/modifyCustomerByCompany.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String modifyCustomerByCompany(@RequestParam(value = "customerId", required = false) Long customerId, Model model) throws Exception {
		initCreateCustomerModelData(model);
		CustomerDto customerDto = new CustomerDto();
		Customer customer = this.customerService.queryCustomerById(customerId);
		if (customer != null) {
		    customerDto.setCustomer(customer);
		    List<CustomerContactor> customerContactors = this.customerService.queryCustomerContactorsByCustomerId(customer.getRecId(), SessionUserHolder.getSessionUser());
		    customerDto.setContactors(fillEmptyCustomerContactors(customerContactors));
		}
		model.addAttribute("customerDto", customerDto);
		return "customer/modifyCustomerByCompany";
    }
    @RequestMapping(value = "/doModifyCustomerByCompany.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String doModifyCustomerByCompany(CustomerDto customerDto, Model model) throws Exception {
		try {
		    this.customerService.doModifyCustomerByCompany(customerDto, SessionUserHolder.getSessionUser());
		    return "redirect:/customer/customerDetail.do?customerId=" + customerDto.getCustomer().getRecId();
		} catch (ServiceException e) {
		    model.addAttribute("errorMessage", e.getMessage());
		    model.addAttribute("customerDto", customerDto);
		    initCreateCustomerModelData(model);
		    return "customer/modifyCustomerByCompany";
		}
    }

    /**
     * 客户申请
     */
    @RequestMapping(value = "/apply.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String apply(Model model) throws Exception {
		initApplyModelData(model);
		if (!model.containsAttribute("customerDto")) {
		    CustomerDto customerDto = new CustomerDto();
		    customerDto.setContactors(fillEmptyCustomerContactors(null));
		    model.addAttribute("customerDto", customerDto);
		}
		return "customer/customerApply";
    }
    @RequestMapping(value = "/doCreateApply.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String doCreateApply(@RequestParam(value = "license", required=false) MultipartFile licenseImage, CustomerDto customerDto, 
	    HttpServletRequest request, Model model) throws Exception {
	
		// 校验验证码
		if (!CodeController.isMatchedCaptchaCode(customerDto.getCaptchaCode(), request)) {
		    model.addAttribute("errorMessage", getMessageSource().getMessage("com.jinzhiyi.ffs.message.invliadCaptchaCode", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		    model.addAttribute("customerDto", customerDto);
		    return apply(model);
		}
	
		try {
		    customerDto.setLicenseImage(licenseImage);
		    this.customerService.doApplyCustomer(customerDto, CompanyHolder.getCompany());
		} catch (ServiceException e) {
		    model.addAttribute("errorMessage", e.getMessage());
		    model.addAttribute("customerDto", customerDto);
		    return apply(model);
		}
		return "redirect:/customer/createApplySuccess.do";
    }
    @RequestMapping(value = "/createApplySuccess.do")
    public String createApplySuccess() throws Exception {
    	return "customer/customerApplySuccess";
    }
    /**
     * 查询该公司下的客户申请
     */
    @RequestMapping(value = "/queryCustomerApplys.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String queryCustomerApplys(@ModelAttribute(name="customerQueryDto") CustomerQueryDto customerQueryDto, Model model) throws Exception {
		List<Customer> customers = this.customerService.queryCustomerApplys(customerQueryDto, SessionUserHolder.getSessionUser());
		model.addAttribute("customers", customers);
		model.addAttribute("customerQueryDto", customerQueryDto);
		return "customer/queryCustomerApplys";
    }
    /**
     * 查询该公司下的客户
     */
    @RequestMapping(value = "/queryCustomersForCompany.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String queryCustomersForCompany(@ModelAttribute(name="customerQueryDto") CustomerQueryDto customerQueryDto, Model model) throws Exception {
		model.addAttribute("customerTypeList", EnumHelper.createLabelValueBeanList(CustomerType.class, getMessageSource(), true));
		model.addAttribute("customerQueryDto", customerQueryDto);
		return "customer/queryCustomersForCompany";
    }
    @RequestMapping(value = "/doQueryCustomersForCompany.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String doQueryCustomersForCompany(@ModelAttribute(name="customerQueryDto") CustomerQueryDto customerQueryDto, Model model, HttpServletRequest request) throws Exception {
		List<Customer> customers = queryWithPagination(request, op -> this.customerService.queryCustomersForCompany(customerQueryDto, op, SessionUserHolder.getSessionUser()));
		model.addAttribute("customers", customers);
		return queryCustomersForCompany(customerQueryDto, model);
    }
    /**
     * 客户详细页面
     */
    @RequestMapping("/customerDetail.do")
    public String customerDetail(@RequestParam(value = "customerId", required = false) Long customerId, 
	    @RequestParam(value = "customerCode", required = false) String customerCode, Model model) {
		Customer customer = null;
		if (customerId != null) {
		    customer = this.customerService.queryCustomerById(customerId);
		} else if (StringUtils.hasText(customerCode)) {
		    customer = this.customerService.queryCustomerByCustomerCode(SessionUserHolder.getSessionUser().getCompanyCode(), customerCode);
		}
		
		if (customer == null) {
		    model.addAttribute("errorMessage", getMessageSource().getMessage("com.jinzhiyi.ffs.message.customer.notexists", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		    return "customer/customerDetail";
		}
		model.addAttribute("customer", customer);
		
		//查询联系人
		List<CustomerContactor> contactors = this.customerService.queryCustomerContactorsByCustomerId(customer.getRecId(), SessionUserHolder.getSessionUser());
		model.addAttribute("contactors", contactors);
		
		if (customer.isCustomerSourceApply()) {
		    // 查询客户下的用户
		    List<User> userList = this.userQueryService.queryUsersByCompanyCodeCustomerCode(SessionUserHolder.getSessionUser().getCompanyCode(), customer.getCustomerCode());
		    model.addAttribute("userList", userList);
		}
		return "customer/customerDetail";
    }
    /**
     * 客服通过客户申请
     */
    @RequestMapping(value = "/passCustomerApplyByCustomerService.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String passCustomerApplyByCustomerService(@RequestParam(value = "customerId", required = false) Long customerId, Model model) throws Exception {
		this.customerService.doPassCustomerApplyByCustomerService(customerId, null, SessionUserHolder.getSessionUser());
		return "redirect:/customer/customerDetail.do?customerId=" + customerId;
    }
    /**
     * 财务通过客户申请
     */
    @RequestMapping(value = "/passCustomerApplyByAccountant.do", method = { RequestMethod.POST, RequestMethod.GET })
    public String passCustomerApplyByAccountant(@RequestParam(value = "customerId", required = false) Long customerId, Model model) throws Exception {
		this.customerService.doPassCustomerApplyByAccountant(customerId, null, SessionUserHolder.getSessionUser());
		return "redirect:/customer/customerDetail.do?customerId=" + customerId;
    }
    
    @RequestMapping("/modifyCustomerApplyByCustomerService.do")
    public String modifyCustomerApplyByCustomerService(@RequestParam(value = "customerId", required = false) Long customerId, Model model) {
		Customer customer = this.customerService.queryCustomerById(customerId);
		model.addAttribute("customer", customer);
		return "customer/modifyCustomerApplyByCustomerService";
    }
    
    @RequestMapping("/doModifyCustomerApplyByCustomerService.do")
    public String doModifyCustomerApplyByCustomerService(@ModelAttribute(name="customer") Customer inputCustomer, Model model) {
		this.customerService.doModifyCustomerApplyByCustomerService(inputCustomer, SessionUserHolder.getSessionUser());
		return "redirect:/customer/customerDetail.do?customerId=" + inputCustomer.getRecId();
    }
    
    @RequestMapping("/modifyCustomerApplyByAccountant.do")
    public String modifyCustomerApplyByAccountant(@RequestParam(value = "customerId", required = false) Long customerId, Model model) {
		Customer customer = this.customerService.queryCustomerById(customerId);
		model.addAttribute("customer", customer);
		model.addAttribute("accountPeriodList", EnumHelper.createLabelValueBeanList(AccountPeriod.class, getMessageSource(), true));
		return "customer/modifyCustomerApplyByAccountant";
    }
    
    @RequestMapping("/doModifyCustomerApplyByAccountant.do")
	public String doModifyCustomerApplyByAccountant(Customer customer, Model model) {
		this.customerService.doModifyCustomerApplyByAccountant(customer, SessionUserHolder.getSessionUser());
		return "redirect:/customer/customerDetail.do?customerId=" + customer.getRecId();
    }
    /**
     * 客户修改自己的信息
     */
    @RequestMapping("/modifyCustomerByCustomer.do")
    public String modifyCustomerByCustomer(@RequestParam(value = "customerId", required = false) Long customerId, Model model) {
		CustomerDto customerDto = new CustomerDto();
		
		Customer customer = this.customerService.queryCustomerById(customerId);
		customerDto.setCustomer(customer);
		
		List<CustomerContactor> customerContactors = this.customerService.queryCustomerContactorsByCustomerId(customer.getRecId(), SessionUserHolder.getSessionUser());
		customerDto.setContactors(fillEmptyCustomerContactors(customerContactors));
		
		model.addAttribute("customerDto", customerDto);
		initUpdateCustomerModelData(model);
		return "customer/modifyCustomerByCustomer";
    }
    @RequestMapping("/doModifyCustomerByCustomer.do")
    public String doModifyCustomerByCustomer(@RequestParam(value = "license", required=false) MultipartFile licenseImage, 
	    @ModelAttribute(name="customerDto") CustomerDto customerDto, Model model) {
		customerDto.setLicenseImage(licenseImage);
		this.customerService.doModifyCustomerByCustomer(customerDto, SessionUserHolder.getSessionUser());
		return "redirect:/sys/userPerson/userPersonDetail.do?recId=" + SessionUserHolder.getSessionUser().getUserId();
	//	return "redirect:/customer/modifyCustomerByCustomer.do?customerId=" + customerDto.getCustomer().getRecId();
    }
    /**
     * 临时调整剩余信用额度
     */
    @RequestMapping("/adjustCustomerRemainingCreditLimit.do")
    public String adjustCustomerRemainingCreditLimit(@RequestParam(value = "customerId", required = false) Long customerId, Model model) {
		Customer customer = this.customerService.queryCustomerById(customerId);
		model.addAttribute("customer", customer);
		return "customer/adjustCustomerRemainingCreditLimit";
    }
    
    @RequestMapping("/doAdjustCustomerRemainingCreditLimit.do")
    public String doAdjustmentCustomerRemainingCreditLimit(Customer customer, Model model) {
		this.customerService.doAdjustCustomerRemainingCreditLimit(customer, SessionUserHolder.getSessionUser());
		return "redirect:/customer/customerDetail.do?customerId=" + customer.getRecId();
    }
    /**
     * 删除客户
     * @param customerId
     * @param model
     * @return
     */
    @RequestMapping("/deleteCustomer.do")
    public String deleteCustomer(@RequestParam(value = "customerId", required = false) Long customerId, Model model) {
		this.customerService.doDeleteCustomer(customerId, SessionUserHolder.getSessionUser());
		return "redirect:/customer/queryCustomersForCompany.do";
    }
    private List<CustomerContactor> fillEmptyCustomerContactors(List<CustomerContactor> existsCustomerContactors) {
    	int emptySize = INIT_CUSTOMER_CONTACTOR_SIZE;
    	if (CollectionUtils.isNotEmpty(existsCustomerContactors)) {
    	    emptySize = emptySize - existsCustomerContactors.size();
    	}
    	if (emptySize < 1) {
    	    return existsCustomerContactors;
    	}
    	ArrayList<CustomerContactor> customerContactors = new ArrayList<>(INIT_CUSTOMER_CONTACTOR_SIZE);
    	if (CollectionUtils.isNotEmpty(existsCustomerContactors)) {
    	    customerContactors.addAll(existsCustomerContactors);
    	}
    	for (int i = 0; i < emptySize; i ++) {
    	    customerContactors.add(new CustomerContactor());
    	}
    	return customerContactors;
    }
    private void initCreateCustomerModelData(Model model) {
    	model.addAttribute("customerTypeList", EnumHelper.createLabelValueBeanList(CustomerType.class, getMessageSource(), true));
    }
    private void initUpdateCustomerModelData(Model model) {
    	model.addAttribute("customerTypeList", EnumHelper.createLabelValueBeanList(CustomerType.getNeedApplyCustomerTypes(), getMessageSource(), true));
//     	model.addAttribute("genderList", EnumHelper.createLabelValueBeanList(Gender.class, getMessageSource(), true));
    //
//     	List<DictionaryData> positionList = this.basicDataQueryService.queryDictionaryDatas(DictionaryData.PARENT_CODE_POSITION, SessionUserHolder.getSessionUser());
//     	model.addAttribute("positionList", LabelValueBeanGenerator.convertFromBeanList(positionList, getMessageSource(), new LabelValueBeanGenerator.LabelValue<DictionaryData>() {
//     		    @Override
//     		    public String getLabel(DictionaryData t) {
//     			return t.getI18nName();
//     		    }
    //
//     		    @Override
//     		    public String getValue(DictionaryData t) {
//     			return t.getI18nName();
//     		    }
//     		}));
         }
    private void initApplyModelData(Model model) {
     	initUpdateCustomerModelData(model);

     	List<User> customerServiceUsers = this.userQueryService.queryUsersByCompanyCodeRoleId(CompanyHolder.getCompanyCode(), Role.CUSTOMER_SERVICE);
     	model.addAttribute("customerServiceUsers", LabelValueBeanGenerator.convertFromBeanList(customerServiceUsers, getMessageSource(), new LabelValueBeanGenerator.LabelValue<User>() {
     		    @Override
     		    public String getLabel(User t) {
     			return t.getI18nName();
     		    }

     		    @Override
     		    public String getValue(User t) {
     			return t.getRecId().toString();
     		    }
     		}));
    }
}
