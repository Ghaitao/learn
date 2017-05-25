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
        <title><spring:message code="com.jinzhiyi.ffs.message.user.title"/></title>
        <%@ include file="/jspi/metronic_base_css.jsp"%>
	</head>

	<body class="page-content-white page-header-fixed">
		<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
		
        <div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
	                <div class="container">
	                	<div class="portlet light bordered">
                            <div class="portlet-title">
                                <div class="caption"><i class="icon-user"></i><spring:message code="com.jinzhiyi.ffs.message.user.panel。query.title"/></div>
                            </div>
                            <div class="portlet-body form">
								<input type="hidden" id="companyId" value="${company.recId}" />
                            	<form action="" class="form-horizontal" id="frm">
                                    <div class="form-body">
                            			<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.user.company.panel.info" /></h4>
										<div class="row">
		                                    <div class="col-lg-4 col-xs-4 col-md-4">
		                                   		<div class="form-group">
			                                    	<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_code" />：</label>
			                                    	<div class="col-lg-8 col-xs-8 col-md-8">
			                                    		<p class="form-control-static">${company.companyCode}</p>
			                                    	</div>
			                                    </div>
											</div>
											<div class="col-lg-4 col-xs-4 col-md-4">
		                                   		<div class="form-group">
			                                    	<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_city_code" />：</label>
			                                    	<div class="col-lg-8 col-xs-8 col-md-8">
			                                    		<p class="form-control-static">${company.cityCode}</p>
			                                    	</div>
			                                    </div>
											</div>
											<div class="col-lg-4 col-xs-4 col-md-4">
		                                   		<div class="form-group">
			                                    	<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_airport_code" />：</label>
			                                    	<div class="col-lg-8 col-xs-8 col-md-8">
			                                    		<p class="form-control-static">${company.airportCode}</p>
			                                    	</div>
			                                    </div>
											</div>
										</div>
									</div>
								</form>
								<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.user.company.panel.search.info" /></h4>
								<div class="row margin-top20">
									<div class="col-lg-12">
										<c:if test="${result != null && (not empty result)}">
                                        <table class="table table-bordered table-striped table-hover">
                                            <thead>
										        <tr>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.user.user_code"/></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.user.user_name_cn"/></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.user.user_name_en"/></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.user.mobile_phone"/></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.user.telephone"/></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.user.email"/></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.user.customer_code" /></th>
										          	<th width="75"><spring:message code="com.jinzhiyi.ffs.message.user.isCustomer" /></th>
										          	<th width="80"><spring:message code="com.jinzhiyi.ffs.message.common.status" /></th>
										          <%-- 	<th width="100"><spring:message code="com.jinzhiyi.ffs.message.common.modifier"/></th>
										          	<th width="100"><spring:message code="com.jinzhiyi.ffs.message.common.modify_date_time"/></th> --%>
										        </tr>
										    </thead>
										    <tbody>
										        <c:forEach items="${result}" var="result">
									          	<tr>
										            <td><a href="${pageContext.request.contextPath}/sys/admin/userCompany/userCompanyDetail.do?recId=${result.recId}">${result.userCode}</a></td>
										            <td>${result.userNameCn}</td>
										            <td>${result.userNameEn}</td>
										            <td>${result.mobilePhone}</td>
										            <td>${result.telephone}</td>
										            <td>${result.email}</td>
										            <td>${result.customerCode}</td>
										            <td><ffs:enum declaringClassName="${result.customer.declaringClass.name}" name="${result.customer}" /></td>
										            <td>
										            <c:if test="${!result.locked}">
										          	<span class="label label-success"><ffs:enum declaringClassName="${result.status.declaringClass.name}" name="${result.status}" /></span>
										          	</c:if>
										          	<c:if test="${result.locked}">
										          	<span class="label label-default"><ffs:enum declaringClassName="${result.status.declaringClass.name}" name="${result.status}" /></span>
										          	</c:if>
										          	</td>
										           <%--  <td><ffs:user userId="${result.modifier}" /></td>
										            <td><fmt:formatDate value="${result.modifyDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
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
                                <div class="form-actions">
                                	<div class="row">
                                		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
											<a href="${pageContext.request.contextPath}/sys/admin/userCompany/preUserCompanyCreate.do?companyId=${company.recId}&companyCode=${company.companyCode}" class="btn red"> <i class="fa fa-plus-square-o"></i> <spring:message code="com.jinzhiyi.ffs.message.user.btn.create_user"/></a>
											<a href="${pageContext.request.contextPath}/sys/admin/company/companyDetail.do?recId=${queryDto.companyId}" class="btn btn-default margin-left10" ><i class="icon-share-alt"></i>  <spring:message code="com.jinzhiyi.ffs.message.common.btn.return"/></a>
										</div>
									</div>
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