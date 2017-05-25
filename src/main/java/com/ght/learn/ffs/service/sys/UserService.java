package com.ght.learn.ffs.service.sys;

import java.util.List;

import com.ght.learn.ffs.entity.sys.User;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.pagination.OrderablePagination;
import framework.service.CommonService;

public interface UserService extends CommonService{
	/**
	 * 通过userId查询用户
	 * @param userId
	 * @return
	 */
	User getUserById(Long userId);

	/**
     * 添加 或 修改 用户的基本信息
     * @param User
     */
    Long doCreateUser(User dto, SessionUser operator);
    

	/**
	 * 通过userDto查询用户信息
	 * @param UserCode
	 * @return
	 */
	List<User> queryUsers(User dto, OrderablePagination op);
	
    
    /**
     * 添加 或 修改 用户的基本信息
     * @param User
     */
    void doUpdateUser(User dto, SessionUser operator);
    
    /**
     * 通过userId删除用户
     * @param userId
     */
    void doDeleteUser(Long userId);
    
    /**
     * 通过userId解锁用户
     * @param userId
     */
    void doNormalUser(Long userId, SessionUser operator);
    
    /**
     * 通过userId锁定用户
     * @param userId
     */
    void doLockedUser(Long userId, SessionUser operator);
    
    boolean hasExistThisUserCodeCompanyId(String userCode, Long companyId, Long userId);
    
    /**
     * 修改用户的密码
     * @param dto
     */
    void doUpdateUserByPassword(User dto, SessionUser operator);
    
    /**
     * 重置用户的密码
     * @param dto
     */
    void doUpdateUserByPasswordReset(User dto, SessionUser operator);
}
