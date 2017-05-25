package com.ght.learn.ffs.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ght.learn.ffs.tool.context.SessionUser;
import com.ght.learn.ffs.tool.context.SessionUserHolder;

import framework.core.utils.HttpRequestUtils;
import framework.web.filter.AbstractUrlsFilter;

public class SessionUserFilter extends AbstractUrlsFilter {

    public static final String USER_SESSION_ID = "ffsSessionUser";
    public static final String MENU_SESSION_ID = "ffsMenus";
    
    private String loginUrl = "/login.do";
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	HttpServletRequest httpReq = HttpServletRequest.class.cast(request);
	HttpServletResponse httpRsq = HttpServletResponse.class.cast(response);
	
	if (isExcludeUrl(httpReq.getRequestURI())) {
	    chain.doFilter(request, response);
	    return;
	}

	HttpSession session = httpReq.getSession(false);
	if (session == null) {
	    redirectToLogin(httpReq, httpRsq);
	    return;
	}
	SessionUser sessionUser = SessionUser.class.cast(session.getAttribute(USER_SESSION_ID));
	if (sessionUser == null) {
	    redirectToLogin(httpReq, httpRsq);
	    return;
	}
	try {
	    SessionUserHolder.setSessionUser(sessionUser);
	    chain.doFilter(request, response);
	} finally {
	    SessionUserHolder.removeSessionUser();
	}
    }
    
    private void redirectToLogin(HttpServletRequest httpReq, HttpServletResponse httpRsq) throws IOException {
	if (HttpRequestUtils.isHttpAbsoluteUrl(this.loginUrl)) {
	    httpRsq.sendRedirect(this.loginUrl);
	} else {
	    httpRsq.sendRedirect(httpReq.getContextPath() + this.loginUrl);
	}
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    
    @Override
    public void destroy() {

    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}