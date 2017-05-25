<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<%@ include file="/jspi/meta.jsp"%>
		<title><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customer_query" /></title>
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
								<i class="icon-docs"></i> <spring:message code="com.jinzhiyi.ffs.message.customer.filed.customer_query" />
							</div>
							<div class="actions">
								<a href="${pageContext.request.contextPath}/customer/createCustomer.do" class="btn btn-default btn-sm"> <i class="fa fa-plus-square-o"></i> <spring:message code="com.jinzhiyi.ffs.message.customer.filed.button.create_customer" /></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="row">
								<form:form cssClass="form-inline" method="get" modelAttribute="customerQueryDto" action="${pageContext.request.contextPath}/customer/doQueryCustomersForCompany.do">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="form-group">
											<spring:bind path="customerCode">
												<input name="customerCode" value="${status.value}" class="form-control input-small disable-ime uppercase" placeholder="<spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode"/>"/>													
											</spring:bind>
											<spring:bind path="name">
												<input name="name" value="${status.value}" class="form-control input-medium" placeholder="<spring:message code="com.jinzhiyi.ffs.message.customer.filed.customer_name"/>"/>													
											</spring:bind>
											<form:select path="customerType" items="${customerTypeList}" itemLabel="label" itemValue="value" cssClass="form-control input-small" />
											<button type="submit" class="btn btn-primary red margin-left10 loading-state-btn" data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.common.btn.searching"/>" autocomplete="off">
												<i class="fa fa-search"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.search" />
											</button>
										</div>
									</div>
								</form:form>
							</div>
							<div class="row margin-top20">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<c:if test="${customers != null && (not empty customers)}">
										<table class="table table-bordered table-striped table-hover table-condensed">
											<thead>
												<tr>
													<th width="100" class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode" /></th>
													<th class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customer_name"/></th>
													<th class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.contactorName"/></th>
													<th class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.address"/></th>
													<th width="130" class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerType" /></th>
													<th width="200" class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.createDateTime" /></th>
													<th width="80" class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.status" /></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${customers}" var="customer">
													<tr>
														<td class="text-center"><a href="${pageContext.request.contextPath }/customer/customerDetail.do?customerId=${customer.recId}">${customer.customerCode}</a></td>
														<td>${customer.nameCn}<c:if test="${customer.nameEn != null && (not empty customer.nameEn)}"> / ${customer.nameEn}</c:if></td>
														<td class="text-center">
															${customer.contactorName} 
															<c:if test="${not empty customer.contactorName}"> / </c:if>
															${customer.contactorPhone}</td>
														<td>${customer.address}</td>
														<td class="text-center">
															<ffs:enum declaringClassName="${customer.customerType.declaringClass.name}" name="${customer.customerType}" />
														</td>
														<td class="text-center"><fmt:formatDate value="${customer.createDateTime }" pattern="yyyy-MM-dd HH:mm" /></td>
														<td class="text-center">
															<span class="${customer.lockStatus.showIcon}"></span>  
															<ffs:enum declaringClassName="${customer.lockStatus.declaringClass.name}" name="${customer.lockStatus}" />
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<div class="clearfix text-right">
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
		/* $(function(){     

		 }); */
	</script>
</body>
</html>