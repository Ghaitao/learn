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
		<title><spring:message code="com.jinzhiyi.ffs.message.customer.filed.title" /></title>
		<%@ include file="/jspi/metronic_base_css.jsp"%>
	</head>
<body class="page-content-white page-header-fixed">
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<div class="clearfix"></div>
	<div class="page-container">
		<div class="page-content-wrapper">
			<div class="page-content">
				<div class="container">
					<div class="portlet light bordered">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-docs"></i>
								<spring:message code="com.jinzhiyi.ffs.message.customer.filed.customer_apply_query" />
							</div>
						</div>
						<div class="portlet-body">
							<div class="row">
								<form:form cssClass="form-inline" method="get" modelAttribute="customerQueryDto" action="${pageContext.request.contextPath}/customer/queryCustomerApplys.do">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="form-group">
											<spring:bind path="customerCode">
												<input name="customerCode" id="customerCode" value="${status.value}" class="form-control input-small disable-ime uppercase" placeholder="<spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode"/>"/>													
											</spring:bind>
											<spring:bind path="name">
												<input name="name" value="${status.value}" class="form-control input-medium" placeholder="<spring:message code="com.jinzhiyi.ffs.message.customer.filed.customer_name"/>"/>													
											</spring:bind>
											<button type="submit" class="btn btn-primary red margin-left10 loading-state-btn" data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.customer.filed.query"/>" autocomplete="off">
												<i class="fa fa-search"></i> <spring:message code="com.jinzhiyi.ffs.message.customer.filed.apply_query" />
											</button>
										</div>
									</div>
								</form:form>
							</div>
							<div class="row margin-top20">
								<div class="col-lg-12">
									<c:if test="${customers != null && (not empty customers)}">
										<table class="table table-bordered table-striped table-hover">
											<thead>
												<tr>
													<th width="80" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.status" /></th>
													<th width="80" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode" /></th>
													<th class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customer_nameCn" /></th>
													<th class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customer_nameEn" /></th>
													<th width="220" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.createDateTime" /></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${customers}" var="customer">
													<tr>
														<td class="text-center"><span class="${customer.auditStatus.cssClass}"><ffs:enum declaringClassName="${customer.auditStatus.declaringClass.name}" name="${customer.auditStatus}" /></span></td>
														<td class="text-center"><a href="${pageContext.request.contextPath }/customer/customerDetail.do?customerId=${customer.recId}">${customer.customerCode}</a></td>
														<td>${customer.nameCn }</td>
														<td>${customer.nameEn }</td>
														<td class="text-center"><fmt:formatDate value="${customer.createDateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END CONTAINER -->
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
	<%@ include file="/jspi/metronic_base_js.jsp"%>
	<script type="text/javascript">
		$(function() {
			$('#customerCode').focus();
		});
	</script>
</body>
</html>