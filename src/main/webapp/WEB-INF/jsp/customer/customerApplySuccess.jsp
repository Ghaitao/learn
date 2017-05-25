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
        <title><spring:message code="com.jinzhiyi.ffs.message.customer.apply.success.page.title"/></title>
        <%@ include file="/jspi/metronic_base_css.jsp"%>
   </head>
    <body class="page-content-white page-header-fixed">
	<%@ include file="/WEB-INF/jsp/common/header_simple.jsp"%>
        <div class="page-container">
	        <div class="page-content-wrapper">
	                <div class="page-content margin-left0">
	                	<div class="container">
		                	<div class="row">
			                	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                        <div class="alert alert-block alert-warning">
                                        <h2 class="alert-heading"><span aria-hidden="true" class="icon-check"></span> <spring:message code="com.jinzhiyi.ffs.message.customer.apply.success.page.title"/></h2>
                                        <p class="padding10"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.success.page.detail"/></p>
                                        <p>
                                            <a class="btn red" href="${pageContext.request.contextPath}/login.do"> <spring:message code="com.jinzhiyi.ffs.message.customer.apply.success.page.btn.backLogin"/> </a>
                                            <a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/customer/apply.do"> <spring:message code="com.jinzhiyi.ffs.message.customer.apply.success.page.btn.applyAgain"/> </a>
                                        </p>
                                    </div>
	                        </div>
		                	</div>
	                	</div>
	                </div>
	        </div>
        </div>
        <%@ include file="/jspi/metronic_base_js.jsp"%>
<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
    </body>
</html>