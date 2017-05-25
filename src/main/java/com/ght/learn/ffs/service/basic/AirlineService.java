package com.ght.learn.ffs.service.basic;

import java.util.List;

import com.ght.learn.ffs.entity.basic.Airline;
import com.ght.learn.ffs.service.FfsService;

import framework.core.pagination.OrderablePagination;

public interface AirlineService extends FfsService {
	//
	List<Airline> queryAirlines(Airline dto, OrderablePagination op);
	
	List<Airline> queryAirlines4Autocomplete(String airlineCode);
}
