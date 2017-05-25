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
                                <div class="actions">
									<%-- <a href="createUser.html" class="btn btn-default btn-sm"> <i class="fa fa-plus-square-o"></i> <spring:message code="com.jinzhiyi.ffs.message.user.btn.create_user"/></a> --%>
								</div>
                            </div>
                            <div class="portlet-body">
								<div class="row">
									<form role="form" class="form-inline" action="${pageContext.request.contextPath}/sys/admin/user/userQuery.do" id="userQueryForm" method="post">
										<div class="col-lg-12">
											<div class="form-group">
												<input type="text" class="form-control input-small disable-ime text-lowercase" id="userCode" name="userCode" value="${queryDto.userCode }" placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.user_code"/>">
												<input type="text" class="form-control input-medium disable-ime" id="userName" name="userName" value="${queryDto.userName }" placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.user_name"/>">
												<input type="text" class="form-control input-small disable-ime text-uppercase" id="companyCode" name="companyCode" value="${queryDto.companyCode }" placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.company_code"/>">
												<input type="text" class="form-control input-small disable-ime text-uppercase" id="customerCode" name="customerCode" value="${queryDto.customerCode }" placeholder="<spring:message code="com.jinzhiyi.ffs.message.user.customer_code"/>">
												<button type="submit" class="btn btn-primary red margin-left10 loading-state-btn" id="btnSubmit" data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.common.btn.searching"/>"><i class="fa fa-search"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.search"/></button>
											</div>
										</div>
									</form>
								</div>
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
										          	<%-- <th><spring:message code="com.jinzhiyi.ffs.message.user.telephone"/></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.user.email"/></th> --%>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.user.company_code"/></th>
										          	<th width="75"><spring:message code="com.jinzhiyi.ffs.message.user.customer_code"/></th>
										          	<th width="50"><spring:message code="com.jinzhiyi.ffs.message.common.status"/></th>
										          	<th width="60"><spring:message code="com.jinzhiyi.ffs.message.common.modifier"/></th>
										          	<th width="160"><spring:message code="com.jinzhiyi.ffs.message.common.modify_date_time"/></th>
										        </tr>
										    </thead>
										    <tbody>
										        <c:forEach items="${result}" var="result">
									          	<tr>
										            <td><a href="${pageContext.request.contextPath}/sys/admin/user/userDetail.do?recId=${result.recId}">${result.userCode}</a></td>
										            <td>${result.userNameCn}</td>
										            <td>${result.userNameEn}</td>
										            <td>${result.mobilePhone}</td>
										            <%-- <td>${result.telephone}</td>
										            <td>${result.email}</td> --%>
										            <td><ffs:company companyCode="${result.companyCode}" /></td>
										            <td><%-- <ffs:customer customerId="${result.customerId}" /> --%>${result.customerCode}</td>
										            <td><ffs:enum declaringClassName="${result.status.declaringClass.name}" name="${result.status}" /></td>
										            <td><ffs:user userId="${result.modifier}" /></td>
										            <td><fmt:formatDate value="${result.modifyDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
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
    <%@ include file="/jspi/metronic_base_js.jsp"%>
	<script type="text/javascript">   
		$(function(){     
			
		});
	</script>   
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
    </body>
</html>