package com.ght.learn.ffs.service.sys.support;

import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.entity.sys.User;
import com.ght.learn.ffs.entity.sys.UserRole;
import com.ght.learn.ffs.enums.LockStatus;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.sys.UserService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.Constants;
import framework.core.pagination.OrderablePagination;
import framework.core.utils.ObjectUtils;
import framework.core.utils.StringUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;
import framework.service.ServiceException;


@Service("UserService")
public class UserServiceImpl extends FfsServiceImpl implements UserService {

	/*@Resource(name = "StandardPasswordEncoder", type = PasswordEncoder.class)
	private PasswordEncoder passwordEncoder;//密码加密工具
*/
	@Override
	public User getUserById(Long userId) {
		return this.getEntityManager().get(User.class, userId);
	}

	@Override
	public Long doCreateUser(User dto, SessionUser operator) {
		if (dto == null || StringUtils.isEmpty(dto.getUserCode())) {
			// 判断是否存在相同的userCode 
			throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.user.exception.service.user_code_empty", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		} else if (this.hasExistThisUserCodeCompanyId(dto.getUserCode(), dto.getCompanyId(), dto.getRecId())) {
			// 判断是否存在相同的userCode
			throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.user.exception.service.user_code_exist", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		} else if (dto.getUserPassword() != null && !dto.getUserPassword().equals(dto.getRepeatUserPassword())) {
			// 判断密码与确认密码是否一致
			throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.user.exception.service.password_error", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		} 
		User entity = new User();
		//存储用户信息
		entity.setUserCode(StringUtils.safeToLowerCase(dto.getUserCode()));
		entity.setUserNameCn(dto.getUserNameCn());
		entity.setUserNameEn(dto.getUserNameEn());
		entity.setUserPassword(dto.getUserPassword());//不用加密
		entity.setMobilePhone(dto.getMobilePhone());
		entity.setTelephone(dto.getTelephone());
		entity.setEmail(dto.getEmail());
		entity.setQq(dto.getQq());
		entity.setCompanyId(dto.getCompanyId());
		Company comopany = getEntityManager().get(Company.class, dto.getCompanyId());
		entity.setCompanyCode(comopany.getCompanyCode());
		// 当customeId为空时 默认取company信息
		if (dto.getCustomerId() == null) {
			entity.setCustomerId(dto.getCompanyId());
			entity.setCustomerCode(comopany.getCompanyCode());
		} else {
			entity.setCustomerId(dto.getCustomerId());
			//TODO customer暂时没有
			entity.setCustomerCode(dto.getCustomerCode());
		}
		entity.setStatus(LockStatus.Normal);
		this.fillAuditableEntityOnCreate(entity, operator);
		Long userId = (Long) getEntityManager().save(entity);

		// 保存用户角色
		this.doSaveUserRoles(userId, entity.getCompanyCode(), dto.getRoleIds());
		
		return userId;
	}

	@Override
	public List<User> queryUsers(User dto, OrderablePagination op) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(User.class);
		if (dto != null) {
			sdc.add(SafeRestrictions.equal("userCode", StringUtils.safeToLowerCase(dto.getUserCode())));
			sdc.add(SafeRestrictions.or(SafeRestrictions.likeAnyWhere("userNameCn", dto.getUserName()),
					SafeRestrictions.likeAnyWhere("userNameEn", dto.getUserName())));
			sdc.add(SafeRestrictions.equal("companyId", dto.getCompanyId()));
			sdc.add(SafeRestrictions.equal("companyCode", dto.getCompanyCode()));
			sdc.add(SafeRestrictions.equal("customerCode", dto.getCustomerCode()));
		}
		if (op != null && !ObjectUtils.isEmpty(op.getSorters())) {
		    sdc.addSorters(op.getSorters());
		}
		return this.getEntityManager().queryForListByCriteria(User.class, sdc, op);
	}

	@Override
	public void doUpdateUser(User dto, SessionUser operator) {
		if(dto == null || dto.getRecId() == null) {
			return;
		}
		User entity = this.getEntityManager().get(User.class, dto.getRecId());
		if(entity == null) {
			return;
		}
		//存储用户信息
		entity.setUserNameCn(dto.getUserNameCn());
		entity.setUserNameEn(dto.getUserNameEn());
		entity.setMobilePhone(dto.getMobilePhone());
		entity.setTelephone(dto.getTelephone());
		entity.setEmail(dto.getEmail());
		entity.setQq(dto.getQq());
		this.fillAuditableEntityOnUpdate(entity, operator);
		getEntityManager().update(entity);
		
		// 保存用户角色
		this.doSaveUserRoles(entity.getRecId(), entity.getCompanyCode(), dto.getRoleIds());
		
	}

