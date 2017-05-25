package com.ght.learn.ffs.tool.file;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import framework.core.utils.CollectionUtils;
import framework.core.utils.ObjectUtils;

public class UploadFileItem {
    
    private MultipartFile multipartFile;
    
    private String description;

    public UploadFileItem() {
    }

    public UploadFileItem(MultipartFile multipartFile) {
    	this(multipartFile, null);
    }
    
    public UploadFileItem(MultipartFile multipartFile, String description) {
		this.multipartFile = multipartFile;
		this.description = description;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isValidUploadFile() {
    	return this.multipartFile != null && this.multipartFile.getSize() > 0;
    }
    
    public static List<UploadFileItem> merge(MultipartFile[] multipartFiles) {
    	return merge(multipartFiles, null);
    }
    
    public static List<UploadFileItem> merge(MultipartFile[] multipartFiles, String[] descriptions) {
		if (ObjectUtils.isEmpty(multipartFiles)) {
		    return Collections.emptyList();
		}
		
		ArrayList<UploadFileItem> ufis = new ArrayList<>(multipartFiles.length);
		for (int i = 0; i < multipartFiles.length; i ++) {
		    ufis.add(new UploadFileItem(multipartFiles[i], getSafeFileDescription(i, descriptions)));
		}
		return ufis;
    }
    
    public static List<UploadFileItem> merge(Collection<MultipartFile> multipartFiles) {
		if (CollectionUtils.isEmpty(multipartFiles)) {
		    return Collections.emptyList();
		}
		
		ArrayList<UploadFileItem> ufis = new ArrayList<>(multipartFiles.size());
		for (MultipartFile multipartFile : multipartFiles) {
		    ufis.add(new UploadFileItem(multipartFile, null));
		}
		return ufis;
    }
    
    private static String getSafeFileDescription(int index, String[] descriptions) {
		if (ObjectUtils.isEmpty(descriptions)) {
		    return null;
		}
		if (index < 0 || index >= descriptions.length) {
		    return null;
		}
		return descriptions[index];
    }
}