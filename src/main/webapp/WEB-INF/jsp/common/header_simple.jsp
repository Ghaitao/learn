<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="page-header navbar">
            <div class="page-header-inner">
            <div class="container">
                <div class="page-logo">
                    <a href="${pageContext.request.contextPath}/login.do"><img src="<ffs:fileMetaImageUrl fileMetaId="${ffsCompany.textLogo}" showDefaultImage="false"/>" alt="${ffsCompany.i18nName}" title="${ffsCompany.i18nName}" class="logo-default" /> </a>
                </div>
                <div class="top-menu">
                    <ul class="nav navbar-nav pull-right">
                    	<li class="dropdown dropdown-user">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <i class="fa fa-language"></i>
                                <span class="username username-hide-on-mobile"> <spring:message code="com.jinzhiyi.ffs.message.header.language_change"/> </span>
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-default">
                                <li>
                                    <a href="${pageContext.request.contextPath}/console.do?_lang=zh_CN"> <i class="flag-icon flag-icon-cn"></i> 简体中文 </a>
                                </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/console.do?_lang=en"> <i class="flag-icon flag-icon-us"></i> English </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
         </div>
</div>
<div class="clearfix"> </div>