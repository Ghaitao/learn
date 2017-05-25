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
        <title><spring:message code="com.jinzhiyi.ffs.message.company.title"/></title>
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
	                            <div class="caption"><i class="icon-user"></i><spring:message code="com.jinzhiyi.ffs.message.company.panel.query.title" /></div>
	                            <div class="actions">
									<a href="${pageContext.request.contextPath}/sys/admin/company/preCompanyCreate.do" class="btn btn-default btn-sm"> <i class="fa fa-plus-square-o"></i> <spring:message code="com.jinzhiyi.ffs.message.company.panel.create.title" /></a>
								</div>
	                        </div>
	                        <div class="portlet-body">
								<div class="row">
									<form role="form" class="form-inline" action="${pageContext.request.contextPath}/sys/admin/company/companyQuery.do" id="queryComapnyForm" method="post">
										<div class="col-lg-12">
											<div class="form-group">
												<input type="text" class="form-control input-medium disable-ime text-uppercase" name="companyCode" id="companyCode" value="${company.companyCode}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.company_code" />">
												<input type="text" class="form-control input-medium disable-ime" name="name" id="name" value="${company.name}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.company_name" />">
												<button type="submit" class="btn btn-primary red margin-left10 loading-state-btn" id="query_submit" data-loading-text='<spring:message code="com.jinzhiyi.ffs.message.common.btn.searching" />'><i class="fa fa-search"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.search" /></button>
											</div>
										</div>
									</form>
								</div>
								<div class="row margin-top20">
									<div class="col-lg-12">
										<c:if test="${listCompany != null && (not empty listCompany)}">
		                                <table class="table table-bordered table-striped table-hover">
		                                    <thead>
										        <tr>
										        	<th width="80"><spring:message code="com.jinzhiyi.ffs.message.company.company_code" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.company.company_name_cn" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.company.company_name_en" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.common.creator" /></th>
										          	<th width="150"><spring:message code="com.jinzhiyi.ffs.message.common.create_date_time" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.common.modifier" /></th>
										          	<th width="150"><spring:message code="com.jinzhiyi.ffs.message.common.modify_date_time" /></th>
										          	<th width="80"><spring:message code="com.jinzhiyi.ffs.message.common.status" /></th>
										        </tr>
										    </thead>
										    <tbody>
										    	<c:forEach items="${listCompany}" var="company">
										    	<tr>
										    		<td><a href="${pageContext.request.contextPath}/sys/admin/company/companyDetail.do?recId=${company.recId}">${company.companyCode}</a></td>
										          	<td>${company.nameCn}</td>
										          	<td>${company.nameEn}</td>
										          	<td><ffs:user userId="${company.creator}" /></td>
										          	<td><fmt:formatDate value="${company.createDateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
										          	<td><ffs:user userId="${company.modifier}" /></td>
										          	<td><fmt:formatDate value="${company.modifyDateTime}" pattern="yyyy-MM-dd HH:mm"/></td>
										          	<c:if test="${!company.locked}">
										          	<td><span class="label label-success"><ffs:enum declaringClassName="${company.status.declaringClass.name}" name="${company.status}" /></span></td>
										          	</c:if>
										          	<c:if test="${company.locked}">
										          	<td><span class="label label-default"><ffs:enum declaringClassName="${company.status.declaringClass.name}" name="${company.status}" /></span></td>
										          	</c:if>
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
		<script type="text/javascript">   
			$(function(){     
				
			});
		</script>
    </body>
</html>