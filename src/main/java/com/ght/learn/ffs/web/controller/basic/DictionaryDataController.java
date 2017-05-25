package com.ght.learn.ffs.web.controller.basic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ght.learn.ffs.entity.basic.DictionaryData;
import com.ght.learn.ffs.service.basic.DictionaryDataService;
import com.ght.learn.ffs.web.controller.FfsController;

import framework.service.ServiceException;

@Controller
@RequestMapping(value = "/basic")
public class DictionaryDataController extends FfsController {

	@Resource(name = "DictionaryDataService", type = DictionaryDataService.class)
	private DictionaryDataService dictionaryDataService;
	
	@RequestMapping(value = "/dictionaryData/dictionaryDataQueryNoResult.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String dictionaryDataueryNoResult(Model model) throws Exception {
		model.addAttribute("dictionaryDatas", null);
		return "/basic/dictionaryData/dictionaryDataQuery";
	}
	
	@RequestMapping(value = "/dictionaryData/dictionaryDataQuery.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String dictionaryDataQuery(DictionaryData dto, HttpServletRequest request , Model model) throws Exception {
		List<DictionaryData> list = this.queryWithPagination(request, op -> dictionaryDataService.queryDictionaryDatas(dto, op));
		model.addAttribute("dictionaryDatas", list);
		model.addAttribute("dictionaryData", dto);
		return "/basic/dictionaryData/dictionaryDataQuery";
	}
	
	@RequestMapping(value = "/dictionaryData/preDictionaryDataCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String preDictionaryDataCreate(String parentCode, Model model) {
		DictionaryData dto = new DictionaryData();
		dto.setParentCode(parentCode);
		model.addAttribute("dictionaryData", dto);
		return "/basic/dictionaryData/dictionaryDataCreate";
	}
	
	@RequestMapping(value = "/dictionaryData/dictionaryDataCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String dictionaryDataCreate(DictionaryData dto, Model model) {
		try { 
			dictionaryDataService.doCreateDictionaryData(dto);
			return "redirect:/basic/dictionaryData/dictionaryDataQueryNoResult.do";
		} catch (ServiceException e) {
			model.addAttribute("dictionaryData", dto);
		    model.addAttribute("errorMessage", e.getMessage());
			return "/basic/dictionaryData/dictionaryDataCreate";
		}
	}
	
	@RequestMapping(value = "/dictionaryData/preDictionaryDataModify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String preDictionaryDataModify(DictionaryData dto, Model model) {
		model.addAttribute("dictionaryData", dictionaryDataService.getDictionaryDataByCode(dto));
		return "/basic/dictionaryData/dictionaryDataModify";
	}
	
	@RequestMapping(value = "/dictionaryData/dictionaryDataModify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String dictionaryDataModify(DictionaryData dto, Model model) {
		try { 
			dictionaryDataService.doUpdateDictionaryData(dto);
			return "redirect:/basic/dictionaryData/dictionaryDataQueryNoResult.do";
		} catch (ServiceException e) {
			model.addAttribute("dictionaryData", dto);
		    model.addAttribute("errorMessage", e.getMessage());
			return "/basic/dictionaryData/dictionaryDataModify";
		}
	}
	
	@RequestMapping(value = "/dictionaryData/dictionaryDataDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String dictionaryDataDelete(String code) {
		dictionaryDataService.doDeleteDictionaryData(code);
		return "redirect:/basic/dictionaryData/dictionaryDataQueryNoResult.do";
	}
}
