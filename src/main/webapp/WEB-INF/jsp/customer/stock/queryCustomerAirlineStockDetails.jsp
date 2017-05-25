<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<%@ include file="/jspi/meta.jsp"%>
		<title><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockDetail.page.query.title" /></title>
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
								<i class="icon-docs"></i> <spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockDetail.page.query.panel.title" />
							</div>
							<div class="actions">
								<a href="${pageContext.request.contextPath}/customer/createCustomerAirlineStockSegment.do" class="btn btn-default btn-sm"> <i class="fa fa-plus-square-o"></i> <spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.page.query.button.add" /></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="row">
								<form:form cssClass="form-inline" method="get" modelAttribute="customerAirlineStockDto" action="${pageContext.request.contextPath}/customer/doQueryCustomerAirlineStockDetails.do">
									<form:hidden path="airlineStockSegmentId"/>
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="form-group">
											<form:select path="customerCode" items="${airlines}" itemLabel="label" itemValue="value" cssClass="form-control input-medium" />
											<spring:bind path="awbFullNo">
												<input name="awbFullNo" value="${status.value}" class="form-control input-small" placeholder="<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockDetail.filed.stkNo"/>"/>													
											</spring:bind>
											<button type="submit" class="btn btn-primary red margin-left10 loading-state-btn" data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.common.btn.searching"/>" autocomplete="off">
												<i class="fa fa-search"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.search" />
											</button>
										</div>
									</div>
								</form:form>
							</div>
							<div class="row margin-top20">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<c:if test="${customerAirlineStockDetails != null && (not empty customerAirlineStockDetails)}">
										<table class="table table-bordered table-condensed table-striped table-hover text-center">
											<thead>
												<tr>
													<th width="100" class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockDetail.filed.canUse"/></th>
													<th width="100" class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.customerCode" /></th>
													<th width="200" class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockDetail.filed.stkNo"/></th>
													<th width="100" class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.common.creator" /></th>
													<th width="200" class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.common.create_date_time" /></th>
													<th width="200" class="text-center text-nowrap"><spring:message code="com.jinzhiyi.ffs.message.common.btn.operate" /></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${customerAirlineStockDetails}" var="customerAirlineStockDetail">
													<tr>
														<td><i class="${customerAirlineStockDetail.canUse.icon}"></i></td>
														<td>${customerAirlineStockDetail.customerCode}</td>
														<td>${customerAirlineStockDetail.awbFullNo}</td>
														<td><ffs:user userId="${customerAirlineStockDetail.creator}"/></td>
														<td><fmt:formatDate value="${customerAirlineStockDetail.createDateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
														<td>
															<c:if test="${customerAirlineStockDetail.hasUsed}">
															<button type="button" class="btn btn-default btn-xs setAsAvailableCustomerAirlineStockDetail" customerAirlineStockDetailRecId="${customerAirlineStockDetail.recId}"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.page.query.button.SetAsAvailable" /></button>
															</c:if>
															<c:if test="${not customerAirlineStockDetail.hasUsed}">
															<button type="button" class="btn btn-default btn-xs setAsUnavailableCustomerAirlineStockDetail" customerAirlineStockDetailRecId="${customerAirlineStockDetail.recId}"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.page.query.button.SetAsUnavailable" /></button>
															</c:if>
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
    	$(function(){
            $('.setAsAvailableCustomerAirlineStockDetail').click(function () {
            	try {
				  	$.ajax({
					    url:'${pageContext.request.contextPath}/customer/setAsAvailableCustomerAirlineStockDetail.do?customerAirlineStockDetailRecId=' + $(this).attr('customerAirlineStockDetailRecId'),
					    type:'GET',
					    async:true,
					    dataType:'json',
					    success:function(response){
					        if (response) {
					        	if (response.status == "SUCCESS") {
					        		window.location.reload();
					        	} else {
					        		toastr.error(response.message);
					        	}
					        }
					    }
					});
				} catch(e) {}
            });
            
            $('.setAsUnavailableCustomerAirlineStockDetail').click(function () {
            	try {
				  	$.ajax({
					    url:'${pageContext.request.contextPath}/customer/setAsUnavailableCustomerAirlineStockDetail.do?customerAirlineStockDetailRecId=' + $(this).attr('customerAirlineStockDetailRecId'),
					    type:'GET',
					    async:true,
					    dataType:'json',
					    success:function(response){
					        if (response) {
					        	if (response.status == "SUCCESS") {
					        		window.location.reload();
					        	} else {
					        		toastr.error(response.message);
					        	}
					        }
					    }
					});
				} catch(e) {}
            });
    	});
    </script>
</body>
</html>