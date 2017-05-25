package com.ght.learn.ffs.service.base;

import java.util.List;

import com.ght.learn.ffs.entity.sys.FileMeta;
import com.ght.learn.ffs.service.FfsService;

import framework.service.ServiceException;

public interface FileMetaService extends FfsService{
	
	FileMeta queryFileMetaByFileMetaId(String fileMetaId);
	
    List<FileMeta> queryFileMetasByFileMetaId(List<String> fileMetaIds);
    
    void doDelete(String fileMetaId) throws ServiceException;
    //保存fileMeta
    String doStore(FileMeta fileMeta) throws ServiceException;

}
