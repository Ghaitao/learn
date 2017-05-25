package com.ght.learn.ffs.service.base.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.sys.FileMeta;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.base.FileMetaService;

import framework.core.utils.CollectionUtils;
import framework.core.utils.StringUtils;
import framework.service.ServiceException;

@Service("FileMetaService")
public class FileMetaServiceImpl extends FfsServiceImpl implements FileMetaService {

	@Override
	public FileMeta queryFileMetaByFileMetaId(String fileMetaId) {
		
		return getEntityManager().get(FileMeta.class, fileMetaId);
	}

	@Override
	public List<FileMeta> queryFileMetasByFileMetaId(List<String> fileMetaIds) {
		if (CollectionUtils.isEmpty(fileMetaIds)) {
		    return Collections.emptyList();
		}
//		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(FileMeta.class);
//		sdc.add(SafeRestrictions.in("fileMetaId", fileMetaIds));
//		return getEntityManager().queryForListByCriteria(FileMeta.class, sdc);
		
		ArrayList<FileMeta> fileMetas = new ArrayList<FileMeta>(fileMetaIds.size());
		for (String fileMetaId : fileMetaIds) {
		    FileMeta fileMeta = queryFileMetaByFileMetaId(fileMetaId);
		    if (fileMeta != null) {
			fileMetas.add(fileMeta);
		    }
		}
		return fileMetas;
	}

	@Override
	public void doDelete(String fileMetaId) throws ServiceException {
		getEntityManager().executeHql("delete from FileMeta f where f.fileMetaId=?", new Object[] {fileMetaId});		
	}

	@Override
	public String doStore(FileMeta fileMeta) throws ServiceException {
		if (fileMeta == null) {
		    return null;
		}
		if (StringUtils.isEmpty(fileMeta.getFileMetaId())) {
		    fileMeta.setFileMetaId(UUID.randomUUID().toString());
		}
		if (fileMeta.getCreateDateTime() == null) {
		    fileMeta.setCreateDateTime(getEntityManager().getDbCurrentTimestamp());
		}
		return (String) getEntityManager().save(fileMeta);
	}

}
