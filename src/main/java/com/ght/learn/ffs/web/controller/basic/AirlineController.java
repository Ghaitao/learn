package com.ght.learn.ffs.web.controller.basic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ght.learn.ffs.entity.basic.Airline;
import com.ght.learn.ffs.service.basic.AirlineService;
import com.ght.learn.ffs.web.controller.FfsController;

import framework.bean.mapping.JsonMapper;
import framework.web.pagination.AjaxableBootstrap3WebPagination;

@Controller
@RequestMapping(value = "/basic")
public class AirlineController extends FfsController {

	@Resource(name = "AirlineService", type = AirlineService.class)
	private AirlineService service;

	@Resource(name = "Jackson2JsonMapper")
	private JsonMapper jsonMapper;
	
	@RequestMapping(value = "/airline/airlineShowDialog.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String airlineShowDialog(@ModelAttribute("queryDto") Airline queryDto, HttpServletRequest request , Model model) throws Exception {
		List<?> result = super.queryWithPagination(AjaxableBootstrap3WebPagination.class, 10, request, op -> service.queryAirlines(queryDto, op));
		model.addAttribute("result", result);
		return "/basic/airline/airlineShowDialog";
	}
	
	@RequestMapping(value = "/airline/airlineAutocomplete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<?> airlineAutocomplete(String airlineCode) throws Exception {
		List<?> result = service.queryAirlines4Autocomplete(airlineCode);
		return result;
	}
	
}
