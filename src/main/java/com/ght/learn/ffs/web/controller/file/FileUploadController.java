package com.ght.learn.ffs.web.controller.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.tool.context.SessionUserHolder;
import com.ght.learn.ffs.tool.file.MultipartFileOperation;
import com.ght.learn.ffs.tool.file.UploadFileItem;
import com.ght.learn.ffs.web.util.MatrixToImageWriter;

@Controller
@RequestMapping("/file")
public class FileUploadController {
	
	@Resource(name="localDiskMultipartFileOperation", type = MultipartFileOperation.class)
	private MultipartFileOperation localDiskMultipartFileOperation;
	
	@RequestMapping("/preFileUpload.do")
	public String fileUpload(){
		
		return "/sys/fileUpload";
	}
	
	@RequestMapping("/upload.do")
	public String upload(MultipartFile multipartFile,HttpServletRequest request,Model model){
		//String realPath=request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		try {
			//localDiskMultipartFileOperation.persist(realPath, multipartFile);
			localDiskMultipartFileOperation.persist(new UploadFileItem(multipartFile), SessionUserHolder.getSessionUser());
			//System.out.println("metaid*****"+metaid);
			model.addAttribute("msg", "上传成功");
		} catch (Exception e) {
			model.addAttribute("msg", "上传失败");
			e.printStackTrace();
		}
		
		return "/error/fileUploadError";
	}
	/**
	 * 通过流的方式上传文件
	 * @throws IOException 
	 */
	@RequestMapping("/upload2.do")
	public String upload2(@RequestParam("file")CommonsMultipartFile file) throws IOException{
		
		long  startTime=System.currentTimeMillis();
        System.out.println("fileName1："+file.getOriginalFilename());
		
        try {
			OutputStream os=new FileOutputStream(FfsConstants.UPLOAD_PATH+"other/");
			
			InputStream is=file.getInputStream();
			
			int temp;
			while((temp=is.read())!=(-1)){
				os.write(temp);
			}
			os.flush();
			os.close();
			is.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long  endTime=System.currentTimeMillis();
        System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
		
		return "/error/fileUploadError";
	}
	
	@RequestMapping("/upload3.do")
	public String upload3(@RequestParam("file")CommonsMultipartFile file) throws IOException{
		long  startTime=System.currentTimeMillis();
        System.out.println("fileName2："+file.getOriginalFilename());
		File newfile=new File(FfsConstants.UPLOAD_PATH+"other/"+File.separator,file.getOriginalFilename());
		file.transferTo(newfile);
		
		long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
        
		return "/error/fileUploadError";
	}
	
	@RequestMapping("/springFileUpload.do")
	public String springFileUpload(HttpServletRequest request,Model model){
		
		// //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver  multipartResolver=new CommonsMultipartResolver (request.getSession().getServletContext());
		//检查form中是否有enctype="multipart/form-data"
		if(multipartResolver.isMultipart(request)){
			//将request变成多部分request
			MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest) request;
			
			//获取multiRequest 中所有的文件名
			@SuppressWarnings("rawtypes")
			Iterator iter=multiRequest.getFileNames();
			while(iter.hasNext()){
				 //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
            		File destDirectory = new File(FfsConstants.UPLOAD_PATH+"other"+File.separator);
            		if (!destDirectory.exists()) {
            		    destDirectory.mkdirs();
            		}
            		File destFile = new File(destDirectory, file.getOriginalFilename());
                    //上传
                    try {
                    	if(!destFile.exists()){
            				destFile.createNewFile();	
            			}
						file.transferTo(destFile);
						model.addAttribute("msg", "上传成功");
					}catch (IOException e) {
						model.addAttribute("msg", "上传失败");
						e.printStackTrace();
					}
                }
			}
		}
		return "/error/fileUploadError";
	}
	
	/**
	 * 生成二维码
	 */
	@RequestMapping("/zxingShow.do")
	public void zxingShow(String url, HttpServletResponse response, Integer width, Integer height){
		try {
			int iWidth = (width == null?200: width);
            int iHeight = (height==null?200: height);
           
            MatrixToImageWriter.createRqCode(url, iWidth, iHeight, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/tagImage.do")
	public String ImageShowByTag(){
		
		
		return "/error/fileUploadError";
	}
	
	

}
