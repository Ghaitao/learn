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

import com.ght.learn.ffs.entity.basic.Airport;
import com.ght.learn.ffs.enums.DI;
import com.ght.learn.ffs.service.basic.AirportService;
import com.ght.learn.ffs.web.controller.FfsController;

import framework.web.pagination.AjaxableBootstrap3WebPagination;

@Controller
@RequestMapping(value = "/basic")
public class AirportController extends FfsController {

	@Resource(name = "AirportService", type = AirportService.class)
	private AirportService service;
	
	@RequestMapping(value = "/airport/airportShowDialog.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String airportShowDialog(@ModelAttribute("queryDto") Airport queryDto, HttpServletRequest request , Model model) throws Exception {
		List<?> result = super.queryWithPagination(AjaxableBootstrap3WebPagination.class, 10, request, op -> service.queryAirports(queryDto, op));
		model.addAttribute("result", result);
		model.addAttribute("diListSelect", createEnumLabelValueBeanList(DI.class, true));
		return "/basic/airport/airportShowDialog";
	}
	
	@RequestMapping(value = "/airport/airportAutocomplete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<?> airportAutocomplete(String airportCode) throws Exception {
		List<?> result = service.queryAirports4Autocomplete(airportCode);
		return result;
	}
}
