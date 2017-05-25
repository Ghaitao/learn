package com.ght.learn.ffs.dto.customer;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ght.learn.ffs.entity.customer.Customer;
import com.ght.learn.ffs.entity.customer.CustomerContactor;

import framework.bean.dto.BaseDto;

public class CustomerDto extends BaseDto {

    private static final long serialVersionUID = -1504499346638831998L;

    private Customer customer;
    
    private MultipartFile licenseImage;
    
    private CustomerAccountDto account;
    
    private List<CustomerContactor> contactors;

    private String captchaCode;
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerAccountDto getAccount() {
        return account;
    }

    public void setAccount(CustomerAccountDto account) {
        this.account = account;
    }

    public List<CustomerContactor> getContactors() {
        return contactors;
    }

    public void setContactors(List<CustomerContactor> contactors) {
        this.contactors = contactors;
    }

    public MultipartFile getLicenseImage() {
        return licenseImage;
    }

    public void setLicenseImage(MultipartFile licenseImage) {
        this.licenseImage = licenseImage;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }
}