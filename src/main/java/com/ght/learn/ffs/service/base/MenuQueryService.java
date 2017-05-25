package com.ght.learn.ffs.service.base;

import java.util.List;

import com.ght.learn.ffs.entity.sys.Menu;
import com.ght.learn.ffs.service.FfsService;

public interface MenuQueryService extends FfsService{
	 List<Long> queryVisibleMenuIdsByRoleId(Long roleId);
	    
	 Menu queryMenuByMenuId(Long menuId);

}
