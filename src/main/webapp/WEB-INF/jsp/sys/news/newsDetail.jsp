<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <head>
        <%@ include file="/jspi/meta.jsp"%>
        <title>新闻详情</title>
        <link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/pages/css/blog.min.css" rel="stylesheet" type="text/css" />
        <%@ include file="/jspi/metronic_base_css.jsp"%>
   </head>
<body class="page-sidebar-closed-hide-logo page-content-white page-header-fixed">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
	                <div class="container">
	                	<div class="portlet light bordered">
	                        <div class="portlet-title">
	                            <div class="caption"><i class="icon-screen-desktop"></i> <spring:message code="com.jinzhiyi.ffs.message.news.panel.information.title" /></div>
	                        </div>
	                        <div class="portlet-body">
	                           	<div class="row">
	                           		<!-- 公告标题及时间 -->
									<div class="col-md-12">
										<h4 class="text-center">${news.title}</h4>
										<p class="text-center" style="color: gray">
											<fmt:formatDate value="${news.modifyDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;&nbsp;
											<spring:message code="com.jinzhiyi.ffs.message.news.detail.company" />：<ffs:company companyCode="${news.companyCode}"/>&nbsp;&nbsp;
											<spring:message code="com.jinzhiyi.ffs.message.news.detail.people" />：<ffs:user userId="${news.modifier}" />
										</p>
										<span class="span-line"></span>
									</div>
									<!--  公告内容 -->
									<div class="col-md-12">
										<c:if test="${news.summary!='' && (not empty news.summary) }">
										<div class="well">
										 	<span style="color: gray;"><spring:message code="com.jinzhiyi.ffs.message.news.detail.summary" />：</span>${news.summary}
										</div>
										</c:if>
										${news.content}
									</div>
	                           	</div>
	                        </div>
	                    </div>
	                </div>
	            </div>
            </div>
        </div>


<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
<%@ include file="/jspi/metronic_base_js.jsp"%>
<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>

</body>
</html>