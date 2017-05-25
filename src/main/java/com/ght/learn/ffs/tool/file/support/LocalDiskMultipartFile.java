package com.ght.learn.ffs.tool.file.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import framework.core.io.FileHelper;

public class LocalDiskMultipartFile implements MultipartFile {

    private File diskFile;
    
    private String contentType;
    
    public LocalDiskMultipartFile(File diskFile) {
	super();
	this.diskFile = diskFile;
    }

    @Override
    public String getName() {
	return null;
    }

    @Override
    public String getOriginalFilename() {
	return this.diskFile.getName();
    }

    @Override
    public String getContentType() {
	return this.contentType;
    }

    @Override
    public boolean isEmpty() {
	return getSize() == 0;
    }

    @Override
    public long getSize() {
	return this.diskFile.length();
    }

    @Override
    public byte[] getBytes() throws IOException {
	InputStream in = null;
	try {
	    in = getInputStream();
	    return FileHelper.copyToByteArray(in);
	} finally {
	    FileHelper.close(in);
	}
    }

    @Override
    public InputStream getInputStream() throws IOException {
	return new FileInputStream(this.diskFile);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
	FileHelper.copy(this.diskFile, dest);
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}