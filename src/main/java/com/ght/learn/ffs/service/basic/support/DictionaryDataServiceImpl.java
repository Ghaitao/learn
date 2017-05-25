package com.ght.learn.ffs.service.basic.support;

import java.util.List;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.basic.DictionaryData;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.basic.DictionaryDataService;

import framework.core.Constants;
import framework.core.pagination.OrderablePagination;
import framework.core.utils.ObjectUtils;
import framework.core.utils.StringUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;
import framework.service.ServiceException;
@Service("DictionaryDataService")
public class DictionaryDataServiceImpl extends FfsServiceImpl implements DictionaryDataService{

	@Override
	public DictionaryData getDictionaryDataByCode(DictionaryData dto) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(DictionaryData.class);
		if( dto != null) {
//			sdc.add(SafeRestrictions.and(SafeRestrictions.equal("code", dto.getCode()),
//					SafeRestrictions.equal("parentCode", dto.getParentCode())));
			sdc.add(SafeRestrictions.equal("code", dto.getCode()));
			sdc.add(SafeRestrictions.equal("parentCode", dto.getParentCode()));
		}
		return this.getEntityManager().queryForUniqueObjectByCriteria(DictionaryData.class, sdc);
	}

	@Override
	public List<DictionaryData> queryDictionaryDatas(DictionaryData dto, OrderablePagination op) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(DictionaryData.class);
		if (dto != null) {
			sdc.add(SafeRestrictions.equal("code", dto.getCode()));
			/*sdc.add(SafeRestrictions.equal("parentCode", dto.getParentCode()));*/
			sdc.add(SafeRestrictions.or(SafeRestrictions.likeAnyWhere("nameCn", dto.getName()),
					SafeRestrictions.likeAnyWhere("nameEn", dto.getName())));
		}
		sdc.add(SafeRestrictions.sqlRestriction("(PARENT_CODE is null or PARENT_CODE = '')"));
		sdc.addInitProperty("subDicts");
		if (op != null && !ObjectUtils.isEmpty(op.getSorters())) {
		    sdc.addSorters(op.getSorters());
		}
		return this.getEntityManager().queryForListByCriteria(DictionaryData.class, sdc, op);
	}

	@Override
	public void doCreateDictionaryData(DictionaryData dto) {
		if(dto == null) {
			return ;
		}
		if(this.hasExistThisCode(dto.getCode())) {
			throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.dictionaryData.exception.service.dictionaryData_code_exist", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		}
		
		DictionaryData entity = new DictionaryData();
		entity.setCode(dto.getCode());
		entity.setNameCn(dto.getNameCn());
		entity.setNameEn(dto.getNameEn());
		entity.setParentCode(dto.getParentCode());
		entity.setSorter(dto.getSorter());
		this.getEntityManager().save(entity);
	}

	@Override
	public void doUpdateDictionaryData(DictionaryData dto) {
		if (dto == null || dto.getCode() == null || dto.getParentCode() == null) {
			return;
		}
		DictionaryData entity = this.getDictionaryDataByCode(dto);
		if (entity == null) {
			return;
		}
		
		entity.setNameCn(dto.getNameCn());
		entity.setNameEn(dto.getNameEn());
		entity.setParentCode(dto.getParentCode());
		entity.setSorter(dto.getSorter());
		this.getEntityManager().update(entity);
	}

	@Override
	public void doDeleteDictionaryData(String code) {
		if(!StringUtils.isEmpty(code)) {			
			this.getEntityManager().executeHql("delete from DictionaryData dd where dd.parentCode=?", new Object[] {code});
			this.getEntityManager().executeHql("delete from DictionaryData dd where dd.code=?", new Object[] {code});
		}
	}

	@Override
	public boolean hasExistThisCode(String code) {
		if (StringUtils.isEmpty(code)) {
			return false;
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(DictionaryData.class);
		sdc.add(SafeRestrictions.equal("code", code));
		return this.getEntityManager().queryForListByCriteria(DictionaryData.class, sdc).size() > 0;
	}

}
