package com.ght.learn.ffs.web.controller.sys;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ght.learn.ffs.entity.sys.FileMeta;
import com.ght.learn.ffs.service.sys.FileMetaQueryService;
import com.ght.learn.ffs.service.sys.UserRoleService;
import com.ght.learn.ffs.web.controller.FfsController;

@Controller
@RequestMapping("/sys/fileMeta")
public class FileMetaController extends FfsController{
	
	@Resource(name="FileMetaQueryService",type=FileMetaQueryService.class)
	private FileMetaQueryService fileMetaQueryService;

	@RequestMapping(value = "/fileMetaQueryNoResult.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String fileMetaQueryNoResult(Model model) throws Exception {
		model.addAttribute("result", null);
		return "sys/fileMeta/fileMetaQuery";
	}
	
	@Resource(name = "UserRoleService", type = UserRoleService.class)
	private UserRoleService userRoleService;
	
	@RequestMapping(value = "/fileMetaQuery.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String fileMetaQuery(@ModelAttribute("queryDto") FileMeta queryDto, HttpServletRequest request, Model model)
			throws Exception {
		List<?> result = queryWithPagination(request, op -> fileMetaQueryService.queryFileMetas(queryDto, op));
		model.addAttribute("result", result);
		return "sys/fileMeta/fileMetaQuery";
	}

	@RequestMapping(value = "/fileMetaDetail.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String fileMetaDetail(String recId, Model model) throws Exception {
		FileMeta dto = fileMetaQueryService.getFileMetaById(recId);
		model.addAttribute("dto", dto);
		return "sys/fileMeta/fileMetaDetail";
	}
}
