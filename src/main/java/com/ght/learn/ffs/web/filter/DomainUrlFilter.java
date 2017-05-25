package com.ght.learn.ffs.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ght.learn.ffs.tool.context.DomainUrlHolder;

import framework.core.utils.HttpRequestUtils;
import framework.web.filter.AbstractUrlsFilter;

/**
 * 设置当前访问的域名URL
 * 如http://lch.okforwarder.com
 * @author MyYate
 */
public class DomainUrlFilter extends AbstractUrlsFilter {

    public static final String DOMAIN_URL_ATTRIBUTE_NAME = "ffsDomainUrl";
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	if (isExcludeUrl(HttpServletRequest.class.cast(request).getRequestURI())) {
	    chain.doFilter(request, response);
	    return;
	}
	try {
	    String domainUrl = extractDomainUrl(request, response);
	    DomainUrlHolder.setDomainUrl(domainUrl);
	    request.setAttribute(DOMAIN_URL_ATTRIBUTE_NAME, domainUrl);
	    chain.doFilter(request, response);
	} finally {
	    DomainUrlHolder.removeDomainUrl();
	}
    }
    
    private String extractDomainUrl(ServletRequest request, ServletResponse response) {
	return HttpRequestUtils.createServerString(request.getScheme(), request.getServerName(), request.getServerPort());
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	
    }
    
    @Override
    public void destroy() {

    }
}