package com.ght.learn.ffs.service.basic.support;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ght.learn.ffs.entity.basic.Airport;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.basic.AirportService;

import framework.core.pagination.OrderablePagination;
import framework.core.pagination.Sorter;
import framework.core.utils.ObjectUtils;
import framework.core.utils.StringUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;

@Component("AirportService")
public class AirportServiceImpl extends FfsServiceImpl implements AirportService {

	@Override
	public List<Airport> queryAirports(Airport dto, OrderablePagination op) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Airport.class);
		if (dto != null) {
			sdc.add(SafeRestrictions.equal("airportCode", StringUtils.safeToUpperCase(dto.getAirportCode())));
			sdc.add(SafeRestrictions.equal("di", dto.getDi()));
			sdc.add(SafeRestrictions.or(SafeRestrictions.likeAnyWhere("nameCn", dto.getName()),
					SafeRestrictions.likeAnyWhere("nameEn", dto.getName())));
		}
		if (op != null && !ObjectUtils.isEmpty(op.getSorters())) {
		    sdc.addSorters(op.getSorters());
		} else {
			sdc.addSorters(new Sorter[]{ new Sorter("airportCode", false)});
		}
		return this.getEntityManager().queryForListByCriteria(Airport.class, sdc, op);
	}
	
	@Override
	public List<Airport> queryAirports4Autocomplete(String airportCode) {
		if (StringUtils.isEmpty(airportCode)) {
			return Collections.emptyList();
		}
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(Airport.class);
		sdc.add(SafeRestrictions.likeAnyWhere("airportCode", StringUtils.safeToUpperCase(airportCode)));
		return this.getEntityManager().queryForListByCriteria(Airport.class, sdc);
	}
}
