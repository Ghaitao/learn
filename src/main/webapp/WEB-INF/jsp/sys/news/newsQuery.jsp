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
        <title><spring:message code="com.jinzhiyi.ffs.message.news.title"/></title>
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
	                            <div class="caption"><i class="icon-user"></i><spring:message code="com.jinzhiyi.ffs.message.news.panel.query.title" /></div>
	                            <div class="actions">
									<a href="${pageContext.request.contextPath}/sys/news/preNewsCreate.do" class="btn btn-default btn-sm"> <i class="fa fa-plus-square-o"></i> <spring:message code="com.jinzhiyi.ffs.message.news.panel.create.title" /></a>
								</div>
	                        </div>
	                        <div class="portlet-body">
								<div class="row">
									<form role="form" class="form-inline" action="${pageContext.request.contextPath}/sys/news/newsQuery.do" id="queryNewsForm" method="post">
										<div class="col-lg-12">
											<div class="form-group">
												<input type="text" class="form-control input-medium disable-ime" name="title" id="title" value="${news.title}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.news.news_title" />">
												<input type="text" class="form-control input-medium disable-ime date-picker" name="startDateTime" id="startDateTime" value="<fmt:formatDate value="${news.startDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"  placeholder="<spring:message code="com.jinzhiyi.ffs.message.news.news_startDateTime" />">
												<input type="text" class="form-control input-medium disable-ime date-picker" name="expiryDateTime" id="expiryDateTime" value="<fmt:formatDate value="${news.expiryDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" placeholder="<spring:message code="com.jinzhiyi.ffs.message.news.news_expiryDateTime" />">
												<button type="submit" class="btn btn-primary red margin-left10 loading-state-btn" id="query_submit" data-loading-text='<spring:message code="com.jinzhiyi.ffs.message.common.btn.searching" />'><i class="fa fa-search"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.search" /></button>
											</div>
										</div>
									</form>
								</div>
								<div class="row margin-top20">
									<div class="col-lg-12">
										<c:if test="${listNews != null && (not empty listNews)}">
		                                <table class="table table-bordered table-striped table-hover">
		                                    <thead>
										        <tr>
										        	<th><spring:message code="com.jinzhiyi.ffs.message.news.news_title" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.news.news_summary" /></th>
										          	<th width="80"><spring:message code="com.jinzhiyi.ffs.message.news.news_grade" /></th>
										          	<th width="180"><spring:message code="com.jinzhiyi.ffs.message.news.news_startDateTime" /></th>
										          	<th width="180"><spring:message code="com.jinzhiyi.ffs.message.news.news_expiryDateTime" /></th>
										          	<th width="75"><spring:message code="com.jinzhiyi.ffs.message.common.btn.operate" /></th>
										        </tr>
										    </thead>
										    <tbody>
										    	<c:forEach items="${listNews}" var="news">
										    	<tr>
										    		<td><a href="${pageContext.request.contextPath}/sys/news/preNewsModify.do?newsId=${news.recId}">${news.title}</a></td>
										          	<td>${news.summary}</td>
										          	<td><ffs:enum declaringClassName="${news.grade.declaringClass.name}" name="${news.grade}"/></td>
										          	<td><fmt:formatDate value="${news.startDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										          	<td><fmt:formatDate value="${news.expiryDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										          	<td>
										          		<%-- <a class="btn btn-xs red" href="${pageContext.request.contextPath}/sys/news/preNewsModify.do?newsId=${news.recId}"><i class="icon-note"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.edit" /></a> --%>
										          		<a class="btn btn-xs default margin-left10" href="${pageContext.request.contextPath}/sys/news/newsDetail.do?newsId=${news.recId}"><i class="icon-screen-desktop"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.preview" /></a>
										          	</td>
										        </tr>
										    	</c:forEach>
										    </tbody>
		                                </table>
                                        <div class="clearfix text-right" style="margin-top:10px;">
											<framework:pagination />
										</div>
		                                </c:if>
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
<script type="text/javascript">   
			$(function(){     
				//显示日历
				$('.date-picker').datetimepicker({
					language: '<ffs:language localeFlag="true" />',
		            autoclose: true,
		            isRTL: App.isRTL(),
		            format: "yyyy-mm-dd hh:ii:ss",
		            pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left")
				});
			});
</script>
</body>
</html>