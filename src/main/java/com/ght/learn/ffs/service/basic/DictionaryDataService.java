package com.ght.learn.ffs.service.basic;

import java.util.List;

import com.ght.learn.ffs.entity.basic.DictionaryData;
import com.ght.learn.ffs.service.FfsService;

import framework.core.pagination.OrderablePagination;

public interface DictionaryDataService extends FfsService {
	
	DictionaryData getDictionaryDataByCode(DictionaryData dto);
	
	/**
	 * 按条件查询数据字典（分页）
	 * @param dto
	 * @param op
	 * @return
	 */
	List<DictionaryData> queryDictionaryDatas(DictionaryData dto, OrderablePagination op);
	
	/**
	 * 新增数据字典
	 * @param dto
	 */
	void doCreateDictionaryData(DictionaryData dto);

	/**
	 * 修改数据字典
	 * @param dto
	 */
	void doUpdateDictionaryData(DictionaryData dto);
	
	/**
	 * 删除数据字典
	 * @param code
	 * @param parentCode
	 */
	void doDeleteDictionaryData(String code);
	
	boolean hasExistThisCode(String code);
	
}
