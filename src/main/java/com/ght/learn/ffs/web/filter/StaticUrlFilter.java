package com.ght.learn.ffs.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import framework.web.filter.AbstractUrlsFilter;

/**
 * 设置当前访问静态资源的URL
 * 如果项目中的js css
 * @author MyYate
 */
public class StaticUrlFilter extends AbstractUrlsFilter {

    public static final String STATIC_URL_ATTRIBUTE_NAME = "ffsStaticUrl";
    
    public static final String USE_THIRD_CDN_ATTRIBUTE_NAME = "ffsUseThirdCdn";
    
    public static final String ENTER_ATTRIBUTE_NAME = "enter";
    
    private boolean useThirdCdn = false;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	if (isExcludeUrl(HttpServletRequest.class.cast(request).getRequestURI())) {
	    chain.doFilter(request, response);
	    return;
	}
	//request.setAttribute(STATIC_URL_ATTRIBUTE_NAME, extractStaticUrl(request, response));
	request.setAttribute(USE_THIRD_CDN_ATTRIBUTE_NAME, this.useThirdCdn);
	request.setAttribute(ENTER_ATTRIBUTE_NAME, "\n");
	chain.doFilter(request, response);
    }
    
    /*private String extractStaticUrl(ServletRequest request, ServletResponse response) {
		//本地环境使用开发环境的静态资源
		if (WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getEnvironment().acceptsProfiles("local")) {
		    return "http://www.devokforwarder.com/static";
		}
		return "/static";
    }*/
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	
    }
    
    @Override
    public void destroy() {

    }

    public void setUseThirdCdn(boolean useThirdCdn) {
        this.useThirdCdn = useThirdCdn;
    }
}