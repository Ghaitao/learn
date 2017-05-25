package com.ght.learn.ffs.web.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ght.learn.ffs.common.FfsConstants;
import com.ght.learn.ffs.entity.sys.Company;
import com.ght.learn.ffs.service.base.CompanyQueryService;
import com.ght.learn.ffs.tool.context.CompanyHolder;

import framework.core.Constants;
import framework.core.utils.StringUtils;
import framework.web.filter.AbstractUrlsFilter;

/**
 * 解析泛域名
 * 如lch.okforwarder.com，需要解析出公司代码lch
 * @author MyYate
 *
 */
public class CompanyFilter extends AbstractUrlsFilter {

    public static final String COMPANY_ATTRIBUTE_NAME = "ffsCompany";
    
    /**
     * 上传文件的访问url
     */
    public static final String UPLOAD_URL_ATTRIBUTE_NAME = "ffsUploadUrl";
    
    private CompanyQueryService companyQueryService;
    
    //"okforwarder.com", "okforwarder.cn", "okscop.com", "okscop.cn"
    private Set<String> domains = Collections.emptySet();
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
	if (isExcludeUrl(HttpServletRequest.class.cast(request).getRequestURI())) {
	    chain.doFilter(request, response);
	    return;
	}
	
	String serverName = request.getServerName();
	
	String matchedDomain = null;
	int endIndex = -1;
	for (String domain : this.domains) {
	    int index = serverName.indexOf(domain);
	    if (index > -1) {
		endIndex = index;
		matchedDomain = domain;
		break;
	    }
	}
	
	if (endIndex < 0) {
	    throw new ServletException(String.format("The request domain (%s) is not in white list(%s)", serverName, this.domains));
	}
	
	if (endIndex == 0) {
	    chain.doFilter(request, response);
	    return;
	}
	
	String companyCode = StringUtils.safeToUpperCase(serverName.substring(0, endIndex - 1));
	//System.out.println("filter:******"+companyCode);
	if (StringUtils.isEmpty(companyCode) || StringUtils.safeEqualsIgnoreCase("www", companyCode) 
		|| StringUtils.safeStartWith(companyCode, "static", true)
		|| StringUtils.safeStartWith(companyCode, "upload", true)) {
	    chain.doFilter(request, response);
	    return;
	}
	
	Company company = this.companyQueryService.queryCompanyByCompanyCode(companyCode);
	if (company == null) {
	    HttpServletResponse hResponse = (HttpServletResponse) response;
	    hResponse.sendRedirect(createSecondLevelDomainUrl(request, "www", matchedDomain));
	    return;
	}
	try {
	    CompanyHolder.setCompany(company);
	    request.setAttribute(COMPANY_ATTRIBUTE_NAME, company);
//	    request.setAttribute(UPLOAD_URL_ATTRIBUTE_NAME, createSecondLevelDomainUrl(request, company.getUploadFileSldName(), matchedDomain));
	    request.setAttribute(UPLOAD_URL_ATTRIBUTE_NAME, FfsConstants.FFS_IMAGE_SHOW);
	    chain.doFilter(request, response);
	} finally {
	    CompanyHolder.removeCompany();
	}
    }
    
    private String createSecondLevelDomainUrl(ServletRequest request, String sldName, String matchedDomain) {
	StringBuilder url = new StringBuilder();
	url.append(request.getScheme());
	url.append("://");
	url.append(sldName);
	url.append(".");
	if (WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getEnvironment().acceptsProfiles("local")) {
	    url.append("devokforwarder.com");
	} else {
	    url.append(matchedDomain);
	    if ((StringUtils.safeEqualsIgnoreCase(request.getScheme(), "http") && (request.getServerPort() != 80)) 
			|| (StringUtils.safeEqualsIgnoreCase(request.getScheme(), "https") && (request.getServerPort() != 443))) {
		url.append(":");
		url.append(request.getServerPort());
	    }
	}
	//System.out.println("上传文件路径*****:"+url.toString());
	return url.toString();
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    
    @Override
    public void destroy() {

    }

    public void setCompanyQueryService(CompanyQueryService companyQueryService) {
        this.companyQueryService = companyQueryService;
    }

    //@Value("${domain.whitelist}")
    public void setDomainWhitelist(String domainWhitelist) {
        this.domains = StringUtils.tokenizeToSet(domainWhitelist, Constants.COMMA);
    }
}