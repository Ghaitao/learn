package com.ght.learn.ffs.service.sys;

import java.util.List;

import com.ght.learn.ffs.entity.sys.FileMeta;

import framework.core.pagination.OrderablePagination;
import framework.service.CommonService;

public interface FileMetaQueryService extends CommonService {

	/**
	 * 通过fileMetaId查询文件
	 * @param fileMetaId
	 * @return
	 */
	FileMeta getFileMetaById(String fileMetaId);
	
	/**
	 * 通过fileMetaDto查询文件信息
	 * @param FileMetaCode
	 * @return
	 */
	List<FileMeta> queryFileMetas(FileMeta dto, OrderablePagination op);
	
}
