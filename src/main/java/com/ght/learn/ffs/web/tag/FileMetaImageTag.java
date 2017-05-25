package com.ght.learn.ffs.web.tag;

import javax.servlet.jsp.JspException;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.ght.learn.ffs.entity.sys.FileMeta;
import com.ght.learn.ffs.service.base.FileMetaService;
import com.ght.learn.ffs.web.filter.CompanyFilter;
import com.ght.learn.ffs.web.filter.StaticUrlFilter;

import framework.core.utils.StringUtils;

public class FileMetaImageTag extends AbstractStringPrintJspTag {

    private static final long serialVersionUID = 8135418295404929448L;

    private String fileMetaId;
    
    private String height;
    
    private String width;
    
    private String cssClass;
    
    private String cssStyle;
    
    @Override
    protected String getOutString() throws JspException {
	if (StringUtils.isEmpty(this.fileMetaId)) {
	    return compositeHtmlImgTag(null, null, this.height, this.width, this.cssClass, this.cssStyle);
	}

	String fileMetaIdString = (String) ExpressionEvaluatorManager.evaluate("fileMetaId", this.fileMetaId, java.lang.String.class, this, this.pageContext);
	FileMeta fileMeta = getBean(FileMetaService.class).queryFileMetaByFileMetaId(fileMetaIdString);
	if (fileMeta == null) {
	    return compositeHtmlImgTag(null, null, this.height, this.width, this.cssClass, this.cssStyle);
	}
	return compositeHtmlImgTag(fileMeta.getFileId(), fileMeta.getFileDesc(), this.height, this.width, this.cssClass, this.cssStyle);
    }

    private String compositeHtmlImgTag(String src, String title, String height, String width, String cssClass, String cssStyle) {
		StringBuilder img = new StringBuilder();
		img.append("<img ");
		if (StringUtils.isEmpty(src)) {
		    img.append("src=\"");
		    img.append(String.format("%s/%s", this.pageContext.getRequest().getAttribute(StaticUrlFilter.STATIC_URL_ATTRIBUTE_NAME), "404.png"));
		    img.append("\" ");
		} else {
		    img.append("src=\"");
		    img.append(String.format("%s%s", this.pageContext.getRequest().getAttribute(CompanyFilter.UPLOAD_URL_ATTRIBUTE_NAME),src));
		    img.append("\" ");
		}
		if (StringUtils.hasText(title)) {
		    img.append("title=\"");
		    img.append(title);
		    img.append("\" ");
		}
		if (StringUtils.hasText(height)) {
		    img.append("height=\"");
		    img.append(height);
		    img.append("\" ");
		}
		if (StringUtils.hasText(width)) {
		    img.append("width=\"");
		    img.append(width);
		    img.append("\" ");
		}
		if (StringUtils.hasText(cssClass)) {
		    img.append("class=\"");
		    img.append(cssClass);
		    img.append("\" ");
		}
		if (StringUtils.hasText(cssStyle)) {
		    img.append("style=\"");
		    img.append(cssStyle);
		    img.append("\" ");
		}
		img.append("/>");
		return img.toString();
    }
    
    public void setFileMetaId(String fileMetaId) {
        this.fileMetaId = fileMetaId;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }
}