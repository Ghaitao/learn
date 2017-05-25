<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<%@ include file="/jspi/meta.jsp"%>
		<title><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.page.query.title" /></title>
		<%@ include file="/jspi/metronic_base_css.jsp"%>
		<link rel="stylesheet" href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/skins/default.css">
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
								<i class="icon-docs"></i> <spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.page.query.panel.title" />
							</div>
							<div class="actions">
								<a href="${pageContext.request.contextPath}/customer/createCustomerAirlineStockSegment.do" class="btn btn-default btn-sm"> <i class="fa fa-plus-square-o"></i> <spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.page.query.button.add" /></a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="row">
								<form:form cssClass="form-inline" method="get" modelAttribute="customerAirlineStockDto" action="${pageContext.request.contextPath}/customer/doQueryCustomerAirlineStockSegments.do">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="form-group">
											<form:select path="customerCode" items="${airlines}" itemLabel="label" itemValue="value" cssClass="form-control input-medium" />
											<spring:bind path="stkPrefix">
												<input name="stkPrefix" value="${status.value}" class="form-control input-xsmall disable-ime" placeholder="<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.stkPrefix"/>"/>													
											</spring:bind>
											<spring:bind path="startNoOrEndNo">
												<input name="startNoOrEndNo" value="${status.value}" class="form-control input-small disable-ime" placeholder="<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.startNo"/>/<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.endNo"/>"/>													
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
									<c:if test="${customerAirlineStockSegments != null && (not empty customerAirlineStockSegments)}">
										<table class="table table-bordered table-condensed table-striped table-hover">
											<thead>
												<tr>
													<th width="100" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.customerCode" /></th>
													<th width="200" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.stkPrefix"/></th>
													<th class="text-center">
														<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.startNo"/>
														&rarr;
														<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.endNo"/>
													</th>
													<th width="100" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.filed.count" /></th>
													<th width="100" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.common.creator" /></th>
													<th width="200" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.common.create_date_time" /></th>
													<th width="100" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.common.btn.operate" /></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${customerAirlineStockSegments}" var="customerAirlineStockSegment">
													<tr id="tr${customerAirlineStockSegment.recId}">
														<td class="text-center">${customerAirlineStockSegment.customerCode}</td>
														<td class="text-center">${customerAirlineStockSegment.stkPrefix}</td>
														<td class="text-center">
															<a target="_blank" href="${pageContext.request.contextPath}/customer/doQueryCustomerAirlineStockDetails.do?airlineStockSegmentId=${customerAirlineStockSegment.recId}">
																${customerAirlineStockSegment.startNo}&rarr;${customerAirlineStockSegment.endNo}
															</a>
														</td>
														<td class="text-center">${customerAirlineStockSegment.count}</td>
														<td class="text-center"><ffs:user userId="${customerAirlineStockSegment.creator}"/></td>
														<td class="text-center"><fmt:formatDate value="${customerAirlineStockSegment.createDateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
														<td class="text-center">
															<a href="javascript:;" class="btnDeleteCustomerAirlineStockSegment" 
																airlineStockSegmentId="${customerAirlineStockSegment.recId}"
																customerCode="${customerAirlineStockSegment.customerCode}"
																title="<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.page.query.button.delete"/>">
																<i class="glyphicon glyphicon-remove"></i>
															</a>
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
   	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/jquery.artDialog.js"></script>
	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/plugins/iframeTools.js"></script>
	<script type="text/javascript">
		$(function(){     
			$(".btnDeleteCustomerAirlineStockSegment").click(function() {
				var airlineStockSegmentId = $(this).attr('airlineStockSegmentId');
				var customerCode = $(this).attr('customerCode');
				$.dialog.confirm('<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.page.query.delete.confirm"/>', function() {
					try {
					  	$.ajax({
						    url:"${pageContext.request.contextPath}/customer/deleteCustomerAirlineStockSegment.do?airlineStockSegmentId=" + airlineStockSegmentId,
						    type:'GET',
						    async:true,
						    dataType:'json',
						    success:function(response) {
						        if (response) {
						        	if (response.status == "SUCCESS") {
						        		$('#tr' + airlineStockSegmentId).remove();
						        		toastr.success('<spring:message code="com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.page.tip.delete.success"/>');
						        	} else {
						        		$.dialog.alert(response.message);
						        	}
						        }
						    }
						});
					} catch(e) {}
				});
			});
		});
	</script>
</body>
</html>