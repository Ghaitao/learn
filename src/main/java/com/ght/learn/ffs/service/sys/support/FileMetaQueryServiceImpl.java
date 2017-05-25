package com.ght.learn.ffs.service.sys.support;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ght.learn.ffs.entity.sys.FileMeta;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.sys.FileMetaQueryService;

import framework.core.pagination.OrderablePagination;
import framework.core.utils.ObjectUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;

@Service("FileMetaQueryService")
public class FileMetaQueryServiceImpl extends FfsServiceImpl implements FileMetaQueryService{

	@Override
	public FileMeta getFileMetaById(String fileMetaId) {
		
		return getEntityManager().get(FileMeta.class, fileMetaId);
	}

	@Override
	public List<FileMeta> queryFileMetas(FileMeta dto, OrderablePagination op) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(FileMeta.class);
		if (dto != null) {
			sdc.add(SafeRestrictions.likeAnyWhere("origFileName", dto.getOrigFileName()));
			sdc.add(SafeRestrictions.equal("companyCode", dto.getCompanyCode()));
			sdc.add(SafeRestrictions.equal("correlatedCustomerCode", dto.getCorrelatedCustomerCode()));
		}
		if (op != null && !ObjectUtils.isEmpty(op.getSorters())) {
		    sdc.addSorters(op.getSorters());
		} else {
		    sdc.addOrder(org.hibernate.criterion.Order.desc("createDateTime"));
		}
		return this.getEntityManager().queryForListByCriteria(FileMeta.class, sdc, op);
	}

}
