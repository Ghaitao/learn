package com.ght.learn.ffs.tool.file.support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.entity.sys.FileMeta;
import com.ght.learn.ffs.enums.file.StorageMedium;
import com.ght.learn.ffs.tool.context.SessionUser;
import com.ght.learn.ffs.tool.file.AbstractMultipartFileOperation;
import com.ght.learn.ffs.tool.file.UploadFileItem;
import com.ght.learn.ffs.tool.image.ImageUtil;

import framework.core.Constants;
import framework.core.io.FileHelper;
import framework.core.utils.StringUtils;
import framework.exception.FrameworkRuntimeException;
/**
 *  存储公司的logo，报关，仓库货物等图片
 * 本地存储时目录结构应该如下:
 * <pre>
 * |-fileMeta.uploadDirPath
 * |--companyCode
 * |----Year
 * |------MonthDay
 * |--------customerCode
 * </pre>
 * @author ght
 *
 */
@Component("localDiskMultipartFileOperation")
public class LocalDiskMultipartFileOperation extends AbstractMultipartFileOperation implements EnvironmentAware{

	/**
     * 压缩图片的临界大小
     * 当图片大小大于等于compressFileSizeBytesThreashold时，进行压缩
     * 默认1MB
     */
    private long compressImageSizeBytesThreashold = 1024 * 1024;

    @SuppressWarnings("unused")
	private Environment environment;
    
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	protected void persist(FileMeta fileMeta, MultipartFile multipartFile, SessionUser sessionUser)
			throws FrameworkRuntimeException {
		String paths=createDestFileDirectory(sessionUser);
		File destDirectory = new File(FfsConstants.UPLOAD_PATH+paths);
		if (!destDirectory.exists()) {
		    destDirectory.mkdirs();
		}
		File destFile = new File(destDirectory, createDestFileName(multipartFile));
		try {
			if(!destFile.exists()){
				destFile.createNewFile();	
			}
			if(ImageUtil.isImage(multipartFile.getContentType())&&multipartFile.getSize()>=this.compressImageSizeBytesThreashold){
				try (InputStream in = multipartFile.getInputStream()){
					ImageUtil.compress(in, destFile);
				} catch (IOException e) {
					getThrowableMonitor().monitor(e, String.format("An exception occurs when compress file(fileMetaId=%s, storageMedium=%s, contentType=%s, fileId=%s, companyCode=%s, correlatedCustomerCode=%s)", 
						    fileMeta.getFileMetaId(), fileMeta.getStorageMedium(), fileMeta.getContentType(), fileMeta.getFileId(), fileMeta.getCompanyCode(), fileMeta.getCorrelatedCustomerCode()));
					FileHelper.copy(multipartFile.getInputStream(), new FileOutputStream(destFile));
				}
			}else{
				multipartFile.transferTo(destFile);
			}
			
		} catch (IOException e) {
			throw new FrameworkRuntimeException(e);
		}
		fileMeta.setStorageMedium(StorageMedium.LocalDisk);
		//fileMeta.setFileId(StringUtils.replace(destFile.getAbsolutePath(), fileMeta.getUploadDirPath(), ""));
		fileMeta.setFileId(paths+destFile.getName());
		
	}
	
	private String createDestFileName(MultipartFile multipartFile) {
		StringBuilder name = new StringBuilder();
		name.append(UUID.randomUUID().toString());
		String suffix = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
		if (StringUtils.hasText(suffix)) {
		    name.append(Constants.DOT);
		    name.append(suffix);
		}
		return name.toString();
	}
	
	public void setCompressImageSizeBytesThreashold(long compressImageSizeBytesThreashold) {
        this.compressImageSizeBytesThreashold = compressImageSizeBytesThreashold;
    }

	//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的  
	@Override
	public void persist(String realPath, MultipartFile multipartFile) throws IOException {
		/*File destDirectory = new File(realPath);
		if (!destDirectory.exists()) {
		    destDirectory.mkdirs();
		}*/
		FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),new File(realPath,multipartFile.getOriginalFilename()));
		
	}
	
	/**
	 * 创建上传路径
	 */
	public String createDestFileDirectory(SessionUser sessionUser){
		StringBuilder path = new StringBuilder();
		if(sessionUser.getCompanyCode()!=null){
			path.append(sessionUser.getCompanyCode());
		}else{
			path.append("other");
		}
		path.append(File.separator);
		return path.toString();
	}

	@Override
	public List<String> persist(List<UploadFileItem> uploadFileItems, SessionUser operator)
			throws FrameworkRuntimeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	 @Override
	protected boolean delete(FileMeta fileMeta, SessionUser sessionUser) throws FrameworkRuntimeException {
		File file = createSpecFile(fileMeta);
		if (!file.exists()) {
		    getThrowableMonitor().monitor(String.format("Failed to delete file(%s) because the file does not exists", file.getAbsolutePath()));
		    return false;
		}
		boolean result = file.delete();
		if (!result) {
		    getThrowableMonitor().monitor(String.format("Failed to delete file(%s)", file.getAbsolutePath()));
		}
		return result;
	}
	
	private File createSpecFile(FileMeta fileMeta) {
		StringBuilder path = new StringBuilder();
		path.append(fileMeta.getUploadDirPath());
		path.append(fileMeta.getFileId());
		return new File(path.toString());
	}



}