	@Override
	public void doDeleteUser(Long userId) {
		// 删除userRole表
    	getEntityManager().executeHql("delete UserRole ur where ur.userId = ?", new Object[] {userId});
    	// 删除user表
		getEntityManager().delete(User.class, userId);
		
	}

	@Override
	public void doNormalUser(Long userId, SessionUser operator) {
		
		doUpdateLockStatus(userId, LockStatus.Normal, operator);
	}

	@Override
	public void doLockedUser(Long userId, SessionUser operator) {
		doUpdateLockStatus(userId, LockStatus.Locked, operator);
		
	}

	@Override
	public boolean hasExistThisUserCodeCompanyId(String userCode, Long companyId, Long userId) {
		if (StringUtils.isEmpty(userCode)) {
			return false;
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(User.class);
		sdc.add(SafeRestrictions.equal("userCode", StringUtils.safeToLowerCase(userCode)));
		sdc.add(SafeRestrictions.equal("companyId", companyId));
		if (userId != null) {
			sdc.addCriterions(SafeRestrictions.notEqual("userId", userId));
		}
		return getEntityManager().queryForListByCriteria(User.class, sdc).size() > 0;
	}

	@Override
	public void doUpdateUserByPassword(User dto, SessionUser operator) {
		if(dto == null || dto.getRecId() == null) {
			return;
		}
		User entity = this.getEntityManager().get(User.class, dto.getRecId());
		if(entity == null) {
			return;
		}
		/*if(dto.getUserPassword().isEmpty() || !this.passwordEncoder.matches(StringUtils.safeToTrim(dto.getUserPassword()), entity.getUserPassword())) {
			throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.user.exception.service.original_user_password_error", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		} else if (!dto.getUserPassword().isEmpty() && !dto.getNewUserPassword().equals(dto.getRepeatUserPassword())) {
			throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.user.exception.service.newAndRepeat_user_password_error", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		} else if (this.passwordEncoder.matches(StringUtils.safeToTrim(dto.getUserPassword()), entity.getUserPassword())
				&& dto.getNewUserPassword().equals(dto.getRepeatUserPassword())) {
			entity.setUserPassword(this.passwordEncoder.encode(StringUtils.safeToTrim(dto.getNewUserPassword())));
		}*///有问题,需要改
		if(dto.getUserPassword().isEmpty()) {
			throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.user.exception.service.original_user_password_error", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		} else if (!dto.getUserPassword().isEmpty() && !dto.getNewUserPassword().equals(dto.getRepeatUserPassword())) {
			throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.user.exception.service.newAndRepeat_user_password_error", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		} else if (dto.getNewUserPassword().equals(dto.getRepeatUserPassword())) {
			entity.setUserPassword(dto.getNewUserPassword());
		}
		this.fillAuditableEntityOnUpdate(entity, operator);
		this.getEntityManager().update(entity);
		
	}

	@Override
	public void doUpdateUserByPasswordReset(User dto, SessionUser operator) {
		if(dto == null || dto.getRecId() == null) {
			return;
		}
		User entity = this.getEntityManager().get(User.class, dto.getRecId());
		if(entity == null) {
			return;
		}
		
		if(dto.getNewUserPassword().isEmpty() || dto.getRepeatUserPassword().isEmpty()) {
			throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.user.exception.service.newOrRepeat_user_password_error", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		} else if (dto.getNewUserPassword().equals(dto.getRepeatUserPassword())) {
			//entity.setUserPassword(this.passwordEncoder.encode(StringUtils.safeToTrim(dto.getNewUserPassword())));
			entity.setUserPassword(dto.getNewUserPassword());
		}
		this.fillAuditableEntityOnUpdate(entity, operator);
		this.getEntityManager().update(entity);
		
	}
	
	/*
     * 修改状态
     */
    protected void doUpdateLockStatus(Long userId, LockStatus lockStatus, SessionUser operator) {
		User user = this.getUserById(userId);
		if (user == null) {
		    return;
		}
		// 状态
		user.setStatus(lockStatus);
		// 操作信息
		this.fillAuditableEntityOnUpdate(user, operator);
    }
    
    private void doSaveUserRoles(Long userId, String companyCode, List<Long> roleIds) {
    	// 删除原有记录
    	getEntityManager().executeHql("delete UserRole ur where ur.userId = ?", new Object[] {userId});
    	if (roleIds != null && roleIds.size() > 0) {
	    	for (Long roleId : roleIds) {
	    		UserRole entity = new UserRole();
	    		entity.setRoleId(roleId);
	    		entity.setUserId(userId);
	    		entity.setCompanyCode(companyCode);
	    		getEntityManager().save(entity);
	    	}
    	}
    }

}
