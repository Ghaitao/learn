<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jspi/tag.jsp"%>
<script type="text/javascript">
	$(function() {
		$.dialog({id: 'customerDialog'}).title('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.dialog.title" />');
		
		$('#btn_close').click(function() {
			closeDialog();
		});
		$('#btn_query').click(function() {
			$.ajax({
				//async : false,
				url : '${pageContext.request.contextPath}/customer/customer/customerShowDialog.do',
				type : 'post',
				data : $("#queryForm").serializeArray(),
				success : function(data) {
					$.dialog({id: 'customerDialog'}).content(data);
				}
			});
			return false;
		});
	});
	
	function showCallback(customerCode, customerName){
		$.showCustomerDialog.customerCallback(customerCode, customerName);
		closeDialog();
	}

	function ajaxPagination(url) {
		$.ajax({
			url : url,
			data : {},
			success : function(data) {
				$.dialog({id: 'customerDialog'}).content(data);
			}
		});
	}
	
	function closeDialog(){
		if ($.dialog({id: 'customerDialog'})) {
			$.dialog({id: 'customerDialog'}).close();
		}
	}
</script>
<div class="form" style="width:650px;">
	<div class="row">
		<form role="form" class="form-inline" id="queryForm" method="post">
			<div class="col-lg-12">
				<div class="form-group">
					<form:select path="queryDto.customerType" items="${customerTypeList}" itemLabel="label" itemValue="value" cssClass="form-control input-small disable-ime text-uppercase" />
					<%-- <input type="text" class="form-control input-small disable-ime text-uppercase" name="customerType" value="${queryDto.customerType}"
						 placeholder="<spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerType" />"> --%>
					<input type="text" class="form-control input-small disable-ime text-uppercase" name="customerCode" value="${queryDto.customerCode}"
						 placeholder="<spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode" />">
					<input type="text" class="form-control input-small disable-ime" name="name" value="${queryDto.name}"
						 placeholder="<spring:message code="com.jinzhiyi.ffs.message.customer.filed.name" />">
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
				        	<th><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode" /></th>
				        	<th><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerType" /></th>
				          	<th><spring:message code="com.jinzhiyi.ffs.message.customer.filed.name" /></th>
				        </tr>
				    </thead>
		    		<tbody>
		    		<c:forEach items="${result}" var="result">
		    			<tr>
				    		<td width="25%"><a href="javascript:;" onclick="showCallback('${result.customerCode}', '${result.getI18nName()}');">${result.customerCode}</a></td>
				          	<td width="25%"><ffs:enum declaringClassName="${result.customerType.declaringClass.name}" name="${result.customerType}"/></td>
				          	<td width="50%">${result.getI18nName()}</td>
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