package com.ght.learn.ffs.service.basic.support;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ght.learn.ffs.entity.basic.Airline;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.basic.AirlineService;

import framework.core.pagination.OrderablePagination;
import framework.core.pagination.Sorter;
import framework.core.utils.ObjectUtils;
import framework.core.utils.StringUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;

@Component("AirlineService")
public class AirlineServiceImpl extends FfsServiceImpl implements AirlineService {

	@Override
	public List<Airline> queryAirlines(Airline dto, OrderablePagination op) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Airline.class);
		if (dto != null) {
			sdc.add(SafeRestrictions.equal("airlineCode", StringUtils.safeToUpperCase(dto.getAirlineCode())));
			sdc.add(SafeRestrictions.equal("stockPrefix", dto.getStockPrefix()));
			sdc.add(SafeRestrictions.or(SafeRestrictions.likeAnyWhere("nameCn", dto.getName()),
					SafeRestrictions.likeAnyWhere("nameEn", dto.getName())));
		}
		if (op != null && !ObjectUtils.isEmpty(op.getSorters())) {
		    sdc.addSorters(op.getSorters());
		}
		sdc.addSorters(new Sorter[]{ new Sorter("airlineCode", false)});
		return this.getEntityManager().queryForListByCriteria(Airline.class, sdc, op);
	}
	
	@Override
	public List<Airline> queryAirlines4Autocomplete(String airlineCode) {
		if (StringUtils.isEmpty(airlineCode)) {
			return Collections.emptyList();
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Airline.class);
		sdc.add(SafeRestrictions.likeAnyWhere("airlineCode", StringUtils.safeToUpperCase(airlineCode)));
		return this.getEntityManager().queryForListByCriteria(Airline.class, sdc);
	}
}
