package com.ght.learn.ffs.service.sys.support;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.sys.Menu;
import com.ght.learn.ffs.enums.Validity;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.sys.MenuService;

import framework.core.pagination.Sorter;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;

@Service("MenuService")
public class MenuServiceImpl extends FfsServiceImpl implements MenuService {

	@Override
	public Menu getMenuById(Long menuId) {
		return getEntityManager().get(Menu.class, menuId);
	}

	@Override
	public List<Menu> queryMenusWithoutPagination(Menu dto) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Menu.class);
		if(dto != null) {
			sdc.add(SafeRestrictions.or(SafeRestrictions.likeAnyWhere("nameCn", dto.getName()),
					SafeRestrictions.likeAnyWhere("nameEn", dto.getName())));
		}
		sdc.addSorters(new Sorter[] {new Sorter("parentId", false), new Sorter("sort", false)});
		return this.getEntityManager().queryForListByCriteria(Menu.class, sdc);
	}
	
	@Override
	public List<Menu> queryAllMenusWithValid() {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Menu.class);
		sdc.add(SafeRestrictions.equal("validity", Validity.Valid));
		sdc.addSorters(new Sorter[] {new Sorter("parentId", false), new Sorter("sort", false)});
		return this.getEntityManager().queryForListByCriteria(Menu.class, sdc);
	}

	@Override
	public Long doCreateMenu(Menu dto) {
		if(dto == null) {
			return null;
		}
		
		Menu entity = new Menu();
		entity.setNameCn(dto.getNameCn());
		entity.setNameEn(dto.getNameEn());
		entity.setIcon(dto.getIcon());
		entity.setParentId(dto.getParentId());
		entity.setSort(dto.getSort());
		entity.setRemark(dto.getRemark());
		entity.setUrl(dto.getUrl());
		entity.setValidity(Validity.Valid);
		Long menuId = (Long) this.getEntityManager().save(entity);
		return menuId;
	}

	@Override
	public void doUpdateMenu(Menu dto) {
		if (dto == null || dto.getRecId() == null) {
			return;
		}
		Menu entity = this.getMenuById(dto.getRecId());
		if (entity == null) {
			return;
		}
		
		entity.setNameCn(dto.getNameCn());
		entity.setNameEn(dto.getNameEn());
		entity.setIcon(dto.getIcon());
		entity.setParentId(dto.getParentId());
		entity.setSort(dto.getSort());
		entity.setRemark(dto.getRemark());
		entity.setUrl(dto.getUrl());
		this.getEntityManager().update(entity);
	}

	@Override
	public void doDeleteMenu(Long menuId) {
		if(menuId != null) {
			//删除RoleMenu表
			SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Menu.class);
			sdc.add(SafeRestrictions.equal("parentId", menuId));
			List<Menu> menus = this.getEntityManager().queryForListByCriteria(Menu.class, sdc);
			if(menus != null) {
	    		for(Menu menu : menus) {
	    			//删除RoleMenu表中的子菜单
	    			this.getEntityManager().executeHql("delete RoleMenu rm where rm.menuId = ?", new Object[] {menu.getRecId()});
	    		}
			}
    		this.getEntityManager().executeHql("delete RoleMenu rm where rm.menuId = ?", new Object[] {menuId});
    		
			//删除Menu表
			this.getEntityManager().executeHql("delete Menu m where m.parentId = ?", new Object[] {menuId});
			this.getEntityManager().delete(Menu.class, menuId);
		}
	}

	@Override
	public void doValidMenu(Long menuId) {
		doUpdateValidStatus(menuId, Validity.Valid);
	}

	@Override
	public void doInvalidMenu(Long menuId) {
		doUpdateValidStatus(menuId, Validity.Invalid);
	}

	//修改状态 
    protected void doUpdateValidStatus(Long menuId, Validity validity) {
		Menu menu = this.getMenuById(menuId);
		if (menu == null) {
		    return;
		}
		// 修改状态
		menu.setValidity(validity);
    }
    
}
