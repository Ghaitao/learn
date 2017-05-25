package com.ght.learn.ffs.web.tag;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ght.learn.ffs.entity.sys.FileMeta;
import com.ght.learn.ffs.service.base.FileMetaService;
import com.ght.learn.ffs.web.filter.CompanyFilter;
import com.ght.learn.ffs.web.filter.StaticUrlFilter;

import framework.core.utils.StringUtils;

public class FileMetaImageUrlTag extends AbstractStringPrintJspTag {

    private static final long serialVersionUID = -9155316451478029843L;

    private String fileMetaId;
    
    /**
     * 当需要显示的图片不存在时，是否显示默认图片
     */
    private boolean showDefaultImage = true;
    
    private boolean icon = false;
    
    @Override
    protected String getOutString() throws JspException {
		if (StringUtils.isEmpty(this.fileMetaId) && this.showDefaultImage) {
		    return getDefaultImage();
		}
		if (StringUtils.isEmpty(this.fileMetaId)) {
		    return null;
		}
		String fileMetaIdString = (String) ExpressionEvaluatorManager.evaluate("fileMetaId", this.fileMetaId, java.lang.String.class, this, this.pageContext);
		FileMetaService fileMetaService = getBean(FileMetaService.class);
		FileMeta fileMeta = fileMetaService.queryFileMetaByFileMetaId(fileMetaIdString);
		if (fileMeta == null && this.showDefaultImage) {
		    return getDefaultImage();
		}
		if (fileMeta == null) {
		    return null;
		}
		//切换成自己定义的地址
		//return String.format("%s%s", this.pageContext.getRequest().getAttribute(CompanyFilter.UPLOAD_URL_ATTRIBUTE_NAME), fileMeta.getFileId());
		return String.format("%s%s", this.pageContext.getRequest().getAttribute(CompanyFilter.UPLOAD_URL_ATTRIBUTE_NAME), fileMeta.getFileId());
    }

    private String getDefaultImage() {
		if (this.icon) {
		    return String.format("%s/%s", this.pageContext.getRequest().getAttribute(StaticUrlFilter.STATIC_URL_ATTRIBUTE_NAME), "favicon.ico");
		} else {
		    return String.format("%s/%s", this.pageContext.getRequest().getAttribute(StaticUrlFilter.STATIC_URL_ATTRIBUTE_NAME), "404.png");
		}
    }
    
    public void setFileMetaId(String fileMetaId) {
        this.fileMetaId = fileMetaId;
    }

    public void setShowDefaultImage(boolean showDefaultImage) {
        this.showDefaultImage = showDefaultImage;
    }

    public void setIcon(boolean icon) {
        this.icon = icon;
    }
}