package com.ght.learn.ffs.web.controller.sys;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ght.learn.ffs.entity.sys.News;
import com.ght.learn.ffs.enums.sys.NewsGrade;
import com.ght.learn.ffs.service.sys.NewsService;
import com.ght.learn.ffs.tool.context.SessionUserHolder;
import com.ght.learn.ffs.web.controller.FfsController;

import framework.core.enums.CommonEnums;
import framework.service.ServiceException;

@Controller
@RequestMapping("/sys/news/")
public class NewsController extends FfsController {

	@Resource
	private NewsService newsService;
	@RequestMapping(value="/newsQueryNoResult.do",method={RequestMethod.GET,RequestMethod.POST})
	public String newsQueryNoResult(Model model) throws Exception{
		model.addAttribute("listNews", null);
		return "/sys/news/newsQuery";
	}
	
	@RequestMapping(value="/newsQuery.do",method={RequestMethod.GET,RequestMethod.POST})
	public String newsQuery(News dto, HttpServletRequest request , Model model) throws Exception{
		List<News> list=this.queryWithPagination(request, op->newsService.queryNewsShow(dto, SessionUserHolder.getSessionUser(), op));
		model.addAttribute("listNews", list);
		model.addAttribute("news", dto);
		return "/sys/news/newsQuery";
	}
	
	@RequestMapping(value = "/newsDetail.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String newsDetail(Long newsId, Model model) {
		News news = newsService.getNewsById(newsId);
		model.addAttribute("news", news );
		return "/sys/news/newsDetail";
	}
	@RequestMapping(value = "/preNewsCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String preNewsCreate(Model model, News dto) {
		model.addAttribute("gradeListSelect", createEnumLabelValueBeanList(NewsGrade.class, true));
		model.addAttribute("topListSelect", createEnumLabelValueBeanList(CommonEnums.IF.class, true));
		model.addAttribute("news", dto);
		return "/sys/news/newsCreate";
	}
	@RequestMapping(value = "/newsCreate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String newsCreate(News dto, Model model) {
		try { 
			newsService.doCreateNews(dto, SessionUserHolder.getSessionUser());
			return "redirect:/sys/news/newsQueryNoResult.do";
		} catch (ServiceException e) {
			model.addAttribute("news", dto);
			model.addAttribute("gradeListSelect", createEnumLabelValueBeanList(NewsGrade.class, true));
			model.addAttribute("topListSelect", createEnumLabelValueBeanList(CommonEnums.IF.class, true));
		    model.addAttribute("errorMessage", e.getMessage());
			return "/sys/news/newsCreate";
		}
	}
	
	@RequestMapping(value = "/preNewsModify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String preNewsModify(Long newsId, Model model) {
		model.addAttribute("news", newsService.getNewsById(newsId));
		model.addAttribute("gradeListSelect", createEnumLabelValueBeanList(NewsGrade.class, true));
		model.addAttribute("topListSelect", createEnumLabelValueBeanList(CommonEnums.IF.class, true));
		return "/sys/news/newsModify";
	}
	
	@RequestMapping(value = "/newsModify.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String newsModify(News dto, Model model) {
		try {
			newsService.doUpdateNews(dto, SessionUserHolder.getSessionUser());
			return "redirect:/sys/news/preNewsModify.do?newsId=" + dto.getRecId();
		} catch (ServiceException e) {
			model.addAttribute("news", dto);
			model.addAttribute("gradeListSelect", createEnumLabelValueBeanList(NewsGrade.class, true));
			model.addAttribute("topListSelect", createEnumLabelValueBeanList(CommonEnums.IF.class, true));
		    model.addAttribute("errorMessage", e.getMessage());
			return "/sys/news/newsModify";
		}
	}
	@RequestMapping(value = "/newsTop.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String newsTop(Long newsId) {
		newsService.doTopNews(newsId, SessionUserHolder.getSessionUser());
		return "redirect:/sys/news/preNewsModify.do?newsId=" + newsId;
	}
	
	@RequestMapping(value = "/newsUnTop.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String newsUnTop(Long newsId) {
		newsService.doUnTopNews(newsId, SessionUserHolder.getSessionUser());
		return "redirect:/sys/news/preNewsModify.do?newsId=" + newsId;
	}
	@RequestMapping(value = "/newsDelete.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String newsDelete(Long newsId) {
		newsService.doDeleteNews(newsId);
		return "redirect:/sys/news/newsQueryNoResult.do";
	}
	
	@RequestMapping(value = "/newsImportantGrade.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String newsImportantGrade(Long newsId) {
		newsService.doImaportantNews(newsId, SessionUserHolder.getSessionUser());
		return "redirect:/sys/news/preNewsModify.do?newsId=" + newsId;
	}
	
	@RequestMapping(value = "/newsNormalGrade.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String newsNormalGrade(Long newsId) {
		newsService.doNormalNews(newsId, SessionUserHolder.getSessionUser());
		return "redirect:/sys/news/preNewsModify.do?newsId=" + newsId;
	}
	
	
}
