<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jspi/tag.jsp"%>
<script type="text/javascript">
	$(function() {
		$.dialog({id: 'airportDialog'}).title('<spring:message code="com.jinzhiyi.ffs.message.airport.panel.query.title" />');
		
		$('#btn_close').click(function() {
			closeDialog();
		});
		$('#btn_query').click(function() {
			$.ajax({
				//async : false,
				url : '${pageContext.request.contextPath}/basic/airport/airportShowDialog.do',
				type : 'post',
				data : $("#queryForm").serializeArray(),
				success : function(data) {
					$.dialog({id: 'airportDialog'}).content(data);
				}
			});
			return false;
		});
	});
	
	function showCallback(airportCode, airportName){
		$.showAirportDialog.airportCallback(airportCode, airportName);
		closeDialog();
	}

	function ajaxPagination(url) {
		$.ajax({
			url : url,
			data : {},
			success : function(data) {
				$.dialog({id: 'airportDialog'}).content(data);
			}
		});
	}
	
	function closeDialog(){
		if ($.dialog({id: 'airportDialog'})) {
			$.dialog({id: 'airportDialog'}).close();
		}
	}
</script>
<div class="form" style="width:650px;">
	<div class="row">
		<form role="form" class="form-inline" id="queryForm" method="post">
			<div class="col-lg-12">
				<div class="form-group">
					<input type="text" class="form-control input-small disable-ime text-uppercase" name="airportCode" value="${queryDto.airportCode}"
						 placeholder="<spring:message code="com.jinzhiyi.ffs.message.airport.airport_code" />">
					<input type="text" class="form-control input-small disable-ime" name="name" value="${queryDto.name}"
						 placeholder="<spring:message code="com.jinzhiyi.ffs.message.airport.name" />">
					<form:select cssClass="form-control input-small disable-ime" name="di" items="${diListSelect}" itemValue="value" itemLabel="label" path="queryDto.di"></form:select>
					<%-- <input type="text" class="form-control input-small disable-ime" name="di" id="di" value="${queryDto.di}"
						 placeholder="<spring:message code="com.jinzhiyi.ffs.message.airport.di" />"> --%>
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
				        	<th><spring:message code="com.jinzhiyi.ffs.message.airport.airport_code" /></th>
				          	<th><spring:message code="com.jinzhiyi.ffs.message.airport.name" /></th>
				          	<th><spring:message code="com.jinzhiyi.ffs.message.airport.di" /></th>
				        </tr>
				    </thead>
		    		<tbody>
		    		<c:forEach items="${result}" var="result">
		    			<tr>
				    		<td width="25%"><a href="javascript:;" onclick="showCallback('${result.airportCode}', '${result.getI18nName()}');">${result.airportCode}</a></td>
				          	<td width="50%">${result.getI18nName()}</td>
				          	<td width="25%"><ffs:enum declaringClassName="${result.di.declaringClass.name}" name="${result.di}"/></td>
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