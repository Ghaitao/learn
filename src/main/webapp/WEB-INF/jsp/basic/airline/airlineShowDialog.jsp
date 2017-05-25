<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jspi/tag.jsp"%>
<script type="text/javascript">
	$(function() {
		$.dialog({id: 'airlineDialog'}).title('<spring:message code="com.jinzhiyi.ffs.message.airline.panel.query.title" />');
		
		$('#btn_close').click(function() {
			closeDialog();
		});
		$('#btn_query').click(function() {
			$.ajax({
				//async : false,
				url : '${pageContext.request.contextPath}/basic/airline/airlineShowDialog.do',
				type : 'post',
				data : $("#queryForm").serializeArray(),
				success : function(data) {
					$.dialog({id: 'airlineDialog'}).content(data);
				}
			});
			return false;
		});
	});
	
	function showCallback(airlineCode, airlineName){
		$.showAirlineDialog.airlineCallback(airlineCode, airlineName);
		closeDialog();
	}

	function ajaxPagination(url) {
		$.ajax({
			url : url,
			data : {},
			success : function(data) {
				$.dialog({id: 'airlineDialog'}).content(data);
			}
		});
	}
	
	function closeDialog(){
		if ($.dialog({id: 'airlineDialog'})) {
			$.dialog({id: 'airlineDialog'}).close();
		}
	}
</script>
<div class="form" style="width:650px;">
	<div class="row">
		<form role="form" class="form-inline" id="queryForm" method="post">
			<div class="col-lg-12">
				<div class="form-group">
					<input type="text" class="form-control input-small disable-ime text-uppercase" name="airlineCode" value="${queryDto.airlineCode}"
						 placeholder="<spring:message code="com.jinzhiyi.ffs.message.airline.airline_code" />">
					<input type="text" class="form-control input-small disable-ime" name="name" value="${queryDto.name}"
						 placeholder="<spring:message code="com.jinzhiyi.ffs.message.airline.name" />">
					<input type="text" class="form-control input-small disable-ime" name="stockPrefix" id="stockPrefix" value="${queryDto.stockPrefix}"
						 placeholder="<spring:message code="com.jinzhiyi.ffs.message.airline.stock_prefix" />">
					<button type="button" class="btn btn-primary red margin-left10 loading-state-btn" id="btn_query" data-loading-text='<spring:message code="com.jinzhiyi.ffs.message.common.btn.searching" />'><i class="fa fa-search"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.search" /></button>
					<button type="button" class="btn btn-default margin-left10" id="btn_close" data-loading-text='<spring:message code="com.jinzhiyi.ffs.message.common.btn.close" />'><i class="fa fa-share-square-o"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.close" /></button>
				</div>
			</div>
		</form>
	</div>
	<div class="row margin-top20">
		<div class="col-lg-12">
			<c:if test="${result != null && (not empty result)}">
				<table class="table table-bordered table-striped table-hover table-condensed">
		       		<thead>
				        <tr>
				        	<th><spring:message code="com.jinzhiyi.ffs.message.airline.airline_code" /></th>
				          	<th><spring:message code="com.jinzhiyi.ffs.message.airline.name" /></th>
				          	<th><spring:message code="com.jinzhiyi.ffs.message.airline.stock_prefix" /></th>
				        </tr>
				    </thead>
		    		<tbody>
		    		<c:forEach items="${result}" var="result">
		    			<tr>
				    		<td width="25%"><a href="javascript:;" onclick="showCallback('${result.airlineCode}', '${result.getI18nName()}');">${result.airlineCode}</a></td>
				          	<td width="50%">${result.getI18nName()}</td>
				          	<td width="25%">${result.stockPrefix}</td>
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