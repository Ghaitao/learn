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
        <title><spring:message code="com.jinzhiyi.ffs.message.console.page.title"/></title>
        <%@ include file="/jspi/metronic_base_css.jsp"%>
   </head>
    <body class="page-content-white page-header-fixed">
		<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
        <div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
                	<div class="container">
	                	<div class="row">
	                		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
								<div class="portlet light bordered">
		                            <div class="portlet-title">
		                                <div class="caption"><span aria-hidden="true" class="icon-screen-desktop"></span> <spring:message code="com.jinzhiyi.ffs.message.news.console.title"/></div>
		                            	<div class="actions">
											<a href="${pageContext.request.contextPath}/sys/news/newsShow.do" class="btn btn-default btn-sm"><spring:message code="com.jinzhiyi.ffs.message.news.console.more"/></a>
										</div>
		                            </div>
		                            <c:if test="${recentNews != null && (not empty recentNews)}">
		                            <div class="portlet-body">
	                                	<ul class="products-list product-list-in-box">
	                                		<c:forEach items="${recentNews}" var="news">
							                <li class="item">
							               		<c:if test="${news.importantGrade }">
								            	<div class="product-date label-danger product-date-color-white">
								                	<div class="product-date-day"><fmt:formatDate value="${news.modifyDateTime}" pattern="dd"/></div>
													<div class="product-date-yearmonth"><fmt:formatDate value="${news.modifyDateTime}" pattern="yyyy-MM"/></div>
								                </div>
							                  	</c:if>
							                  	<c:if test="${!news.importantGrade }">
								            	<div class="product-date">
								                	<div class="product-date-day"><fmt:formatDate value="${news.modifyDateTime}" pattern="dd"/></div>
													<div class="product-date-yearmonth"><fmt:formatDate value="${news.modifyDateTime}" pattern="yyyy-MM"/></div>
								                </div>
							                  	</c:if>
							                  <div class="product-info">
							                    <a href="${pageContext.request.contextPath}/sys/news/newsDetail.do?newsId=${news.recId}" target="_blank">${news.title}</a>
							                    <div>${news.summary}</div>
							                  </div>
							                </li>
							                </c:forEach>
					              		</ul>
	                                 </div>
	                                 </c:if>
                             	</div>
                			</div>
                		</div>
                	</div>
               	</div>
           	</div>
       	</div>
   <%@ include file="/jspi/metronic_base_js.jsp"%>
<script type="text/javascript">   
$(function(){     

});
</script>
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
    </body>
</html>