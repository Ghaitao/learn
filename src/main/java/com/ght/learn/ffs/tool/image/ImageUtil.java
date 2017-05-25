package com.ght.learn.ffs.tool.image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import framework.core.io.FileHelper;
import framework.core.utils.StringUtils;

public class ImageUtil {

    static float DEFAULT_QUALITY = 0.4f;
    
    public static boolean isImage(String contentType) {
    	return StringUtils.safeStartWith(contentType, "image/", true);
    }
    
    public static void compress(Image src, File dest) throws IOException {
    	compress(src, dest, DEFAULT_QUALITY);
    }
    
    public static void compress(Image src, File dest, float quality) throws IOException {
		int srcHeight = src.getHeight(null);
		int srcWidth = src.getWidth(null);
		BufferedImage tag = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_3BYTE_BGR);
		tag.getGraphics().drawImage(src, 0, 0, srcWidth, srcHeight, null);
	
		ImageOutputStream ios = null;
		
		try {
		    ios = ImageIO.createImageOutputStream(new FileOutputStream(dest));
		    ImageWriter imageWriter = ImageIO.getImageWritersByMIMEType("image/jpeg").next();
		    imageWriter.setOutput(ios);
		    ImageWriteParam jpegParams = imageWriter.getDefaultWriteParam();
		    jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
		    jpegParams.setCompressionQuality(quality);
		    imageWriter.write(null, new IIOImage(tag, null, null), jpegParams);
		    imageWriter.dispose();
		} finally {
		    FileHelper.close(ios);
		}
    }
    
    public static void compress(InputStream src, File dest, float quality) throws IOException {
		if (src == null) {
		    return;
		}
		if (dest == null || !dest.exists()) {
		    return;
		}
		compress(ImageIO.read(src), dest, quality);
    }
    
    public static void compress(InputStream src, File dest) throws IOException {
    	compress(src, dest, DEFAULT_QUALITY);
    }
    
    public static void compress(File src, File dest, float quality) throws IOException {
		if (src == null || !src.exists()) {
		    return;
		}
		compress(ImageIO.read(src), dest, quality);
    }
    
    public static void compress(File src, File dest) throws IOException {
    	compress(src, dest, DEFAULT_QUALITY);
    }

    public static void main(String[] args) throws IOException {
		File src = new File("C:/Users/MyYate/desktop/login_bg.png");
		File dest = new File("C:/Users/MyYate/desktop/login_bg1.png");
		if (!dest.exists()) {
		    dest.createNewFile();
		}
		ImageUtil.compress(src, dest);
		System.out.println("src's size is " + src.length());
		System.out.println("dest's size is " + dest.length());
    }
}