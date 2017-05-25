<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<div class="page-header navbar">
            <div class="page-header-inner ">
            <div class="container">
                <div class="page-logo">
                    <a href="${pageContext.request.contextPath}/console.do"><img src="${pageContext.request.contextPath }/staticResource/img/admin/text_logo_cn.png" class="logo-default" /> </a>
                </div>
                <div class="hor-menu  hor-menu-light">
                	<ul class="nav navbar-nav">
                		<c:if test="${sessionScope.ffsMenus != null && (not empty sessionScope.ffsMenus)}">
						<li class="mega-menu-dropdown">
                            <a href="javascript:;" class="dropdown-toggle hover-initialized" data-hover="megamenu-dropdown" data-close-others="true">
                            	<i class="fa fa-list-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.header.shortcut_menu"/>
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <c:set var="menuSize" value="${fn:length(sessionScope.ffsMenus)}"/>
                            <c:if test="${menuSize <= 3}">
                                <fmt:parseNumber var="col" integerOnly="true" value="${12/menuSize}" />
                            </c:if>
                            <c:if test="${menuSize > 3}">
                                <c:set var="col" value="4"/>
                            </c:if>
                            <ul class="dropdown-menu">
                                <li>
                                    <div class="mega-menu-content">
                                        <div class="row">
                                        	<c:forEach items="${sessionScope.ffsMenus}" var="menu">
                                            <div class="col-lg-${col} col-md-${col} col-sm-${col} col-xs-${col}">
                                                <ul class="mega-menu-submenu">
                                                    <li>
                                                        <h3 class="text-nowrap"><i class="${menu.icon}"></i> ${menu.getI18nName()}</h3>
                                                    </li>
                                                    <c:forEach items="${menu.children}" var="subMenu">
													<li class="nav-item text-nowrap"><a href="${pageContext.request.contextPath}/${subMenu.url}" class="nav-link"> ${subMenu.getI18nName()}</a></li>
													</c:forEach>
												</ul>
                                            </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        </c:if>
                    </ul>
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
                        <c:if test="${sessionScope.ffsSessionUser != null}">
                        <li class="dropdown dropdown-user">
                            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                <i class="icon-user"></i>
                                <span class="username username-hide-on-mobile"> ${sessionScope.ffsSessionUser.i18nName} </span>
                                <i class="fa fa-angle-down"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-default">
                                <li>
                                    <a href="${pageContext.request.contextPath}/sys/userPerson/userPersonDetail.do?recId=${sessionScope.ffsSessionUser.userId}">
                                        <i class="icon-docs"></i> <spring:message code="com.jinzhiyi.ffs.message.header.personal_info"/> </a>
                                </li>
                                 <li>
                                    <a href="${pageContext.request.contextPath}/sys/userPerson/prePasswordModify.do?recId=${sessionScope.ffsSessionUser.userId}">
                                        <i class="icon-lock"></i> <spring:message code="com.jinzhiyi.ffs.message.header.modify_password"/> </a>
                                </li>
                                <li class="divider"> </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/logout.do"><i class="icon-logout"></i> <spring:message code="com.jinzhiyi.ffs.message.header.logout"/> </a>
                                </li>
                            </ul>
                        </li>
                        </c:if>
                        <li class="dropdown dropdown-quick-sidebar-toggler">
                            <a href="javascript:;" id="aMyNtn" class="dropdown-toggle" title="<spring:message code="com.jinzhiyi.ffs.message.notification.siderbartitle"/>">
                                <i class="icon-bell"></i>
                                <span class="badge badge-default" id="spnNtnCnt"></span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
         </div>
        </div>
        <div class="clearfix"> </div>
        <div class="page-quick-sidebar-wrapper" data-close-on-body-click="false" id="divNtnSidebar">
                <div class="page-quick-sidebar">
                        <div class="page-quick-sidebar-alerts">
                            <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 357px;">
                            	<div class="page-quick-sidebar-alerts-list" data-height="357" data-initialized="1" style="overflow: hidden; width: auto; height: 357px;">
	                                <h3 class="list-heading text-center"><i class="fa fa-comments"></i> <spring:message code="com.jinzhiyi.ffs.message.notification.siderbartitle"/></h3>
	                                <ul class="feeds list-items" id="ulNtnLst">
	                                </ul>
                            	</div>
                            	<div class="slimScrollBar" style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; background: rgb(187, 187, 187);"></div><div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(234, 234, 234);"></div>
                            </div>
                        </div>
                    </div>
                </div>
<script type="text/javascript">
/* function retrieveUnreadNtnCnt() {
  	try {
	  	$.ajax({
		    url:'${pageContext.request.contextPath}/task/unreadNotificationCount.do?r=' + Math.random().toString(16).substr(2),
		    type:'GET',
		    async:true,
		    dataType:'text',
		    success:function(data,textStatus,jqXHR){
		        if (data) {
		        	var count = parseInt(data);
		        	if (count > 0) {
		        		$("#spnNtnCnt").html(data);
		        	} else {
		        		$("#spnNtnCnt").html('');
		        	}
		        }
		    }
		});
	} catch(e) {}
}
function retrieveNtn() {
	try {
	  	$.ajax({
		    url:'${pageContext.request.contextPath}/task/notifications.do?r=' + Math.random().toString(16).substr(2),
		    type:'GET',
		    async:true,
		    dataType:'json',
		    success:function(data){
		        if (!data) {
		        	return;
		        }
		        var jqulNtnLst = $("#ulNtnLst");
		        jqulNtnLst.html('');
		        for (var i = 0; i < data.length; i ++) {
		        	var html = '<li><div class="col1"><div class="cont"><div class="cont-col1">';
		        	if (data[i].read) {
		        		html = html + '<div class="label label-sm">';
		        	} else {
		        		html = html + '<div class="label label-sm label-warning">';
		        	}
		        	html = html + '<i class="fa fa-comment-o"></i></div></div><div class="cont-col2"><div class="desc">';
		        	html = html + data[i].message + '</div></div></div></div><div class="col2"><div class="date">';
		        	html = html + data[i].time + '</div></div></li>';
		        	jqulNtnLst.append(html);
		        }
		    },
		    error:function(xhr,textStatus){
		    },
		    complete:function(){
		    	readNtn();
		    }
		});
	} catch(e) {}
}
function readNtn() {
	try {
	  	$.ajax({
		    url:'${pageContext.request.contextPath}/task/readNotifications.do?r=' + Math.random().toString(16).substr(2),
		    type:'GET',
		    async:true,
		    dataType:'json',
		    success:function(data){
		    	$("#spnNtnCnt").html('');   
		    }
		});
	} catch(e) {}
}
$(function() {
	 setInterval("retrieveUnreadNtnCnt()", 120000);
	 $('#aMyNtn').click(function() {
		 if (!$('body').hasClass('page-quick-sidebar-open')) {
			 retrieveNtn();
		 }
	 });
}); */
</script>