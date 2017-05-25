package com.ght.learn.ffs.web.util.barcode4j;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import com.ght.learn.ffs.common.FfsConstants;

/**
 * 条形码工具类
 * @author ght
 *
 */
public class BarcodeUtil {
	/**
	 * 生成文件
	 * @param msg
	 * @param path
	 * @return
	 */
	public static File generateFile(String msg,String path){
		File file =new File(path);
		
		try {
			generate(msg, new FileOutputStream(file));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return file;
	}
	/**
     * 生成字节
     *
     * @param msg
     * @return
     */
    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous);
        return ous.toByteArray();
    }
	/**
     * 生成到流
     *
     * @param msg
     * @param ous
     */
    public static void generate(String msg, OutputStream ous) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        }
        //Code39Bean bean = new Code39Bean();
        Code128Bean bean = new Code128Bean();
        
        // 精细度
        final int dpi = 150;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
 
        // 配置对象
        bean.setModuleWidth(moduleWidth);
        //bean.setWideFactor(3);
        bean.setBarHeight(15);//设置条形码的高度
        //bean.setHeight(15);
        bean.doQuietZone(false);
 
        String format = "image/png";
        try {
 
            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);
 
            // 生成条形码
            bean.generateBarcode(canvas, msg);
 
            // 结束绘制
            canvas.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        String msg = "01234567891021345";
        String path = FfsConstants.UPLOAD_PATH+"barcode4.png";
        generateFile(msg, path);
    }

}
