package com.ght.learn.ffs.service.base;

import java.util.List;

import com.ght.learn.ffs.entity.sys.User;
import com.ght.learn.ffs.service.FfsService;

public interface UserQueryService extends FfsService{

	 User queryUserByUserCode(String companyCode, String userCode);
	 User queryUserByUserId(Long userId);
	
	 List<User> queryUsersByCompanyCodeCustomerCode(String companyCode, String customerCode);
	    
	 List<User> queryUsersByCompanyCodeRoleId(String companyCode, Long roleId);
}
