package com.ght.learn.ffs.service.sys;

import java.util.List;

import com.ght.learn.ffs.entity.sys.Menu;

import framework.service.CommonService;

public interface MenuService extends CommonService {

	/**
	 * 通过id获取菜单
	 * @param menuId
	 * @return
	 */
	Menu getMenuById(Long menuId);
	
	/**
	 * 按条件查询菜单
	 * @param dto
	 * @param op
	 * @return
	 */
	List<Menu> queryMenusWithoutPagination(Menu dto);
	
	/**
	 * 按条件查询菜单（分页）
	 * @param dto
	 * @param op
	 * @return
	 */
	List<Menu> queryAllMenusWithValid();
	
	/**
	 * 添加菜单信息
	 * @param dto
	 * @return
	 */
	Long doCreateMenu(Menu dto);
	
	/**
	 * 修改菜单信息
	 * @param dto
	 * @return
	 */
	void doUpdateMenu(Menu dto);
	
	/**
	 * 通过id删除菜单
	 * @param menuId
	 */
	void doDeleteMenu(Long menuId);
	
	/**
	 * 设置菜单有效
	 * @param menuId
	 */
	void doValidMenu(Long menuId);
	
	/**
	 * 设置菜单无效
	 * @param menuId
	 */
	void doInvalidMenu(Long menuId);
}
