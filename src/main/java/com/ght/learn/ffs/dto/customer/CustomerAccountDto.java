package com.ght.learn.ffs.dto.customer;

import framework.bean.dto.BaseDto;

public class CustomerAccountDto extends BaseDto {

    private static final long serialVersionUID = 2415541822902746874L;

    private String userCode;

    /**
     * 用户密码
     */
    private String userPassword;
    
    private String confirmPassword;

    /**
     * 用户姓名（中文）
     */
    private String userNameCn;

    /**
     * 用户姓名（英文）
     */
    private String userNameEn;
    
    /**
     * 手机
     */
    private String mobilePhone;

    /**
     * 电话
     */
    private String telephone;

    /**
     * Email
     */
    private String email;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUserNameCn() {
        return userNameCn;
    }

    public void setUserNameCn(String userNameCn) {
        this.userNameCn = userNameCn;
    }

    public String getUserNameEn() {
        return userNameEn;
    }

    public void setUserNameEn(String userNameEn) {
        this.userNameEn = userNameEn;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}