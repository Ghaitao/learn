package com.ght.learn.ffs.service.basic;

import java.util.List;

import com.ght.learn.ffs.entity.basic.Airport;
import com.ght.learn.ffs.service.FfsService;

import framework.core.pagination.OrderablePagination;

public interface AirportService extends FfsService {
	List<Airport> queryAirports(Airport dto, OrderablePagination op);
	
	List<Airport> queryAirports4Autocomplete(String airportCode);
}
