package com.ght.learn.ffs.tool.file;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ght.learn.ffs.tool.context.SessionUser;

import framework.exception.FrameworkRuntimeException;

/**
 * 文件操作
 * @author ght
 *
 */
public interface MultipartFileOperation {

	/**
	 * 存储文件
	 * @param uploadFileItem
	 * @param sessionUser
	 * @return 生成的fileMetaId
	 */
	String persist(UploadFileItem uploadFileItem,SessionUser sessionUser);
	
	void persist(String realPath,MultipartFile multipartFile) throws IOException;
	
	/**
     * 多文件存储
     * @return 返回fileMetaId的list，size()与uploadFileItems.size()相同，如果某个文件上传失败，则该list中的fileMetaId为null
     * @throws FrameworkRuntimeException
     * @throws IllegalArgumentException
     */
    List<String> persist(List<UploadFileItem> uploadFileItems, SessionUser operator) throws FrameworkRuntimeException;
	/**
	 * 删除文件
	 * @param fileMetaId
	 * @param operator
	 * @throws FrameworkRuntimeException
	 */
	void delete(String fileMetaId, SessionUser operator) throws FrameworkRuntimeException;
    void delete(List<String> fileMetaIds, SessionUser operator) throws FrameworkRuntimeException;
}
